package java2.application_target_list.core.database.board;

import java2.application_target_list.core.domain.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

//@Component
public class JdbcBoardRepositoryImpl implements BoardRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void addToBoard(Record record) {
        jdbcTemplate.update(
                "INSERT INTO targets_board (target_id, user_id, target_added_date) " +
                        "VALUES (?, ?, ?)", record.getTargetId(), record.getUserId(), getDate());
    }

    @Override
    public boolean deleteFromBoard(Long id) {
        jdbcTemplate.update("DELETE FROM targets_board WHERE id = " + id);
        return true;
    }

    @Override
    public List<Record> getAllRecordsList() {
        return jdbcTemplate.query("SELECT * FROM targets_board", new RecordsMapper());
    }

    @Override
    public boolean setRecordCompleteDate(Long id) {
        jdbcTemplate.update("UPDATE targets_board SET target_date_of_completion = ? WHERE id = ?", getDate(), id);
        return true;
    }

    @Override
    public boolean isIdInBoardList(Long boardId) {
        List<Record> records = getAllRecordsList();

        for (Record tempRecord : records) {
            if (tempRecord.getRecordId().equals(boardId)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public List<Record> getFullInfoAboutRecords(){
       return jdbcTemplate.query("SELECT * FROM targets_board " +
                "JOIN targets ON targets.id = targets_board.target_id " +
                "JOIN users ON users.id = targets_board.user_id", new RecordsInfoMapper());
    }

    @Override
    public List<Record> getUnfinishedRecords() {
        return null;
    }

    @Override
    public Optional<Record> getById(Long id) {
        return Optional.empty();
    }

    private String getDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter myFormatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(myFormatDate);
    }

}
