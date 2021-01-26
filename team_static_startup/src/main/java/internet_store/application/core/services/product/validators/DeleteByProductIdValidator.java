package internet_store.application.core.services.product.validators;

import internet_store.application.core.requests.product.DeleteByProductIdRequest;
import internet_store.application.core.responses.product.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteByProductIdValidator {

    public List<CoreError> validate (DeleteByProductIdRequest request){
        List<CoreError> errors = new ArrayList<>();
        if (request.getProductId() == null) {
            errors.add(new CoreError("Product ID", "Should not be empty."));
        } return errors;
    }
}
