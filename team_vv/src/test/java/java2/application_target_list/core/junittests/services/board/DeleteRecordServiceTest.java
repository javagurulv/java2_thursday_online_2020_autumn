package java2.application_target_list.core.junittests.services.board;

import java2.application_target_list.core.database.jpa.JpaBoardRepository;
import java2.application_target_list.core.requests.board.DeleteRecordRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.board.DeleteRecordResponse;
import java2.application_target_list.core.services.board.DeleteRecordService;
import java2.application_target_list.core.validators.board.DeleteRecordValidator;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.Silent.class)
public class DeleteRecordServiceTest {

    private List<CoreError> errors;
    @Mock
    private DeleteRecordValidator deleteRecordValidator;
    @Mock
    private JpaBoardRepository jpaBoardRepository;
    @InjectMocks
    DeleteRecordService deleteRecordService;

    @Before
    public void setup() {
        errors = new ArrayList<>();
    }

    @Test
    public void shouldDeleteRecordFromDatabase() {
        Mockito.when(jpaBoardRepository.existsById(1L)).thenReturn(true);
        DeleteRecordRequest deleteRecordRequest = new DeleteRecordRequest(1L);
        DeleteRecordResponse deleteRecordResponse = deleteRecordService.execute(deleteRecordRequest);
        Assert.assertFalse(deleteRecordResponse.hasErrors());
    }

    @Test
    public void shouldReturnResponseWithErrors_v1() {
        DeleteRecordRequest deleteRecordRequest = new DeleteRecordRequest(1L);
        Mockito.when(deleteRecordValidator.validate(deleteRecordRequest)).thenReturn(errors);
        DeleteRecordResponse deleteRecordResponse = deleteRecordService.execute(deleteRecordRequest);
        Assert.assertTrue(deleteRecordResponse.hasErrors());
        Assert.assertEquals(deleteRecordResponse.getErrorList().size(), 1);
        Assert.assertEquals(deleteRecordResponse.getErrorList().get(0).getField(), "Record ID");
        Assert.assertEquals(deleteRecordResponse.getErrorList().get(0).getMessage(), "no record with that ID");
    }

    @Test
    public void shouldReturnResponseWithErrors_v2() {
        errors.add(new CoreError("Record ID","must not be empty!"));
        DeleteRecordRequest deleteRecordRequest = new DeleteRecordRequest(null);
        Mockito.when(deleteRecordValidator.validate(deleteRecordRequest)).thenReturn(errors);
        DeleteRecordResponse deleteRecordResponse = deleteRecordService.execute(deleteRecordRequest);
        Assert.assertTrue(deleteRecordResponse.hasErrors());
        Assert.assertEquals(deleteRecordResponse.getErrorList().size(), 2);
        Assert.assertEquals(deleteRecordResponse.getErrorList().get(1).getField(), "Record ID");
        Assert.assertEquals(deleteRecordResponse.getErrorList().get(1).getMessage(), "no record with that ID");
        Assert.assertEquals(deleteRecordResponse.getErrorList().get(0).getField(), "Record ID");
        Assert.assertEquals(deleteRecordResponse.getErrorList().get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v3() {
        errors.add(new CoreError("Record ID","must not be negative!"));
        DeleteRecordRequest deleteRecordRequest = new DeleteRecordRequest(-2L);
        Mockito.when(deleteRecordValidator.validate(deleteRecordRequest)).thenReturn(errors);
        DeleteRecordResponse deleteRecordResponse = deleteRecordService.execute(deleteRecordRequest);
        Assert.assertTrue(deleteRecordResponse.hasErrors());
        Assert.assertEquals(deleteRecordResponse.getErrorList().size(), 2);
        Assert.assertEquals(deleteRecordResponse.getErrorList().get(1).getField(), "Record ID");
        Assert.assertEquals(deleteRecordResponse.getErrorList().get(1).getMessage(), "no record with that ID");
        Assert.assertEquals(deleteRecordResponse.getErrorList().get(0).getField(), "Record ID");
        Assert.assertEquals(deleteRecordResponse.getErrorList().get(0).getMessage(), "must not be negative!");
    }
}