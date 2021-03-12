package java2.application_target_list.core.database.user;

import java2.application_target_list.core.domain.User;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

class UsersMapper implements RowMapper<User> {
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User(rs.getString("user_first_name"),
                rs.getString("user_last_name"));
        user.setId(rs.getLong("id"));
        return user;
    }
}
