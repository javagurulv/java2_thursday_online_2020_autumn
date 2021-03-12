package internet_store.application.core.database.jpa;

import internet_store.application.core.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Repository
@Transactional
public interface JpaProductRepository extends JpaRepository<Product, Long> {

    @Modifying
    @Query("UPDATE Product p SET p.productName = :name WHERE p.id =:id")
    Integer changeProductName(@Param("id") Long id, @Param("name") String newName);

    @Modifying
    @Query("DELETE FROM Product WHERE id = :id")
    Integer deleteByProductId(@Param("id") Long id);

    @Modifying
    Integer deleteByProductName(String name);

    @Modifying
    Integer deleteByProductNameAndProductDescriptionAndPrice(String name, String description, BigDecimal price);

    List<Product> findProductByProductName(String name);

    List<Product> findProductByProductDescription(String description);

    List<Product> findProductByProductNameAndProductDescription (String name, String description);

}
