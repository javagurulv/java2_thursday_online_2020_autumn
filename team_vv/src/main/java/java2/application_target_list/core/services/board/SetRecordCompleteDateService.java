package java2.application_target_list.core.services.board;

import java2.application_target_list.core.database.jpa.JpaBoardRepository;
import java2.application_target_list.core.requests.board.SetRecordCompleteDateRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.board.SetRecordCompleteDateResponse;
import java2.application_target_list.core.validators.ErrorCreator;
import java2.application_target_list.core.validators.board.SetRecordCompleteDateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
public class SetRecordCompleteDateService extends ErrorCreator {

    @Autowired
    private SetRecordCompleteDateValidator setRecordCompleteDateValidator;
    @Autowired
    private JpaBoardRepository jpaBoardRepository;

    public SetRecordCompleteDateResponse execute(SetRecordCompleteDateRequest setRecordCompleteDateRequest){

        List<CoreError> errors = checkRequestForErrors(setRecordCompleteDateRequest);
        checkAvailabilityInDB(setRecordCompleteDateRequest, errors);

        if (requestHaveErrors(errors)){
            return createSetRecordCompleteDateResponseWithErrors(errors);
        }

        setSetRecordCompleteDate(setRecordCompleteDateRequest);
        return createSetRecordCompleteDateResponse(setRecordCompleteDateRequest);
    }

    private SetRecordCompleteDateResponse createSetRecordCompleteDateResponse(SetRecordCompleteDateRequest setRecordCompleteDateRequest){
        return new SetRecordCompleteDateResponse(setRecordCompleteDateRequest.getRecordIdToSetCompleteDate());
    }

    private void setSetRecordCompleteDate(SetRecordCompleteDateRequest setRecordCompleteDateRequest) {
        jpaBoardRepository.setRecordCompleteDate(setRecordCompleteDateRequest.getRecordIdToSetCompleteDate(), getDate());;
    }

    private SetRecordCompleteDateResponse createSetRecordCompleteDateResponseWithErrors(List<CoreError> errors){
        return new SetRecordCompleteDateResponse(errors);
    }

    private boolean requestHaveErrors(List<CoreError> errors) {
        return !errors.isEmpty();
    }

    private void checkAvailabilityInDB(SetRecordCompleteDateRequest setRecordCompleteDateRequest, List<CoreError> errors) {
        if (recordDoesNotExistInDB(setRecordCompleteDateRequest)){
            errors.add(createCoreError("Record ID","no record with that ID"));
        }
    }

    private boolean recordDoesNotExistInDB(SetRecordCompleteDateRequest setRecordCompleteDateRequest) {
        return !jpaBoardRepository.existsById(setRecordCompleteDateRequest.getRecordIdToSetCompleteDate());
    }

    private List<CoreError> checkRequestForErrors(SetRecordCompleteDateRequest setRecordCompleteDateRequest){
        return setRecordCompleteDateValidator.validate(setRecordCompleteDateRequest);
    }

    private String getDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter myFormatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(myFormatDate);
    }
}
