package java2.application_target_list.core.validators.target;

import java2.application_target_list.core.requests.target.ChangeTargetNameRequest;
import java2.application_target_list.core.validators.ErrorCreator;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Component;
import java2.application_target_list.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChangeTargetNameValidator extends ErrorCreator {

    public List<CoreError> validate(ChangeTargetNameRequest changeTargetNameRequest) {
        List<CoreError> errors = new ArrayList<>();
        checkTargetId(changeTargetNameRequest, errors);
        checkTargetName(changeTargetNameRequest, errors);
        return errors;
    }

    private void checkTargetName(ChangeTargetNameRequest changeTargetNameRequest, List<CoreError> errors){
        if (isTargetNameEmpty(changeTargetNameRequest)){
            errors.add(createCoreError("Target new name","must not be empty!"));
        }
    }

    private void checkTargetId(ChangeTargetNameRequest changeTargetNameRequest, List<CoreError> errors){
        if (isTargetIdEmpty(changeTargetNameRequest)){
            errors.add(createCoreError("Target ID","must not be empty!"));
        }
        if (isTargetIdNegative(changeTargetNameRequest)){
            errors.add(createCoreError("Target ID","must not be negative!"));
        }
    }

    private boolean isTargetNameEmpty(ChangeTargetNameRequest request) {
        return request.getNewTargetName() == null || request.getNewTargetName().isEmpty();
    }

    private boolean isTargetIdEmpty(ChangeTargetNameRequest request) {
        return request.getTargetIdToChange() == null;
    }

    private boolean isTargetIdNegative(ChangeTargetNameRequest request){
        return request.getTargetIdToChange() < 0;
    }

}
