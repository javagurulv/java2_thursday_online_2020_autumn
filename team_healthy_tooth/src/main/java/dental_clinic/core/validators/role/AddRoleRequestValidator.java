package dental_clinic.core.validators.role;

import dental_clinic.core.requests.role.AddRoleRequest;
import dental_clinic.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddRoleRequestValidator {

    public List<CoreError> validate(AddRoleRequest addRoleRequest) {
        return new ArrayList<>();
    }
}
