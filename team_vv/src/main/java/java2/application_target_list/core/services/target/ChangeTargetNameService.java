package java2.application_target_list.core.services.target;

import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.requests.target.ChangeTargetNameRequest;
import java2.application_target_list.core.validators.ErrorCreator;
import java2.application_target_list.core.validators.target.ChangeTargetNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import java2.application_target_list.core.responses.target.ChangeTargetNameResponse;
import java2.application_target_list.core.responses.CoreError;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ChangeTargetNameService extends ErrorCreator {

    @Autowired
    private JpaTargetRepository jpaTargetRepository;
    @Autowired
    private ChangeTargetNameValidator validator;

    public ChangeTargetNameResponse execute(ChangeTargetNameRequest changeTargetNameRequest){

        List<CoreError> errors = validator.validate(changeTargetNameRequest);
        checkAvailabilityInDB(changeTargetNameRequest, errors);

        if (requestHavErrors(errors)) {
            return createChangeTargetNameResponseWithErrors(errors);
        }

        changeTargetName(changeTargetNameRequest);
        return createChangeTargetNameResponse(changeTargetNameRequest);
    }

    private ChangeTargetNameResponse createChangeTargetNameResponse(ChangeTargetNameRequest changeTargetNameRequest){
        return new ChangeTargetNameResponse(changeTargetNameRequest.getTargetIdToChange(), changeTargetNameRequest.getNewTargetName());
    }

    private void changeTargetName(ChangeTargetNameRequest changeTargetNameRequest){
        jpaTargetRepository.changeTargetName(changeTargetNameRequest.getTargetIdToChange(), changeTargetNameRequest.getNewTargetName());
    }

    private ChangeTargetNameResponse createChangeTargetNameResponseWithErrors(List<CoreError> errors) {
        return new ChangeTargetNameResponse(errors);
    }

    private boolean requestHavErrors(List<CoreError> errors) {
        return !errors.isEmpty();
    }

    private void checkAvailabilityInDB(ChangeTargetNameRequest changeTargetNameRequest, List<CoreError> errors) {
        if (targetDoesNotExist(changeTargetNameRequest)){
            errors.add(createCoreError("Target ID;","no target with that ID"));
        }
    }

    private boolean targetDoesNotExist(ChangeTargetNameRequest changeTargetNameRequest) {
        return !jpaTargetRepository.existsById(changeTargetNameRequest.getTargetIdToChange());
    }

    private List<CoreError> checkRequestForErrors(ChangeTargetNameRequest changeTargetNameRequest) {
        return validator.validate(changeTargetNameRequest);
    }
}
