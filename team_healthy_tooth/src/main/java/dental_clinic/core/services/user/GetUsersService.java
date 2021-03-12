package dental_clinic.core.services.user;

import dental_clinic.core.database.user.UserRepository;
import dental_clinic.core.domain.User;
import dental_clinic.core.requests.user.GetUsersRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.user.GetUsersResponse;
import dental_clinic.core.validators.user.GetUsersRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetUsersService {

    @Autowired
    private GetUsersRequestValidator getUsersRequestValidator;

    @Autowired
    private UserRepository userRepository;

    public GetUsersResponse execute (GetUsersRequest getUsersRequest) {
        List<CoreError> errors = getUsersRequestValidator.validate(getUsersRequest);

        if (!errors.isEmpty()) {
            return new GetUsersResponse(errors, new ArrayList<>());
        }

        List<User> users = userRepository.getUsers();

        if (users.isEmpty()) {
            errors.add(new CoreError("database", "User's database is empty"));
            return new GetUsersResponse(errors, new ArrayList<>());
        }

        return new GetUsersResponse(users);
    }
}
