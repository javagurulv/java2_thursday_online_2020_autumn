package dental_clinic.core.services.manipulation.rest;

import dental_clinic.core.database.manipulation.ManipulationRepository;
import dental_clinic.core.requests.manipulation.rest.GetManipulationRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.manipulation.rest.GetManipulationResponse;
import dental_clinic.core.validators.manipulation.rest.GetManipulationRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class GetManipulationService {

    @Autowired private ManipulationRepository manipulationRepository;
    @Autowired private GetManipulationRequestValidator getManipulationRequestValidator;

    public GetManipulationResponse execute(GetManipulationRequest getManipulationRequest) {
        List<CoreError> errors = getManipulationRequestValidator.validate(getManipulationRequest);
        if (!errors.isEmpty()) {
            return new GetManipulationResponse(errors);
        }
        return manipulationRepository.getManipulationById(getManipulationRequest.getId())
                .map(GetManipulationResponse::new)
                .orElseGet(() -> {
                    errors.add(new CoreError("id", "Database doesn't contain manipulation with id " + getManipulationRequest.getId()));
                    return new GetManipulationResponse(errors);
                });
    }
}
