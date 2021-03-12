package dental_clinic.core.responses.user;

import dental_clinic.core.domain.User;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.CoreResponse;

import java.util.List;

public class GetUsersResponse extends CoreResponse {

    private List<User> users;

    public GetUsersResponse(List<User> users) {
        this.users = users;
    }

    public GetUsersResponse(List<CoreError> errors, List<User> users) {
        super(errors);
    }

    public List<User> getUsers() {
        return users;
    }
}
