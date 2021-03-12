package java2.application_target_list.core.junittests.services.user;

import java2.application_target_list.core.database.jpa.JpaUserRepository;
import java2.application_target_list.core.requests.user.ChangeUserFirstNameRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.user.ChangeUserFirstNameResponse;
import java2.application_target_list.core.services.user.ChangeUserFirstNameService;
import java2.application_target_list.core.validators.user.ChangeUserFirstNameValidator;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ChangeUserFirstNameServiceTest {

    private List<CoreError> errorList;
    @Mock
    private JpaUserRepository jpaUserRepository;
    @Mock
    private ChangeUserFirstNameValidator changeUserFirstNameValidator;
    @InjectMocks
    ChangeUserFirstNameService changeUserFirstNameService;

    @Before
    public void setup() {
        errorList = new ArrayList<>();
    }

    @Test
    public void shouldChangeUserName() {
        Mockito.when(jpaUserRepository.existsById(1L)).thenReturn(true);
        Mockito.when(jpaUserRepository.changeUserFirstName(1L, "new name")).thenReturn(1);
        ChangeUserFirstNameRequest changeUserFirstNameRequest = new ChangeUserFirstNameRequest(1L, "new name");
        ChangeUserFirstNameResponse changeUserFirstNameResponse = changeUserFirstNameService.execute(changeUserFirstNameRequest);
        Assert.assertFalse(changeUserFirstNameResponse.hasErrors());
    }

    @Test
    public void shouldReturnResponseWithErrors_v1() {
        ChangeUserFirstNameRequest changeUserFirstNameRequest = new ChangeUserFirstNameRequest(2L, "new name");
        Mockito.when(changeUserFirstNameValidator.validate(changeUserFirstNameRequest)).thenReturn(errorList);
        ChangeUserFirstNameResponse changeUserFirstNameResponse = changeUserFirstNameService.execute(changeUserFirstNameRequest);
        Assert.assertTrue(changeUserFirstNameResponse.hasErrors());
        Assert.assertEquals(changeUserFirstNameResponse.getErrorList().size(), 1);
        Assert.assertEquals(changeUserFirstNameResponse.getErrorList().get(0).getField(), "User ID;");
        Assert.assertEquals(changeUserFirstNameResponse.getErrorList().get(0).getMessage(), "no user with that ID");
    }

    @Test
    public void shouldReturnResponseWithErrors_v2() {
        errorList.add(new CoreError("User ID","must not be empty!"));
        ChangeUserFirstNameRequest changeUserFirstNameRequest = new ChangeUserFirstNameRequest(null, "new name");
        Mockito.when(changeUserFirstNameValidator.validate(changeUserFirstNameRequest)).thenReturn(errorList);
        ChangeUserFirstNameResponse changeUserFirstNameResponse = changeUserFirstNameService.execute(changeUserFirstNameRequest);
        Assert.assertTrue(changeUserFirstNameResponse.hasErrors());
        Assert.assertEquals(changeUserFirstNameResponse.getErrorList().size(), 2);
        Assert.assertEquals(changeUserFirstNameResponse.getErrorList().get(0).getField(), "User ID");
        Assert.assertEquals(changeUserFirstNameResponse.getErrorList().get(0).getMessage(), "must not be empty!");
        Assert.assertEquals(changeUserFirstNameResponse.getErrorList().get(1).getField(), "User ID;");
        Assert.assertEquals(changeUserFirstNameResponse.getErrorList().get(1).getMessage(), "no user with that ID");
    }

    @Test
    public void shouldReturnResponseWithErrors_v3() {
        errorList.add(new CoreError("User ID","must not be negative!"));
        ChangeUserFirstNameRequest changeUserFirstNameRequest = new ChangeUserFirstNameRequest(-3L, "new name");
        Mockito.when(changeUserFirstNameValidator.validate(changeUserFirstNameRequest)).thenReturn(errorList);
        ChangeUserFirstNameResponse changeUserFirstNameResponse = changeUserFirstNameService.execute(changeUserFirstNameRequest);
        Assert.assertTrue(changeUserFirstNameResponse.hasErrors());
        Assert.assertEquals(changeUserFirstNameResponse.getErrorList().size(), 2);
        Assert.assertEquals(changeUserFirstNameResponse.getErrorList().get(0).getField(), "User ID");
        Assert.assertEquals(changeUserFirstNameResponse.getErrorList().get(0).getMessage(), "must not be negative!");
        Assert.assertEquals(changeUserFirstNameResponse.getErrorList().get(1).getField(), "User ID;");
        Assert.assertEquals(changeUserFirstNameResponse.getErrorList().get(1).getMessage(), "no user with that ID");
    }

    @Test
    public void shouldReturnResponseWithErrors_v4() {
        errorList.add(new CoreError("User new first name","must not be empty!"));
        ChangeUserFirstNameRequest changeUserFirstNameRequest = new ChangeUserFirstNameRequest(1L, "");
        Mockito.when(changeUserFirstNameValidator.validate(changeUserFirstNameRequest)).thenReturn(errorList);
        ChangeUserFirstNameResponse changeUserFirstNameResponse = changeUserFirstNameService.execute(changeUserFirstNameRequest);
        Assert.assertTrue(changeUserFirstNameResponse.hasErrors());
        Assert.assertEquals(changeUserFirstNameResponse.getErrorList().size(), 2);
        Assert.assertEquals(changeUserFirstNameResponse.getErrorList().get(0).getField(), "User new first name");
        Assert.assertEquals(changeUserFirstNameResponse.getErrorList().get(0).getMessage(), "must not be empty!");
        Assert.assertEquals(changeUserFirstNameResponse.getErrorList().get(1).getField(), "User ID;");
        Assert.assertEquals(changeUserFirstNameResponse.getErrorList().get(1).getMessage(), "no user with that ID");
    }

    @Test
    public void shouldReturnResponseWithErrors_v5() {
        errorList.add(new CoreError("User new first name","must not be empty!"));
        errorList.add(new CoreError("User ID","must not be negative!"));
        ChangeUserFirstNameRequest changeUserFirstNameRequest = new ChangeUserFirstNameRequest(-1L, "");
        Mockito.when(changeUserFirstNameValidator.validate(changeUserFirstNameRequest)).thenReturn(errorList);
        ChangeUserFirstNameResponse changeUserFirstNameResponse = changeUserFirstNameService.execute(changeUserFirstNameRequest);
        Assert.assertTrue(changeUserFirstNameResponse.hasErrors());
        Assert.assertEquals(changeUserFirstNameResponse.getErrorList().size(), 3);
        Assert.assertEquals(changeUserFirstNameResponse.getErrorList().get(0).getField(), "User new first name");
        Assert.assertEquals(changeUserFirstNameResponse.getErrorList().get(0).getMessage(), "must not be empty!");
        Assert.assertEquals(changeUserFirstNameResponse.getErrorList().get(1).getField(), "User ID");
        Assert.assertEquals(changeUserFirstNameResponse.getErrorList().get(1).getMessage(), "must not be negative!");
        Assert.assertEquals(changeUserFirstNameResponse.getErrorList().get(2).getField(), "User ID;");
        Assert.assertEquals(changeUserFirstNameResponse.getErrorList().get(2).getMessage(), "no user with that ID");
    }
}