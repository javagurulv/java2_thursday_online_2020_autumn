package lv.estore.app.core.validators;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.errors.Errors;
import lv.estore.app.core.request.CoreRequest;
import lv.estore.app.core.request.SearchRequest;
import lv.estore.app.dependency_injection.DIComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static lv.estore.app.core.validators.Utils.isEmptyField;
import static lv.estore.app.core.validators.Utils.isValidDirection;
import static lv.estore.app.core.validators.Utils.isValidOrderBy;
import static lv.estore.app.core.validators.Utils.isValidPagingParameters;

@DIComponent
public class SearchValidator implements iValidator {

    @Override
    public Errors validate(CoreRequest request) {
        List<CoreError> errors = new ArrayList<>();
        SearchRequest searchRequest = (SearchRequest) request;
        errors.add(validateForEmptyField("Name", request.getName()).orElse(null));
        errors.add(validateForEmptyField("OrderBy", request.getOrdering().getOrderBy()).orElse(null));
        errors.add(validateOrderBy(request.getOrdering().getOrderBy()).orElse(null));
        errors.add(validateDirection(request.getOrdering().getOrderBy()).orElse(null));
        errors.add(validatePageSize(searchRequest.getPageSize()).orElse(null));
        errors.add(validatePageNumber(searchRequest.getPageNumber()).orElse(null));
        errors.removeIf(Objects::isNull);
        return new Errors(errors);
    }

    private Optional<CoreError> validateForEmptyField(final String fieldName, final String value){
        return isEmptyField(value)
                ? Optional.of(new CoreError(fieldName,  "Field should not be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validateOrderBy(final String value) {
        return isValidOrderBy(value)
                ? Optional.empty()
                : Optional.of(new CoreError("OrderBy", "field value should be valid 'name'/'price'"));
    }

    private Optional <CoreError> validateDirection(final String value){
        return isValidDirection(value) || isEmptyField(value)
                ? Optional.empty()
                : Optional.of(new CoreError("Direction", "Value should be valid: 'ASC' or 'DESC'"));
    }

    private Optional <CoreError> validatePageNumber(final Integer value){
        return isValidPagingParameters(value)
                ? Optional.empty()
                : Optional.of(new CoreError("PageNumber", "Value should be valid (>=0)"));
    }

    private Optional <CoreError> validatePageSize(final Integer value){
        return isValidPagingParameters(value)
                ? Optional.empty()
                : Optional.of(new CoreError("PageSize", "Value should be valid (>=0)"));
    }
}
