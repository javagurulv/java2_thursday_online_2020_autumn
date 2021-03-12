package java2.application_target_list.core.validators.target;

import java2.application_target_list.core.requests.target.DeleteTargetRequest;
import java2.application_target_list.core.validators.ErrorCreator;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Component;
import java2.application_target_list.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteTargetValidator extends ErrorCreator {

    public List<CoreError> validate(DeleteTargetRequest deleteTargetRequest) {
        List<CoreError> errors = new ArrayList<>();
        checkTargetId(deleteTargetRequest, errors);
        return errors;
    }

    private void checkTargetId(DeleteTargetRequest deleteTargetRequest, List<CoreError> errors){
        if (isTargetIdEmpty(deleteTargetRequest)){
            errors.add(createCoreError("Target ID","must not be empty!"));
        }
        if (isTargetIdNegative(deleteTargetRequest)){
            errors.add(createCoreError("Target ID","must not be negative!"));
        }
    }

    private boolean isTargetIdEmpty(DeleteTargetRequest request) {
        return request.getTargetIdToDelete() == null;
    }

    private boolean isTargetIdNegative(DeleteTargetRequest request){
        return request.getTargetIdToDelete() < 0;
    }

}
