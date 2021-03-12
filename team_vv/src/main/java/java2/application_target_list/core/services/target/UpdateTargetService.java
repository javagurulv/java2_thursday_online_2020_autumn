package java2.application_target_list.core.services.target;

import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.requests.target.UpdateTargetRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.target.UpdateTargetResponse;
import java2.application_target_list.core.validators.ErrorCreator;
import java2.application_target_list.core.validators.target.UpdateTargetValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UpdateTargetService extends ErrorCreator {

    @Autowired
    private UpdateTargetValidator updateTargetValidator;
    @Autowired
    private JpaTargetRepository jpaTargetRepository;

    public UpdateTargetResponse execute(UpdateTargetRequest updateTargetRequest) {
        List<CoreError> errors = checkRequestForErrors(updateTargetRequest);
        checkAvailabilityInDB(updateTargetRequest, errors);

        if (requestHaveError(errors)) {
            return createUpdateTargetResponseWithErrors(errors);
        }

        updateTarget(updateTargetRequest);

        return createUpdateTargetResponse(updateTargetRequest);
    }

    private UpdateTargetResponse createUpdateTargetResponse(UpdateTargetRequest updateTargetRequest){
        return new UpdateTargetResponse(updateTargetRequest.getTargetIdToChange(),
                updateTargetRequest.getNewTargetName(),
                updateTargetRequest.getNewTargetDescription(),
                updateTargetRequest.getNewTargetDeadline());
    }

    private void updateTargetName(UpdateTargetRequest updateTargetRequest) {
        jpaTargetRepository.changeTargetName(updateTargetRequest.getTargetIdToChange(), updateTargetRequest.getNewTargetName());
    }

    private void updateTargetDescription(UpdateTargetRequest updateTargetRequest) {
        jpaTargetRepository.changeTargetDescription(updateTargetRequest.getTargetIdToChange(), updateTargetRequest.getNewTargetDescription());
    }

    private void updateTargetDeadline(UpdateTargetRequest updateTargetRequest) {
        jpaTargetRepository.changeTargetDeadline(updateTargetRequest.getTargetIdToChange(), updateTargetRequest.getNewTargetDeadline());
    }

    private void updateTarget(UpdateTargetRequest updateTargetRequest) {
        updateTargetName(updateTargetRequest);
        updateTargetDescription(updateTargetRequest);
        updateTargetDeadline(updateTargetRequest);
    }

    private UpdateTargetResponse createUpdateTargetResponseWithErrors(List<CoreError> errors){
        return new UpdateTargetResponse(errors);
    }

    private boolean requestHaveError(List<CoreError> errors) {
        return !errors.isEmpty();
    }

    private void checkAvailabilityInDB(UpdateTargetRequest updateTargetRequest, List<CoreError> errors) {
        if (targetDoesNotExist(updateTargetRequest)){
            errors.add(createCoreError("Target ID;","no target with that ID"));
        }
    }

    private boolean targetDoesNotExist(UpdateTargetRequest updateTargetRequest){
        return !jpaTargetRepository.existsById(updateTargetRequest.getTargetIdToChange());
    }

    private List<CoreError> checkRequestForErrors(UpdateTargetRequest updateTargetRequest){
        return updateTargetValidator.validate(updateTargetRequest);
    }
}
