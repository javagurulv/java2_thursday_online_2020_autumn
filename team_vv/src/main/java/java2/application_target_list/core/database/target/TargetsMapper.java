package java2.application_target_list.core.database.target;

import java2.application_target_list.core.domain.Target;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

class TargetsMapper implements RowMapper<Target> {
    public Target mapRow(ResultSet rs, int rowNum) throws SQLException {
        Target target = new Target(rs.getString("target_name"),
                rs.getString("target_description"),
                rs.getLong("target_deadline"));
        target.setId(rs.getLong("id"));
        return target;
    }
}
