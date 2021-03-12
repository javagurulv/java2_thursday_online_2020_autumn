package java2.application_target_list.core.services.target;

import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.requests.target.ChangeTargetDescriptionRequest;
import java2.application_target_list.core.responses.target.ChangeTargetDescriptionResponse;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.ErrorCreator;
import java2.application_target_list.core.validators.target.ChangeTargetDescriptionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ChangeTargetDescriptionService extends ErrorCreator {

    @Autowired
    private JpaTargetRepository jpaTargetRepository;
    @Autowired
    private ChangeTargetDescriptionValidator validator;

    public ChangeTargetDescriptionResponse execute(ChangeTargetDescriptionRequest changeTargetDescriptionRequest){

        List<CoreError> errors = checkRequestFoErrors(changeTargetDescriptionRequest);
        checkAvailabilityInDB(changeTargetDescriptionRequest, errors);

        if (requestHaveErrors(errors)) {
            return createChangeTargetDescriptionResponseWithErrors(errors);
        }

        changeTargetDescription(changeTargetDescriptionRequest);
        return createChangeTargetDescriptionResponse(changeTargetDescriptionRequest);
    }

    private void changeTargetDescription(ChangeTargetDescriptionRequest changeTargetDescriptionRequest){
        jpaTargetRepository.changeTargetDescription(changeTargetDescriptionRequest.getTargetIdToChange(), changeTargetDescriptionRequest.getNewTargetDescription());
    }

    private boolean requestHaveErrors(List<CoreError> errors) {
        return !errors.isEmpty();
    }

    private ChangeTargetDescriptionResponse createChangeTargetDescriptionResponse(ChangeTargetDescriptionRequest changeTargetDescriptionRequest){
        return new ChangeTargetDescriptionResponse(changeTargetDescriptionRequest.getTargetIdToChange(), changeTargetDescriptionRequest.getNewTargetDescription());
    }

    private ChangeTargetDescriptionResponse createChangeTargetDescriptionResponseWithErrors(List<CoreError> errors) {
        return new ChangeTargetDescriptionResponse(errors);
    }

    private void checkAvailabilityInDB(ChangeTargetDescriptionRequest changeTargetDescriptionRequest, List<CoreError> errors) {
        if (targetDoesNotExist(changeTargetDescriptionRequest)) {
            errors.add(createCoreError("Target ID;", "no target with that ID"));
        }
    }

    private boolean targetDoesNotExist(ChangeTargetDescriptionRequest changeTargetDescriptionRequest){
        return !jpaTargetRepository.existsById(changeTargetDescriptionRequest.getTargetIdToChange());
    }

    private List<CoreError> checkRequestFoErrors(ChangeTargetDescriptionRequest changeTargetDescriptionRequest) {
        return validator.validate(changeTargetDescriptionRequest);
    }
}
