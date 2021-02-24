package java2.application_target_list.core.validators.board;

import java2.application_target_list.core.requests.board.AddRecordRequest;
import java2.application_target_list.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddRecordValidator {

    public List<CoreError> validate(AddRecordRequest request) {
        List<CoreError> errors = new ArrayList<>();

        if (isTargetIdEmpty(request)){
            errors.add(new CoreError("Target ID","must not be empty!"));
        }

        if (isTargetIdNegative(request)){
            errors.add(new CoreError("Target ID","must not be negative!"));
        }

        if (isUserIdEmpty(request)){
            errors.add(new CoreError("User ID", "must not be empty!"));
        }

        if (isUserIdNegative(request)){
            errors.add(new CoreError("User ID","must not be negative!"));
        }
        return errors;
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
