package internet_store.core.services.product;

import internet_store.core.domain.Product;
import internet_store.core.requests.product.ChangeProductRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.product.ChangeProductResponse;
import internet_store.core.services.product.validators.ChangeProductValidator;

import internet_store.database.jpa.ProductRepository;
import internet_store.database.product.ProductDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class ChangeProductService {

    @Autowired private ProductRepository productDatabase;
    @Autowired private ChangeProductValidator changeProductValidator;

    public ChangeProductResponse execute (ChangeProductRequest changeProductRequest){
        List<CoreError> errors = changeProductValidator.validate(changeProductRequest);

        if (!errors.isEmpty()){
            return new ChangeProductResponse(errors);
        }

        if (!productDatabase.existsById(changeProductRequest.getId())){
            errors.add(new CoreError("database", "Database doesn't contain product with id " +
                    changeProductRequest.getId()));
            return new ChangeProductResponse(errors);
        }

        if (changeProductRequest.getTitle() != null && !changeProductRequest.getTitle().isEmpty()){
            productDatabase.changeTitle(changeProductRequest.getId(), changeProductRequest.getTitle());
        }

        if (changeProductRequest.getDescription() != null && !changeProductRequest.getDescription().isEmpty()){
            productDatabase.changeDescription(changeProductRequest.getId(), changeProductRequest.getDescription());
        }

        if (changeProductRequest.getPrice() != null){
            productDatabase.changePrice(changeProductRequest.getId(), changeProductRequest.getPrice());
        }

        return  new ChangeProductResponse(changeProductRequest.getId());
    }

    private Optional<Product> databaseContainsProductWithId(long id){
        return productDatabase.findAll().stream()
                .filter(product -> product.getId() == id)
                .findAny();
    }
}
