package internet_store.application.core.database;

import internet_store.application.core.domain.Customer;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class HibernateCustomerDatabase implements CustomerDatabase{

    private final SessionFactory sessionFactory;

    public HibernateCustomerDatabase(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Long add(Customer customer) {
        return (Long) sessionFactory.getCurrentSession().save(customer);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        Customer foundCustomer = (Customer) sessionFactory.getCurrentSession()
                .createCriteria(Customer.class).add(Restrictions.eq("id", id)).uniqueResult();
        return Optional.ofNullable(foundCustomer);
    }

    @Override
    public List<Customer> findAll() {
        return sessionFactory.getCurrentSession().createCriteria(Customer.class).list();
    }

}
