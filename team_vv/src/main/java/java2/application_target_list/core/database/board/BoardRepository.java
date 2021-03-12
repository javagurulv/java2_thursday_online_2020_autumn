package java2.application_target_list.core.database.board;

import java2.application_target_list.core.domain.Record;
import java.util.List;
import java.util.Optional;

public interface BoardRepository {

    void addToBoard(Record record);
    boolean deleteFromBoard(Long id);
    List<Record> getAllRecordsList();
    boolean setRecordCompleteDate(Long id);
    boolean isIdInBoardList(Long boardId);
    List<Record> getFullInfoAboutRecords();
    List<Record> getUnfinishedRecords();
    Optional<Record> getById(Long id);
}
