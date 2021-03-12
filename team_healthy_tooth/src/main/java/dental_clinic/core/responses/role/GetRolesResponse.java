package dental_clinic.core.responses.role;

import dental_clinic.core.domain.Role;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.CoreResponse;

import java.util.List;

public class GetRolesResponse extends CoreResponse {

    private List<Role> roles;

    public GetRolesResponse(List<Role> roles) {
        this.roles = roles;
    }

    public GetRolesResponse(List<CoreError> errors, List<Role> roles) {
        super(errors);
    }

    public List<Role> getRoles() {
        return roles;
    }
}
