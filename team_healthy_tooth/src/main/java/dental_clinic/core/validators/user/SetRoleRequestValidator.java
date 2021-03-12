package dental_clinic.core.validators.user;

import dental_clinic.core.requests.user.SetRoleRequest;
import dental_clinic.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SetRoleRequestValidator {

    public List<CoreError> validate (SetRoleRequest setRoleRequest) {
        List<CoreError> errors = new ArrayList<>();
        errors.addAll(idValidationError(setRoleRequest.getUserId()));
        errors.addAll(idValidationError(setRoleRequest.getRoleId()));
        return errors;
    }

    private List<CoreError> idValidationError(Long id) {
        List<CoreError> errors = new ArrayList<>();
        if (id == null || id < 1) {
            errors.add(new CoreError("id", "Not valid input for id"));
        }
        return errors;
    }
}
