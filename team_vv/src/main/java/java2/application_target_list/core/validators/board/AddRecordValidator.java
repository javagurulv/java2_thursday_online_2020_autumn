package java2.application_target_list.core.validators.board;

import java2.application_target_list.core.requests.board.AddRecordRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.ErrorCreator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddRecordValidator extends ErrorCreator {

    public List<CoreError> validate(AddRecordRequest addRecordRequest) {
        List<CoreError> errors = new ArrayList<>();
        checkTargetId(addRecordRequest, errors);
        checkUserId(addRecordRequest, errors);
        return errors;
    }

    private void checkUserId(AddRecordRequest addRecordRequest, List<CoreError> errors){
        if (isUserIdEmpty(addRecordRequest)){
            errors.add(createCoreError("User ID", "must not be empty!"));
        }

        if (isUserIdNegative(addRecordRequest)){
            errors.add(createCoreError("User ID","must not be negative!"));
        }
    }

    private void checkTargetId(AddRecordRequest addRecordRequest, List<CoreError> errors){
        if (isTargetIdEmpty(addRecordRequest)){
            errors.add(createCoreError("Target ID","must not be empty!"));
        }

        if (isTargetIdNegative(addRecordRequest)){
            errors.add(createCoreError("Target ID","must not be negative!"));
        }
    }


    private boolean isTargetIdEmpty(AddRecordRequest request) {
        return request.getTargetId() == null;
    }

    private boolean isUserIdEmpty(AddRecordRequest request) {
        return request.getUserId() == null;
    }

    private boolean isTargetIdNegative(AddRecordRequest request){
        return request.getTargetId() < 0;
    }

    private boolean isUserIdNegative(AddRecordRequest request){
        return request.getUserId() < 0;
    }

}
