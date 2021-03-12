package java2.application_target_list.core.services.target;

import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.requests.target.DeleteTargetRequest;
import java2.application_target_list.core.validators.ErrorCreator;
import java2.application_target_list.core.validators.target.DeleteTargetValidator;
import org.springframework.beans.factory.annotation.Autowired;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.target.DeleteTargetResponse;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DeleteTargetService extends ErrorCreator {

    @Autowired
    private DeleteTargetValidator validator;
    @Autowired
    private JpaTargetRepository jpaTargetRepository;

    public DeleteTargetResponse execute(DeleteTargetRequest deleteTargetRequest){

        List<CoreError> errors = checkRequestForErrors(deleteTargetRequest);
        checkAvailabilityInDB(deleteTargetRequest, errors);

        if (requestHaveErrors(errors)) {
            return createDeleteTargetResponseWithErrors(errors);
        }

        deleteTargetFromDB(deleteTargetRequest);
        return createDeleteTargetResponse(deleteTargetRequest);
    }

    private DeleteTargetResponse createDeleteTargetResponse(DeleteTargetRequest deleteTargetRequest) {
        return new DeleteTargetResponse(deleteTargetRequest.getTargetIdToDelete());
    }

    private void deleteTargetFromDB(DeleteTargetRequest deleteTargetRequest){
        jpaTargetRepository.deleteById(deleteTargetRequest.getTargetIdToDelete());
    }

    private DeleteTargetResponse createDeleteTargetResponseWithErrors(List<CoreError> errors) {
        return new DeleteTargetResponse(errors);
    }

    private boolean requestHaveErrors(List<CoreError> errors) {
        return !errors.isEmpty();
    }

    private void checkAvailabilityInDB(DeleteTargetRequest deleteTargetRequest, List<CoreError> errors) {
        if (targetDoesNotExist(deleteTargetRequest)){
            errors.add(createCoreError("Target ID;","no target with that ID"));
        }
    }

    private boolean targetDoesNotExist(DeleteTargetRequest deleteTargetRequest){
        return !jpaTargetRepository.existsById(deleteTargetRequest.getTargetIdToDelete());
    }

    private List<CoreError> checkRequestForErrors(DeleteTargetRequest deleteTargetRequest){
        return validator.validate(deleteTargetRequest);
    }

}
