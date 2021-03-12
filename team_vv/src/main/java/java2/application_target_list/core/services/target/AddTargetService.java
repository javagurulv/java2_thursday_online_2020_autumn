package java2.application_target_list.core.services.target;

import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.responses.target.AddTargetResponse;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.target.AddTargetValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AddTargetService {

    @Autowired
    private JpaTargetRepository jpaTargetRepository;
    @Autowired
    private AddTargetValidator validator;

    public AddTargetResponse execute(AddTargetRequest addTargetRequest){
        List<CoreError> errors = checkRequestForErrors(addTargetRequest);

        if (requestHaveErrors(errors)) {
            return createAddTargetResponseWithErrors(errors);
        }

        Target target = createTarget(addTargetRequest);
        addTargetToDB(target);
        return createAddTargetResponse(target);
    }

    private AddTargetResponse createAddTargetResponse(Target target) {
        return new AddTargetResponse(target);
    }

    private void addTargetToDB(Target target){
        jpaTargetRepository.save(target);
    }

    private Target createTarget(AddTargetRequest addTargetRequest){
        return new Target(addTargetRequest.getName(), addTargetRequest.getDescription(), addTargetRequest.getDeadline());
    }

    private AddTargetResponse createAddTargetResponseWithErrors(List<CoreError> errors) {
        return new AddTargetResponse(errors);
    }

    private boolean requestHaveErrors(List<CoreError> errors){
        return !errors.isEmpty();
    }

    private List<CoreError> checkRequestForErrors(AddTargetRequest addTargetRequest){
        return validator.validate(addTargetRequest);
    }

}
