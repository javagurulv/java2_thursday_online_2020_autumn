package java2.application_target_list.core.validators.target;

import java2.application_target_list.core.requests.target.UpdateTargetRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.ErrorCreator;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class UpdateTargetValidator extends ErrorCreator {

    public List<CoreError> validate(UpdateTargetRequest updateTargetRequest) {
        List<CoreError> errors = new ArrayList<>();
        checkTargetId(updateTargetRequest, errors);
        checkTargetName(updateTargetRequest, errors);
        checkTargetDescription(updateTargetRequest, errors);
        checkTargetDeadline(updateTargetRequest, errors);
        return errors;
    }

    private void checkTargetName(UpdateTargetRequest updateTargetRequest, List<CoreError> errors){
        if (isTargetNameEmpty(updateTargetRequest)){
            errors.add(createCoreError("Target new name","must not be empty!"));
        }
    }

    private void checkTargetDeadline(UpdateTargetRequest updateTargetRequest, List<CoreError> errors){
        if (isDeadlineNegative(updateTargetRequest)){
            errors.add(createCoreError("Target new deadline", "must not be negative!"));
        }

        if (isDeadlineEmpty(updateTargetRequest)){
            errors.add(createCoreError("Target new deadline", "must not be empty!"));
        }
    }

    private void checkTargetDescription(UpdateTargetRequest updateTargetRequest, List<CoreError> errors){
        if (isTargetDescriptionEmpty(updateTargetRequest)){
            errors.add(createCoreError("Target new description","must not be empty!"));
        }
    }

    private void checkTargetId(UpdateTargetRequest updateTargetRequest, List<CoreError> errors){
        if (isTargetIdEmpty(updateTargetRequest)){
            errors.add(createCoreError("Target ID","must not be empty!"));
        }
        if (isTargetIdNegative(updateTargetRequest)){
            errors.add(createCoreError("Target ID","must not be negative!"));
        }
    }

    private boolean isTargetNameEmpty(UpdateTargetRequest request) {
        return request.getNewTargetName() == null || request.getNewTargetName().isEmpty();
    }

    private boolean isTargetIdEmpty(UpdateTargetRequest request) {
        return request.getTargetIdToChange() == null;
    }

    private boolean isTargetIdNegative(UpdateTargetRequest request){
        return request.getTargetIdToChange() < 0;
    }

    private boolean isTargetDescriptionEmpty(UpdateTargetRequest request) {
        return request.getNewTargetDescription() == null || request.getNewTargetDescription().isEmpty();
    }

    private boolean isDeadlineEmpty(UpdateTargetRequest request) {
        return request.getNewTargetDeadline() == null;
    }

    private boolean isDeadlineNegative(UpdateTargetRequest request){
        return request.getNewTargetDeadline() < 0;
    }

}
