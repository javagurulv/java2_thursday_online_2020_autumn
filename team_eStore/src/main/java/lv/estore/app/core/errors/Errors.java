package lv.estore.app.core.errors;

import java.util.List;

public class Errors {
    private List<CoreError> errors;

    public Errors (List<CoreError> errors) {
        this.errors = errors;
    }

    public List<CoreError> getAllErrors(){
        return this.errors;
    }
}
