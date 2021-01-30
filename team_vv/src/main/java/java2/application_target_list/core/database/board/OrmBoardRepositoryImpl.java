package java2.application_target_list.core.database.board;

import java2.application_target_list.core.domain.Record;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@Transactional
public class OrmBoardRepositoryImpl implements BoardRepository{

    @Autowired private SessionFactory sessionFactory;

    @Override
    public void addToBoard(Record record) {
        sessionFactory.getCurrentSession().save(record);
    }

    @Override
    public boolean deleteFromBoard(Long recordId) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "DELETE Record WHERE id = :recordId");
        query.setParameter("recordId", recordId);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public List<Record> getAllRecordsList() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Record", Record.class)
                .getResultList();
    }

    @Override
    public boolean setRecordCompleteDate(Long recordId) {
        Query query = sessionFactory.getCurrentSession().createQuery("UPDATE Record SET target_date_of_completion =: date " +
                "WHERE id =: recordId");
        query.setParameter("date", getDate());
        query.setParameter("recordId", recordId);
        int result = query.executeUpdate();
        return result == 1;
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
    public List<Record> getFullInfoAboutRecords() {
//        return sessionFactory.getCurrentSession()
//                .createQuery("From Record", Record.class)
//                .getResultList();
        return getAllRecordsList();
    }

    @Override
    public List<Record> getUnfinishedRecords() {
        return sessionFactory.getCurrentSession()
                .createQuery("From Record WHERE target_date_of_completion IS null", Record.class)
                .getResultList();
    }

    private String getDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter myFormatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(myFormatDate);
    }
}