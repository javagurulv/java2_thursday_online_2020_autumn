package internet_store.application.core.services.product;


import internet_store.application.core.database.jpa.JpaProductRepository;
import internet_store.application.core.requests.product.DeleteProductRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.product.DeleteProductResponse;
import internet_store.application.core.services.product.validators.DeleteProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class DeleteProductService {

    @Autowired private JpaProductRepository productRepository;
    @Autowired private DeleteProductValidator validator;

    public DeleteProductResponse execute(DeleteProductRequest request){
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteProductResponse(errors);
        }
        return productRepository.findById(request.getId())
                .map(product -> {
                    productRepository.deleteById(request.getId());
                    return new DeleteProductResponse(product);
                })
                .orElseGet(() -> {
                    errors.add(new CoreError("id", "Nor found!"));
                    return new DeleteProductResponse(errors);
                });
    }
}
