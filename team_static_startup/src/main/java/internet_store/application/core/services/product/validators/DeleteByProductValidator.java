package internet_store.application.core.services.product.validators;

import internet_store.application.core.requests.product.DeleteByProductRequest;
import internet_store.application.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DeleteByProductValidator {

    public List<CoreError> validate(DeleteByProductRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateProductName(request).ifPresent(errors::add);
        validateProductDescription(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateProductName(DeleteByProductRequest request) {
        return (request.getProductName() == null || request.getProductName().isBlank()
                ? Optional.of(new CoreError("Name", "must not be empty"))
                : Optional.empty());
    }

    private Optional<CoreError> validateProductDescription(DeleteByProductRequest request) {
        return (request.getProductDescription() == null || request.getProductDescription().isBlank()
                ? Optional.of(new CoreError("Description", "must not be empty"))
                : Optional.empty());
    }

}
