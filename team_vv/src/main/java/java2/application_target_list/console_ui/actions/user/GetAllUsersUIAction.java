package java2.application_target_list.console_ui.actions.user;

import java2.application_target_list.console_ui.UIAction;
import java2.application_target_list.core.domain.User;
import java2.application_target_list.core.requests.user.GetAllUsersRequest;
import java2.application_target_list.core.responses.user.GetAllUsersResponse;
import java2.application_target_list.core.services.user.GetAllUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAllUsersUIAction implements UIAction {

    @Autowired
    private GetAllUserService getAllUserService;

    @Override
    public void execute() {

        GetAllUsersRequest getAllUserRequest = createGetAllUsersRequest();
        GetAllUsersResponse getAllUsersResponse = validateGetAllUsersRequest(getAllUserRequest);

        if (isUsersListEmpty(getAllUsersResponse)) {
            printResponseMessage();
        } else {
            printUsersList(getAllUsersResponse);
        }

    }

    private void printUsersList(GetAllUsersResponse getAllUsersResponse){
        System.out.println("Users: ");
        for (User user : getAllUsersResponse.getUsersList()){
            System.out.println(user.getId() + ". " +
                    user.getFirstName() + " " + user.getLastName());
        }
        System.out.println("----------");
    }

    private void printResponseMessage(){
        System.out.println("----------");
        System.out.println("Users list is empty");
        System.out.println("----------");
    }

    private boolean isUsersListEmpty(GetAllUsersResponse getAllUsersResponse){
        return getAllUsersResponse.getUsersList().isEmpty();
    }

    private GetAllUsersResponse validateGetAllUsersRequest(GetAllUsersRequest getAllUserRequest){
        return getAllUserService.execute(getAllUserRequest);
    }

    private GetAllUsersRequest createGetAllUsersRequest(){
        return new GetAllUsersRequest();
    }


}
