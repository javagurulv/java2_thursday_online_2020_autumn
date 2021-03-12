package dental_clinic.core.validators.user;

import dental_clinic.core.requests.user.UsersRoleRequest;
import dental_clinic.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRoleRequestValidator {

    public List<CoreError> validate (UsersRoleRequest usersRoleRequest) {
        List<CoreError> errors = new ArrayList<>();
        errors.addAll(idValidationError(usersRoleRequest.getUserId()));
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
