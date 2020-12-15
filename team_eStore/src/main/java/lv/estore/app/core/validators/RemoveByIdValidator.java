package lv.estore.app.core.validators;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.errors.Errors;
import lv.estore.app.core.request.CoreRequest;
import lv.estore.app.dependency_injection.DIComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lv.estore.app.core.validators.Utils.isEmptyId;
import static lv.estore.app.core.validators.Utils.isValidId;

@DIComponent
public class RemoveByIdValidator implements iValidator{

    @Override
    public Errors validate(final CoreRequest request) {
        Long id = request.getId();
        List<CoreError> errors = new ArrayList<>();
        errors.add(validateForEmptyField("Id", id).orElse(null));
        errors.add(validateId(id).orElse(null));
        return new Errors(errors);
    }

    private Optional<CoreError> validateForEmptyField(final String fieldName, final Long value){
        return isEmptyId(value)
                ? Optional.of(new CoreError(fieldName,  "Field should not be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validateId(final Long id){
        return isValidId(id)
                ? Optional.empty()
                : Optional.of(new CoreError("Id",  "Value should be valid"));
    }
}
