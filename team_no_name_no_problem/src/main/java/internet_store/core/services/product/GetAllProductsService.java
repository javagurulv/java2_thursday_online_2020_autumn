package internet_store.core.services.product;

import internet_store.core.requests.product.GetProductsRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.product.GetProductsResponse;
import internet_store.core.services.product.validators.GetAllProductsValidator;

import internet_store.database.jpa.ProductRepository;
import internet_store.database.product.ProductDatabase;
import internet_store.core.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class GetAllProductsService {

    @Autowired private ProductRepository productDatabase;
    @Autowired private GetAllProductsValidator getAllProductsValidator;

    public GetProductsResponse execute(GetProductsRequest getProductsRequest){
        List <CoreError> errors = getAllProductsValidator.validate(getProductsRequest);

        if (productDatabase.findAll().isEmpty()){
            errors.add(new CoreError("database", "Database is empty"));
            return new GetProductsResponse(errors, new ArrayList<>());
        }
        List<Product>products = productDatabase.findAll();
        return new GetProductsResponse(products);
    }
}