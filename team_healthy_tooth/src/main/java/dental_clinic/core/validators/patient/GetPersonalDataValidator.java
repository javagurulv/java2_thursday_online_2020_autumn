package dental_clinic.core.validators.patient;

import dental_clinic.core.requests.patient.GetPersonalDataRequest;
import dental_clinic.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GetPersonalDataValidator {

    public List<CoreError> validate(GetPersonalDataRequest getPersonalDataRequest) {
        List<CoreError> errors = new ArrayList<>();
        validateId(getPersonalDataRequest).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(GetPersonalDataRequest getPersonalDataRequest) {
        return (getPersonalDataRequest.getId() == null || getPersonalDataRequest.getId() < 1)
                ? Optional.of(new CoreError("id", "Database doesn't contain personal data with id"))
                : Optional.empty();
    }
}
