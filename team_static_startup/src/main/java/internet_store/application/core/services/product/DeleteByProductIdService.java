package internet_store.application.core.services.product;

import internet_store.application.core.database.jpa.JpaProductRepository;
import internet_store.application.core.requests.product.DeleteByProductIdRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.product.DeleteByProductIdResponse;
import internet_store.application.core.services.product.validators.DeleteByProductIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeleteByProductIdService {

    @Autowired private JpaProductRepository productRepository;
    @Autowired private DeleteByProductIdValidator validator;

    public DeleteByProductIdResponse execute(DeleteByProductIdRequest productIdRequest) {
        List<CoreError> errors = validator.validate(productIdRequest);
        Long id = productIdRequest.getProductId();

        if (!errors.isEmpty()) {
            return new DeleteByProductIdResponse(errors);
        }

        DeleteByProductIdResponse response = new DeleteByProductIdResponse(
                productRepository.deleteByProductId(id) == 1);

        if (!response.isProductRemoved()) {
            errors.add(new CoreError("id", "Not found!"));
            return new DeleteByProductIdResponse(errors);
        }

        return response;
    }

}
