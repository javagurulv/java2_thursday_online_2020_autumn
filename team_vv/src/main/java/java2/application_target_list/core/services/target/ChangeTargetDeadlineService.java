package java2.application_target_list.core.services.target;

import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.validators.ErrorCreator;
import org.springframework.beans.factory.annotation.Autowired;
import java2.application_target_list.core.requests.target.ChangeTargetDeadlineRequest;
import java2.application_target_list.core.responses.target.ChangeTargetDeadlineResponse;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.target.ChangeTargetDeadlineValidator;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ChangeTargetDeadlineService extends ErrorCreator {

    @Autowired
    private JpaTargetRepository jpaTargetRepository;
    @Autowired
    private ChangeTargetDeadlineValidator validator;

    public ChangeTargetDeadlineResponse execute(ChangeTargetDeadlineRequest changeTargetDeadlineRequest){

        List<CoreError> errors = checkRequestForErrors(changeTargetDeadlineRequest);
        checkAvailabilityInDB(changeTargetDeadlineRequest, errors);

        if (requestHaveErrors(errors)) {
            return new ChangeTargetDeadlineResponse(errors);
        }

        changeTargetDeadline(changeTargetDeadlineRequest);
        return createChangeTargetDeadlineResponse(changeTargetDeadlineRequest);
    }

    private ChangeTargetDeadlineResponse createChangeTargetDeadlineResponse(ChangeTargetDeadlineRequest changeTargetDeadlineRequest) {
        return new ChangeTargetDeadlineResponse(changeTargetDeadlineRequest.getTargetIdToChange(), changeTargetDeadlineRequest.getNewTargetDeadline());
    }

    private void changeTargetDeadline(ChangeTargetDeadlineRequest changeTargetDeadlineRequest){
        jpaTargetRepository.changeTargetDeadline(changeTargetDeadlineRequest.getTargetIdToChange(), changeTargetDeadlineRequest.getNewTargetDeadline());
    }

    private boolean targetDoesNotExistInDB(ChangeTargetDeadlineRequest changeTargetDeadlineRequest){
        return !jpaTargetRepository.existsById(changeTargetDeadlineRequest.getTargetIdToChange());
    }

    private void checkAvailabilityInDB(ChangeTargetDeadlineRequest changeTargetDeadlineRequest, List<CoreError> errors){
        if (targetDoesNotExistInDB(changeTargetDeadlineRequest)){
            errors.add(createCoreError("Target ID;","no target with that ID"));
        }
    }

    private boolean requestHaveErrors(List<CoreError> errors) {
        return !errors.isEmpty();
    }

    private List<CoreError> checkRequestForErrors(ChangeTargetDeadlineRequest changeTargetDeadlineRequest) {
        return validator.validate(changeTargetDeadlineRequest);
    }
}
