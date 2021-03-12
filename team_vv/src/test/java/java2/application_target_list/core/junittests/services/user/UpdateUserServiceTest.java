package java2.application_target_list.core.junittests.services.user;

import java2.application_target_list.core.database.jpa.JpaUserRepository;
import java2.application_target_list.core.requests.user.UpdateUserRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.user.UpdateUserResponse;
import java2.application_target_list.core.services.user.UpdateUserService;
import java2.application_target_list.core.validators.user.UpdateUserValidator;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UpdateUserServiceTest {

    private List<CoreError> errorList;
    @Mock
    private JpaUserRepository jpaUserRepository;
    @Mock
    private UpdateUserValidator updateUserValidator;
    @InjectMocks
    UpdateUserService updateUserService;

    @Before
    public void setup() {
        errorList = new ArrayList<>();
    }

    @Test
    public void shouldChangeUserName() {
        Mockito.when(jpaUserRepository.existsById(1L)).thenReturn(true);
        Mockito.when(jpaUserRepository.changeUserFirstName(1L, "new name")).thenReturn(1);
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(1L, "new first name", "new last name");
        UpdateUserResponse updateUserResponse = updateUserService.execute(updateUserRequest);
        Assert.assertFalse(updateUserResponse.hasErrors());
    }

    @Test
    public void shouldReturnResponseWithErrors_v1() {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(2L, "new first name", "new last name");
        Mockito.when(updateUserValidator.validate(updateUserRequest)).thenReturn(errorList);
        UpdateUserResponse updateUserResponse = updateUserService.execute(updateUserRequest);
        Assert.assertTrue(updateUserResponse.hasErrors());
        Assert.assertEquals(updateUserResponse.getErrorList().size(), 1);
        Assert.assertEquals(updateUserResponse.getErrorList().get(0).getField(), "User ID;");
        Assert.assertEquals(updateUserResponse.getErrorList().get(0).getMessage(), "no user with that ID");
    }

    @Test
    public void shouldReturnResponseWithErrors_v2() {
        errorList.add(new CoreError("User ID","must not be empty!"));
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(null, "new first name", "new last name");
        Mockito.when(updateUserValidator.validate(updateUserRequest)).thenReturn(errorList);
        UpdateUserResponse updateUserResponse = updateUserService.execute(updateUserRequest);
        Assert.assertTrue(updateUserResponse.hasErrors());
        Assert.assertEquals(updateUserResponse.getErrorList().size(), 2);
        Assert.assertEquals(updateUserResponse.getErrorList().get(1).getField(), "User ID;");
        Assert.assertEquals(updateUserResponse.getErrorList().get(1).getMessage(), "no user with that ID");
        Assert.assertEquals(updateUserResponse.getErrorList().get(0).getField(), "User ID");
        Assert.assertEquals(updateUserResponse.getErrorList().get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v3() {
        errorList.add(new CoreError("User ID","must not be negative!"));
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(-3L, "new first name", "new last name");
        Mockito.when(updateUserValidator.validate(updateUserRequest)).thenReturn(errorList);
        UpdateUserResponse updateUserResponse = updateUserService.execute(updateUserRequest);
        Assert.assertTrue(updateUserResponse.hasErrors());
        Assert.assertEquals(updateUserResponse.getErrorList().size(), 2);
        Assert.assertEquals(updateUserResponse.getErrorList().get(1).getField(), "User ID;");
        Assert.assertEquals(updateUserResponse.getErrorList().get(1).getMessage(), "no user with that ID");
        Assert.assertEquals(updateUserResponse.getErrorList().get(0).getField(), "User ID");
        Assert.assertEquals(updateUserResponse.getErrorList().get(0).getMessage(), "must not be negative!");

    }

    @Test
    public void shouldReturnResponseWithErrors_v4() {
        errorList.add(new CoreError("User new first name","must not be empty!"));
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(1L, null, "new last name");
        Mockito.when(updateUserValidator.validate(updateUserRequest)).thenReturn(errorList);
        UpdateUserResponse updateUserResponse = updateUserService.execute(updateUserRequest);
        Assert.assertTrue(updateUserResponse.hasErrors());
        Assert.assertEquals(updateUserResponse.getErrorList().size(), 2);
        Assert.assertEquals(updateUserResponse.getErrorList().get(1).getField(), "User ID;");
        Assert.assertEquals(updateUserResponse.getErrorList().get(1).getMessage(), "no user with that ID");
        Assert.assertEquals(updateUserResponse.getErrorList().get(0).getField(), "User new first name");
        Assert.assertEquals(updateUserResponse.getErrorList().get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v5() {
        errorList.add(new CoreError("User new first name","must not be empty!"));
        errorList.add(new CoreError("User ID","must not be negative!"));
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(-1L, null, "new last name");
        Mockito.when(updateUserValidator.validate(updateUserRequest)).thenReturn(errorList);
        UpdateUserResponse updateUserResponse = updateUserService.execute(updateUserRequest);
        Assert.assertTrue(updateUserResponse.hasErrors());
        Assert.assertEquals(updateUserResponse.getErrorList().size(), 3);
        Assert.assertEquals(updateUserResponse.getErrorList().get(1).getField(), "User ID");
        Assert.assertEquals(updateUserResponse.getErrorList().get(1).getMessage(), "must not be negative!");
        Assert.assertEquals(updateUserResponse.getErrorList().get(0).getField(), "User new first name");
        Assert.assertEquals(updateUserResponse.getErrorList().get(0).getMessage(), "must not be empty!");
        Assert.assertEquals(updateUserResponse.getErrorList().get(2).getField(), "User ID;");
        Assert.assertEquals(updateUserResponse.getErrorList().get(2).getMessage(), "no user with that ID");
    }

    @Test
    public void shouldReturnResponseWithErrors_v6() {
        errorList.add(new CoreError("User new last name","must not be empty!"));
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(1L, "new first name", null);
        Mockito.when(updateUserValidator.validate(updateUserRequest)).thenReturn(errorList);
        UpdateUserResponse updateUserResponse = updateUserService.execute(updateUserRequest);
        Assert.assertTrue(updateUserResponse.hasErrors());
        Assert.assertEquals(updateUserResponse.getErrorList().size(), 2);
        Assert.assertEquals(updateUserResponse.getErrorList().get(1).getField(), "User ID;");
        Assert.assertEquals(updateUserResponse.getErrorList().get(1).getMessage(), "no user with that ID");
        Assert.assertEquals(updateUserResponse.getErrorList().get(0).getField(), "User new last name");
        Assert.assertEquals(updateUserResponse.getErrorList().get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v7() {
        errorList.add(new CoreError("User new last name","must not be empty!"));
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(1L, "new first name", "");
        Mockito.when(updateUserValidator.validate(updateUserRequest)).thenReturn(errorList);
        UpdateUserResponse updateUserResponse = updateUserService.execute(updateUserRequest);
        Assert.assertTrue(updateUserResponse.hasErrors());
        Assert.assertEquals(updateUserResponse.getErrorList().size(), 2);
        Assert.assertEquals(updateUserResponse.getErrorList().get(1).getField(), "User ID;");
        Assert.assertEquals(updateUserResponse.getErrorList().get(1).getMessage(), "no user with that ID");
        Assert.assertEquals(updateUserResponse.getErrorList().get(0).getField(), "User new last name");
        Assert.assertEquals(updateUserResponse.getErrorList().get(0).getMessage(), "must not be empty!");
    }
    @Test
    public void shouldReturnResponseWithErrors_v8() {
        errorList.add(new CoreError("User new first name","must not be empty!"));
        errorList.add(new CoreError("User new last name","must not be empty!"));
        errorList.add(new CoreError("User ID","must not be negative!"));
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(-1L, null, "");
        Mockito.when(updateUserValidator.validate(updateUserRequest)).thenReturn(errorList);
        UpdateUserResponse updateUserResponse = updateUserService.execute(updateUserRequest);
        Assert.assertTrue(updateUserResponse.hasErrors());
        Assert.assertEquals(updateUserResponse.getErrorList().size(), 4);
        Assert.assertEquals(updateUserResponse.getErrorList().get(2).getField(), "User ID");
        Assert.assertEquals(updateUserResponse.getErrorList().get(2).getMessage(), "must not be negative!");
        Assert.assertEquals(updateUserResponse.getErrorList().get(0).getField(), "User new first name");
        Assert.assertEquals(updateUserResponse.getErrorList().get(0).getMessage(), "must not be empty!");
        Assert.assertEquals(updateUserResponse.getErrorList().get(3).getField(), "User ID;");
        Assert.assertEquals(updateUserResponse.getErrorList().get(3).getMessage(), "no user with that ID");
        Assert.assertEquals(updateUserResponse.getErrorList().get(1).getField(), "User new last name");
        Assert.assertEquals(updateUserResponse.getErrorList().get(1).getMessage(), "must not be empty!");
    }

}