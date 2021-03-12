package java2.application_target_list.core.junittests.services.board;

import java2.application_target_list.core.database.jpa.JpaBoardRepository;
import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.database.jpa.JpaUserRepository;
import java2.application_target_list.core.junittests.matchers.RecordMatcher;
import java2.application_target_list.core.requests.board.AddRecordRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.board.AddRecordResponse;
import java2.application_target_list.core.services.board.AddRecordService;
import java2.application_target_list.core.validators.board.AddRecordValidator;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

@RunWith(MockitoJUnitRunner.Silent.class)
public class AddRecordServiceTest {

    private List<CoreError> errorList;
    @Mock
    private AddRecordValidator addRecordValidator;
    @Mock
    private JpaUserRepository jpaUserRepository;
    @Mock
    private JpaTargetRepository jpaTargetRepository;
    @Mock
    private JpaBoardRepository jpaBoardRepository;
    @InjectMocks
    AddRecordService addRecordService;

    @Before
    public void setup() {
        errorList = new ArrayList<>();
    }

    @Test
    public void shouldAddRecordToDatabase() {
        Mockito.when(jpaTargetRepository.existsById(1L)).thenReturn(true);
        Mockito.when(jpaUserRepository.existsById(1L)).thenReturn(true);
        Mockito.when(addRecordValidator.validate(any())).thenReturn(new ArrayList<>());
        AddRecordRequest recordRequest = new AddRecordRequest(1L, 1L);
        AddRecordResponse addRecordResponse = addRecordService.execute(recordRequest);
        Assert.assertFalse(addRecordResponse.hasErrors());
        Mockito.verify(jpaBoardRepository).save(argThat(new RecordMatcher(1L, 1L)));
    }

    @Test
    public void shouldReturnResponseWithErrors_v1() {
        errorList.add(new CoreError("Target ID", "must not be empty!"));
        Mockito.when(addRecordValidator.validate(any())).thenReturn(errorList);
        AddRecordRequest recordRequest = new AddRecordRequest(null, 1L);
        AddRecordResponse addRecordResponse = addRecordService.execute(recordRequest);
        Assert.assertTrue(addRecordResponse.hasErrors());
        Assert.assertEquals(addRecordResponse.getErrorList().size(), 3);
        Assert.assertEquals(addRecordResponse.getErrorList().get(0).getField(), "Target ID");
        Assert.assertEquals(addRecordResponse.getErrorList().get(0).getMessage(), "must not be empty!");
        Assert.assertEquals(addRecordResponse.getErrorList().get(1).getField(), "Target ID");
        Assert.assertEquals(addRecordResponse.getErrorList().get(1).getMessage(), "no target with that ID!");
        Assert.assertEquals(addRecordResponse.getErrorList().get(2).getField(), "User ID");
        Assert.assertEquals(addRecordResponse.getErrorList().get(2).getMessage(), "no user with that ID!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v2() {
        errorList.add(new CoreError("Target ID", "must not be negative!"));
        Mockito.when(addRecordValidator.validate(any())).thenReturn(errorList);
        AddRecordRequest recordRequest = new AddRecordRequest(-2L, 1L);
        AddRecordResponse addRecordResponse = addRecordService.execute(recordRequest);
        Assert.assertTrue(addRecordResponse.hasErrors());
        Assert.assertEquals(addRecordResponse.getErrorList().size(), 3);
        Assert.assertEquals(addRecordResponse.getErrorList().get(0).getField(),"Target ID");
        Assert.assertEquals(addRecordResponse.getErrorList().get(0).getMessage(),"must not be negative!");
        Assert.assertEquals(addRecordResponse.getErrorList().get(1).getField(), "Target ID");
        Assert.assertEquals(addRecordResponse.getErrorList().get(1).getMessage(), "no target with that ID!");
        Assert.assertEquals(addRecordResponse.getErrorList().get(2).getField(), "User ID");
        Assert.assertEquals(addRecordResponse.getErrorList().get(2).getMessage(), "no user with that ID!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v3() {
        errorList.add(new CoreError("User ID", "must not be negative!"));
        Mockito.when(addRecordValidator.validate(any())).thenReturn(errorList);
        AddRecordRequest recordRequest = new AddRecordRequest(1L, -1L);
        AddRecordResponse addRecordResponse = addRecordService.execute(recordRequest);
        Assert.assertTrue(addRecordResponse.hasErrors());
        Assert.assertEquals(addRecordResponse.getErrorList().size(), 3);
        Assert.assertEquals(addRecordResponse.getErrorList().get(0).getField(),"User ID");
        Assert.assertEquals(addRecordResponse.getErrorList().get(0).getMessage(),"must not be negative!");
        Assert.assertEquals(addRecordResponse.getErrorList().get(1).getField(), "Target ID");
        Assert.assertEquals(addRecordResponse.getErrorList().get(1).getMessage(), "no target with that ID!");
        Assert.assertEquals(addRecordResponse.getErrorList().get(2).getField(), "User ID");
        Assert.assertEquals(addRecordResponse.getErrorList().get(2).getMessage(), "no user with that ID!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v4() {
        errorList.add(new CoreError("User ID", "must not be empty!"));
        Mockito.when(addRecordValidator.validate(any())).thenReturn(errorList);
        AddRecordRequest recordRequest = new AddRecordRequest(1L, null);
        AddRecordResponse addRecordResponse = addRecordService.execute(recordRequest);
        Assert.assertTrue(addRecordResponse.hasErrors());
        Assert.assertEquals(addRecordResponse.getErrorList().size(), 3);
        Assert.assertEquals(addRecordResponse.getErrorList().get(0).getField(),"User ID");
        Assert.assertEquals(addRecordResponse.getErrorList().get(0).getMessage(),"must not be empty!");
        Assert.assertEquals(addRecordResponse.getErrorList().get(1).getField(), "Target ID");
        Assert.assertEquals(addRecordResponse.getErrorList().get(1).getMessage(), "no target with that ID!");
        Assert.assertEquals(addRecordResponse.getErrorList().get(2).getField(), "User ID");
        Assert.assertEquals(addRecordResponse.getErrorList().get(2).getMessage(), "no user with that ID!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v5() {
        errorList.add(new CoreError("Target ID", "must not be negative!"));
        errorList.add(new CoreError("User ID", "must not be empty!"));
        Mockito.when(addRecordValidator.validate(any())).thenReturn(errorList);
        AddRecordRequest recordRequest = new AddRecordRequest(-1L, null);
        AddRecordResponse addRecordResponse = addRecordService.execute(recordRequest);
        Assert.assertTrue(addRecordResponse.hasErrors());
        Assert.assertEquals(addRecordResponse.getErrorList().size(), 4);
        Assert.assertEquals(addRecordResponse.getErrorList().get(0).getField(),"Target ID");
        Assert.assertEquals(addRecordResponse.getErrorList().get(0).getMessage(),"must not be negative!");
        Assert.assertEquals(addRecordResponse.getErrorList().get(1).getField(),"User ID");
        Assert.assertEquals(addRecordResponse.getErrorList().get(1).getMessage(),"must not be empty!");
        Assert.assertEquals(addRecordResponse.getErrorList().get(2).getField(), "Target ID");
        Assert.assertEquals(addRecordResponse.getErrorList().get(2).getMessage(), "no target with that ID!");
        Assert.assertEquals(addRecordResponse.getErrorList().get(3).getField(), "User ID");
        Assert.assertEquals(addRecordResponse.getErrorList().get(3).getMessage(), "no user with that ID!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v6() {

        Mockito.when(addRecordValidator.validate(any())).thenReturn(errorList);
        AddRecordRequest recordRequest = new AddRecordRequest(2L, 2L);
        AddRecordResponse addRecordResponse = addRecordService.execute(recordRequest);
        Assert.assertTrue(addRecordResponse.hasErrors());
        Assert.assertEquals(addRecordResponse.getErrorList().size(), 2);
        Assert.assertEquals(addRecordResponse.getErrorList().get(0).getField(),"Target ID");
        Assert.assertEquals(addRecordResponse.getErrorList().get(0).getMessage(),"no target with that ID!");
        Assert.assertEquals(addRecordResponse.getErrorList().get(1).getField(),"User ID");
        Assert.assertEquals(addRecordResponse.getErrorList().get(1).getMessage(),"no user with that ID!");
    }
}