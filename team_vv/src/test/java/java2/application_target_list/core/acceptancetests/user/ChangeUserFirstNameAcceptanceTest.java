package java2.application_target_list.core.acceptancetests.user;

import java2.application_target_list.TargetListApplication;
import java2.application_target_list.core.acceptancetests.DatabaseCleaner;
import java2.application_target_list.core.domain.User;
import java2.application_target_list.core.requests.user.AddUserRequest;
import java2.application_target_list.core.requests.user.ChangeUserFirstNameRequest;
import java2.application_target_list.core.requests.user.GetAllUsersRequest;
import java2.application_target_list.core.responses.user.AddUserResponse;
import java2.application_target_list.core.responses.user.ChangeUserFirstNameResponse;
import java2.application_target_list.core.responses.user.GetAllUsersResponse;
import java2.application_target_list.core.services.user.AddUserService;
import java2.application_target_list.core.services.user.ChangeUserFirstNameService;
import java2.application_target_list.core.services.user.GetAllUserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TargetListApplication.class)
public class ChangeUserFirstNameAcceptanceTest {

    @Autowired
    private AddUserService addUserService;
    @Autowired
    private GetAllUserService getAllUserService;
    @Autowired
    private ChangeUserFirstNameService changeUserFirstNameService;
    @Autowired
    private DatabaseCleaner databaseCleaner;

    private List<User> userList;
    private Long firstUserId;
    private Long secondUserId;

    @Before
    public void setup() {
        databaseCleaner.clean();
        addUsersToDatabase();
        userList = getAllUserService.execute(new GetAllUsersRequest()).getUsersList();
    }

    @Test
    public void shouldChangeUserFirstName() {

        ChangeUserFirstNameResponse changeUserFirstNameResponse =
                changeUserFirstNameService.execute(new ChangeUserFirstNameRequest(firstUserId, "New Name"));

        GetAllUsersResponse getAllUsersResponse = getAllUserService.execute(new GetAllUsersRequest());

        Assert.assertFalse(changeUserFirstNameResponse.hasErrors());
        Assert.assertEquals(getAllUsersResponse.getUsersList().size(), 2);
        Assert.assertFalse(getAllUsersResponse.hasErrors());
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(0).getFirstName(), "New Name");
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(0).getLastName(), "surname");
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(1).getFirstName(), "name2");
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(1).getLastName(), "surname2");
    }

    @Test
    public void shouldReturnErrorList() {
        ChangeUserFirstNameResponse changeUserFirstNameResponse = changeUserFirstNameService.execute(
                new ChangeUserFirstNameRequest(3L, "New Name"));

        GetAllUsersResponse getAllUsersResponse = getAllUserService.execute(new GetAllUsersRequest());

        Assert.assertTrue(changeUserFirstNameResponse.hasErrors());
        Assert.assertEquals(changeUserFirstNameResponse.getErrorList().size(), 1);
        Assert.assertEquals(changeUserFirstNameResponse.getErrorList().get(0).getField(), "User ID;");
        Assert.assertEquals(changeUserFirstNameResponse.getErrorList().get(0).getMessage(), "no user with that ID");
        Assert.assertEquals(getAllUsersResponse.getUsersList().size(), 2);
        Assert.assertFalse(getAllUsersResponse.hasErrors());
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(0).getFirstName(), "name");
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(0).getLastName(), "surname");
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(1).getFirstName(), "name2");
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(1).getLastName(), "surname2");
    }


    private AddUserResponse createAddUserResponse(AddUserRequest addUserRequest) {
        return addUserService.execute(addUserRequest);
    }

    private AddUserRequest createAddUserRequest(String userFirstName, String userLastName) {
        return new AddUserRequest(userFirstName, userLastName);
    }

    private void addUsersToDatabase() {
        AddUserRequest addUserRequest1 = createAddUserRequest("name", "surname");
        AddUserRequest addUserRequest2 = createAddUserRequest("name2", "surname2");
        AddUserResponse addUserResponse1 = createAddUserResponse(addUserRequest1);
        AddUserResponse addUserResponse2 = createAddUserResponse(addUserRequest2);
        firstUserId = addUserResponse1.getNewUser().getId();
        secondUserId = addUserResponse2.getNewUser().getId();
    }
}
