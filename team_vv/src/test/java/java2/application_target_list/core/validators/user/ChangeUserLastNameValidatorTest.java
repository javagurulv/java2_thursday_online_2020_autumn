package java2.application_target_list.core.validators.user;

import java2.application_target_list.core.database.user.UserRepository;
import java2.application_target_list.core.database.user.InMemoryUserRepositoryImpl;
import java2.application_target_list.core.domain.User;
import java2.application_target_list.core.requests.user.ChangeUserLastNameRequest;
import java2.application_target_list.core.responses.CoreError;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ChangeUserLastNameValidatorTest {

    private ChangeUserLastNameValidator changeUserLastNameValidator;
    private UserRepository userRepository;

    @Before
    public void setup() {
        changeUserLastNameValidator = new ChangeUserLastNameValidator();
        userRepository = new InMemoryUserRepositoryImpl();
        userRepository.addUser(new User("name", "surname"));
    }

    @Test
    public void testValidate_validRequest() {
        ChangeUserLastNameRequest request = new ChangeUserLastNameRequest(1L, "new last name");
        List<CoreError> actualErrors = changeUserLastNameValidator.validate(request, userRepository);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_invalidRequest_v1() {
        ChangeUserLastNameRequest request = new ChangeUserLastNameRequest(-1L, "new last name");
        List<CoreError> actualErrors = changeUserLastNameValidator.validate(request, userRepository);
        Assert.assertEquals(actualErrors.size(), 2);
        Assert.assertTrue(actualErrors.get(0).getField().contains("User ID;"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("no user with that ID"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("User ID"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must not be negative!"));
    }

    @Test
    public void testValidate_invalidRequest_v2() {
        ChangeUserLastNameRequest request = new ChangeUserLastNameRequest(3L, "new last name");
        List<CoreError> actualErrors = changeUserLastNameValidator.validate(request, userRepository);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("User ID;"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("no user with that ID"));
    }

    @Test
    public void testValidate_invalidRequest_v3() {
        ChangeUserLastNameRequest request = new ChangeUserLastNameRequest(1L, "");
        List<CoreError> actualErrors = changeUserLastNameValidator.validate(request, userRepository);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("User new last name"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
    }

    @Test
    public void testValidate_invalidRequest_v4() {
        ChangeUserLastNameRequest request = new ChangeUserLastNameRequest(1L, null);
        List<CoreError> actualErrors = changeUserLastNameValidator.validate(request, userRepository);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("User new last name"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
    }

    @Test
    public void testValidate_invalidRequest_v5() {
        ChangeUserLastNameRequest request = new ChangeUserLastNameRequest(-5L, null);
        List<CoreError> actualErrors = changeUserLastNameValidator.validate(request, userRepository);
        Assert.assertTrue(actualErrors.get(0).getField().contains("User ID;"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("no user with that ID"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("User ID"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must not be negative!"));
        Assert.assertTrue(actualErrors.get(2).getField().contains("User new last name"));
        Assert.assertTrue(actualErrors.get(2).getMessage().contains("must not be empty!"));
    }
}