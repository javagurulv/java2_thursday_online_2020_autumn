package java2.application_target_list.core.database.user;

import java2.application_target_list.core.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import java.util.Optional;

//@Component
public class JdbcUserRepositoryImpl implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addUser(User user) {
        jdbcTemplate.update(
                "INSERT INTO users (user_first_name, user_last_name) " +
                        "VALUES (?, ?)", user.getFirstName(), user.getLastName());
    }

    @Override
    public boolean deleteUser(Long userId) {
        jdbcTemplate.update("DELETE FROM users WHERE id = " + userId);
        return true;
    }

    @Override
    public boolean changeUserFirstName(Long userId, String newName) {
        jdbcTemplate.update("UPDATE users SET user_first_name = ? WHERE id = ?", newName, userId);
        return true;
    }

    @Override
    public boolean changeUserLastName(Long userId, String newLastName) {
        jdbcTemplate.update("UPDATE users SET user_last_name = ? WHERE id = ?", newLastName, userId);
        return true;
    }

    @Override
    public List<User> getUsersList() {
        return jdbcTemplate.query("SELECT * FROM users", new UsersMapper());
    }

    @Override
    public List<User> findUserByFirstName(String userFirstName) {
        return jdbcTemplate.query("SELECT * FROM users WHERE user_first_name = ?", new Object[]{userFirstName} , new UsersMapper());
    }

    @Override
    public List<User> findUserByLastName(String userLastName) {
        return jdbcTemplate.query("SELECT * FROM users WHERE user_last_name = ?", new Object[]{userLastName} , new UsersMapper());
    }

    @Override
    public boolean isIdInUserList(Long userId) {
        List<User> users = getUsersList();

        for (User tempUser : users) {
            if (tempUser.getId().equals(userId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Optional<User> getById(Long id) {
        return Optional.empty();
    }

}
