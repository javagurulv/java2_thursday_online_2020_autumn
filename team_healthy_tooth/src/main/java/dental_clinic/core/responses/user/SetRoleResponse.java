package dental_clinic.core.responses.user;

import dental_clinic.core.domain.Role;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.CoreResponse;

import java.util.List;

public class SetRoleResponse extends CoreResponse {

    private Role role;

    public SetRoleResponse(Role role) {
        this.role = role;
    }

    public SetRoleResponse(List<CoreError> errors) {
        super(errors);
    }

    public Role getRole() {
        return role;
    }
}
