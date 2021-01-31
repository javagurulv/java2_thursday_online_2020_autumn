package dental_clinic.core.database.manipulation;

import dental_clinic.core.domain.Manipulation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class JdbcManipulationRepositoryImpl implements ManipulationRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void addManipulation(Manipulation manipulation) {
        jdbcTemplate.update(
                "INSERT INTO manipulation (service_type, price, isActive) "
                + "VALUES (?, ?, ?)",
                manipulation.getManipulation_type(), manipulation.getPrice(), manipulation.isActive()
        );
    }

    @Override
    public List<Manipulation> getManipulationsList() {
        String sql = "SELECT * FROM manipulation";
        return jdbcTemplate.query(sql, new ManipulationRowMapper());
    }

    @Override
    public void deactivateManipulation(Long id) {
        jdbcTemplate.update(
                "UPDATE manipulation " +
                    "SET isActive = " + false +
                    " WHERE id = " + id + ";");
    }

    @Override
    public boolean containsTheSameManipulation(Manipulation manipulation) {
        String sql = "SELECT * FROM manipulation WHERE manipulation_type = \""
                + manipulation.getManipulation_type() +"\";";
        return jdbcTemplate.query(sql, new ManipulationRowMapper()).size() == 1;
    }

    @Override
    public boolean manipulationIsActive(Long id) {
        String sql = "SELECT * FROM manipulation WHERE id = " + id;
        return jdbcTemplate.query(sql, new ManipulationRowMapper()).get(0).isActive();
    }

    @Override
    public boolean containsId(Long id) {
        String sql = "SELECT * FROM manipulation WHERE id = " + id + ";";
        return jdbcTemplate.query(sql, new ManipulationRowMapper()).size() == 1;
    }
}
