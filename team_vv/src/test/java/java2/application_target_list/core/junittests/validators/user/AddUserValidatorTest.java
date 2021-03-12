package java2.application_target_list.core.junittests.validators.user;

import java2.application_target_list.core.requests.user.AddUserRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.user.AddUserValidator;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;

public class AddUserValidatorTest {

    private final AddUserValidator addUserValidator = new AddUserValidator();

    @Test
    public void testValidate_validRequest() {
        AddUserRequest request = new AddUserRequest("name", "surname");
        List<CoreError> actualErrors = addUserValidator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_invalidFirstNameRequest_v1() {
        AddUserRequest request = new AddUserRequest(null, "surname");
        List<CoreError> actualErrors = addUserValidator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("User first name"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
    }

    @Test
    public void testValidate_invalidFirstNameRequest_v2() {
        AddUserRequest request = new AddUserRequest("", "surname");
        List<CoreError> actualErrors = addUserValidator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("User first name"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
    }

    @Test
    public void testValidate_invalidLastNameRequest_v1() {
        AddUserRequest request = new AddUserRequest("name", "");
        List<CoreError> actualErrors = addUserValidator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("User last name"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
    }

    @Test
    public void testValidate_invalidLastNameRequest_v2() {
        AddUserRequest request = new AddUserRequest("name", null);
        List<CoreError> actualErrors = addUserValidator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("User last name"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
    }

    @Test
    public void testValidate_invalidFirstAndLastNameRequest() {
        AddUserRequest request = new AddUserRequest("", null);
        List<CoreError> actualErrors = addUserValidator.validate(request);
        Assert.assertEquals(actualErrors.size(), 2);
        Assert.assertTrue(actualErrors.get(0).getField().contains("User first name"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("User last name"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must not be empty!"));
    }
}