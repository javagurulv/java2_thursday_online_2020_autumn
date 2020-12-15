package lv.estore.app.core.responses;

import lv.estore.app.core.errors.Errors;

public abstract class CoreResponse {
    private Errors errors;

    public CoreResponse() { }

    public CoreResponse(Errors errors) {
        this.errors = errors;
    }

    public Errors getErrors() {
        return errors;
    }

    public boolean hasErrors() {
        return errors != null && !errors.getAllErrors().isEmpty();
    }
}
