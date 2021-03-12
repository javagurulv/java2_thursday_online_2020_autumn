package java2.application_target_list.core.validators.user;

import java2.application_target_list.core.requests.user.ChangeUserFirstNameRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.ErrorCreator;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class ChangeUserFirstNameValidator extends ErrorCreator {

    public List<CoreError> validate(ChangeUserFirstNameRequest changeUserFirstNameRequest) {
        List<CoreError> errors = new ArrayList<>();
        checkUserId(changeUserFirstNameRequest, errors);
        checkUserFirstName(changeUserFirstNameRequest, errors);
        return errors;
    }

    private void checkUserFirstName(ChangeUserFirstNameRequest changeUserFirstNameRequest, List<CoreError> errors){
        if (isUserFirstNameEmpty(changeUserFirstNameRequest)){
            errors.add(createCoreError("User new first name","must not be empty!"));
        }
    }

    private void checkUserId(ChangeUserFirstNameRequest changeUserFirstNameRequest, List<CoreError> errors){
        if (isUserIdEmpty(changeUserFirstNameRequest)){
            errors.add(createCoreError("User ID","must not be empty!"));
        }
        if (isUserIdNegative(changeUserFirstNameRequest)){
            errors.add(createCoreError("User ID","must not be negative!"));
        }
    }

    private boolean isUserFirstNameEmpty(ChangeUserFirstNameRequest request) {
        return request.getNewUserFirstName() == null || request.getNewUserFirstName().isEmpty();
    }

    private boolean isUserIdEmpty(ChangeUserFirstNameRequest request) {
        return request.getUserIdToChange() == null;
    }

    private boolean isUserIdNegative(ChangeUserFirstNameRequest request){
        return request.getUserIdToChange() < 0;
    }

}
