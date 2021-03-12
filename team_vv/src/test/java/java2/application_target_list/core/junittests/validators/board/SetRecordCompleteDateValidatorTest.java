package java2.application_target_list.core.junittests.validators.board;

import java2.application_target_list.core.database.board.BoardRepository;
import java2.application_target_list.core.database.board.InMemoryBoardRepositoryImpl;
import java2.application_target_list.core.domain.Record;
import java2.application_target_list.core.requests.board.SetRecordCompleteDateRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.board.SetRecordCompleteDateValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class SetRecordCompleteDateValidatorTest {

    private SetRecordCompleteDateValidator setRecordCompleteDateValidator;

    @Before
    public void setup(){
        setRecordCompleteDateValidator = new SetRecordCompleteDateValidator();
        BoardRepository boardRepository = new InMemoryBoardRepositoryImpl();
        boardRepository.addToBoard(new Record(1L, 1L));
    }

    @Test
    public void testValidate_validRequest() {
        SetRecordCompleteDateRequest setRecordCompleteDateRequest = new SetRecordCompleteDateRequest(1L);
        List<CoreError> actualErrors = setRecordCompleteDateValidator.validate(setRecordCompleteDateRequest);
        Assert.assertTrue(actualErrors.isEmpty());
    }

    @Test
    public void testValidate_invalidRequest_v2() {
        SetRecordCompleteDateRequest setRecordCompleteDateRequest = new SetRecordCompleteDateRequest(-2L);
        List<CoreError> actualErrors = setRecordCompleteDateValidator.validate(setRecordCompleteDateRequest);
        Assert.assertFalse(actualErrors.isEmpty());
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertEquals(actualErrors.get(0).getField(), "Record ID");
        Assert.assertEquals(actualErrors.get(0).getMessage(), "must not be negative!");
    }

    @Test
    public void testValidate_invalidRequest_v3() {
        try {
            SetRecordCompleteDateRequest setRecordCompleteDateRequest = new SetRecordCompleteDateRequest(null);
            List<CoreError> actualErrors = setRecordCompleteDateValidator.validate(setRecordCompleteDateRequest);
            Assert.assertFalse(actualErrors.isEmpty());
            Assert.assertEquals(actualErrors.size(), 1);
            Assert.assertEquals(actualErrors.get(0).getField(), "Record ID");
            Assert.assertEquals(actualErrors.get(0).getMessage(), "must not be negative!");
        } catch (NullPointerException ignored) {

        }
    }
}