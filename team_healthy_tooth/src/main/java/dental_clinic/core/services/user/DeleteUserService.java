package dental_clinic.core.services.user;

import dental_clinic.core.database.user.UserRepository;
import dental_clinic.core.requests.user.DeleteUserRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.user.DeleteUserResponse;
import dental_clinic.core.validators.user.DeleteUserRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeleteUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DeleteUserRequestValidator deleteUserRequestValidator;

    public DeleteUserResponse execute (DeleteUserRequest deleteUserRequest) {

        List<CoreError> errors = deleteUserRequestValidator.validate(deleteUserRequest);

        if (!errors.isEmpty()) {
            return new DeleteUserResponse(errors);
        }

        if (!userRepository.containsId(deleteUserRequest.getId())) {
            errors.add(new CoreError("id", "Database doesn't contain user with id " + deleteUserRequest.getId()));
            return new DeleteUserResponse(errors);
        }

        userRepository.deleteUser(deleteUserRequest.getId());
        return new DeleteUserResponse(deleteUserRequest.getId());
    }
}
