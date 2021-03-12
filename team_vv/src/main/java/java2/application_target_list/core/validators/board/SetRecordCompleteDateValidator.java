package java2.application_target_list.core.validators.board;

import java2.application_target_list.core.requests.board.SetRecordCompleteDateRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.ErrorCreator;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class SetRecordCompleteDateValidator extends ErrorCreator {

    public List<CoreError> validate(SetRecordCompleteDateRequest setRecordCompleteDateRequest) {
        List<CoreError> errors = new ArrayList<>();
        checkRecordId(setRecordCompleteDateRequest, errors);
        return errors;
    }

    private void checkRecordId(SetRecordCompleteDateRequest setRecordCompleteDateRequest, List<CoreError> errors){
        if (isUserIdEmpty(setRecordCompleteDateRequest)){
            errors.add(createCoreError("Record ID","must not be empty!"));
        }
        if (isUserIdNegative(setRecordCompleteDateRequest)){
            errors.add(createCoreError("Record ID","must not be negative!"));
        }
    }

    private boolean isUserIdEmpty(SetRecordCompleteDateRequest request) {
        return request.getRecordIdToSetCompleteDate() == null;
    }

    private boolean isUserIdNegative(SetRecordCompleteDateRequest request){
        return request.getRecordIdToSetCompleteDate() < 0;
    }
}
