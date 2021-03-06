package adventure_time.core.services.events;

import adventure_time.core.requests.events.SearchEventRequest;
import adventure_time.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class SearchEventRequestValidator {

    private static final Pattern PATTERN_ID = Pattern.compile("[0-9]+");

    public List<CoreError> validate (SearchEventRequest request) {

        List<CoreError> errors = new ArrayList<>();

        boolean id = !(request.getEventId() == null) && request.getEventName() == null;
        boolean name =  request.getEventId() == null && !(request.getEventName() == null);

        if (request.getEventId() == null && request.getEventName() == null) {
            CoreError error = new CoreError("searchCriteria", "It is required to define at list one of the search criteria. You did not define any.");
            errors.add(error);
        }

        if (request.getEventId() != null && request.getEventName() != null) {
            CoreError error = new CoreError("searchCriteria", "It is required to define only one of the search criteria. You defined both of them.");
            errors.add(error);
        }

        if (name && request.getEventName().isBlank()) {
            CoreError error = new CoreError("eventName", "The event name is not defined");
            errors.add(error);
        }

        if (id && request.getEventId().toString().isBlank()) {
            CoreError error = new CoreError("eventId", "The event id is not defined");
            errors.add(error);
        }

        if (id && !PATTERN_ID.matcher(request.getEventId().toString()).matches()) {
            CoreError error = new CoreError("eventId", "Id is incorrect.");
            errors.add(error);
        }

        return errors;
    }

}
