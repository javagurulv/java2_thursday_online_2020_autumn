package java2.application_target_list.core.acceptancetests.user;

import java2.application_target_list.TargetListApplication;
import java2.application_target_list.core.acceptancetests.DatabaseCleaner;
import java2.application_target_list.core.requests.Ordering;
import java2.application_target_list.core.requests.Paging;
import java2.application_target_list.core.requests.user.AddUserRequest;
import java2.application_target_list.core.requests.user.SearchUsersByFirstNameRequest;
import java2.application_target_list.core.responses.user.SearchUserByFirstNameResponse;
import java2.application_target_list.core.services.user.AddUserService;
import java2.application_target_list.core.services.user.SearchUserByFirstNameService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TargetListApplication.class)
public class SearchUserByFirstNameAcceptanceTest {

    @Autowired
    private SearchUserByFirstNameService searchUserByFirstNameService;
    @Autowired
    private AddUserService addUserService;
    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Before
    public void setup(){
        databaseCleaner.clean();
        addUsersToDatabase();

        ReflectionTestUtils.setField(searchUserByFirstNameService, "orderingEnabled", true);
        ReflectionTestUtils.setField(searchUserByFirstNameService, "pagingEnabled", true);
    }

    @Test
    public void shouldReturnList() {
        SearchUsersByFirstNameRequest searchUsersByFirstNameRequest = new SearchUsersByFirstNameRequest("name");
        SearchUserByFirstNameResponse searchUserByFirstNameResponse = searchUserByFirstNameService.execute(searchUsersByFirstNameRequest);

        Assert.assertFalse(searchUserByFirstNameResponse.hasErrors());
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().size(), 3);
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getFirstName(), "name1");
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getLastName(), "surname1");
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(1).getFirstName(), "name2");
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(1).getLastName(), "surname2");
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(2).getFirstName(), "name3");
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(2).getLastName(), "surname3");
    }

    @Test
    public void shouldReturnListWithOrderingByFirstNameASC() {
        SearchUsersByFirstNameRequest searchUsersByFirstNameRequest = new SearchUsersByFirstNameRequest("name",
                new Ordering("first name", "ASCENDING"));
        SearchUserByFirstNameResponse searchUserByFirstNameResponse = searchUserByFirstNameService.execute(searchUsersByFirstNameRequest);

        Assert.assertFalse(searchUserByFirstNameResponse.hasErrors());
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().size(), 3);
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getFirstName(), "name1");
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getLastName(), "surname1");
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(1).getFirstName(), "name2");
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(1).getLastName(), "surname2");
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(2).getFirstName(), "name3");
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(2).getLastName(), "surname3");
    }

    @Test
    public void shouldReturnListWithOrderingByLastNameASC() {
        SearchUsersByFirstNameRequest searchUsersByFirstNameRequest = new SearchUsersByFirstNameRequest("name",
                new Ordering("last name", "ASCENDING"));
        SearchUserByFirstNameResponse searchUserByFirstNameResponse = searchUserByFirstNameService.execute(searchUsersByFirstNameRequest);

        Assert.assertFalse(searchUserByFirstNameResponse.hasErrors());
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().size(), 3);
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getFirstName(), "name1");
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getLastName(), "surname1");
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(1).getFirstName(), "name2");
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(1).getLastName(), "surname2");
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(2).getFirstName(), "name3");
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(2).getLastName(), "surname3");
    }

    @Test
    public void shouldReturnListWithOrderingByLastNameDSC() {
        SearchUsersByFirstNameRequest searchUsersByFirstNameRequest = new SearchUsersByFirstNameRequest("name",
                new Ordering("last name", "DESCENDING"));
        SearchUserByFirstNameResponse searchUserByFirstNameResponse = searchUserByFirstNameService.execute(searchUsersByFirstNameRequest);

        Assert.assertFalse(searchUserByFirstNameResponse.hasErrors());
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().size(), 3);
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(2).getFirstName(), "name1");
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(2).getLastName(), "surname1");
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(1).getFirstName(), "name2");
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(1).getLastName(), "surname2");
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getFirstName(), "name3");
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getLastName(), "surname3");
    }

    @Test
    public void shouldReturnListWithOrderingByFirstNameDSC() {
        SearchUsersByFirstNameRequest searchUsersByFirstNameRequest = new SearchUsersByFirstNameRequest("name",
                new Ordering("last name", "DESCENDING"));
        SearchUserByFirstNameResponse searchUserByFirstNameResponse = searchUserByFirstNameService.execute(searchUsersByFirstNameRequest);

        Assert.assertFalse(searchUserByFirstNameResponse.hasErrors());
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().size(), 3);
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(2).getFirstName(), "name1");
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(2).getLastName(), "surname1");
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(1).getFirstName(), "name2");
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(1).getLastName(), "surname2");
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getFirstName(), "name3");
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getLastName(), "surname3");
    }

    @Test
    public void shouldReturnListWithPaging() {
        SearchUsersByFirstNameRequest searchUsersByFirstNameRequest = new SearchUsersByFirstNameRequest("name",
                new Paging(1, 1));
        SearchUserByFirstNameResponse searchUserByFirstNameResponse = searchUserByFirstNameService.execute(searchUsersByFirstNameRequest);

        Assert.assertFalse(searchUserByFirstNameResponse.hasErrors());
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().size(), 1);
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getFirstName(), "name1");
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getLastName(), "surname1");
    }

    @Test
    public void shouldReturnListWithOrderingByFirstNameASCWithPaging() {
        SearchUsersByFirstNameRequest searchUsersByFirstNameRequest = new SearchUsersByFirstNameRequest("name",
                new Paging(1,1),
                new Ordering("first name", "ASCENDING"));
        SearchUserByFirstNameResponse searchUserByFirstNameResponse = searchUserByFirstNameService.execute(searchUsersByFirstNameRequest);

        Assert.assertFalse(searchUserByFirstNameResponse.hasErrors());
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().size(), 1);
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getFirstName(), "name1");
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getLastName(), "surname1");
    }

    @Test
    public void shouldReturnListWithOrderingByLastNameASCWithPaging() {
        SearchUsersByFirstNameRequest searchUsersByFirstNameRequest = new SearchUsersByFirstNameRequest("name",
                new Paging(1,1),
                new Ordering("last name", "ASCENDING"));
        SearchUserByFirstNameResponse searchUserByFirstNameResponse = searchUserByFirstNameService.execute(searchUsersByFirstNameRequest);

        Assert.assertFalse(searchUserByFirstNameResponse.hasErrors());
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().size(), 1);
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getFirstName(), "name1");
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getLastName(), "surname1");
    }

    @Test
    public void shouldReturnListWithOrderingByLastNameDSCWithPaging() {
        SearchUsersByFirstNameRequest searchUsersByFirstNameRequest = new SearchUsersByFirstNameRequest("name",
                new Paging(1,1),
                new Ordering("last name", "DESCENDING"));
        SearchUserByFirstNameResponse searchUserByFirstNameResponse = searchUserByFirstNameService.execute(searchUsersByFirstNameRequest);

        Assert.assertFalse(searchUserByFirstNameResponse.hasErrors());
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().size(), 1);
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getFirstName(), "name3");
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getLastName(), "surname3");
    }

    @Test
    public void shouldReturnListWithOrderingByFirstNameDSCWithPaging() {
        SearchUsersByFirstNameRequest searchUsersByFirstNameRequest = new SearchUsersByFirstNameRequest("name",
                new Paging(1,2),
                new Ordering("last name", "DESCENDING"));
        SearchUserByFirstNameResponse searchUserByFirstNameResponse = searchUserByFirstNameService.execute(searchUsersByFirstNameRequest);

        Assert.assertFalse(searchUserByFirstNameResponse.hasErrors());
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().size(), 2);
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(1).getFirstName(), "name2");
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(1).getLastName(), "surname2");
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getFirstName(), "name3");
        Assert.assertEquals(searchUserByFirstNameResponse.getUsersList().get(0).getLastName(), "surname3");
    }

    private void addUsersToDatabase() {
        AddUserRequest addUserRequest1 = new AddUserRequest("name1", "surname1");
        AddUserRequest addUserRequest2 = new AddUserRequest("name2", "surname2");
        AddUserRequest addUserRequest3 = new AddUserRequest("name3", "surname3");
        AddUserRequest addUserRequest4 = new AddUserRequest("asd", "qwe");

        addUserService.execute(addUserRequest1);
        addUserService.execute(addUserRequest2);
        addUserService.execute(addUserRequest3);
        addUserService.execute(addUserRequest4);
    }
}
