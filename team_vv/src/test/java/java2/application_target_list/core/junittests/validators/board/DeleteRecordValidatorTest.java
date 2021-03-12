package java2.application_target_list.core.junittests.validators.board;

import java2.application_target_list.core.database.board.BoardRepository;
import java2.application_target_list.core.database.board.InMemoryBoardRepositoryImpl;
import java2.application_target_list.core.domain.Record;
import java2.application_target_list.core.requests.board.DeleteRecordRequest;
import java2.application_target_list.core.responses.CoreError;
import java.util.List;

import java2.application_target_list.core.validators.board.DeleteRecordValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DeleteRecordValidatorTest {
    
    private DeleteRecordValidator deleteRecordValidator;
    
    @Before
    public void setup() {
        deleteRecordValidator = new DeleteRecordValidator();
        BoardRepository boardRepository = new InMemoryBoardRepositoryImpl();
        boardRepository.addToBoard(new Record(1L, 2L));

    }

    @Test
    public void testValidate_validRequest() {
        DeleteRecordRequest deleteRecordRequest = new DeleteRecordRequest(1L);
        List<CoreError> actualErrors = deleteRecordValidator.validate(deleteRecordRequest);
        Assert.assertTrue(actualErrors.isEmpty());
    }


    @Test
    public void testValidate_invalidRequest_v2() {
        DeleteRecordRequest deleteRecordRequest = new DeleteRecordRequest(-3L);
        List<CoreError> actualErrors = deleteRecordValidator.validate(deleteRecordRequest);
        Assert.assertFalse(actualErrors.isEmpty());
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertEquals(actualErrors.get(0).getField(), "Record ID");
        Assert.assertEquals(actualErrors.get(0).getMessage(), "must not be negative!");
    }

    @Test
    public void testValidate_invalidRequest_v3() {
        try {
            DeleteRecordRequest deleteRecordRequest = new DeleteRecordRequest(null);
            List<CoreError> actualErrors = deleteRecordValidator.validate(deleteRecordRequest);
            Assert.assertFalse(actualErrors.isEmpty());
            Assert.assertEquals(actualErrors.size(), 1);
            Assert.assertEquals(actualErrors.get(0).getField(), "Record ID");
            Assert.assertEquals(actualErrors.get(0).getMessage(), "must not be empty!");
        } catch (NullPointerException ignored) {
        }
    }
}