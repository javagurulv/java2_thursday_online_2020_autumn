package dental_clinic_tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseCleanerClinic {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void clean() {
        for(String tableName : getTableNames()) {
            String sql = "delete from " + tableName;
            jdbcTemplate.execute(sql);
        }
    }

    private List<String> getTableNames() {
        List<String> tableNames = new ArrayList<>();
        tableNames.add("doctor");
        tableNames.add("personalData");
        tableNames.add("service");
        tableNames.add("jowl");
        tableNames.add("visit");
        tableNames.add("plannedVisit");
        return tableNames;
    }

}
