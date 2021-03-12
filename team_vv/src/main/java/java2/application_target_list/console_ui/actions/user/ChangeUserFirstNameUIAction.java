package java2.application_target_list.console_ui.actions.user;

import java2.application_target_list.console_ui.UIAction;
import java2.application_target_list.core.requests.user.ChangeUserFirstNameRequest;
import java2.application_target_list.core.responses.user.ChangeUserFirstNameResponse;
import java2.application_target_list.core.services.user.ChangeUserFirstNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class ChangeUserFirstNameUIAction implements UIAction {

    @Autowired
    private ChangeUserFirstNameService changeUserFirstNameService;

    private final Scanner scr = new Scanner(System.in);

    @Override
    public void execute() {
        while (true) {
            Long userId = getIdFromUser();
            String newUserFirstName = getNewFirstNameFromUser();

            ChangeUserFirstNameRequest changeUserFirstNameRequest = createUserFirstNameRequest(userId, newUserFirstName);
            ChangeUserFirstNameResponse changeUserFirstNameResponse = validateChangeUserFirstNameRequest(changeUserFirstNameRequest);

            if (changeUserFirstNameResponse.hasErrors()) {
                printResponseErrors(changeUserFirstNameResponse);
            } else {
                printResponseResultMessage();
                break;
            }
        }
    }

    private void printResponseResultMessage(){
        System.out.println("----------");
        System.out.println("Target name was changed!");
        System.out.println("----------");
    }

    private void printResponseErrors(ChangeUserFirstNameResponse changeUserFirstNameResponse){
        changeUserFirstNameResponse.getErrorList().forEach(System.out::println);
    }

    private ChangeUserFirstNameResponse validateChangeUserFirstNameRequest(ChangeUserFirstNameRequest changeUserFirstNameRequest){
        return changeUserFirstNameService.execute(changeUserFirstNameRequest);
    }

    private ChangeUserFirstNameRequest createUserFirstNameRequest(Long targetId, String newTargetName){
        return new ChangeUserFirstNameRequest(targetId, newTargetName);
    }

    private Long getIdFromUser(){
        System.out.print("Enter user ID: ");
        return Long.parseLong(scr.nextLine());
    }

    private String getNewFirstNameFromUser(){
        System.out.print("Enter new first name: ");
        return scr.nextLine();
    }
}
