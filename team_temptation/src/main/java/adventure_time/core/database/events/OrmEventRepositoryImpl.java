package adventure_time.core.database.events;

import adventure_time.core.domain.Events;
import adventure_time.core.requests.events.SearchEventRequest;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class OrmEventRepositoryImpl implements EventRepository {

    @Autowired private SessionFactory sessionFactory;


    @Override
    public boolean add(Events event) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Optional<Events> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Events> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public boolean updateEvent(Events event, Long id) {
        return false;
    }

    @Override
    public List<Events> findEvents(String query) {
        return null;
    }
}
