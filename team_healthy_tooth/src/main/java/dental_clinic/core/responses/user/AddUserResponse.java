package dental_clinic.core.responses.user;

import dental_clinic.core.domain.User;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.CoreResponse;

import java.util.List;

public class AddUserResponse extends CoreResponse {

    private User user;

    public AddUserResponse(User user) {
        this.user = user;
    }

    public AddUserResponse(List<CoreError> errors) {
        super(errors);
    }

    public User getUser() {
        return user;
    }
}
