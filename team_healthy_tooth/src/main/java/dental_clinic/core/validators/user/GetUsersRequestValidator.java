package dental_clinic.core.validators.user;

import dental_clinic.core.requests.user.GetUsersRequest;
import dental_clinic.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetUsersRequestValidator {

    public List<CoreError> validate (GetUsersRequest getUsersRequest) {
        return new ArrayList<>();
    }
}
