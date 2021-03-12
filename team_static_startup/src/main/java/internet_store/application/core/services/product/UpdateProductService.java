package internet_store.application.core.services.product;

import internet_store.application.core.database.jpa.JpaProductRepository;
import internet_store.application.core.requests.product.UpdateProductRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.product.UpdateProductResponse;
import internet_store.application.core.services.product.validators.UpdateProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class UpdateProductService {

    @Autowired private JpaProductRepository productRepository;
    @Autowired private UpdateProductValidator validator;

    public UpdateProductResponse execute(UpdateProductRequest request){
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()){
            return new UpdateProductResponse(errors);
        }

        return productRepository.findById(request.getId())
                .map(product -> {
                    product.setName(request.getProductName());
                    product.setDescription(request.getProductDescription());
                    product.setPrice(request.getProductPrice());
                    return new UpdateProductResponse(product);
                })
                .orElseGet(() -> {
                        errors.add(new CoreError("id", "Not fount!"));
                        return new UpdateProductResponse(errors);
                });
    }

}
