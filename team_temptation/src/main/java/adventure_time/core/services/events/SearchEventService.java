package adventure_time.core.services.events;

import adventure_time.core.requests.Ordering;
import adventure_time.core.requests.Paging;
import adventure_time.core.requests.events.SearchEventRequest;
import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.customer.SearchCustomerResponse;
import adventure_time.core.responses.events.SearchEventResponse;
import adventure_time.core.database.events.EventRepository;
import adventure_time.core.domain.Events;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@Component
public class SearchEventService {

    @Autowired
    private EventRepository database;
    @Autowired
    private SearchEventRequestValidator validator;

    public SearchEventResponse searchEvent(SearchEventRequest request) {

        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SearchEventResponse(null, errors);
        }

        Optional<Events> events;
        String field;
        String criteria;
        if (request.getEventId() == null) {
            field = "event name";
            criteria = request.getEventName();
            events = database.findByName(request.getEventName());
        } else {
            field = "event ID";
            criteria = request.getEventId().toString();
            events = database.findById(request.getEventId());
        }

        if (events.isPresent()) {
            return new SearchEventResponse(events.get(), null);
        } else {
            CoreError error = new CoreError(field, "The event with " + field + " \"" + criteria + "\" is not present in DB");
            errors.add(error);
            return new SearchEventResponse(null, errors);
        }
    }
}