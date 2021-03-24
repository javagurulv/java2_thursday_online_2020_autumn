package adventure_time.core.services.events;

import adventure_time.core.requests.events.RemoveEventRequest;
import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.customer.RemoveCustomerResponse;
import adventure_time.core.responses.events.RemoveEventResponse;
import adventure_time.core.database.events.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RemoveEventService {
    @Autowired
    private EventRepository database;
    @Autowired private RemoveEventRequestValidator validator;

    public RemoveEventResponse removeEvent(RemoveEventRequest request) {

        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new RemoveEventResponse(errors);
        }

        if (!database.delete(request.getEventId())) {
            errors.add(new CoreError("customerId",
                    "There is no any event with ID: " + request.getEventId() + " in the database"));
            return new RemoveEventResponse(errors);
        } else {
            return new RemoveEventResponse();
        }
    }
}
