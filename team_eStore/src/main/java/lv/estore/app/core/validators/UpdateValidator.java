package lv.estore.app.core.validators;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.errors.Errors;
import lv.estore.app.core.request.CoreRequest;
import lv.estore.app.dependency_injection.DIComponent;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lv.estore.app.core.validators.Utils.isEmptyField;
import static lv.estore.app.core.validators.Utils.isEmptyPrice;
import static lv.estore.app.core.validators.Utils.isValidPrice;

@DIComponent
public class UpdateValidator implements iValidator {

    @Override
    public Errors validate(final CoreRequest request) {
        List<CoreError> errors = new ArrayList<>();
        errors.add(validateForEmptyField("Name", request.getName()).orElse(null));
        errors.add(validateForEmptyField("Description", request.getDescription()).orElse(null));
        errors.add(validateForEmptyFieldPrice(request.getPrice()).orElse(null));
        errors.add(validatePrice(request.getPrice()).orElse(null));
        return new Errors(errors);
    }

    private Optional<CoreError> validateForEmptyField(final String fieldName, final String value){
        return isEmptyField(value)
                ? Optional.of(new CoreError(fieldName,  "Field should not be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validateForEmptyFieldPrice(final BigDecimal value){
        return isEmptyPrice(value)
                ? Optional.of(new CoreError("Price",  "Field should not be empty or '0'"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePrice(final BigDecimal price) {
        return isValidPrice(price)
                ? Optional.empty()
                : Optional.of(new CoreError("Price", "Field should be valid"));
    }
}
