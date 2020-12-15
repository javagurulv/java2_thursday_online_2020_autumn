package lv.estore.app.core.validators;

import lv.estore.app.core.errors.Errors;
import lv.estore.app.core.request.CoreRequest;

public interface iValidator {
    Errors validate(CoreRequest request);
}
