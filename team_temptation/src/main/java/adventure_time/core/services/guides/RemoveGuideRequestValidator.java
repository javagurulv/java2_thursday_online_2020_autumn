package adventure_time.core.services.guides;

import adventure_time.core.requests.guides.RemoveGuideRequest;
import adventure_time.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RemoveGuideRequestValidator {

    public List<CoreError> validate(RemoveGuideRequest request) {

        // Validation
        List<CoreError> errors = new ArrayList<>();
        // In case of deletionWay error definition
        if (request.getDeletionWay() == null || request.getDeletionWay().isBlank()) {
            CoreError error = new CoreError("deletionWay", "Must be defined");
            errors.add(error);
        } else {

            if (!request.getDeletionWay().equals("byName") && !request.getDeletionWay().equals("byId")) {
                CoreError error = new CoreError("deletionWay", "Must be \"by name\" or \"by ID-number\"");
                errors.add(error);
            }
        }

        if (request.getDeletionWay().equals("byName")
                && (request.getGuideName().isBlank() || request.getGuideName() == null)) {
            // error
            CoreError error = new CoreError("guideName", "Must be defined");
            errors.add(error);
        }

        if (request.getDeletionWay().equals("byId") && request.getGuideId() == null) {
            // error
            CoreError error = new CoreError("guideId", "Must be defined");
            errors.add(error);
        }

        if (request.getDeletionWay().equals("byId") && request.getGuideId() <= 0 ) {
            // error
            CoreError error = new CoreError("guideId", "Must be above zero");
            errors.add(error);
        }

        return errors;
    }
}
