package java2.application_target_list.core.junittests.validators.user;

import java2.application_target_list.core.database.user.InMemoryUserRepositoryImpl;
import java2.application_target_list.core.database.user.UserRepository;
import java2.application_target_list.core.domain.User;
import java2.application_target_list.core.requests.user.UpdateUserRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.user.UpdateUserValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class UpdateUserValidatorTest {

    private UpdateUserValidator updateUserValidator;

    @Before
    public void setup() {
        updateUserValidator = new UpdateUserValidator();
        UserRepository userRepository = new InMemoryUserRepositoryImpl();
        userRepository.addUser(new User("name", "surname"));
    }

    @Test
    public void testValidate_validRequest() {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(1L, "new first name", "new last name");
        List<CoreError> actualErrors = updateUserValidator.validate(updateUserRequest);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_invalidRequest_v1() {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(-1L, "new first name", "new last name");
        List<CoreError> actualErrors = updateUserValidator.validate(updateUserRequest);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("User ID"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be negative!"));
    }

    @Test
    public void testValidate_invalidRequest_v2() {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(1L, "new first name", "");
        List<CoreError> actualErrors = updateUserValidator.validate(updateUserRequest);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("User new last name"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
    }


    @Test
    public void testValidate_invalidRequest_v3() {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(1L, "", "new last name");
        List<CoreError> actualErrors = updateUserValidator.validate(updateUserRequest);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("User new first name"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
    }

    @Test
    public void testValidate_invalidRequest_v4() {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(1L, null, "new last name");
        List<CoreError> actualErrors = updateUserValidator.validate(updateUserRequest);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("User new first name"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
    }

    @Test
    public void testValidate_invalidRequest_v5() {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(1L, "new first name", null);
        List<CoreError> actualErrors = updateUserValidator.validate(updateUserRequest);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("User new last name"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
    }

    @Test
    public void testValidate_invalidRequest_v6() {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(-1L, null, "new last name");
        List<CoreError> actualErrors = updateUserValidator.validate(updateUserRequest);
        Assert.assertEquals(actualErrors.size(), 2);
        Assert.assertTrue(actualErrors.get(0).getField().contains("User ID"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be negative!"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("User new first name"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must not be empty!"));
    }

    @Test
    public void testValidate_invalidRequest_v7() {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(-1L, "", null);
        List<CoreError> actualErrors = updateUserValidator.validate(updateUserRequest);
        Assert.assertEquals(actualErrors.size(), 3);
        Assert.assertTrue(actualErrors.get(0).getField().contains("User ID"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be negative!"));
        Assert.assertTrue(actualErrors.get(2).getField().contains("User new first name"));
        Assert.assertTrue(actualErrors.get(2).getMessage().contains("must not be empty!"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("User new last name"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must not be empty!"));
    }
}