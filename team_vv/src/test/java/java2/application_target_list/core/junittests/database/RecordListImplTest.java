package java2.application_target_list.core.junittests.database;

import java2.application_target_list.core.database.board.BoardRepository;
import java2.application_target_list.core.database.board.InMemoryBoardRepositoryImpl;
import java2.application_target_list.core.domain.Record;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


public class RecordListImplTest {

    private BoardRepository boardRepository;
    private Record record1;
    private Record record2;
    private Record record3;
    private Target firstTarget;
    private Target secondTarget;
    private Target thirdTarget;
    private User firstUser;
    private User secondUser;

    @Before
    public void setup(){
        boardRepository = new InMemoryBoardRepositoryImpl();
        firstTarget = new Target("name", "description", 5L);
        secondTarget = new Target("name1", "description1", 2L);
        thirdTarget = new Target("name2", "description2", 54L);
        firstUser = new User("name", "surname");
        secondUser = new User("name1", "surname1");
        record1 = new Record(firstTarget.getId(), firstUser.getId());
        record2 = new Record(secondTarget.getId(), firstUser.getId());
        record3 = new Record(thirdTarget.getId(), secondUser.getId());
    }

    @Test
    public void testAddToBoard_v1() {
        boardRepository.addToBoard(record1);
        Assert.assertEquals(boardRepository.getAllRecordsList().size(), 1);
        Assert.assertEquals(java.util.Optional.ofNullable(record1.getRecordId()), Optional.of(1L));
        Assert.assertNotNull(boardRepository.getAllRecordsList().get(0).getDateAdded());
        Assert.assertNull(boardRepository.getAllRecordsList().get(0).getDateComplete());
    }

    @Test
    public void testAddToBoard_v2() {
        boardRepository.addToBoard(record1);
        boardRepository.addToBoard(record2);
        boardRepository.addToBoard(record3);
        Assert.assertEquals(boardRepository.getAllRecordsList().size(), 3);
        Assert.assertEquals(java.util.Optional.ofNullable(record1.getRecordId()), Optional.of(1L));
        Assert.assertEquals(java.util.Optional.ofNullable(record2.getRecordId()), Optional.of(2L));
        Assert.assertEquals(java.util.Optional.ofNullable(record3.getRecordId()), Optional.of(3L));
        Assert.assertNotNull(boardRepository.getAllRecordsList().get(0).getDateAdded());
        Assert.assertNull(boardRepository.getAllRecordsList().get(0).getDateComplete());
        Assert.assertNotNull(boardRepository.getAllRecordsList().get(1).getDateAdded());
        Assert.assertNull(boardRepository.getAllRecordsList().get(1).getDateComplete());
        Assert.assertNotNull(boardRepository.getAllRecordsList().get(2).getDateAdded());
        Assert.assertNull(boardRepository.getAllRecordsList().get(2).getDateComplete());
    }

    @Test
    public void testDeleteFromBoard() {
        boardRepository.addToBoard(record1);
        boardRepository.addToBoard(record2);
        boardRepository.addToBoard(record3);
        boardRepository.deleteFromBoard(1L);
        Assert.assertEquals(boardRepository.getAllRecordsList().size(), 2);
        Assert.assertEquals(Optional.ofNullable(boardRepository.getAllRecordsList().get(0).getRecordId()), Optional.of(2L));
        Assert.assertEquals(Optional.ofNullable(boardRepository.getAllRecordsList().get(1).getRecordId()), Optional.of(3L));
    }

    @Test
    public void testGetAllList_v1() {
        boardRepository.addToBoard(record1);
        Assert.assertEquals(boardRepository.getAllRecordsList().size(), 1);
        Assert.assertEquals(java.util.Optional.ofNullable(boardRepository.getAllRecordsList().get(0).getRecordId()), Optional.of(1L));
        Assert.assertEquals(boardRepository.getAllRecordsList().get(0).getTargetId(), firstTarget.getId());
        Assert.assertEquals(boardRepository.getAllRecordsList().get(0).getUserId(), firstUser.getId());
        Assert.assertNull(boardRepository.getAllRecordsList().get(0).getDateComplete());
        Assert.assertNotNull(boardRepository.getAllRecordsList().get(0).getDateAdded());
    }

    @Test
    public void testGetAllList_v2() {
        boardRepository.addToBoard(record1);
        boardRepository.addToBoard(record2);
        boardRepository.addToBoard(record3);
        Assert.assertEquals(boardRepository.getAllRecordsList().size(), 3);
        Assert.assertEquals(java.util.Optional.ofNullable(boardRepository.getAllRecordsList().get(0).getRecordId()), Optional.of(1L));
        Assert.assertEquals(java.util.Optional.ofNullable(boardRepository.getAllRecordsList().get(1).getRecordId()), Optional.of(2L));
        Assert.assertEquals(java.util.Optional.ofNullable(boardRepository.getAllRecordsList().get(2).getRecordId()), Optional.of(3L));
        Assert.assertEquals(boardRepository.getAllRecordsList().get(0).getTargetId(), firstTarget.getId());
        Assert.assertEquals(boardRepository.getAllRecordsList().get(0).getUserId(), firstUser.getId());
        Assert.assertEquals(boardRepository.getAllRecordsList().get(1).getTargetId(), secondTarget.getId());
        Assert.assertEquals(boardRepository.getAllRecordsList().get(1).getUserId(), secondUser.getId());
        Assert.assertEquals(boardRepository.getAllRecordsList().get(2).getTargetId(), thirdTarget.getId());
        Assert.assertEquals(boardRepository.getAllRecordsList().get(2).getUserId(), firstUser.getId());

    }

    @Test
    public void testSetBoardCompleteDate_v1() {
        boardRepository.addToBoard(record1);
        boardRepository.setRecordCompleteDate(1L);
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter myFormatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String expectedDate =  localDateTime.format(myFormatDate);
        Assert.assertEquals(boardRepository.getAllRecordsList().get(0).getDateComplete(), expectedDate);
    }

    @Test
    public void testSetBoardCompleteDate_v2() {
        boardRepository.addToBoard(record1);
        boardRepository.addToBoard(record2);
        boardRepository.setRecordCompleteDate(1L);
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter myFormatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String expectedDate =  localDateTime.format(myFormatDate);
        Assert.assertEquals(boardRepository.getAllRecordsList().get(0).getDateComplete(), expectedDate);
        Assert.assertNull(boardRepository.getAllRecordsList().get(1).getDateComplete());
    }
}