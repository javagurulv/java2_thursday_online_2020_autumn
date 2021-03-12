package internet_store.database.order;

import internet_store.core.domain.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

//@Component
//@Transactional
public class OrmOrderRepository implements OrderItemDatabase{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();
        List<ShoppingCart> shoppingCarts = sessionFactory.getCurrentSession()
                .createQuery("SELECT s FROM ShoppingCart s", ShoppingCart.class)
                .getResultList();
        for (ShoppingCart shoppingCart : shoppingCarts){
            List<ShoppingCartItem> shoppingCartItems =  sessionFactory.getCurrentSession()
                    .createQuery("SELECT s FROM ShoppingCartItem s WHERE shopping_cart_id = " +
                            shoppingCart.getId(), ShoppingCartItem.class)
                    .getResultList();

            Order order = new Order(shoppingCart.getCustomer(), saveShoppingCartToMap(shoppingCartItems), shoppingCart.getSumTotal());
            orders.add(order);
        }
        return orders;
    };

    @Override
    public void addOrder(Order order) {
        ShoppingCart shoppingCart = new ShoppingCart(order.getCustomer(), order.getSum());
        sessionFactory.getCurrentSession().save(shoppingCart);
        Map<Product, Integer> shoppingCartItemMap = order.getShoppingCart();
        for (Product product : shoppingCartItemMap.keySet()) {
            ShoppingCartItem shoppingCartItem = new ShoppingCartItem(shoppingCart, product,
                    shoppingCartItemMap.get(product), product.getPrice());
            sessionFactory.getCurrentSession().save(shoppingCartItem);
        }
    }

    private Map<Product, Integer> saveShoppingCartToMap(List<ShoppingCartItem> items){
        Map< Product, Integer> result = new HashMap<>();
        for (ShoppingCartItem shoppingCartItem : items){
            result.put(shoppingCartItem.getProduct(), shoppingCartItem.getQuantity());
        }
        return result;
    }

}
