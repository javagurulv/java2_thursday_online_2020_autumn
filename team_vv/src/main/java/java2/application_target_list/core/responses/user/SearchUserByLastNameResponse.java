package java2.application_target_list.core.responses.user;

import java2.application_target_list.core.domain.User;
import java2.application_target_list.core.responses.CoreError;

import java.util.List;

public class SearchUserByLastNameResponse extends UserCoreResponse{

    private List<User> usersList;

    public SearchUserByLastNameResponse(List<CoreError> errorList, List<User> usersList) {
        super(errorList);
        this.usersList = usersList;
    }

    public List<User> getUsersList() {
        return usersList;
    }
}
