package java2.application_target_list.core.services.target;

import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.requests.target.UpdateTargetRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.target.UpdateTargetResponse;
import java2.application_target_list.core.validators.target.UpdateTargetValidator;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest
public class UpdateTargetServiceTest {

    private List<CoreError> errorList;
    @Mock
    private JpaTargetRepository jpaTargetRepository;
    @Mock
    private UpdateTargetValidator updateTargetValidator;
    @InjectMocks
    UpdateTargetService updateTargetService;

    @BeforeEach
    public void setup() {
        errorList = new ArrayList<>();
    }

    @Test
    public void shouldChangeUserName() {
       Mockito.when(jpaTargetRepository.existsById(1L)).thenReturn(true);
       UpdateTargetRequest updateTargetRequest = new UpdateTargetRequest(1L, "new name", "new description", 100L);
       UpdateTargetResponse updateTargetResponse = updateTargetService.execute(updateTargetRequest);
       Assertions.assertFalse(updateTargetResponse.hasErrors());
    }

    @Test
    public void shouldReturnResponseWithErrors_v1() {
        UpdateTargetRequest updateTargetRequest = new UpdateTargetRequest(1L, "new name", "new description", 100L);
        Mockito.when(updateTargetValidator.validate(updateTargetRequest)).thenReturn(errorList);
        UpdateTargetResponse updateTargetResponse = updateTargetService.execute(updateTargetRequest);
        Assertions.assertTrue(updateTargetResponse.hasErrors());
        Assertions.assertEquals(updateTargetResponse.getErrorList().size(), 1);
        Assertions.assertEquals(updateTargetResponse.getErrorList().get(0).getField(), "Target ID;");
        Assertions.assertEquals(updateTargetResponse.getErrorList().get(0).getMessage(), "no target with that ID");
    }

    @Test
    public void shouldReturnResponseWithErrors_v2() {
        errorList.add(new CoreError("Target ID","must not be empty!"));
        UpdateTargetRequest updateTargetRequest = new UpdateTargetRequest(null, "new name", "new description", 100L);
        Mockito.when(updateTargetValidator.validate(updateTargetRequest)).thenReturn(errorList);
        UpdateTargetResponse updateTargetResponse = updateTargetService.execute(updateTargetRequest);
        Assertions.assertTrue(updateTargetResponse.hasErrors());
        Assertions.assertEquals(updateTargetResponse.getErrorList().size(), 2);
        Assertions.assertEquals(updateTargetResponse.getErrorList().get(1).getField(), "Target ID;");
        Assertions.assertEquals(updateTargetResponse.getErrorList().get(1).getMessage(), "no target with that ID");
        Assertions.assertEquals(updateTargetResponse.getErrorList().get(0).getField(), "Target ID");
        Assertions.assertEquals(updateTargetResponse.getErrorList().get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v3() {
        errorList.add(new CoreError("Target ID","must not be negative!"));
        UpdateTargetRequest updateTargetRequest = new UpdateTargetRequest(-3L, "new name", "new description", 100L);
        Mockito.when(updateTargetValidator.validate(updateTargetRequest)).thenReturn(errorList);
        UpdateTargetResponse updateTargetResponse = updateTargetService.execute(updateTargetRequest);
        Assertions.assertTrue(updateTargetResponse.hasErrors());
        Assertions.assertEquals(updateTargetResponse.getErrorList().size(), 2);
        Assertions.assertEquals(updateTargetResponse.getErrorList().get(1).getField(), "Target ID;");
        Assertions.assertEquals(updateTargetResponse.getErrorList().get(1).getMessage(), "no target with that ID");
        Assertions.assertEquals(updateTargetResponse.getErrorList().get(0).getField(), "Target ID");
        Assertions.assertEquals(updateTargetResponse.getErrorList().get(0).getMessage(), "must not be negative!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v4() {
        errorList.add(new CoreError("Target new name","must not be empty!"));
        UpdateTargetRequest updateTargetRequest = new UpdateTargetRequest(3L, "", "new description", 100L);
        Mockito.when(updateTargetValidator.validate(updateTargetRequest)).thenReturn(errorList);
        UpdateTargetResponse updateTargetResponse = updateTargetService.execute(updateTargetRequest);
        Assertions.assertTrue(updateTargetResponse.hasErrors());
        Assertions.assertEquals(updateTargetResponse.getErrorList().size(), 2);
        Assertions.assertEquals(updateTargetResponse.getErrorList().get(1).getField(), "Target ID;");
        Assertions.assertEquals(updateTargetResponse.getErrorList().get(1).getMessage(), "no target with that ID");
        Assertions.assertEquals(updateTargetResponse.getErrorList().get(0).getField(), "Target new name");
        Assertions.assertEquals(updateTargetResponse.getErrorList().get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v5() {
        errorList.add(new CoreError("Target new description","must not be empty!"));
        UpdateTargetRequest updateTargetRequest = new UpdateTargetRequest(3L, "new name", null, 100L);
        Mockito.when(updateTargetValidator.validate(updateTargetRequest)).thenReturn(errorList);
        UpdateTargetResponse updateTargetResponse = updateTargetService.execute(updateTargetRequest);
        Assertions.assertTrue(updateTargetResponse.hasErrors());
        Assertions.assertEquals(updateTargetResponse.getErrorList().size(), 2);
        Assertions.assertEquals(updateTargetResponse.getErrorList().get(1).getField(), "Target ID;");
        Assertions.assertEquals(updateTargetResponse.getErrorList().get(1).getMessage(), "no target with that ID");
        Assertions.assertEquals(updateTargetResponse.getErrorList().get(0).getField(), "Target new description");
        Assertions.assertEquals(updateTargetResponse.getErrorList().get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v6() {
        errorList.add(new CoreError("Target new deadline","must not be negative!"));
        UpdateTargetRequest updateTargetRequest = new UpdateTargetRequest(3L, "new name", "new description", -100L);
        Mockito.when(updateTargetValidator.validate(updateTargetRequest)).thenReturn(errorList);
        UpdateTargetResponse updateTargetResponse = updateTargetService.execute(updateTargetRequest);
        Assertions.assertTrue(updateTargetResponse.hasErrors());
        Assertions.assertEquals(updateTargetResponse.getErrorList().size(), 2);
        Assertions.assertEquals(updateTargetResponse.getErrorList().get(1).getField(), "Target ID;");
        Assertions.assertEquals(updateTargetResponse.getErrorList().get(1).getMessage(), "no target with that ID");
        Assertions.assertEquals(updateTargetResponse.getErrorList().get(0).getField(), "Target new deadline");
        Assertions.assertEquals(updateTargetResponse.getErrorList().get(0).getMessage(), "must not be negative!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v7() {
        errorList.add(new CoreError("Target new deadline","must not be empty!"));
        UpdateTargetRequest updateTargetRequest = new UpdateTargetRequest(3L, "new name", "new description", null);
        Mockito.when(updateTargetValidator.validate(updateTargetRequest)).thenReturn(errorList);
        UpdateTargetResponse updateTargetResponse = updateTargetService.execute(updateTargetRequest);
        Assertions.assertTrue(updateTargetResponse.hasErrors());
        Assertions.assertEquals(updateTargetResponse.getErrorList().size(), 2);
        Assertions.assertEquals(updateTargetResponse.getErrorList().get(1).getField(), "Target ID;");
        Assertions.assertEquals(updateTargetResponse.getErrorList().get(1).getMessage(), "no target with that ID");
        Assertions.assertEquals(updateTargetResponse.getErrorList().get(0).getField(), "Target new deadline");
        Assertions.assertEquals(updateTargetResponse.getErrorList().get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v8() {
        errorList.add(new CoreError("Target ID","must not be negative!"));
        errorList.add(new CoreError("Target new name","must not be empty!"));
        errorList.add(new CoreError("Target new description","must not be empty!"));
        errorList.add(new CoreError("Target new deadline","must not be empty!"));
        UpdateTargetRequest updateTargetRequest = new UpdateTargetRequest(-3L, null, "", null);
        Mockito.when(updateTargetValidator.validate(updateTargetRequest)).thenReturn(errorList);
        UpdateTargetResponse updateTargetResponse = updateTargetService.execute(updateTargetRequest);
        Assertions.assertTrue(updateTargetResponse.hasErrors());
        Assertions.assertEquals(updateTargetResponse.getErrorList().size(), 5);
        Assertions.assertEquals(updateTargetResponse.getErrorList().get(4).getField(), "Target ID;");
        Assertions.assertEquals(updateTargetResponse.getErrorList().get(4).getMessage(), "no target with that ID");
        Assertions.assertEquals(updateTargetResponse.getErrorList().get(0).getField(), "Target ID");
        Assertions.assertEquals(updateTargetResponse.getErrorList().get(0).getMessage(), "must not be negative!");
        Assertions.assertEquals(updateTargetResponse.getErrorList().get(1).getField(), "Target new name");
        Assertions.assertEquals(updateTargetResponse.getErrorList().get(1).getMessage(), "must not be empty!");
        Assertions.assertEquals(updateTargetResponse.getErrorList().get(2).getField(), "Target new description");
        Assertions.assertEquals(updateTargetResponse.getErrorList().get(2).getMessage(), "must not be empty!");
        Assertions.assertEquals(updateTargetResponse.getErrorList().get(3).getField(), "Target new deadline");
        Assertions.assertEquals(updateTargetResponse.getErrorList().get(3).getMessage(), "must not be empty!");
    }
}