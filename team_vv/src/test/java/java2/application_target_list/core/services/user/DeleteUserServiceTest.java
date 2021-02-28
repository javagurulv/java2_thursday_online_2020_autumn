package java2.application_target_list.core.services.user;

import java2.application_target_list.core.database.jpa.JpaUserRepository;
import java2.application_target_list.core.requests.user.DeleteUserRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.user.DeleteUserResponse;
import java2.application_target_list.core.validators.user.DeleteUserValidator;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.Silent.class)
public class DeleteUserServiceTest extends TestCase {

    private List<CoreError> errors;
    @Mock
    private JpaUserRepository jpaUserRepository;
    @Mock
    private DeleteUserValidator deleteUserValidator;
    @InjectMocks
    private DeleteUserService deleteUserService;

    @Before
    public void setup() {
        errors = new ArrayList<>();
    }

//    @Test
//    public void shouldDeleteUserFromDatabase() {
////        Mockito.when(userRepository.isIdInUserList(1L)).thenReturn(true);
//        Mockito.when(jpaUserRepository.existsById(1L)).thenReturn(true);
//        DeleteUserRequest deleteUserRequest = new DeleteUserRequest(1L);
//        DeleteUserResponse deleteUserResponse = deleteUserService.execute(deleteUserRequest);
//        assertFalse(deleteUserResponse.hasErrors());
//    }

    @Test
    public void shouldReturnResponseWithErrors_v1() {
        DeleteUserRequest deleteUserRequest = new DeleteUserRequest(2L);
        Mockito.when(deleteUserValidator.validate(deleteUserRequest)).thenReturn(errors);
        DeleteUserResponse deleteUserResponse = deleteUserService.execute(deleteUserRequest);
        assertTrue(deleteUserResponse.hasErrors());
        assertEquals(deleteUserResponse.getErrorList().size(), 1);
        assertEquals(deleteUserResponse.getErrorList().get(0).getField(), "User ID;");
        assertEquals(deleteUserResponse.getErrorList().get(0).getMessage(), "no user with that ID");
    }

    @Test
    public void shouldReturnResponseWithErrors_v2() {
        errors.add(new CoreError("User ID;", "must not be negative!"));
        DeleteUserRequest deleteUserRequest = new DeleteUserRequest(-2L);
        Mockito.when(deleteUserValidator.validate(deleteUserRequest)).thenReturn(errors);
        DeleteUserResponse deleteUserResponse = deleteUserService.execute(deleteUserRequest);
        assertTrue(deleteUserResponse.hasErrors());
        assertEquals(deleteUserResponse.getErrorList().size(), 2);
        assertEquals(deleteUserResponse.getErrorList().get(1).getField(), "User ID;");
        assertEquals(deleteUserResponse.getErrorList().get(1).getMessage(), "no user with that ID");
        assertEquals(deleteUserResponse.getErrorList().get(0).getField(), "User ID;");
        assertEquals(deleteUserResponse.getErrorList().get(0).getMessage(), "must not be negative!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v3() {
        errors.add(new CoreError("User ID;", "must not be empty!"));
        DeleteUserRequest deleteUserRequest = new DeleteUserRequest(null);
        Mockito.when(deleteUserValidator.validate(deleteUserRequest)).thenReturn(errors);
        DeleteUserResponse deleteUserResponse = deleteUserService.execute(deleteUserRequest);
        assertTrue(deleteUserResponse.hasErrors());
        assertEquals(deleteUserResponse.getErrorList().size(), 2);
        assertEquals(deleteUserResponse.getErrorList().get(1).getField(), "User ID;");
        assertEquals(deleteUserResponse.getErrorList().get(1).getMessage(), "no user with that ID");
        assertEquals(deleteUserResponse.getErrorList().get(0).getField(), "User ID;");
        assertEquals(deleteUserResponse.getErrorList().get(0).getMessage(), "must not be empty!");
    }
}