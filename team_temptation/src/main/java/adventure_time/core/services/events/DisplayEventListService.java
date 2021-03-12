package adventure_time.core.services.events;

import adventure_time.core.domain.Events;
import adventure_time.core.database.events.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DisplayEventListService {
@Autowired
private EventRepository databaseEvents;

    public List<Events> getEventsList () {
        return null; //databaseEvents.getEventsList();
    }

}
