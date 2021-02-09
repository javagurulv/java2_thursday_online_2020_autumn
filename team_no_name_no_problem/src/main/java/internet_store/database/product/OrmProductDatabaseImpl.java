package internet_store.database.product;

import internet_store.core.domain.Product;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Component
@Transactional
public class OrmProductDatabaseImpl implements ProductDatabase{

    @Autowired private SessionFactory sessionFactory;

    @Override
    public List<Product> getProducts() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT p FROM Product p", Product.class)
                .getResultList();
    }

    @Override
    public void add(Product product) {
        sessionFactory.getCurrentSession().save(product);
    }

    @Override
    public boolean deleteById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("DELETE Product WHERE id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean deleteAllByTitle(String title) {
        Query query = sessionFactory.getCurrentSession().createQuery("DELETE Product WHERE title = :title");
        query.setParameter("title", title);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean deleteAllByDescription(String description) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("DELETE Product WHERE description = :description");
        query.setParameter("description", description);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean deleteAllByPriceRange(Integer startPrice, Integer endPrice) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("DELETE Product WHERE price >= :startPrice and price <= :endPrice ");
        query.setParameter("startPrice", startPrice);
        query.setParameter("endPrice", endPrice);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean deleteAllByTitleAndDescription(String title, String description) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("DELETE Product WHERE title = :title AND description = :description");
        query.setParameter("title", title);
        query.setParameter("description", description);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean deleteAllByDescriptionAndPriceRange(String description, Integer startPrice, Integer endPrice) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("DELETE Product WHERE description = :description " +
                        "  AND price >= :startPrice and price <= :endPrice ");
        query.setParameter("description", description);
        query.setParameter("startPrice", startPrice);
        query.setParameter("endPrice", endPrice);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean deleteAllByTitleAndPriceRange(String title, Integer startPrice, Integer endPrice) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("DELETE Product WHERE title = :title " +
                        "  AND price >= :startPrice and price <= :endPrice ");
        query.setParameter("title", title);
        query.setParameter("startPrice", startPrice);
        query.setParameter("endPrice", endPrice);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean deleteAllByTitleAndDescriptionAndPriceRange(String title, String description, Integer startPrice, Integer endPrice) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("DELETE Product WHERE title = :title " +
                        "  AND description =: description AND price >= :startPrice and price <= :endPrice ");
        query.setParameter("title", title);
        query.setParameter("description", description);
        query.setParameter("startPrice", startPrice);
        query.setParameter("endPrice", endPrice);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public Optional<Product> findById(Long id) {
        Product product = (Product) sessionFactory.getCurrentSession().createCriteria(Product.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
        return Optional.ofNullable(product);
    }

    @Override
    public void changeTitle(Long id, String newTitle) {
        Product product = new Product();
        product.getId();
        product.setTitle(newTitle);
        sessionFactory.getCurrentSession().update(product);

    }

    @Override
    public void changeDescription(Long id, String newDescription) {
        Product product = new Product();
        product.setDescription(newDescription);
        sessionFactory.getCurrentSession().update(product);
    }

    @Override
    public void changePrice(Long id, Integer newPrice) {
        Product product = new Product();
        product.setPrice(newPrice);
        sessionFactory.getCurrentSession().update(product);
    }

    @Override
    public List<Product> findAllByTitle(String title) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("SELECT p FROM Product p WHERE title = :title");
        query.setParameter("title", title);
        return query.getResultList();
    }

    @Override
    public List<Product> findAllByDescription(String description) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("SELECT p FROM Product p WHERE description = :description");
        query.setParameter("description", description);
        return query.getResultList();
    }

    @Override
    public List<Product> findAllByPriceRange(Integer startPrice, Integer endPrice) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("SELECT p FROM Product p WHERE description = :description");
        query.setParameter("startPrice", startPrice);
        query.setParameter("endPrice", endPrice);
        return query.getResultList();
    }

    @Override
    public List<Product> findAllByTitleAndDescriptionAndPriceRange(String title, String description, Integer startPrice, Integer endPrice) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("SELECT p FROM Product p WHERE title = :title " +
                        "  AND description =: description AND price >= :startPrice and price <= :endPrice ");
        query.setParameter("title", title);
        query.setParameter("description", description);
        query.setParameter("startPrice", startPrice);
        query.setParameter("endPrice", endPrice);
        return query.getResultList();
    }

    @Override
    public List<Product> findAllByTitleAndDescription(String title, String description) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("SELECT p FROM Product p WHERE title = :title AND description = :description");
        query.setParameter("title", title);
        query.setParameter("description", description);
        return query.getResultList();
    }

    @Override
    public List<Product> findAllByTitleAndPriceRange(String title, Integer startPrice, Integer endPrice) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("SELECT p FROM Product p WHERE description = :description " +
                        "  AND description =: description AND price >= :startPrice and price <= :endPrice ");
        query.setParameter("title", title);
        query.setParameter("startPrice", startPrice);
        query.setParameter("endPrice", endPrice);
        return query.getResultList();
    }

    @Override
    public List<Product> findAllByDescriptionAndPriceRange(String description, Integer startPrice, Integer endPrice) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("SELECT p FROM Product p WHERE description = :description " +
                        "  AND description =: description AND price >= :startPrice and price <= :endPrice ");
        query.setParameter("description", description);
        query.setParameter("startPrice", startPrice);
        query.setParameter("endPrice", endPrice);
        return query.getResultList();
    }

    @Override
    public boolean containsProduct(Product product) {
        return sessionFactory.getCurrentSession().contains(product);
    }

    @Override
    public boolean containsId(Long id) {

        return true;
    }

    @Override
    public boolean containsTitle(String title) {
        return true;
    }

    @Override
    public boolean containsDescription(String description) {
        return true;
    }

    @Override
    public boolean containsPrice(Integer price) {
        return false;
    }

    @Override
    public boolean containsTitleAndDescription(String title, String description) {
        return false;
    }
}
