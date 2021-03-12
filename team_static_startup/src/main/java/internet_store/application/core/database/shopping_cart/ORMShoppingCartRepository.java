package internet_store.application.core.database.shopping_cart;

import internet_store.application.core.database.customer.CustomerRepository;
import internet_store.application.core.domain.Customer;
import internet_store.application.core.domain.ShoppingCart;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

//@Component
//@Transactional
public class ORMShoppingCartRepository implements ShoppingCartRepository {

    @Autowired
    private CustomerRepository customerRepository;

    private final SessionFactory sessionFactory;

    public ORMShoppingCartRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Long add(Long customerId) {
        Customer foundCustomer = customerRepository.findByCustomerId(customerId).get();
        ShoppingCart shoppingCart = new ShoppingCart(foundCustomer, true);
        return (Long) sessionFactory.getCurrentSession().save(shoppingCart);
    }

    @Override
    public ShoppingCart findById(Long id) {
        return sessionFactory.getCurrentSession().find(ShoppingCart.class, id);
    }

    @Override
    public List<ShoppingCart> findAll() {
        return sessionFactory.getCurrentSession().createQuery("FROM ShoppingCart").getResultList();
    }

}