package java2.application_target_list.core.validators.target;

import java2.application_target_list.core.requests.target.ChangeTargetDescriptionRequest;
import java2.application_target_list.core.validators.ErrorCreator;
import org.springframework.stereotype.Component;
import java2.application_target_list.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChangeTargetDescriptionValidator extends ErrorCreator {

    public List<CoreError> validate(ChangeTargetDescriptionRequest changeTargetDescriptionRequest) {
        List<CoreError> errors = new ArrayList<>();
        checkTargetId(changeTargetDescriptionRequest, errors);
        checkTargetDescription(changeTargetDescriptionRequest, errors);
        return errors;
    }

    private void checkTargetDescription(ChangeTargetDescriptionRequest changeTargetDescriptionRequest, List<CoreError> errors){
        if (isTargetDescriptionEmpty(changeTargetDescriptionRequest)) {
            errors.add(createCoreError("Target new description", "must not be empty!"));
        }
    }

    private void checkTargetId(ChangeTargetDescriptionRequest changeTargetDescriptionRequest, List<CoreError> errors){
        if (isTargetIdEmpty(changeTargetDescriptionRequest)) {
            errors.add(createCoreError("Target ID", "must not be empty!"));
        }
        if (isTargetIdNegative(changeTargetDescriptionRequest)) {
            errors.add(createCoreError("Target ID", "must not be negative!"));
        }
    }

    private boolean isTargetDescriptionEmpty(ChangeTargetDescriptionRequest request) {
        return request.getNewTargetDescription() == null || request.getNewTargetDescription().isEmpty();
    }

    private boolean isTargetIdEmpty(ChangeTargetDescriptionRequest request) {
        return request.getTargetIdToChange() == null;
    }

    private boolean isTargetIdNegative(ChangeTargetDescriptionRequest request) {
        return request.getTargetIdToChange() < 0;
    }

}
