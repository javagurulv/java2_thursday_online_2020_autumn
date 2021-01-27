package estore.database;

import estore.domain.ProductCategory;

import java.util.List;

public interface ProductCategoryRepository {

    List<ProductCategory> getDatabase();
    boolean addNewCategory(ProductCategory category);

}
