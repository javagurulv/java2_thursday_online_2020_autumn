package java2.application_target_list.core.validators.user;

import java2.application_target_list.core.requests.user.ChangeUserLastNameRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.ErrorCreator;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class ChangeUserLastNameValidator extends ErrorCreator {

    public List<CoreError> validate(ChangeUserLastNameRequest changeUserLastNameRequest) {
        List<CoreError> errors = new ArrayList<>();
        checkUserId(changeUserLastNameRequest, errors);
        checkUserLastName(changeUserLastNameRequest, errors);
        return errors;
    }

    private void checkUserLastName(ChangeUserLastNameRequest changeUserLastNameRequest, List<CoreError> errors){
        if (isUserLastNameEmpty(changeUserLastNameRequest)){
            errors.add(createCoreError("User new last name","must not be empty!"));
        }
    }

    private void checkUserId(ChangeUserLastNameRequest changeUserLastNameRequest, List<CoreError> errors){
        if (isUserIdEmpty(changeUserLastNameRequest)){
            errors.add(createCoreError("User ID","must not be empty!"));
        }
        if (isUserIdNegative(changeUserLastNameRequest)){
            errors.add(createCoreError("User ID","must not be negative!"));
        }
    }

    private boolean isUserLastNameEmpty(ChangeUserLastNameRequest request) {
        return request.getNewUserLastName() == null || request.getNewUserLastName().isEmpty();
    }

    private boolean isUserIdEmpty(ChangeUserLastNameRequest request) {
        return request.getUserIdToChange() == null;
    }

    private boolean isUserIdNegative(ChangeUserLastNameRequest request){
        return request.getUserIdToChange() < 0;
    }
}
