package internet_store.application.core.responses.product;

import java.util.List;

public class ChangeProductNameResponse extends CoreResponse {

    private boolean nameChanged;

    public ChangeProductNameResponse(boolean nameChanged) {
        this.nameChanged = nameChanged;
    }

    public ChangeProductNameResponse(List<CoreError> errors) {
        super(errors);
    }
    public boolean isNameChanged() {
        return nameChanged;
    }

}
