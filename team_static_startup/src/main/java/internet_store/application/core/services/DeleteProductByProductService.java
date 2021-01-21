package internet_store.application.core.services;

import internet_store.application.core.database.ProductDatabase;
import internet_store.application.core.domain.Product;
import internet_store.application.core.requests.DeleteByProductRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.DeleteByProductResponse;
import internet_store.application.core.services.validators.DeleteByProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeleteProductByProductService {

    @Autowired
    private ProductDatabase database;
    @Autowired
    private DeleteByProductValidator deleteByProductValidator;

    public DeleteByProductResponse execute(DeleteByProductRequest request) {
        List<CoreError> errors = deleteByProductValidator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteByProductResponse(errors);
        }
        Product productToDelete = new Product(request.getProductName(), request.getProductDescription(), request.getProductPrice());
        boolean deleted = database.delete(productToDelete);
        if (deleted) {
            return new DeleteByProductResponse(productToDelete);
        } else return new DeleteByProductResponse((Product) null);
    }

}
