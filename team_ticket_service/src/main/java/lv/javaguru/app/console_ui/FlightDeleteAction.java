package lv.javaguru.app.console_ui;

import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.request.DeleteFlightRequest;
import lv.javaguru.app.core.response.FlightDeleteResponse;
import lv.javaguru.app.core.services.FlightDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlightDeleteAction implements UIActions {

	@Autowired
	private FlightDeleteService flightDeleteService;


	@Override
	public void execute () {
		BaseFunc.printHeader("Enter flight ID:");
		long id = BaseFunc.getMenuNumberFromUser();

		DeleteFlightRequest request = new DeleteFlightRequest(id);
		FlightDeleteResponse response = flightDeleteService.execute(request);

		if (response.hasErrors()) {
			response.getErrorList().forEach(System.out::println);
		}
		else {
			System.out.println(response.getMessage());
		}

	}


}
