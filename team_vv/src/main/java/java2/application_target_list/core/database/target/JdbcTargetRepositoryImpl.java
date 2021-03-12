package java2.application_target_list.core.database.target;

import java2.application_target_list.core.domain.Target;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import java.util.Optional;

//@Component
public class JdbcTargetRepositoryImpl implements TargetRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addTarget(Target target) {
        jdbcTemplate.update(
                "INSERT INTO targets (target_name, target_description, target_deadline ) " +
                        "VALUES (?, ?, ?)", target.getName(), target.getDescription(), target.getDeadline());
    }

    @Override
    public boolean deleteTarget(Long targetId) {
        jdbcTemplate.update("DELETE FROM targets WHERE id = " + targetId);
        return true;
    }

    @Override
    public boolean changeTargetName(Long targetId, String newName) {
        jdbcTemplate.update("UPDATE targets SET target_name = ? WHERE id = ?", newName, targetId);
        return true;
    }

    @Override
    public boolean changeTargetDescription(Long targetId, String newDescription) {
        jdbcTemplate.update("UPDATE targets SET target_description = ? WHERE id = ?", newDescription, targetId);
        return true;
    }

    @Override
    public boolean changeTargetDeadline(Long targetId, Long newDeadline) {
        jdbcTemplate.update("UPDATE targets SET target_deadline = ? WHERE id = ?", newDeadline, targetId);
        return true;
    }

    @Override
    public List<Target> getTargetsList() {
        return jdbcTemplate.query("SELECT * FROM targets", new TargetsMapper());
    }

    @Override
    public boolean isIdInTargetList(Long targetId) {
        List<Target> targets = getTargetsList();

        for (Target tempTarget : targets) {
            if (tempTarget.getId().equals(targetId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Target> findByTargetName(String targetName) {
        return jdbcTemplate.query("SELECT * FROM targets WHERE target_name = ?", new Object[]{targetName} , new TargetsMapper());
    }

    @Override
    public List<Target> findByTargetDescription(String targetDescription) {
        return jdbcTemplate.query("SELECT * FROM targets WHERE target_description = ?", new Object[]{targetDescription},
                new TargetsMapper());
    }

    @Override
    public Optional<Target> getById(Long id) {
        return Optional.empty();
    }

}
