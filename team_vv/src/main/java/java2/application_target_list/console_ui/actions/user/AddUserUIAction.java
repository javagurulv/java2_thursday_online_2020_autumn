package java2.application_target_list.console_ui.actions.user;

import java2.application_target_list.console_ui.UIAction;
import java2.application_target_list.core.requests.user.AddUserRequest;
import java2.application_target_list.core.responses.user.AddUserResponse;
import java2.application_target_list.core.services.user.AddUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class AddUserUIAction implements UIAction {

    @Autowired
    private AddUserService addUserService;

    private final Scanner scr = new Scanner(System.in);

    @Override
    public void execute() {
        while (true) {
            String userFirstName = getFirstNameFromUser();
            String userLastName = getLastNameFromUser();

            AddUserRequest addUserRequest = createAddUserRequest(userFirstName, userLastName);
            AddUserResponse addUserResponse = validateAddUserRequest(addUserRequest);

            if (addUserResponse.hasErrors()) {
                printResponseErrors(addUserResponse);
            } else {
                printResponseResultMessage();
                break;
            }
        }
    }

    private AddUserResponse validateAddUserRequest(AddUserRequest addUserRequest){
        return addUserService.execute(addUserRequest);
    }

    private AddUserRequest createAddUserRequest(String userFirstName, String userLastName){
        return new AddUserRequest(userFirstName, userLastName);
    }

    private void printResponseErrors(AddUserResponse addUserResponse){
        addUserResponse.getErrorList().forEach(System.out::println);
    }

    private String getFirstNameFromUser(){
        System.out.print("Enter user first name: ");
        return scr.nextLine();
    }

    private String getLastNameFromUser(){
        System.out.print("Enter user last name: ");
        return scr.nextLine();
    }
    private void printResponseResultMessage(){
        System.out.println("----------");
        System.out.println("User was added to list.");
        System.out.println("----------");
    }

}
