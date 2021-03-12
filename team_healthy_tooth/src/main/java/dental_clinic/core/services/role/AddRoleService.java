package dental_clinic.core.services.role;

import dental_clinic.core.database.role.RoleRepository;
import dental_clinic.core.domain.Role;
import dental_clinic.core.requests.role.AddRoleRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.role.AddRoleResponse;
import dental_clinic.core.validators.role.AddRoleRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddRoleService {

    @Autowired
    private AddRoleRequestValidator addRoleRequestValidator;

    @Autowired
    private RoleRepository roleRepository;

    public AddRoleResponse execute (AddRoleRequest addRoleRequest) {
        List<CoreError> errorList = addRoleRequestValidator.validate(addRoleRequest);

        if (!errorList.isEmpty()) {
            return new AddRoleResponse(errorList);
        }

        Role role = new Role(addRoleRequest.getName(), addRoleRequest.getComment());
        roleRepository.addRole(role);

        return new AddRoleResponse(role);
    }
}
