package internet_store.application.core.responses.product;

import java.util.List;

abstract class CoreResponse {

    List<CoreError> errors;

    public CoreResponse() {
    }

    public CoreResponse(List<CoreError> errors) {
        this.errors = errors;
    }

    public List<CoreError> getErrors() {
        return errors;
    }

    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }
}
