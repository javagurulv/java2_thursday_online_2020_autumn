package internet_store.core.services.product;

import internet_store.core.domain.Product;
import internet_store.core.requests.product.SearchProductByIdRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.product.SearchProductByIdResponse;
import internet_store.core.services.product.validators.SearchByIdRequestValidator;

import internet_store.database.jpa.ProductRepository;
import internet_store.database.product.ProductDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class SearchProductByIdService {

    @Autowired private ProductRepository productDatabase;
    @Autowired private SearchByIdRequestValidator findByIdRequestValidator;

    public SearchProductByIdResponse execute (SearchProductByIdRequest findByIdRequest){

        List<CoreError> errors = findByIdRequestValidator.validate(findByIdRequest);

        if (!errors.isEmpty()){
            return new SearchProductByIdResponse(errors);

        }
        Optional<Product> expectedProduct = productDatabase.searchById(findByIdRequest.getId());

        if (expectedProduct.isEmpty()) {
            errors.add(new CoreError("database", "Database doesn't contain product with id "
                    + findByIdRequest.getId()));
            return new SearchProductByIdResponse(errors);
        }

        return new SearchProductByIdResponse(productDatabase.searchById(findByIdRequest.getId()).get());

    }
}
