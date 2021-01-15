package java2.application_target_list.core.services.target;

import java2.application_target_list.core.database.TargetDatabase;
import java2.application_target_list.core.requests.target.ChangeTargetDescriptionRequest;
import java2.application_target_list.core.responses.target.ChangeTargetDescriptionResponse;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.target.ChangeTargetDescriptionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class ChangeTargetDescriptionService {

    @Autowired private TargetDatabase targetDatabase;
    @Autowired private ChangeTargetDescriptionValidator validator;

    public ChangeTargetDescriptionResponse execute(ChangeTargetDescriptionRequest request){
        List<CoreError> errors = validator.validate(request, targetDatabase);

        if (!errors.isEmpty()) {
            return new ChangeTargetDescriptionResponse(errors);
        }
        targetDatabase.changeTargetDescription(request.getTargetIdToChange(), request.getNewTargetDescription());
        return new ChangeTargetDescriptionResponse(request.getTargetIdToChange(), request.getNewTargetDescription());
    }
}