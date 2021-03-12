package java2.application_target_list.core.junittests.services.user;

import java2.application_target_list.core.database.jpa.JpaUserRepository;
import java2.application_target_list.core.junittests.matchers.UserMatcher;
import java2.application_target_list.core.requests.user.AddUserRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.user.AddUserResponse;
import java2.application_target_list.core.services.user.AddUserService;
import java2.application_target_list.core.validators.user.AddUserValidator;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

@RunWith(MockitoJUnitRunner.class)
public class AddUserServiceTest {

    private List<CoreError> errorList;
    @Mock
    private JpaUserRepository jpaUserRepository;
    @Mock
    private AddUserValidator addUserValidator;
    @InjectMocks
    AddUserService addUserService;

    @Before
    public void setup() {
        errorList = new ArrayList<>();
    }

    @Test
    public void shouldAddUserToDatabase() {
        Mockito.when(addUserValidator.validate(any())).thenReturn(new ArrayList<>());
        AddUserRequest addUserRequest = new AddUserRequest("name", "surname");
        AddUserResponse addUserResponse = addUserService.execute(addUserRequest);
        Assert.assertFalse(addUserResponse.hasErrors());
        Mockito.verify(jpaUserRepository).save(argThat(new UserMatcher("name", "surname")));
    }

    @Test
    public void shouldReturnResponseWithErrors_v1() {
        errorList.add(new CoreError("User first name", "must not be empty!"));
        AddUserRequest addUserRequest = new AddUserRequest("", "surname");
        Mockito.when(addUserValidator.validate(any())).thenReturn(errorList);
        AddUserResponse addUserResponse = addUserService.execute(addUserRequest);
        Assert.assertTrue(addUserResponse.hasErrors());
        Assert.assertEquals(addUserResponse.getErrorList().size(), 1);
        Assert.assertEquals(addUserResponse.getErrorList().get(0).getField(), "User first name");
        Assert.assertEquals(addUserResponse.getErrorList().get(0).getMessage(), "must not be empty!");
        Mockito.verifyNoInteractions(jpaUserRepository);
    }

    @Test
    public void shouldReturnResponseWithErrors_v2() {
        errorList.add(new CoreError("User first name", "must not be empty!"));
        AddUserRequest addUserRequest = new AddUserRequest(null, "surname");
        Mockito.when(addUserValidator.validate(any())).thenReturn(errorList);
        AddUserResponse addUserResponse = addUserService.execute(addUserRequest);
        Assert.assertTrue(addUserResponse.hasErrors());
        Assert.assertEquals(addUserResponse.getErrorList().size(), 1);
        Assert.assertEquals(addUserResponse.getErrorList().get(0).getField(), "User first name");
        Assert.assertEquals(addUserResponse.getErrorList().get(0).getMessage(), "must not be empty!");
        Mockito.verifyNoInteractions(jpaUserRepository);
    }

    @Test
    public void shouldReturnResponseWithErrors_v3() {
        errorList.add(new CoreError("User last name", "must not be empty!"));
        AddUserRequest addUserRequest = new AddUserRequest("name", "");
        Mockito.when(addUserValidator.validate(any())).thenReturn(errorList);
        AddUserResponse addUserResponse = addUserService.execute(addUserRequest);
        Assert.assertTrue(addUserResponse.hasErrors());
        Assert.assertEquals(addUserResponse.getErrorList().size(), 1);
        Assert.assertEquals(addUserResponse.getErrorList().get(0).getField(), "User last name");
        Assert.assertEquals(addUserResponse.getErrorList().get(0).getMessage(), "must not be empty!");
        Mockito.verifyNoInteractions(jpaUserRepository);
    }

    @Test
    public void shouldReturnResponseWithErrors_v4() {
        errorList.add(new CoreError("User last name", "must not be empty!"));
        AddUserRequest addUserRequest = new AddUserRequest("name", null);
        Mockito.when(addUserValidator.validate(any())).thenReturn(errorList);
        AddUserResponse addUserResponse = addUserService.execute(addUserRequest);
        Assert.assertTrue(addUserResponse.hasErrors());
        Assert.assertEquals(addUserResponse.getErrorList().size(), 1);
        Assert.assertEquals(addUserResponse.getErrorList().get(0).getField(), "User last name");
        Assert.assertEquals(addUserResponse.getErrorList().get(0).getMessage(), "must not be empty!");
        Mockito.verifyNoInteractions(jpaUserRepository);
    }

    @Test
    public void shouldReturnResponseWithErrors_v5() {
        errorList.add(new CoreError("User first name", "must not be empty!"));
        errorList.add(new CoreError("User last name", "must not be empty!"));
        AddUserRequest addUserRequest = new AddUserRequest("", null);
        Mockito.when(addUserValidator.validate(any())).thenReturn(errorList);
        AddUserResponse addUserResponse = addUserService.execute(addUserRequest);
        Assert.assertTrue(addUserResponse.hasErrors());
        Assert.assertEquals(addUserResponse.getErrorList().size(), 2);
        Assert.assertEquals(addUserResponse.getErrorList().get(0).getField(), "User first name");
        Assert.assertEquals(addUserResponse.getErrorList().get(0).getMessage(), "must not be empty!");
        Assert.assertEquals(addUserResponse.getErrorList().get(1).getField(), "User last name");
        Assert.assertEquals(addUserResponse.getErrorList().get(1).getMessage(), "must not be empty!");
        Mockito.verifyNoInteractions(jpaUserRepository);
    }
}