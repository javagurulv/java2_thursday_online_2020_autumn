package dental_clinic.core.services.role;

import dental_clinic.core.database.role.RoleRepository;
import dental_clinic.core.domain.Role;
import dental_clinic.core.requests.role.GetRolesRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.role.GetRolesResponse;
import dental_clinic.core.validators.role.GetRolesRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetRolesService {

    @Autowired
    private GetRolesRequestValidator getRolesRequestValidator;
    @Autowired
    private RoleRepository roleRepository;

    public GetRolesResponse execute (GetRolesRequest getRolesRequest) {
        List<CoreError> errorList = getRolesRequestValidator.validate(getRolesRequest);

        if (!errorList.isEmpty()) {
            return new GetRolesResponse(errorList, new ArrayList<>());
        }

        List<Role>roles = roleRepository.getRoles();

        if (roles.isEmpty()) {
            errorList.add(new CoreError("database", "Database is empty"));
            return new GetRolesResponse(errorList, new ArrayList<>());
        }

        return new GetRolesResponse(roles);
    }
}
