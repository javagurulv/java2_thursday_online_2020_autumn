package internet_store.application.core.database.shopping_cart_item;

import internet_store.application.core.database.product.ProductRepository;
import internet_store.application.core.database.shopping_cart.ShoppingCartRepository;
import internet_store.application.core.domain.Product;
import internet_store.application.core.domain.ProductShoppingCart;
import internet_store.application.core.domain.ShoppingCart;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class ORMShoppingCartItemRepository implements ShoppingCartItemRepository {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    @Autowired
    ProductRepository productRepository;
    private final SessionFactory sessionFactory;

    public ORMShoppingCartItemRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Long add(Long shoppingCartId, Long productId, int quantity) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartId);
        Product product = productRepository.findById(productId).get();
        ProductShoppingCart productShoppingCart = new ProductShoppingCart(shoppingCart, product, quantity);
        return (Long) sessionFactory.getCurrentSession().save(productShoppingCart);
    }

    @Override
    public ProductShoppingCart findById(Long id) {
        return sessionFactory.getCurrentSession().find(ProductShoppingCart.class, id);
    }

    @Override
    public List<ProductShoppingCart> findAll() {
        return sessionFactory.getCurrentSession().createQuery("FROM ProductShoppingCart").getResultList();
    }

}
