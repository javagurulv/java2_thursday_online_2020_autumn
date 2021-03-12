package java2.application_target_list.core.acceptancetests.user;

import java2.application_target_list.TargetListApplication;
import java2.application_target_list.core.acceptancetests.DatabaseCleaner;
import java2.application_target_list.core.requests.user.AddUserRequest;
import java2.application_target_list.core.requests.user.GetAllUsersRequest;
import java2.application_target_list.core.responses.user.AddUserResponse;
import java2.application_target_list.core.responses.user.GetAllUsersResponse;
import java2.application_target_list.core.services.user.AddUserService;
import java2.application_target_list.core.services.user.GetAllUserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TargetListApplication.class)
public class AddUserAcceptanceTests {

    @Autowired
    private AddUserService addUserService;
    @Autowired
    private GetAllUserService getAllUserService;
    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Before
    public void setup() {
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

        Assert.assertFalse(addUserResponse1.hasErrors());
        Assert.assertFalse(addUserResponse2.hasErrors());
        Assert.assertEquals(getAllUsersResponse.getUsersList().size(), 2);
        Assert.assertNull(getAllUsersResponse.getErrorList());
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(0).getFirstName(), "name");
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(0).getLastName(), "surname");
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(1).getFirstName(), "name2");
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(1).getLastName(), "surname2");
    }

    @Test
    public void shouldReturnErrorList() {
        AddUserRequest addUserRequest = createAddUserRequest("", "surname2");
        AddUserResponse addUserResponse = createAddUserResponse(addUserRequest);

        Assert.assertEquals(addUserResponse.getErrorList().size(), 1);
        Assert.assertTrue(addUserResponse.hasErrors());
        Assert.assertEquals(addUserResponse.getErrorList().get(0).getField(), "User first name");
        Assert.assertEquals(addUserResponse.getErrorList().get(0).getMessage(), "must not be empty!");
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
}
