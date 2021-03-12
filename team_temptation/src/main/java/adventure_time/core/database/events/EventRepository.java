package adventure_time.core.database.events;

import adventure_time.core.requests.events.AddEventRequest;
import adventure_time.core.requests.events.SearchEventRequest;
import adventure_time.core.domain.Events;
import adventure_time.core.requests.events.UpdateEventRequest;

import java.util.List;
import java.util.Optional;

public interface EventRepository {

    boolean add (Events event);

    boolean delete (Long id);

    Optional<Events> findById (Long id);

    Optional<Events> findByName (String name);

    boolean updateEvent (Events event, Long id);

    List<Events> findEvents (String query);

}
