package java2.application_target_list.core.acceptancetests.user;

import java2.application_target_list.TargetListApplication;
import java2.application_target_list.core.acceptancetests.DatabaseCleaner;
import java2.application_target_list.core.requests.user.*;
import java2.application_target_list.core.responses.user.*;
import java2.application_target_list.core.services.user.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TargetListApplication.class)
public class AcceptanceTest {

    @Autowired
    private AddUserService addUserService;
    @Autowired
    private DeleteUserService deleteUserService;
    @Autowired
    private GetAllUserService getAllUsersService;
    @Autowired
    private ChangeUserFirstNameService changeUserFirstNameService;
    @Autowired
    private ChangeUserLastNameService changeUserLastNameService;
    @Autowired
    private SearchUserByFirstNameService searchUserByFirstNameService;
    @Autowired
    private SearchUserByLastNameService searchUserByLastNameService;
    @Autowired
    private DatabaseCleaner databaseCleaner;

    private Long firstUserId;
    private Long secondUserId;
    private Long thirdUserId;

    @Before
    public void setup() {
        databaseCleaner.clean();
    }

    @Test
    public void acceptanceTest() {
        addUsersToDB();
        deleteFirstUser();
        changeSecondUserFirstName();
        changeThirdUserLastName();
        checkChanges();
        searchUsersByFirstName();
        searchUsersByLastName();
    }

    private void searchUsersByLastName() {
        SearchUsersByLastNameRequest searchUsersByLastNameRequest = new SearchUsersByLastNameRequest("New Surname");
        SearchUserByLastNameResponse searchUserByLastNameResponse = searchUserByLastNameService.execute(searchUsersByLastNameRequest);
        Assert.assertFalse(searchUserByLastNameResponse.hasErrors());
        Assert.assertEquals(searchUserByLastNameResponse.getUsersList().size(), 1);
        Assert.assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getFirstName(), "name3");
        Assert.assertEquals(searchUserByLastNameResponse.getUsersList().get(0).getLastName(), "New Surname");
    }

    private void searchUsersByFirstName() {
        SearchUsersByFirstNameRequest searchUsersByFirstNameRequest = new SearchUsersByFirstNameRequest("New Name");
        SearchUserByFirstNameResponse searchUserByFirstNameResponse = searchUserByFirstNameService.execute(searchUsersByFirstNameRequest);
        Assert.assertFalse(searchUserByFirstNameResponse.hasErrors());
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().size(), 1);
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getFirstName(), "New Name");
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getLastName(), "surname2");
    }

    private void checkChanges() {
        GetAllUsersRequest getAllUsersRequest = new GetAllUsersRequest();
        GetAllUsersResponse getAllUsersResponse = getAllUsersService.execute(getAllUsersRequest);
        Assert.assertEquals(getAllUsersResponse.getUsersList().size(), 2);
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(0).getFirstName(), "New Name");
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(0).getLastName(), "surname2");
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(1).getFirstName(), "name3");
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(1).getLastName(), "New Surname");
    }

    private void changeThirdUserLastName() {
        ChangeUserLastNameRequest changeUserLastNameRequest = new ChangeUserLastNameRequest(thirdUserId, "New Surname");
        ChangeUserLastNameResponse changeUserLastNameResponse = changeUserLastNameService.execute(changeUserLastNameRequest);
        Assert.assertFalse(changeUserLastNameResponse.hasErrors());
    }

    private void changeSecondUserFirstName() {
        ChangeUserFirstNameRequest changeUserFirstNameRequest = new ChangeUserFirstNameRequest(secondUserId, "New Name");
        ChangeUserFirstNameResponse changeUserFirstNameResponse = changeUserFirstNameService.execute(changeUserFirstNameRequest);
        Assert.assertFalse(changeUserFirstNameResponse.hasErrors());

        GetAllUsersRequest getAllUsersRequest = new GetAllUsersRequest();
        GetAllUsersResponse getAllUsersResponse = getAllUsersService.execute(getAllUsersRequest);
        Assert.assertEquals(getAllUsersResponse.getUsersList().size(), 2);
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(0).getFirstName(), "New Name");
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(0).getLastName(), "surname2");
    }

    private void deleteFirstUser() {
        DeleteUserRequest deleteUserRequest = new DeleteUserRequest(firstUserId);
        DeleteUserResponse deleteUserResponse = deleteUserService.execute(deleteUserRequest);
        Assert.assertFalse(deleteUserResponse.hasErrors());
    }

    private void addUsersToDB() {
        AddUserRequest addUserRequest1 = new AddUserRequest("name1", "surname1");
        AddUserRequest addUserRequest2 = new AddUserRequest("name2", "surname2");
        AddUserRequest addUserRequest3 = new AddUserRequest("name3", "surname3");

        AddUserResponse addUserResponse1 = addUserService.execute(addUserRequest1);
        AddUserResponse addUserResponse2 = addUserService.execute(addUserRequest2);
        AddUserResponse addUserResponse3 = addUserService.execute(addUserRequest3);

        firstUserId = addUserResponse1.getNewUser().getId();
        secondUserId = addUserResponse2.getNewUser().getId();
        thirdUserId = addUserResponse3.getNewUser().getId();

        Assert.assertFalse(addUserResponse1.hasErrors());
        Assert.assertFalse(addUserResponse2.hasErrors());
        Assert.assertFalse(addUserResponse3.hasErrors());
    }
}
