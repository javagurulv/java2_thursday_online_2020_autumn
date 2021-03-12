package dental_clinic.core.validators.visit;

import dental_clinic.core.requests.visit.GetVisitRequest;
import dental_clinic.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GetVisitValidator {

    public List<CoreError> validate(GetVisitRequest getVisitRequest) {
        List<CoreError> errors = new ArrayList<>();
        validateId(getVisitRequest).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(GetVisitRequest getVisitRequest) {
        return (getVisitRequest.getId() == null)
                ? Optional.of(new CoreError("id", "Not valid input for id"))
                : Optional.empty();
    }
}
