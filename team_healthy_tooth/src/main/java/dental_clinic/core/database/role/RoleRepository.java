package dental_clinic.core.database.role;

import dental_clinic.core.domain.Role;
import dental_clinic.core.domain.User;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {

    List<Role> getRoles();

    void addRole(Role role);

    boolean containsId(Long id);

    Optional<Role> getRoleById(Long id);
}
