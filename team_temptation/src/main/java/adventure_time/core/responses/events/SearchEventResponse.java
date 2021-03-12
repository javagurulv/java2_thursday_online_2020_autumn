package adventure_time.core.responses.events;

import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.CoreResponse;
import adventure_time.core.domain.Events;

import java.util.List;

public class SearchEventResponse extends CoreResponse {

    private Events events;

    public SearchEventResponse(Events events, List<CoreError> errors) {
        super(errors);
        this.events = events;
    }

    public Events getEvents() {
        return events;
    }
}
