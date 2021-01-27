package java2.application_target_list.core.validators.target;

import java2.application_target_list.core.database.target.TargetRepository;
import java2.application_target_list.core.requests.target.DeleteTargetRequest;
import org.springframework.stereotype.Component;

import java2.application_target_list.core.responses.CoreError;
import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteTargetValidator {

    public List<CoreError> validate(DeleteTargetRequest request, TargetRepository targetRepository) {
        List<CoreError> errors = new ArrayList<>();


        if (!targetRepository.isIdInTargetList(request.getTargetIdToDelete())){
            errors.add(new CoreError("Target ID;","no target with that ID"));
        }

        if (isTargetIdEmpty(request)){
            errors.add(new CoreError("Target ID","must not be empty!"));
        }
        if (isTargetIdNegative(request)){
            errors.add(new CoreError("Target ID","must not be negative!"));
        }

        return errors;
    }


    private boolean isTargetIdEmpty(DeleteTargetRequest request) {
        return request.getTargetIdToDelete() == null;
    }

    private boolean isTargetIdNegative(DeleteTargetRequest request){
        return request.getTargetIdToDelete() < 0;
    }

}
