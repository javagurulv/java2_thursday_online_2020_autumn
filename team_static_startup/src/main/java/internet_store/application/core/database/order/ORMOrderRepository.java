package internet_store.application.core.database.order;

import internet_store.application.core.database.shopping_cart.ShoppingCartRepository;
import internet_store.application.core.domain.Order;
import internet_store.application.core.domain.ShoppingCart;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

//@Component
//@Transactional
public class ORMOrderRepository implements OrderRepository{

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    private final SessionFactory sessionFactory;

    public ORMOrderRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Long add(Order order) {
        return (Long) sessionFactory.getCurrentSession().save(order);
    }

    @Override
    public Order findById(Long id) {
        return sessionFactory.getCurrentSession().find(Order.class, id);
    }

    @Override
    public List<Order> findAll() {
        return sessionFactory.getCurrentSession().createQuery("FROM Order").getResultList();
    }

}