package internet_store.application.core.database.customer;

import internet_store.application.core.domain.Customer;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

//@Component
//@Profile("hibernate")
//@Transactional
public class ORMCustomerRepository implements CustomerRepository {

    private final SessionFactory sessionFactory;

    public ORMCustomerRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Long addCustomer(Customer customer) {
        return (Long) sessionFactory.getCurrentSession().save(customer);
    }

    @Override
    public boolean deleteByCustomerId(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "delete Customer where id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public List<Customer> findByFirstName(String customerName) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "FROM Customer WHERE customerFirstName = :name");
        query.setParameter("name", customerName);
        return query.getResultList();
    }

    @Override
    public Optional<Customer> findByCustomerId(Long id) {
        Customer customer = sessionFactory.getCurrentSession().find(Customer.class, id);
        return Optional.ofNullable(customer);
    }

    @Override
    public List<Customer> findAll() {
        CriteriaQuery<Customer> query = sessionFactory.getCriteriaBuilder().createQuery(Customer.class);
        query.from(Customer.class);
        return sessionFactory.getCurrentSession().createQuery(query).getResultList();
    }

    @Override
    public boolean changeFirstName(Long id, String newFirstName) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "UPDATE Customer SET first_name = :newFirstName WHERE id = :id");
        query.setParameter("newFirstName", newFirstName);
        query.setParameter("id", id);
        return query.executeUpdate() >= 1;
    }

    @Override
    public Optional<Customer> getById(Long id) {
        Customer customer = sessionFactory.getCurrentSession().get(Customer.class, id);
        if (customer == null) {
            return Optional.empty();
        } else {
            return Optional.of(customer);
        }
    }
}
