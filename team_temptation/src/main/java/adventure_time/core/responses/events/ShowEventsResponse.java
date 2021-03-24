package adventure_time.core.responses.events;

import adventure_time.core.domain.Events;
import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.CoreResponse;

import java.util.List;

public class ShowEventsResponse extends CoreResponse {

    private List<Events> eventsList;

    public ShowEventsResponse(List<Events> events, List<CoreError> errors) {
        super(errors);
        this.eventsList = events;
    }

    public List<Events> getEventsList() {
        return eventsList;
    }
}
