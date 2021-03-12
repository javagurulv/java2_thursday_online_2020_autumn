package java2.application_target_list.core.validators.target;

import java2.application_target_list.core.requests.target.GetTargetRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.ErrorCreator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GetTargetValidator extends ErrorCreator {

    public List<CoreError> validate (GetTargetRequest getTargetRequest) {
        List<CoreError> errors = new ArrayList<>();
        validateId(getTargetRequest).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(GetTargetRequest request) {
        return (request.getId() == null)
                ? Optional.of(createCoreError("id", "Must not be empty!"))
                : Optional.empty();
    }
}
