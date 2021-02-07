package adventure_time.core.services.guides;

import adventure_time.core.requests.guides.AddGuideRequest;
import adventure_time.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class AddGuideRequestValidator {

    public List<CoreError> validate(AddGuideRequest request) {

        // Validation
        List<CoreError> errors = new ArrayList<>();
        if (request.getGuideName() == null || request.getGuideName().isEmpty()) {
            // error
            CoreError error = new CoreError("guideName", "Must be not empty");
            errors.add(error);
        }
        if (request.getGuideEmail() == null || request.getGuideEmail().isEmpty()) {
            // error
            CoreError error = new CoreError("guideEmail", "Must be not empty");
            errors.add(error);
        }
        if (request.getGuidePhone() == null || request.getGuidePhone().isEmpty()) {
            // error
            CoreError error = new CoreError("guidePhone", "Must be not empty");
            errors.add(error);
        }
        if (request.getGuidePhone().length()<11) {
            // error
            CoreError error = new CoreError("guidePhone", "Must be longer than 7 symbols");
            errors.add(error);
        }
        if (!request.getGuidePhone().startsWith("+")) {
            // error
            CoreError error = new CoreError("guidePhone", "Must start with a symbol + ");
            errors.add(error);
        }
        if (!request.getGuideEmail().contains("@")) {
            // error
            CoreError error = new CoreError("guidePhone", "Must contain a symbol @ ");
            errors.add(error);
        }
        return errors;
    }
}