package lv.estore.app.core.validators;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.errors.Errors;
import lv.estore.app.core.request.CoreRequest;
import lv.estore.app.dependency_injection.DIComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static lv.estore.app.core.validators.Utils.isEmptyField;

@DIComponent
public class FindByNameValidator implements iValidator {

    @Override
    public Errors validate(final CoreRequest request) {
        List<CoreError> errors = new ArrayList<>();
        errors.add(validateForEmptyField("Name", request.getName()).orElse(null));
        errors.removeIf(Objects::isNull);
        return new Errors(errors);
    }

    private Optional<CoreError> validateForEmptyField(final String fieldName, final String value){
        return isEmptyField(value)
                ? Optional.of(new CoreError(fieldName,  "Field should not be empty"))
                : Optional.empty();
    }
}
