package dental_clinic.core.validators.role;

import dental_clinic.core.requests.role.GetRolesRequest;
import dental_clinic.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetRolesRequestValidator {

    public List<CoreError> validate (GetRolesRequest getRolesRequest) {
        return new ArrayList<>();
    }
}
