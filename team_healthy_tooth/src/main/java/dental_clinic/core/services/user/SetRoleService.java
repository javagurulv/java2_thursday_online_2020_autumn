package dental_clinic.core.services.user;

import dental_clinic.core.database.role.RoleRepository;
import dental_clinic.core.database.user.UserRepository;
import dental_clinic.core.domain.Role;
import dental_clinic.core.requests.user.SetRoleRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.user.SetRoleResponse;
import dental_clinic.core.validators.user.SetRoleRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SetRoleService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SetRoleRequestValidator setRoleRequestValidator;

    @Autowired
    private RoleRepository roleRepository;

    public SetRoleResponse execute(SetRoleRequest setRoleRequest) {
        List<CoreError> errorList = setRoleRequestValidator.validate(setRoleRequest);

        if (!errorList.isEmpty()) {
            return new SetRoleResponse(errorList);
        }

        if (!roleRepository.containsId(setRoleRequest.getRoleId())) {
            errorList.add(new CoreError("id", "Database doesn't contain role with id " +
                    setRoleRequest.getRoleId()));
            return new SetRoleResponse(errorList);
        }

        userRepository.setRole(setRoleRequest.getUserId(), setRoleRequest.getRoleId());

        Role role = roleRepository.getRoleById(setRoleRequest.getRoleId()).get();

        return new SetRoleResponse(role);
    }
}
