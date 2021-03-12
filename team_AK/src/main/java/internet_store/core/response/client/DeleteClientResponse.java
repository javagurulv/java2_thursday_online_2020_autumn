package internet_store.core.response.client;

import internet_store.core.core_error.CoreError;
import internet_store.core.core_error.CoreErrorResponse;

import java.util.List;

public class DeleteClientResponse extends CoreErrorResponse {

    public DeleteClientResponse(List<CoreError> errors) {
        super(errors);
    }
}