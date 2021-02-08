package lv.tssweb.estore.core.services.customer.validators;

import lv.tssweb.estore.core.requests.customer.AddCustomerRequest;
import lv.tssweb.estore.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddCustomerValidator {

    public List<CoreError> validate(AddCustomerRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateCustomerFirstName(request).ifPresent(errors::add);
        validateCustomerSecondName(request).ifPresent(errors::add);
        validateCustomerPhone(request).ifPresent(errors::add);
        validateCustomerEMail(request).ifPresent(errors::add);
        validateCustomerAddress(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateCustomerFirstName(AddCustomerRequest request) {
        return (request.getCustomerFirstName() == null || request.getCustomerFirstName().isBlank()
                ? Optional.of(new CoreError("First Name", "must not be empty"))
                : Optional.empty());
    }

    private Optional<CoreError> validateCustomerSecondName(AddCustomerRequest request) {
        return (request.getCustomerSecondName() == null || request.getCustomerSecondName().isBlank()
                ? Optional.of(new CoreError("Second Name", "must not be empty"))
                : Optional.empty());
    }

    private Optional<CoreError> validateCustomerPhone(AddCustomerRequest request) {
        return (request.getCustomerPhone() == null || request.getCustomerPhone().isBlank()
                ? Optional.of(new CoreError("Phone", "must not be empty"))
                : Optional.empty());
    }

    private Optional<CoreError> validateCustomerEMail(AddCustomerRequest request) {
        return (request.getCustomerEMail() == null || request.getCustomerEMail().isBlank()
                ? Optional.of(new CoreError("e-mail", "must not be empty"))
                : Optional.empty());
    }

    private Optional<CoreError> validateCustomerAddress(AddCustomerRequest request) {
        return (request.getCustomerAddress() == null || request.getCustomerAddress().isBlank()
                ? Optional.of(new CoreError("Address", "must not be empty"))
                : Optional.empty());
    }

}
