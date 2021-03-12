package dental_clinic.core.responses.user;

import dental_clinic.core.domain.Role;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.CoreResponse;

import java.util.List;

public class UsersRoleResponse extends CoreResponse {

    private Role role;

    public UsersRoleResponse(Role role) {
        this.role = role;
    }

    public UsersRoleResponse(List<CoreError> errors) {
        super(errors);
    }

    public Role getRole() {
        return role;
    }
}
