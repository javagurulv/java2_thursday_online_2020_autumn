package dental_clinic.core.services.user;

import dental_clinic.core.database.user.OrmUserRepositoryImpl;
import dental_clinic.core.database.user.UserRepository;
import dental_clinic.core.domain.Role;
import dental_clinic.core.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContainsUserServiceGUI {

    @Autowired
    private UserRepository userRepository;

    public boolean execute (String login, String password) {

        return userRepository.containsUser(login, password);
    }
}
