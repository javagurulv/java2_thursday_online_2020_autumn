package java2.application_target_list.core.services.board;

import java2.application_target_list.core.database.jpa.JpaBoardRepository;
import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.database.jpa.JpaUserRepository;
import java2.application_target_list.core.domain.Record;
import java2.application_target_list.core.requests.board.AddRecordRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.board.AddRecordResponse;
import java2.application_target_list.core.validators.ErrorCreator;
import java2.application_target_list.core.validators.board.AddRecordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
public class AddRecordService extends ErrorCreator {

    @Autowired
    private AddRecordValidator addRecordValidator;
    @Autowired
    private JpaBoardRepository jpaBoardRepository;
    @Autowired
    private JpaTargetRepository jpaTargetRepository;
    @Autowired
    private JpaUserRepository jpaUserRepository;

    public AddRecordResponse execute(AddRecordRequest addRecordRequest){

        List<CoreError> errors = checkRequestForErrors(addRecordRequest);
        checkAvailabilityInDB(addRecordRequest, errors);

        if (requestHaveErrors(errors)){
            return createAddRecordResponseWithErrors(errors);
        }

        Record record = createRecord(addRecordRequest);
        saveRecordInDB(record);
        return createAddRecordResponse(record);
    }

    private void checkAvailabilityInDB(AddRecordRequest addRecordRequest, List<CoreError> errors){

        if (targetDoesNotExist(addRecordRequest)){
            errors.add(createCoreError("Target ID","no target with that ID!"));
        }

        if (userDoesNotExist(addRecordRequest)){
            errors.add(createCoreError("User ID","no user with that ID!"));
        }
    }

    private boolean userDoesNotExist(AddRecordRequest request) {
        return !jpaUserRepository.existsById(request.getUserId());
    }

    private boolean targetDoesNotExist(AddRecordRequest addRecordRequest){
        return !jpaTargetRepository.existsById(addRecordRequest.getTargetId());
    }

    private AddRecordResponse createAddRecordResponse(Record record){
        return new AddRecordResponse(record);
    }

    private AddRecordResponse createAddRecordResponseWithErrors(List<CoreError> errors) {
        return new AddRecordResponse(errors);
    }

    private boolean requestHaveErrors(List<CoreError> errors) {
        return !errors.isEmpty();
    }

    private void saveRecordInDB(Record record){
        jpaBoardRepository.save(record);
    }

    private Record createRecord(AddRecordRequest addRecordRequest){
        return new Record(addRecordRequest.getTargetId(), addRecordRequest.getUserId(), getDate());
    }

    private List<CoreError> checkRequestForErrors(AddRecordRequest addRecordRequest){
        return addRecordValidator.validate(addRecordRequest);
    }

    private String getDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter myFormatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(myFormatDate);
    }
}
