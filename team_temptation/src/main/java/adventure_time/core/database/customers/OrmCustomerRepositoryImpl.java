package adventure_time.core.database.customers;

import adventure_time.core.domain.Customers;

import adventure_time.core.requests.Paging;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Component
public class OrmCustomerRepositoryImpl implements CustomerRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean add(Customers customer) {
        try {
            sessionFactory.getCurrentSession()
                    .save(customer);
        } catch (ConstraintViolationException exception) {
            sessionFactory.getCurrentSession().clear();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Long id) {
        return sessionFactory.getCurrentSession()
                .createQuery("delete Customers where id = :id")
                .setParameter("id", id)
                .executeUpdate() == 1;
    }

    @Override
    public Optional<Customers> findById(Long id) {
        List<Customers> result = sessionFactory.getCurrentSession()
                .createQuery("from Customers as c where c.id = :id", Customers.class)
                .setParameter("id", id)
                .getResultList();
        if (result.isEmpty()) return Optional.empty();
        return Optional.of(result.get(0));
    }

    @Override
    public Optional<Customers> findByEmail(String email) {
        List<Customers> result = sessionFactory.getCurrentSession()
                .createQuery("from Customers as c where c.customerEmail = :email", Customers.class)
                .setParameter("email", email)
                .getResultList();
        if (result.isEmpty()) return Optional.empty();
        return Optional.of(result.get(0));
    }

    @Override
    public boolean updateCustomer(Customers customer, Long id) {
        sessionFactory.getCurrentSession()
                .createQuery("UPDATE Customers SET " +
                        "name =: name, " +
                        "email =: email, " +
                        "phone =: phone, " +
                        "password =: password " +
                        "WHERE id = :id")
                .setParameter("id", id)
                .setParameter("name", customer.getCustomerName())
                .setParameter("email", customer.getCustomerEmail())
                .setParameter("phone", customer.getCustomerPhone())
                .setParameter("password", customer.getCustomerPassword())
                .executeUpdate();

        return true;
    }

    @Override
    public List<Customers> findCustomers(String query, Paging paging) {
        return sessionFactory.getCurrentSession()
                .createQuery(query, Customers.class)
                .getResultList()
                .stream()
                .skip((paging.getPageNumber()-1)*paging.getPageSize())
                .limit(paging.getPageSize())
                .collect(Collectors.toList());
    }


}
