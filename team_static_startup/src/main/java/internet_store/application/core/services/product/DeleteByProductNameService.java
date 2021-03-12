package internet_store.application.core.services.product;

import internet_store.application.core.database.jpa.JpaProductRepository;
import internet_store.application.core.requests.product.DeleteByProductNameRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.product.DeleteByProductNameResponse;
import internet_store.application.core.services.product.validators.DeleteByProductNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeleteByProductNameService {

    @Autowired private JpaProductRepository productRepository;
    @Autowired private DeleteByProductNameValidator deleteByNameValidator;

    public DeleteByProductNameResponse execute(DeleteByProductNameRequest productNameRequest) {
        List<CoreError> errors = deleteByNameValidator.validate(productNameRequest);
        if (!errors.isEmpty()) {
            return new DeleteByProductNameResponse(errors);
        }

        Integer removedProductsQty = productRepository.deleteByProductName(productNameRequest.getProductName());
        return new DeleteByProductNameResponse(removedProductsQty == 1);
    }

}
