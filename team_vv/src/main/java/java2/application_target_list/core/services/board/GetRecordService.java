package java2.application_target_list.core.services.board;

import java2.application_target_list.core.database.jpa.JpaBoardRepository;
import java2.application_target_list.core.domain.Record;
import java2.application_target_list.core.requests.board.GetRecordRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.board.GetRecordResponse;
import java2.application_target_list.core.validators.ErrorCreator;
import java2.application_target_list.core.validators.board.GetRecordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GetRecordService extends ErrorCreator {

    @Autowired
    private GetRecordValidator getRecordValidator;
    @Autowired
    private JpaBoardRepository jpaBoardRepository;

    public GetRecordResponse execute(GetRecordRequest getRecordRequest){
        List<CoreError> errors = checkRequestForErrors(getRecordRequest);

        if (requestHaveErrors(errors)) {
            return createResponseWithErrors(errors);
        }

        return jpaBoardRepository.findById(getRecordRequest.getId()).map(GetRecordResponse::new)
                .orElseGet(() -> {
                    errors.add(createCoreError("id", "Not found"));
                    return createResponseWithErrors(errors);});
    }

    private GetRecordResponse createResponseWithErrors(List<CoreError> errors) {
        return new GetRecordResponse(errors);
    }

    private boolean requestHaveErrors(List<CoreError> errors){
        return !errors.isEmpty();
    }

    private List<CoreError> checkRequestForErrors(GetRecordRequest getRecordRequest){
        return getRecordValidator.validate(getRecordRequest);
    }

}
