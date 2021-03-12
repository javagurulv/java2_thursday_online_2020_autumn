package java2.application_target_list.core.junittests.database;

import java2.application_target_list.core.database.user.UserRepository;
import java2.application_target_list.core.database.user.InMemoryUserRepositoryImpl;
import java2.application_target_list.core.domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class UserTargetListImplTest {

    private UserRepository userRepository;
    private User firstUser;
    private User secondUser;
    private User thirdUser;

    @Before
    public void setup() {
        userRepository = new InMemoryUserRepositoryImpl();
        firstUser = new User("name1", "surname1");
        secondUser = new User("name2", "surname2");
        thirdUser = new User("name3", "surname3");
    }


    @Test
    public void testAddUser_v1() {
        userRepository.addUser(firstUser);
        Assert.assertEquals(userRepository.getUsersList().size(), 1);
        Long actual = firstUser.getId();
        Long expected = 1L;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testAddUser_v2() {
        userRepository.addUser(firstUser);
        userRepository.addUser(secondUser);
        Assert.assertEquals(userRepository.getUsersList().size(), 2);
        Long actual = secondUser.getId();
        Long expected = 2L;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testAddUser_v3() {
        userRepository.addUser(firstUser);
        userRepository.addUser(secondUser);
        userRepository.addUser(thirdUser);
        Assert.assertEquals(userRepository.getUsersList().size(), 3);
        Long actual = thirdUser.getId();
        Long expected = 3L;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testDeleteUser_v1() {
        userRepository.addUser(firstUser);
        userRepository.addUser(secondUser);
        userRepository.addUser(thirdUser);

        userRepository.deleteUser(2L);
        Assert.assertEquals(userRepository.getUsersList().size(), 2);
        Assert.assertEquals(userRepository.getUsersList().get(0).getFirstName(), "name1");
        Assert.assertEquals(userRepository.getUsersList().get(1).getFirstName(), "name3");
    }

    @Test
    public void testDeleteUser_v2() {
        userRepository.addUser(firstUser);
        userRepository.addUser(secondUser);
        userRepository.addUser(thirdUser);
        userRepository.deleteUser(1L);
        Assert.assertEquals(userRepository.getUsersList().size(), 2);
        Assert.assertEquals(userRepository.getUsersList().get(0).getFirstName(), "name2");
        Assert.assertEquals(userRepository.getUsersList().get(1).getFirstName(), "name3");
    }

    @Test
    public void testChangeUserFirstName_v1() {
        userRepository.addUser(firstUser);
        userRepository.changeUserFirstName(1L, "New Name");
        Assert.assertEquals(userRepository.getUsersList().get(0).getFirstName(), "New Name");
        Assert.assertEquals(userRepository.getUsersList().get(0).getLastName(), "surname1");
    }

    @Test
    public void testChangeUserFirstName_v2() {
        userRepository.addUser(firstUser);
        userRepository.addUser(secondUser);
        userRepository.changeUserFirstName(2L, "New Name");
        Assert.assertEquals(userRepository.getUsersList().get(1).getFirstName(), "New Name");
        Assert.assertEquals(userRepository.getUsersList().get(1).getLastName(), "surname2");
    }

    @Test
    public void testChangeUserLastName_v1() {
        userRepository.addUser(firstUser);
        userRepository.changeUserLastName(1L, "New Surname");
        Assert.assertEquals(userRepository.getUsersList().get(0).getFirstName(), "name1");
        Assert.assertEquals(userRepository.getUsersList().get(0).getLastName(), "New Surname");
    }

    @Test
    public void testChangeUserLastName_v2() {
        userRepository.addUser(firstUser);
        userRepository.addUser(secondUser);
        userRepository.changeUserLastName(2L, "New Surname");
        Assert.assertEquals(userRepository.getUsersList().get(1).getFirstName(), "name2");
        Assert.assertEquals(userRepository.getUsersList().get(1).getLastName(), "New Surname");
    }

    @Test
    public void testGetUsersList_v1() {
        userRepository.addUser(firstUser);
        List<User> users = userRepository.getUsersList();
        Assert.assertEquals(users.size(), 1);
        Assert.assertEquals(users.get(0).getFirstName(), "name1");
        Assert.assertEquals(users.get(0).getLastName(), "surname1");
    }

    @Test
    public void testGetUsersList_v2() {
        userRepository.addUser(firstUser);
        userRepository.addUser(secondUser);
        List<User> users = userRepository.getUsersList();
        Assert.assertEquals(users.size(), 2);
        Assert.assertEquals(users.get(0).getFirstName(), "name1");
        Assert.assertEquals(users.get(0).getLastName(), "surname1");
        Assert.assertEquals(users.get(1).getFirstName(), "name2");
        Assert.assertEquals(users.get(1).getLastName(), "surname2");
    }

    @Test
    public void testGetUsersList_v3() {
        userRepository.addUser(firstUser);
        userRepository.addUser(secondUser);
        userRepository.deleteUser(1L);
        List<User> users = userRepository.getUsersList();
        Assert.assertEquals(users.size(), 1);
        Assert.assertEquals(users.get(0).getFirstName(), "name2");
        Assert.assertEquals(users.get(0).getLastName(), "surname2");
    }

    @Test
    public void testFindUserByFirstName_v1() {
        userRepository.addUser(firstUser);
        userRepository.addUser(secondUser);
        userRepository.addUser(thirdUser);
        List<User> users = userRepository.findUserByFirstName("name1");
        Assert.assertEquals(users.size(), 1);
        Assert.assertEquals(users.get(0).getFirstName(), "name1");
        Assert.assertEquals(users.get(0).getLastName(), "surname1");
    }

    @Test
    public void testFindUserByFirstName_v2() {
        userRepository.addUser(firstUser);
        userRepository.addUser(secondUser);
        userRepository.addUser(thirdUser);
        List<User> users = userRepository.findUserByFirstName("name");
        Assert.assertEquals(users.size(), 3);
        Assert.assertEquals(users.get(0).getFirstName(), "name1");
        Assert.assertEquals(users.get(0).getLastName(), "surname1");
        Assert.assertEquals(users.get(1).getFirstName(), "name2");
        Assert.assertEquals(users.get(1).getLastName(), "surname2");
        Assert.assertEquals(users.get(2).getFirstName(), "name3");
        Assert.assertEquals(users.get(2).getLastName(), "surname3");
    }

    @Test
    public void testFindUserByLastName_v1() {
        userRepository.addUser(firstUser);
        userRepository.addUser(secondUser);
        userRepository.addUser(thirdUser);
        List<User> users = userRepository.findUserByLastName("surname2");
        Assert.assertEquals(users.size(), 1);
        Assert.assertEquals(users.get(0).getFirstName(), "name2");
        Assert.assertEquals(users.get(0).getLastName(), "surname2");
    }

    @Test
    public void testFindUserByLastName_v2() {
        userRepository.addUser(firstUser);
        userRepository.addUser(secondUser);
        userRepository.addUser(thirdUser);
        List<User> users = userRepository.findUserByLastName("surname");
        Assert.assertEquals(users.size(), 3);
        Assert.assertEquals(users.get(0).getFirstName(), "name1");
        Assert.assertEquals(users.get(0).getLastName(), "surname1");
        Assert.assertEquals(users.get(1).getFirstName(), "name2");
        Assert.assertEquals(users.get(1).getLastName(), "surname2");
        Assert.assertEquals(users.get(2).getFirstName(), "name3");
        Assert.assertEquals(users.get(2).getLastName(), "surname3");
    }


}