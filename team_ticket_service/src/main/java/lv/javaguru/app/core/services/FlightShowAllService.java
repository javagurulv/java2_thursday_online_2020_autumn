package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.domain.PersonType;
import lv.javaguru.app.core.request.FlightShowAllRequest;
import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.response.FlightShowAllResponse;
import lv.javaguru.app.database.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class FlightShowAllService {

	@Autowired
	private FlightRepository flightRepository;


	public FlightShowAllResponse<?> execute (FlightShowAllRequest request) {
		List<?> responseList = validate(request.getCurrUser());

		if (!responseList.isEmpty()) {
			return new FlightShowAllResponse<>(responseList);
		}
		else if (request.getCurrUser().getPersonType() != PersonType.ADMIN)
			responseList = flightRepository.getAllUserFlights(request.getCurrUser());
		else {
			responseList = flightRepository.getAllFlights();
		}

		return new FlightShowAllResponse<>(responseList);
	}

	private List<CodeError> validate (User user) {
		List<CodeError> errorList = new ArrayList<>();

		//if (!database.userTableContainsUser(user.getId()))
		//	errorList.add(new CodeError("User", "no user in database"));

		return errorList;
	}
}
