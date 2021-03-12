package internet_store.application.core.database.shopping_cart_item;

import internet_store.application.core.database.product.ProductRepository;
import internet_store.application.core.database.shopping_cart.ShoppingCartRepository;
import internet_store.application.core.domain.Product;
import internet_store.application.core.domain.ProductShoppingCart;
import internet_store.application.core.domain.ShoppingCart;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

//@Component
//@Transactional
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
    public Long add(Long shoppingCartId, Long productId, Long quantity) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartId);
        Product product = productRepository.findById(productId).get();
        ProductShoppingCart productShoppingCart = new ProductShoppingCart(shoppingCart, product, quantity);
        return (Long) sessionFactory.getCurrentSession().save(productShoppingCart);
    }

    @Override
    public Optional<ProductShoppingCart> findById(Long id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().find(ProductShoppingCart.class, id));
    }

    @Override
    public List<ProductShoppingCart> findAll() {
        CriteriaQuery<ProductShoppingCart> query = sessionFactory.getCriteriaBuilder().createQuery(ProductShoppingCart.class);
        query.from(ProductShoppingCart.class);
        return sessionFactory.getCurrentSession().createQuery(query).getResultList();
        //return sessionFactory.getCurrentSession().createQuery("FROM ProductShoppingCart").getResultList();
    }

    @Override
    public boolean deleteById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("DELETE ProductShoppingCart WHERE id = :id");
        query.setParameter("id", id);
        return query.executeUpdate() == 0;
    }

}