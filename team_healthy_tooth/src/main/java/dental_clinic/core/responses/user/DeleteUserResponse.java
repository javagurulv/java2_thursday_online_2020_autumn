package dental_clinic.core.responses.user;

import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.CoreResponse;

import java.util.List;

public class DeleteUserResponse extends CoreResponse {

    private Long id;

    public DeleteUserResponse(Long id) {
        this.id = id;
    }

    public DeleteUserResponse(List<CoreError> errors) {
        super(errors);
    }

    public Long getId() {
        return id;
    }
}
