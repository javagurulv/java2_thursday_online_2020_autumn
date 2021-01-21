package internet_store.application.core.database;

import internet_store.application.core.domain.Order;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class HibernateOrderDatabase implements OrderDatabase {

    private final SessionFactory sessionFactory;

    public HibernateOrderDatabase(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Long add(Order order) {
        return (Long) sessionFactory.getCurrentSession().save(order);
    }

    @Override
    public Optional<Order> findById(Long id) {
        Order foundOrder = (Order) sessionFactory.getCurrentSession()
                .createCriteria(Order.class).add(Restrictions.eq("id", id)).uniqueResult();
        return Optional.ofNullable(foundOrder);
    }

    @Override
    public List<Order> findAll() {
        return sessionFactory.getCurrentSession().createCriteria(Order.class).list();
    }

}
