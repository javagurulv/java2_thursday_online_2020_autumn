package java2.application_target_list.core.services.target;

import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.requests.target.GetTargetRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.target.GetTargetResponse;
import java2.application_target_list.core.validators.ErrorCreator;
import java2.application_target_list.core.validators.target.GetTargetValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GetTargetService extends ErrorCreator {

    @Autowired
    private GetTargetValidator validator;
    @Autowired
    private JpaTargetRepository jpaTargetRepository;

    public GetTargetResponse execute(GetTargetRequest getTargetRequest) {
        List<CoreError> errors = checkRequestForErrors(getTargetRequest);

        if (requestHaveErrors(errors)){
            return createGetTargetResponseWithErrors(errors);
        }

        return createGetTargetResponse(getTargetRequest, errors);
    }

    private GetTargetResponse createGetTargetResponseWithErrors(List<CoreError> errors) {
        return new GetTargetResponse(errors);
    }

    private GetTargetResponse createGetTargetResponse(GetTargetRequest getTargetRequest, List<CoreError> errors) {
        return jpaTargetRepository.findById(getTargetRequest.getId())
                .map(GetTargetResponse::new)
                .orElseGet(() -> {
                    errors.add(createCoreError("id", "Not found!"));
                    return createGetTargetResponseWithErrors(errors);});
    }

    private boolean requestHaveErrors(List<CoreError> errors){
        return !errors.isEmpty();
    }

    private List<CoreError> checkRequestForErrors(GetTargetRequest getTargetRequest){
        return validator.validate(getTargetRequest);
    }
}


