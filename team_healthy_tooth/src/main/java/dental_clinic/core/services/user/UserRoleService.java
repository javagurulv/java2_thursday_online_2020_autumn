package dental_clinic.core.services.user;

import dental_clinic.core.database.user.UserRepository;
import dental_clinic.core.domain.Role;
import dental_clinic.core.requests.user.UsersRoleRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.user.UsersRoleResponse;
import dental_clinic.core.validators.user.UserRoleRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRoleService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRequestValidator userRoleRequestValidator;

    public UsersRoleResponse execute (UsersRoleRequest usersRoleRequest) {

        List<CoreError> errorList = userRoleRequestValidator.validate(usersRoleRequest);

        if (!errorList.isEmpty()) {
            return new UsersRoleResponse(errorList);
        }

        if (!userRepository.containsId(usersRoleRequest.getUserId())) {
            errorList.add(new CoreError("id", "Database doesn't contain user with id " +
                    usersRoleRequest.getUserId()));
            return new UsersRoleResponse(errorList);
        }

        Role role = userRepository.userRole(usersRoleRequest.getUserId());

        return new UsersRoleResponse(role);
    }
}
