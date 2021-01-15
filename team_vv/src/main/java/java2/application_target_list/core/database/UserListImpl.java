package java2.application_target_list.core.database;

import java2.application_target_list.core.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserListImpl implements UserDatabase {

    List<User> userList = new ArrayList<>();
    Long userId = 0L;


    @Override
    public Long addUser(User user) {
        user.setId(userId += 1);
        userList.add(user);
        return user.getId();
    }

    @Override
    public boolean deleteUser(Long userId) {
        if (isIdInUserList(userId)){
            userList.remove(getUserIndexFromListById(userId));
            return true;
        }
        return false;
    }

    @Override
    public boolean changeUserFirstName(Long userId, String newFirstName) {
        if (isIdInUserList(userId)){
            userList.get(getUserIndexFromListById(userId)).setFirstName(newFirstName);
            return true;
        }
        return false;
    }

    @Override
    public boolean changeUserLastName(Long userId, String newLastName) {
        if (isIdInUserList(userId)){
            userList.get(getUserIndexFromListById(userId)).setLastName(newLastName);
            return true;
        }
        return false;
    }

    @Override
    public List<User> getUsersList() {
        return userList;
    }

    @Override
    public List<User> findUserByFirstName(String userFirstName) {
        List<User> users = new ArrayList<>();

        for (User user : userList){
            if (user.getFirstName().contains(userFirstName)){
                users.add(user);
            }
        }
        return users;
    }

    @Override
    public List<User> findUserByLastName(String userLastName) {
        List<User> users = new ArrayList<>();

        for (User user : userList){
            if (user.getLastName().contains(userLastName)){
                users.add(user);
            }
        }

        return users;
    }


    public boolean isIdInUserList(Long userId) {
        for (User tempUser : userList) {
            if (tempUser.getId().equals(userId)) {
                return true;
            }
        }
        return false;
    }

    private int getUserIndexFromListById(Long userId) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId().equals(userId)) {
                return i;
            }
        }
        return 0;
    }
}