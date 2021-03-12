package java2.application_target_list.core.validators.target;

import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.validators.ErrorCreator;
import org.springframework.stereotype.Component;
import java2.application_target_list.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddTargetValidator extends ErrorCreator {

    public List<CoreError> validate(AddTargetRequest addTargetRequest) {
        List<CoreError> errors = new ArrayList<>();
        checkTargetName(addTargetRequest, errors);
        checkTargetDescription(addTargetRequest, errors);
        checkTargetDeadline(addTargetRequest, errors);
        return errors;
    }

    private void checkTargetDeadline(AddTargetRequest addTargetRequest, List<CoreError> errors){
        if (isDeadlineNegative(addTargetRequest)) {
            errors.add(createCoreError("Target deadline", "must not be negative!"));
        }
    }

    private void checkTargetDescription(AddTargetRequest addTargetRequest, List<CoreError> errors){
        if (isTargetDescriptionEmpty(addTargetRequest)){
            errors.add(createCoreError("Target description", "must not be empty!"));
        }
    }

    private void checkTargetName(AddTargetRequest addTargetRequest, List<CoreError> errors){
        if (isTargetNameEmpty(addTargetRequest)){
            errors.add(createCoreError("Target name","must not be empty!"));
        }
    }

    private boolean isTargetNameEmpty(AddTargetRequest request) {
        return request.getName() == null || request.getName().isEmpty();
    }

    private boolean isTargetDescriptionEmpty(AddTargetRequest request) {
        return request.getDescription() == null || request.getDescription().isEmpty();
    }

    private boolean isDeadlineNegative(AddTargetRequest request){
        return request.getDeadline() < 0;
    }
}
