package dental_clinic.core.database.user;

import dental_clinic.core.domain.Role;
import dental_clinic.core.domain.User;

import java.util.List;

public interface UserRepository {

    List<User> getUsers();

    void addUser(User user);

    void deleteUser(Long id);

    void setRole(Long userId, Long roleId);

    Role userRole(Long id);

    boolean containsId(Long id);

    boolean containsUser(String login, String password);
}
