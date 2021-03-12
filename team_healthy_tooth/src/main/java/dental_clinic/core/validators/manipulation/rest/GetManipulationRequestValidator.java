package dental_clinic.core.validators.manipulation.rest;

import dental_clinic.core.requests.manipulation.rest.GetManipulationRequest;
import dental_clinic.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GetManipulationRequestValidator {

    public List<CoreError> validate(GetManipulationRequest getManipulationRequest) {
        List<CoreError> errors = new ArrayList<>();
        validateId(getManipulationRequest).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(GetManipulationRequest getManipulationRequest) {
        return (getManipulationRequest.getId() == null
        || getManipulationRequest.getId() < 1L)
                ? Optional.of(new CoreError("id", "Not valid id"))
                : Optional.empty();
    }
}
