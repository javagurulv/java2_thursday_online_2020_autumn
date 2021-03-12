package internet_store.database.jpa;

import internet_store.core.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAll();

    void deleteById(Long id);

    boolean deleteAllByTitle(String title);

    boolean deleteAllByDescription(String description);

    @Query("DELETE Product p WHERE p.price <= :startPrice and p.price >= :endPrice")
    boolean deleteAllByPriceRange(@Param("startPrice") Integer startPrice,@Param("endPrice") Integer endPrice);

    boolean deleteAllByTitleAndDescription(String title, String description);

    @Query("DELETE Product p WHERE p.description = :description and p.price <= :startPrice and p.price >= :endPrice")
    boolean deleteAllByDescriptionAndPriceRange(@Param("description") String description,@Param("startPrice") Integer startPrice,@Param("endPrice") Integer endPrice);

    @Query("DELETE Product p WHERE p.title = :title and p.price <= :startPrice and p.price >= :endPrice")
    boolean deleteAllByTitleAndPriceRange(@Param("title") String title,@Param("startPrice") Integer startPrice,@Param("endPrice") Integer endPrice);

    @Query("DELETE Product p WHERE p.title = :title and p.description = :description and p.price <= :startPrice and p.price >= :endPrice")
    boolean deleteAllByTitleAndDescriptionAndPriceRange(@Param("title") String title,@Param("description") String description,@Param("startPrice") Integer startPrice,@Param("endPrice") Integer endPrice);

    Optional<Product> searchById(Long id);

    @Query("UPDATE Product p set p.title = :newTitle WHERE p.id = :id")
    void changeTitle(@Param("id") Long id, @Param("newTitle") String newTitle);

    @Query("UPDATE Product p set p.description = :newDescription WHERE p.id = :id")
    void changeDescription(@Param("id") Long id,@Param("newDescription") String newDescription);

    @Query("UPDATE Product p set p.price = :newPrice WHERE p.id = :id")
    void changePrice(@Param("id") Long id,@Param("newPrice") Integer newPrice);

    List<Product> searchAllByTitle(String title);

    List<Product> searchAllByDescription(String description);

    @Query("SELECT p FROM Product p WHERE p.price <= :startPrice and p.price >= :endPrice")
    List<Product> searchAllByPriceRange(@Param("startPrice") Integer startPrice,@Param("endPrice") Integer endPrice);

    @Query("SELECT p FROM Product p WHERE p.title = :title and p.description = :description and p.price <= :startPrice and p.price >= :endPrice")
    List<Product> searchAllByTitleAndDescriptionAndPriceRange(@Param("title") String title,@Param("description") String description,@Param("startPrice") Integer startPrice,@Param("endPrice") Integer endPrice);

    List<Product> searchAllByTitleAndDescription(String title, String description);

    @Query("SELECT p FROM Product p WHERE p.title = :title and p.price <= :startPrice and p.price >= :endPrice")
    List<Product> searchAllByTitleAndPriceRange(String title, Integer startPrice, Integer endPrice);

    @Query("SELECT p FROM Product p WHERE p.description = :description and p.price <= :startPrice and p.price >= endPrice")
    List<Product> searchAllByDescriptionAndPriceRange(String description, Integer startPrice, Integer endPrice);

    boolean existsById(Long id);

}