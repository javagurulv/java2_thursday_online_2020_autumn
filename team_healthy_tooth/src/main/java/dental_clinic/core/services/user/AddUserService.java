package dental_clinic.core.services.user;

import dental_clinic.core.database.role.RoleRepository;
import dental_clinic.core.database.user.UserRepository;
import dental_clinic.core.domain.Role;
import dental_clinic.core.domain.User;
import dental_clinic.core.requests.user.AddUserRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.user.AddUserResponse;
import dental_clinic.core.validators.user.AddUserRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AddUserService {

    @Autowired
    private AddUserRequestValidator addUserRequestValidator;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public AddUserResponse execute(AddUserRequest addUserRequest) {
        List<CoreError> errors = addUserRequestValidator.validate(addUserRequest);

        if (!errors.isEmpty()) {
            return new AddUserResponse(errors);
        }

        Optional<Role> role = roleRepository.getRoleById(addUserRequest.getRoleId());

        if (role.isEmpty()) {
            errors.add(new CoreError("role", "Not valid input for role"));
            return new AddUserResponse(errors);
        }

        User user = new User(addUserRequest.getName(), addUserRequest.getSurname(), addUserRequest.getLogin(), addUserRequest.getPassword(), role.get());

        userRepository.addUser(user);

        return new AddUserResponse(user);
    }
}
