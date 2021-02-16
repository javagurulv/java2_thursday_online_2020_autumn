package java2.application_target_list.core.acceptancetests.user;

import java2.application_target_list.config.SpringCoreConfiguration;
import java2.application_target_list.core.DatabaseCleaner;
import java2.application_target_list.core.requests.user.AddUserRequest;
import java2.application_target_list.core.requests.user.GetAllUsersRequest;
import java2.application_target_list.core.responses.user.AddUserResponse;
import java2.application_target_list.core.responses.user.GetAllUsersResponse;
import java2.application_target_list.core.services.user.AddUserService;
import java2.application_target_list.core.services.user.GetAllUserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

public class AddUserAcceptanceTests {

    private ApplicationContext applicationContext;
    private AddUserService addUserService;
    private GetAllUserService getAllUserService;
    private DatabaseCleaner databaseCleaner;

    @Before
    public void setup() {
        createServices();
        databaseCleaner.clean();
    }

    @Test
    public void shouldAddUsersToDB() {
        AddUserRequest addUserRequest1 = createAddUserRequest("name", "surname");
        AddUserRequest addUserRequest2 = createAddUserRequest("name2", "surname2");

        AddUserResponse addUserResponse1 = createAddUserResponse(addUserRequest1);
        AddUserResponse addUserResponse2 = createAddUserResponse(addUserRequest2);

        GetAllUsersRequest getAllUsersRequest = createGetAllUsersRequest();
        GetAllUsersResponse getAllUsersResponse = createGetAllUserResponse(getAllUsersRequest);

        assertFalse(addUserResponse1.hasErrors());
        assertFalse(addUserResponse2.hasErrors());
        assertEquals(getAllUsersResponse.getUsersList().size(), 2);
        assertNull(getAllUsersResponse.getErrorList());
        assertEquals(getAllUsersResponse.getUsersList().get(0).getFirstName(), "name");
        assertEquals(getAllUsersResponse.getUsersList().get(0).getLastName(), "surname");
        assertEquals(getAllUsersResponse.getUsersList().get(1).getFirstName(), "name2");
        assertEquals(getAllUsersResponse.getUsersList().get(1).getLastName(), "surname2");
    }

    @Test
    public void shouldReturnErrorList() {
        AddUserRequest addUserRequest = createAddUserRequest("", "surname2");
        AddUserResponse addUserResponse = createAddUserResponse(addUserRequest);

        assertEquals(addUserResponse.getErrorList().size(), 1);
        assertTrue(addUserResponse.hasErrors());
        assertEquals(addUserResponse.getErrorList().get(0).getField(), "User first name");
        assertEquals(addUserResponse.getErrorList().get(0).getMessage(), "must not be empty!");
    }

    private AddUserResponse createAddUserResponse(AddUserRequest addUserRequest) {
        return addUserService.execute(addUserRequest);
    }

    private GetAllUsersResponse createGetAllUserResponse(GetAllUsersRequest getAllUsersRequest) {
        return getAllUserService.execute(getAllUsersRequest);
    }

    private GetAllUsersRequest createGetAllUsersRequest() {
        return new GetAllUsersRequest();
    }

    private AddUserRequest createAddUserRequest(String userFirstName, String userLastName) {
        return new AddUserRequest(userFirstName, userLastName);
    }

    private ApplicationContext createApplicationContext(){
        return new AnnotationConfigApplicationContext(SpringCoreConfiguration.class);
    }

    private DatabaseCleaner createDatabaseCleaner() {
       return applicationContext.getBean(DatabaseCleaner.class);
    }

    private GetAllUserService createGetAllUserService() {
        return applicationContext.getBean(GetAllUserService.class);
    }

    private AddUserService createAddUserService() {
        return applicationContext.getBean(AddUserService.class);
    }

    private void createServices() {
        applicationContext = createApplicationContext();
        addUserService = createAddUserService();
        getAllUserService = createGetAllUserService();
        databaseCleaner = createDatabaseCleaner();
    }
}
