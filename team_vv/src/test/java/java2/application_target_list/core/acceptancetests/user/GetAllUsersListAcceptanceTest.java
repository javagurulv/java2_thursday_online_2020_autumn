package java2.application_target_list.core.acceptancetests.user;

import java2.application_target_list.TargetListApplication;
import java2.application_target_list.core.acceptancetests.DatabaseCleaner;
import java2.application_target_list.core.requests.user.AddUserRequest;
import java2.application_target_list.core.requests.user.GetAllUsersRequest;
import java2.application_target_list.core.responses.user.AddUserResponse;
import java2.application_target_list.core.responses.user.GetAllUsersResponse;
import java2.application_target_list.core.services.user.AddUserService;
import java2.application_target_list.core.services.user.GetAllUserService;;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TargetListApplication.class)
public class GetAllUsersListAcceptanceTest {

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
    public void shouldReturnUsersList() {
        AddUserRequest addUserRequest = createAddUserRequest("name", "surname");
        AddUserResponse addUserResponse = createAddUserResponse(addUserRequest);

        GetAllUsersRequest getAllUsersRequest = createGetAllUsersRequest();
        GetAllUsersResponse getAllUsersResponse = createGetAllUserResponse(getAllUsersRequest);

        Assert.assertFalse(addUserResponse.hasErrors());
        Assert.assertEquals(getAllUsersResponse.getUsersList().size(), 1);
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(0).getFirstName(), "name");
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(0).getLastName(), "surname");
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
