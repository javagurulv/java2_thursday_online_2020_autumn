package internet_store.core.persistence;

import internet_store.core.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByTitle(String title);

    boolean existsByTitle(String title);

    @Query(value = "SELECT * FROM product limit ? offset ?", nativeQuery = true)
    List<Product> getLimitsProductsRecords(int limit, int offset);

    @Query(value = "SELECT * FROM product WHERE category=1 limit ? offset ?", nativeQuery = true)
    List<Product> getLimitsProductsCategoryOne(int limit, int offset);

    @Query(value = "SELECT COUNT(*) FROM product WHERE category=1", nativeQuery = true)
    Long getCategoryOneCount();

    @Query(value = "SELECT * FROM product WHERE category=2 limit ? offset ?", nativeQuery = true)
    List<Product> getLimitsProductsCategoryTwo(int limit, int offset);

    @Query(value = "SELECT COUNT(*) FROM product WHERE category=2", nativeQuery = true)
    Long getCategoryTwoCount();

    @Query(value = "SELECT * FROM product WHERE category=3 limit ? offset ?", nativeQuery = true)
    List<Product> getLimitsProductsCategoryThree(int limit, int offset);

    @Query(value = "SELECT COUNT(*) FROM product WHERE category=3", nativeQuery = true)
    Long getCategoryThreeCount();

    @Query(value = "SELECT COUNT(*) FROM product WHERE title LIKE :productTitle%", nativeQuery = true)
    Long searchByProductTitleCount(@Param("productTitle") String productTitle);

    @Query(value = "SELECT * FROM product WHERE title LIKE :productTitle% limit :limit offset :offset", nativeQuery = true)
    List<Product> searchByTitle(@Param("productTitle") String productTitle,@Param("limit") int limit,
                                @Param("offset") int offset);

    @Query(value = "SELECT COUNT(*) FROM product WHERE price LIKE :productPrice%", nativeQuery = true)
    Long searchByProductPriceCount(@Param("productPrice") String productTitle);

    @Query(value = "SELECT * FROM product WHERE price LIKE :productPrice% limit :limit offset :offset", nativeQuery = true)
    List<Product> searchByPrice(@Param("productPrice") String productTitle,@Param("limit") int limit,
                                @Param("offset") int offset);
}