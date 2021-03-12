package java2.application_target_list.core.validators.user;


import java2.application_target_list.core.requests.user.AddUserRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.ErrorCreator;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class AddUserValidator extends ErrorCreator {

    public List<CoreError> validate(AddUserRequest addUserRequest) {
        List<CoreError> errors = new ArrayList<>();
        checkUserFirstName(addUserRequest, errors);
        checkUserLastName(addUserRequest, errors);
        return errors;
    }

    private void  checkUserLastName(AddUserRequest addUserRequest, List<CoreError> errors){
        if (isUserLastNameEmpty(addUserRequest)){
            errors.add(createCoreError("User last name", "must not be empty!"));
        }
    }

    private void checkUserFirstName(AddUserRequest addUserRequest, List<CoreError> errors){
        if (isUserFirstNameEmpty(addUserRequest)){
            errors.add(createCoreError("User first name","must not be empty!"));
        }
    }

    private boolean isUserFirstNameEmpty(AddUserRequest request) {
        return request.getFirstName() == null || request.getFirstName().isEmpty();
    }

    private boolean isUserLastNameEmpty(AddUserRequest request) {
        return request.getLastName() == null || request.getLastName().isEmpty();
    }

}
