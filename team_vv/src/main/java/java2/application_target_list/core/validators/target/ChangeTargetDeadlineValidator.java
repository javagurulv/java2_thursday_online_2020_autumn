package java2.application_target_list.core.validators.target;

import java2.application_target_list.core.validators.ErrorCreator;
import org.springframework.stereotype.Component;
import java2.application_target_list.core.requests.target.ChangeTargetDeadlineRequest;
import java2.application_target_list.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChangeTargetDeadlineValidator extends ErrorCreator {

    public List<CoreError> validate(ChangeTargetDeadlineRequest changeTargetDeadlineRequest) {
        List<CoreError> errors = new ArrayList<>();
        checkTargetId(changeTargetDeadlineRequest, errors);
        checkTargetDeadline(changeTargetDeadlineRequest, errors);
        return errors;
    }

    private void checkTargetDeadline(ChangeTargetDeadlineRequest changeTargetDeadlineRequest, List<CoreError> errors){
        if (isDeadlineNegative(changeTargetDeadlineRequest)){
            errors.add(createCoreError("Target new deadline", "must not be negative!"));
        }

        if (isDeadlineEmpty(changeTargetDeadlineRequest)){
            errors.add(createCoreError("Target new deadline", "must not be empty!"));
        }
    }

    private void checkTargetId(ChangeTargetDeadlineRequest changeTargetDeadlineRequest, List<CoreError> errors){
        if (isTargetIdEmpty(changeTargetDeadlineRequest)){
            errors.add(createCoreError("Target ID","must not be empty!"));
        }
        if (isTargetIdNegative(changeTargetDeadlineRequest)){
            errors.add(createCoreError("Target ID","must not be negative!"));
        }
    }

    private boolean isTargetIdEmpty(ChangeTargetDeadlineRequest request) {
        return request.getTargetIdToChange() == null;
    }

    private boolean isDeadlineEmpty(ChangeTargetDeadlineRequest request) {
        return request.getNewTargetDeadline() == null;
    }

    private boolean isTargetIdNegative(ChangeTargetDeadlineRequest request){
        return request.getTargetIdToChange() < 0;
    }
    private boolean isDeadlineNegative(ChangeTargetDeadlineRequest request){
        return request.getNewTargetDeadline() < 0;
    }

}
