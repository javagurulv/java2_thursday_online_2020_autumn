package java2.application_target_list.core.database.board;

import java2.application_target_list.core.domain.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@Profile("mysql")
public class BoardListJDBCImpl implements BoardDatabase{

    @Autowired JdbcTemplate jdbcTemplate;


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
        return jdbcTemplate.query("SELECT * FROM targets_board", new BoardListJDBCImpl.RecordsMapper());
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

    private class RecordsMapper implements RowMapper<Record> {
        public Record mapRow(ResultSet rs, int rowNum) throws SQLException {
            Record record = new Record(rs.getLong("target_id"), rs.getLong("user_id"));
            record.setRecordId(rs.getLong("id"));
            record.setDateAdded(rs.getString("target_added_date"));
            record.setDateComplete(rs.getString("target_date_of_completion"));
            return record;
        }
    }

    private String getDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter myFormatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(myFormatDate);
    }

    private class RecordsInfoMapper implements RowMapper<Record> {
        public Record mapRow(ResultSet rs, int rowNum) throws SQLException {
            Record record = new Record(rs.getLong("target_id"), rs.getLong("user_id"));
            record.setRecordId(rs.getLong("id"));
            record.setDateAdded(rs.getString("target_added_date"));
            record.setDateComplete(rs.getString("target_date_of_completion"));
            record.setTargetName(rs.getString("target_name"));
            record.setTargetDescription(rs.getString("target_description"));
            record.setTargetDeadline(rs.getInt("target_deadline"));
            record.setUserFirstName(rs.getString("user_first_name"));
            record.setUserLastName(rs.getString("user_last_name"));
            return record;
        }
    }
}