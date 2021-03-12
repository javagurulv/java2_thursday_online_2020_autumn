package adventure_time.core.services.events;

import adventure_time.core.requests.events.RemoveEventRequest;
import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.events.RemoveEventResponse;
import adventure_time.core.database.events.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

//@DIComponent
@Component
public class RemoveEventService {
//    @DIDependency
    @Autowired
    private EventRepository databaseEvents;
//    @DIDependency
    @Autowired private RemoveEventRequestValidator validator;

//    public RemoveEventService(EventDatabase databaseEvents, RemoveEventRequestValidator validator) {
//        this.databaseEvents = databaseEvents;
//        this.validator = validator;
//    }

    public RemoveEventResponse removeEvent(RemoveEventRequest request) {

        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new RemoveEventResponse(errors);
        }

//        boolean isSuccessRemoval;
//        if (request.getDeletionWay().equals("byName")) {
//            isSuccessRemoval = databaseEvents.removeByName(request.getEventName());
//        } else {
//            isSuccessRemoval = databaseEvents.removeById(request.getEventId());
//        }

        return new RemoveEventResponse(true); //isSuccessRemoval);

    }
}
