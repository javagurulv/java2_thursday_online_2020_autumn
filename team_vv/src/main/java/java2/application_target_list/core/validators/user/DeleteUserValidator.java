package java2.application_target_list.core.validators.user;


import java2.application_target_list.core.requests.user.DeleteUserRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.ErrorCreator;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteUserValidator extends ErrorCreator {

    public List<CoreError> validate(DeleteUserRequest deleteUserRequest) {
        List<CoreError> errors = new ArrayList<>();
        checkUserId(deleteUserRequest, errors);
        return errors;
    }

    private void checkUserId(DeleteUserRequest deleteUserRequest, List<CoreError> errors){
        if (isUserIdEmpty(deleteUserRequest)){
            errors.add(createCoreError("User ID","must not be empty!"));
        }
        if (isUserIdNegative(deleteUserRequest)){
            errors.add(createCoreError("User ID","must not be negative!"));
        }
    }

    private boolean isUserIdEmpty(DeleteUserRequest request) {
        return request.getUserIdToDelete() == null;
    }

    private boolean isUserIdNegative(DeleteUserRequest request){
        return request.getUserIdToDelete() < 0;
    }
}
