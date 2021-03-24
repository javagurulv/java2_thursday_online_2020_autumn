package adventure_time.core.services.events;

import adventure_time.core.requests.events.UpdateEventRequest;
import adventure_time.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class   UpdateEventRequestValidator {

    public List<CoreError> validateId (UpdateEventRequest request) {

        List<CoreError> errors = new ArrayList<>();

        if (request.getEventId() == null) {
            CoreError error = new CoreError("eventId", "Must not be null");
            errors.add(error);
        }

        if (request.getEventId().equals(Long.valueOf(0))) {
            CoreError error = new CoreError("eventId", "Must not be 0");
            errors.add(error);
        }
        return errors;
    }



}
