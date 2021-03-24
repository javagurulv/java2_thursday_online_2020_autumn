package adventure_time.core.services.events;

import adventure_time.core.domain.Events;
import adventure_time.core.requests.events.AddEventRequest;
import adventure_time.core.requests.events.UpdateEventRequest;
import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.events.UpdateEventResponse;
import adventure_time.core.database.events.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UpdateEventService {

    @Autowired
    private EventRepository databaseEvents;
    @Autowired private UpdateEventRequestValidator updateValidator;
    @Autowired private AddEventRequestValidator addValidator;

    public UpdateEventResponse findById (UpdateEventRequest request) {

        List<CoreError> errors = updateValidator.validateId(request);
        if (!errors.isEmpty()) {
            return new UpdateEventResponse(null, errors);
        }

        Optional<Events> result = databaseEvents.findById(request.getEventId());

        if (result.isEmpty()) {
            errors.add(new CoreError("eventId", "Not found"));
            return new UpdateEventResponse(null, errors);
        }

        return new UpdateEventResponse(result.get(), null);
    }

    public UpdateEventResponse updateEvent (AddEventRequest request, Long id) {

        List<CoreError> errors = addValidator.validate(request);
        if (!errors.isEmpty()) {
            return new UpdateEventResponse(null, errors);
        }

        Events event = new Events(request.getEventName(), request.getEventKind(),
                request.getDurationHours(), request.getMaxNumberParticipants(),
                request.getMinNumberParticipants(), request.getRoute(),
                request.getDetailsDescription());

        databaseEvents.updateEvent(event, id);

        return new UpdateEventResponse(null, null);
    }

}
