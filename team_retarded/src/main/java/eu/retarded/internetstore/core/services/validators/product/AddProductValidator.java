package eu.retarded.internetstore.core.services.validators.product;

import eu.retarded.internetstore.core.requests.product.AddProductRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddProductValidator {

    public List<CoreError> validate(AddProductRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateName(request).ifPresent(errors::add);
        validateDescription(request).ifPresent(errors::add);
        validatePrice(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateName(AddProductRequest request) {
        if (request.getName() == null || request.getName().isEmpty()) {
            return Optional.of(new CoreError("Name", "Must not be empty!"));
        }

        if (request.getName().length() < 3 || request.getName().length() > 100) {
            return Optional.of(new CoreError("Name", "Must be between 3 and 100 characters"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateDescription(AddProductRequest request) {
        if (request.getDescription() == null || request.getDescription().isEmpty()) {
            return Optional.of(new CoreError("Description", "Must not be empty!"));
        }

        if (request.getDescription().length() < 10 || request.getDescription().length() > 2000) {
            return Optional.of(new CoreError("Description", "Must be between 10 and 2000 characters"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validatePrice(AddProductRequest request) {

        return (request.getPrice() <= 0 || request.getPrice() >= 1000000000)
                ? Optional.of(new CoreError("Price", "Must be between 0 and 1000000000"))
                : Optional.empty();
    }
}
