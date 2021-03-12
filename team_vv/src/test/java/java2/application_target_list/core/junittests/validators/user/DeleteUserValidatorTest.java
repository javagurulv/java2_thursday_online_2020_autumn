package java2.application_target_list.core.junittests.validators.user;

import java2.application_target_list.core.database.user.UserRepository;
import java2.application_target_list.core.database.user.InMemoryUserRepositoryImpl;
import java2.application_target_list.core.domain.User;
import java2.application_target_list.core.requests.user.DeleteUserRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.user.DeleteUserValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class DeleteUserValidatorTest {

    private DeleteUserValidator deleteUserValidator;

    @Before
    public void setup() {
        deleteUserValidator = new DeleteUserValidator();
        UserRepository userRepository = new InMemoryUserRepositoryImpl();
        userRepository.addUser(new User("name", "surname"));
    }

    @Test
    public void testValidate_validRequest() {
        DeleteUserRequest request = new DeleteUserRequest(1L);
        List<CoreError> actualErrors = deleteUserValidator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_invalidRequest_v2() {
        DeleteUserRequest request = new DeleteUserRequest(-2L);
        List<CoreError> actualErrors = deleteUserValidator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("User ID"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be negative!"));
    }
}