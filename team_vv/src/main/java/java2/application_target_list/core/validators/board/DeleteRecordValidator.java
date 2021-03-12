package java2.application_target_list.core.validators.board;

import java2.application_target_list.core.requests.board.DeleteRecordRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.ErrorCreator;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteRecordValidator extends ErrorCreator {

    public List<CoreError> validate(DeleteRecordRequest deleteRecordRequest) {
        List<CoreError> errors = new ArrayList<>();
        checkRecordId(deleteRecordRequest, errors);
        return errors;
    }

    private void checkRecordId(DeleteRecordRequest deleteRecordRequest, List<CoreError> errors){
        if (isUserIdEmpty(deleteRecordRequest)){
            errors.add(createCoreError("Record ID","must not be empty!"));
        }
        if (isUserIdNegative(deleteRecordRequest)){
            errors.add(createCoreError("Record ID","must not be negative!"));
        }
    }

    private boolean isUserIdEmpty(DeleteRecordRequest request) {
        return request.getRecordIdToDelete() == null;
    }

    private boolean isUserIdNegative(DeleteRecordRequest request){
        return request.getRecordIdToDelete() < 0;
    }

}
