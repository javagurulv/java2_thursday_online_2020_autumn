package java2.application_target_list.core.validators.user;

import java2.application_target_list.core.requests.user.GetUserRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.ErrorCreator;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GetUserValidator extends ErrorCreator {

    public List<CoreError> validate (GetUserRequest getUserRequest){
        List<CoreError> errors = new ArrayList<>();
        validateId(getUserRequest).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(GetUserRequest getUserRequest){
        return (getUserRequest.getId() == null)
                ? Optional.of(createCoreError("id", "Must not be empty!"))
                : Optional.empty();
    }
}
