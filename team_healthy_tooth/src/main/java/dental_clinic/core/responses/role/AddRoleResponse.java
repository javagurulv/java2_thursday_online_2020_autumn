package dental_clinic.core.responses.role;

import dental_clinic.core.domain.Role;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.CoreResponse;

import java.util.List;

public class AddRoleResponse extends CoreResponse {

    private Role role;

    public AddRoleResponse(Role role) {
        this.role = role;
    }

    public AddRoleResponse(List<CoreError> errors) {
        super(errors);
    }

    public Role getRole() {
        return role;
    }
}
