package adventure_time.core.services.events;

import adventure_time.core.requests.events.RemoveEventRequest;
import adventure_time.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RemoveEventRequestValidator {

    public List<CoreError> validate (RemoveEventRequest request) {

        List<CoreError> errors = new ArrayList<>();

        if (request.getEventId() == null) {
            CoreError error = new CoreError("customerId", "Must be not null");
            errors.add(error);
        }

        if (request.getEventId() <= 0) {
            CoreError error = new CoreError("customerId", "Must be bigger than zero");
            errors.add(error);
        }
        return errors;
    }
}
