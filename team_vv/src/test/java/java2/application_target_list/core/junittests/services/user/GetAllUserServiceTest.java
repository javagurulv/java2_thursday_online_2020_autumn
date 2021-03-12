package java2.application_target_list.core.junittests.services.user;

import java2.application_target_list.core.database.jpa.JpaUserRepository;
import java2.application_target_list.core.domain.User;
import java2.application_target_list.core.requests.user.GetAllUsersRequest;
import java2.application_target_list.core.responses.user.GetAllUsersResponse;
import java2.application_target_list.core.services.user.GetAllUserService;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.Silent.class)
public class GetAllUserServiceTest {

    private List<User> userList;
    @Mock
    private JpaUserRepository jpaUserRepository;
    @InjectMocks
    GetAllUserService getAllUserService;

    @Before
    public void setup() {
        userList = new ArrayList<>();
    }

    @Test
    public void shouldReturnDB_v1() {
        userList.add(new User("name", "surname"));
        Mockito.when(jpaUserRepository.findAll()).thenReturn(userList);
        GetAllUsersRequest getAllUsersRequest = new GetAllUsersRequest();
        GetAllUsersResponse getAllUsersResponse = getAllUserService.execute(getAllUsersRequest);
        Assert.assertFalse(getAllUsersResponse.getUsersList().isEmpty());
        Assert.assertFalse(getAllUsersResponse.hasErrors());
        Assert.assertEquals(getAllUsersResponse.getUsersList().size(), 1);
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(0).getFirstName(), "name");
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(0).getLastName(), "surname");
    }

    @Test
    public void shouldReturnDB_v2() {
        userList.add(new User("name", "surname"));
        userList.add(new User("name1", "surname2"));
        Mockito.when(jpaUserRepository.findAll()).thenReturn(userList);
        GetAllUsersRequest getAllUsersRequest = new GetAllUsersRequest();
        GetAllUsersResponse getAllUsersResponse = getAllUserService.execute(getAllUsersRequest);
        Assert.assertFalse(getAllUsersResponse.getUsersList().isEmpty());
        Assert.assertFalse(getAllUsersResponse.hasErrors());
        Assert.assertEquals(getAllUsersResponse.getUsersList().size(), 2);
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(0).getFirstName(), "name");
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(0).getLastName(), "surname");
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(1).getFirstName(), "name1");
        Assert.assertEquals(getAllUsersResponse.getUsersList().get(1).getLastName(), "surname2");
    }    @Test
    public void shouldReturnDB_v3() {
        Mockito.when(jpaUserRepository.findAll()).thenReturn(userList);
        GetAllUsersRequest getAllUsersRequest = new GetAllUsersRequest();
        GetAllUsersResponse getAllUsersResponse = getAllUserService.execute(getAllUsersRequest);
        Assert.assertTrue(getAllUsersResponse.getUsersList().isEmpty());
        Assert.assertFalse(getAllUsersResponse.hasErrors());
    }
}