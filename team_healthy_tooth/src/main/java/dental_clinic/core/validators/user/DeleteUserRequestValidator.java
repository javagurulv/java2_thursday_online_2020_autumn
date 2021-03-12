package dental_clinic.core.validators.user;

import dental_clinic.core.requests.user.DeleteUserRequest;
import dental_clinic.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteUserRequestValidator {

    public List<CoreError> validate (DeleteUserRequest deleteUserRequest) {
        List<CoreError> errors = new ArrayList<>();
        errors.addAll(idValidationErrors(deleteUserRequest.getId()));
        return errors;
    }

    private List<CoreError> idValidationErrors (Long id) {
        List<CoreError> errors = new ArrayList<>();
        if (id == null || id < 1) {
            errors.add(new CoreError("id", "Not valid id number"));
        }
        return errors;
    }
}
