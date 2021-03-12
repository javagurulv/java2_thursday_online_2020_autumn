package java2.application_target_list.core.validators.user;

import java2.application_target_list.core.requests.user.UpdateUserRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.ErrorCreator;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class UpdateUserValidator extends ErrorCreator {

    public List<CoreError> validate(UpdateUserRequest updateUserRequest) {
        List<CoreError> errors = new ArrayList<>();
        checkUserId(updateUserRequest, errors);
        checkUserFirstName(updateUserRequest, errors);
        checkUserLastName(updateUserRequest, errors);
        return errors;
    }

    private void checkUserId(UpdateUserRequest updateUserRequest, List<CoreError> errors){
        if (isUserIdEmpty(updateUserRequest)){
            errors.add(createCoreError("User ID","must not be empty!"));
        }
        if (isUserIdNegative(updateUserRequest)){
            errors.add(createCoreError("User ID","must not be negative!"));
        }
    }

    private void checkUserFirstName(UpdateUserRequest updateUserRequest, List<CoreError> errors){
        if (isUserLastNameEmpty(updateUserRequest)){
            errors.add(createCoreError("User new last name","must not be empty!"));
        }
    }

    private void checkUserLastName(UpdateUserRequest updateUserRequest, List<CoreError> errors){
        if (isUserFirstNameEmpty(updateUserRequest)){
            errors.add(createCoreError("User new first name","must not be empty!"));
        }
    }

    private boolean isUserFirstNameEmpty(UpdateUserRequest request) {
        return request.getNewUserFirstName() == null || request.getNewUserFirstName().isEmpty();
    }

    private boolean isUserLastNameEmpty(UpdateUserRequest request) {
        return request.getNewUserLastName() == null || request.getNewUserLastName().isEmpty();
    }

    private boolean isUserIdEmpty(UpdateUserRequest request) {
        return request.getUserIdToChange() == null;
    }

    private boolean isUserIdNegative(UpdateUserRequest request){
        return request.getUserIdToChange() < 0;
    }

}
