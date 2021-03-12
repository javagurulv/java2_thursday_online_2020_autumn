package internet_store.database.customer;

import internet_store.core.domain.Customer;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Component
//@Transactional
public class OrmCustomerDatabaseImpl implements CustomerDatabase{

    @Autowired private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT c FROM Customer c", Customer.class)
                .getResultList();
    }

    @Override
    public void addCustomer(Customer customer) {
        sessionFactory.getCurrentSession().save(customer);
    }

    @Override
    public boolean deleteCustomerById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("DELETE Customer where id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        Customer customer = (Customer) sessionFactory.getCurrentSession().createCriteria(Customer.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
        return Optional.ofNullable(customer);
    }

    @Override
    public List<Customer> findCustomersByNameAndSurname(String name, String surname) {
        Query query = sessionFactory.getCurrentSession().
                createQuery("SELECT c FROM Customer c WHERE name = :name AND surname = :surname");
        query.setParameter("name", name);
        query.setParameter("surname", surname);
        return query.getResultList();
    }

    @Override
    public List<Customer> findAllCustomersByName(String name) {
        Query query = sessionFactory.getCurrentSession().
                createQuery("SELECT c FROM Customer c WHERE name = :name");
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public List<Customer> findAllCustomersBySurname(String surname) {
        Query query = sessionFactory.getCurrentSession().
                createQuery("SELECT c FROM Customer c WHERE surname = :surname");
        query.setParameter("surname", surname);
        return query.getResultList();
    }

    @Override
    public boolean containsCustomer(Customer customer) {
        return getCustomers().stream()
                .filter(customer1 -> customer1.equals(customer))
                .findAny().isPresent();
    }

    @Override
    public boolean containsId(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT c FROM Customer c WHERE id = :id");
        query.setParameter("id", id);
        return !query.getResultList().isEmpty();
    }
}