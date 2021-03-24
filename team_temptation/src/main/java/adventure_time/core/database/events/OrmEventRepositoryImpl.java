package adventure_time.core.database.events;

import adventure_time.core.domain.Events;
import adventure_time.core.requests.Paging;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Transactional
public class OrmEventRepositoryImpl implements EventRepository {

    @Autowired private SessionFactory sessionFactory;


    @Override
    public boolean add(Events event) {
        try {
            sessionFactory.getCurrentSession()
                    .save(event);
        } catch (ConstraintViolationException exception) {
            sessionFactory.getCurrentSession().clear();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Long id) {
        return sessionFactory.getCurrentSession()
                .createQuery("delete Events where id = :id")
                .setParameter("id", id)
                .executeUpdate() == 1;
    }

    @Override
    public Optional<Events> findById(Long id) {
        List<Events> result = sessionFactory.getCurrentSession()
                .createQuery("from Events as c where c.id = :id", Events.class)
                .setParameter("id", id)
                .getResultList();
        if (result.isEmpty()) return Optional.empty();
        return Optional.of(result.get(0));
    }

    @Override
    public Optional<Events> findByName(String name) {
        List<Events> result = sessionFactory.getCurrentSession()
                .createQuery("from Events as e where e.eventName = :name", Events.class)
                .setParameter("name", name)
                .getResultList();
        if (result.isEmpty()) return Optional.empty();
        return Optional.of(result.get(0));
    }

    @Override
    public boolean updateEvent(Events event, Long id) {
        try {
            sessionFactory.getCurrentSession()
                    .createQuery("UPDATE Events SET " +
                            "name =: name, " +
                            "kind =: kind, " +
                            "duration =: duration, " +
                            "max_guys =: max, " +
                            "min_guys =: min, " +
                            "route =: route, " +
                            "detail =: details " +
                            "WHERE id = :id")
                    .setParameter("id", id)
                    .setParameter("name", event.getEventName())
                    .setParameter("kind", event.getEventKind())
                    .setParameter("duration", event.getDurationHours())
                    .setParameter("max", event.getMaxNumberParticipants())
                    .setParameter("min", event.getMinNumberParticipants())
                    .setParameter("route", event.getRoute())
                    .setParameter("details", event.getDetailsDescription())
                    .executeUpdate();
            return true;
        } catch (PersistenceException exception) {
            sessionFactory.getCurrentSession().clear();
            return false;
        }
    }

    @Override
    public List<Events> findEvents(String query, Paging paging) {
        return sessionFactory.getCurrentSession()
                .createQuery(query, Events.class)
                .getResultList()
                .stream()
                .skip((paging.getPageNumber()-1)*paging.getPageSize())
                .limit(paging.getPageSize())
                .collect(Collectors.toList());
    }
}
