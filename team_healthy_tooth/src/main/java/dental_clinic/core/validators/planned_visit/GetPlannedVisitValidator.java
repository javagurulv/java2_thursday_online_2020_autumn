package dental_clinic.core.validators.planned_visit;

import dental_clinic.core.requests.plannedVisit.GetPlannedVisitRequest;
import dental_clinic.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GetPlannedVisitValidator {

    public List<CoreError> validate(GetPlannedVisitRequest getPlannedVisitRequest) {
        List<CoreError> errors = new ArrayList<>();
        validateId(getPlannedVisitRequest).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(GetPlannedVisitRequest getPlannedVisitRequest) {
        return (getPlannedVisitRequest.getId() == null)
                ? Optional.of(new CoreError("id", "Not valid input for id"))
                : Optional.empty();
    }
}
