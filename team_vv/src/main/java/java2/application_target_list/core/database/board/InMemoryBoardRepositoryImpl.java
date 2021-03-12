package java2.application_target_list.core.database.board;

import java2.application_target_list.core.domain.Record;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Component
public class InMemoryBoardRepositoryImpl implements BoardRepository {

    private Long recordId = 0L;
    private List<Record> recordsList = new ArrayList<>();


    @Override
    public void addToBoard(Record record) {
        record.setRecordId(recordId += 1);
        record.setDateAdded(getDate());
        recordsList.add(record);
    }

    @Override
    public boolean deleteFromBoard(Long id) {
        if (isIdInBoardList(id)){
            recordsList.remove(getBoardIndexFromListById(id));
            return true;
        }
        return false;
    }

    @Override
    public List<Record> getAllRecordsList() {
        return recordsList;
    }

    @Override
    public boolean setRecordCompleteDate(Long id) {
        if (isIdInBoardList(id)){
            recordsList.get(getBoardIndexFromListById(id)).setDateComplete(getDate());
            return true;
        }
        return false;
    }

    private String getDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter myFormatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(myFormatDate);
    }

    @Override
    public boolean isIdInBoardList(Long boardId) {
        for (Record tempRecord : recordsList) {
            if (tempRecord.getRecordId().equals(boardId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Record> getFullInfoAboutRecords(){
        return null;
    }

    @Override
    public List<Record> getUnfinishedRecords() {
        return null;
    }

    @Override
    public Optional<Record> getById(Long id) {
        return Optional.empty();
    }

    private int getBoardIndexFromListById(Long boardId) {
        for (int i = 0; i < recordsList.size(); i++) {
            if (recordsList.get(i).getRecordId().equals(boardId)) {
                return i;
            }
        }
        return 0;
    }
}
