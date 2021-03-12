package java2.application_target_list.console_ui.actions.user;

import java2.application_target_list.console_ui.UIAction;
import java2.application_target_list.core.requests.user.ChangeUserLastNameRequest;
import java2.application_target_list.core.responses.user.ChangeUserLastNameResponse;
import java2.application_target_list.core.services.user.ChangeUserLastNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class ChangeUserLastNameUIAction implements UIAction {

    @Autowired
    private ChangeUserLastNameService changeUserLastNameService;

    private final Scanner scr = new Scanner(System.in);

    @Override
    public void execute() {
        while (true) {
            Long userId = getIdFromUser();
            String newUserLastName = getNewLastNameFromUser();

            ChangeUserLastNameRequest changeUserLastNameRequest = createChangeUserLastNameRequest(userId, newUserLastName);
            ChangeUserLastNameResponse changeUserLastNameResponse = validateChangeUserLastNameRequest(changeUserLastNameRequest);

            if (changeUserLastNameResponse.hasErrors()) {
                printResponseErrors(changeUserLastNameResponse);
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

    private void printResponseErrors(ChangeUserLastNameResponse changeUserLastNameResponse){
        changeUserLastNameResponse.getErrorList().forEach(System.out::println);
    }

    private ChangeUserLastNameResponse validateChangeUserLastNameRequest(ChangeUserLastNameRequest changeUserLastNameRequest){
        return changeUserLastNameService.execute(changeUserLastNameRequest);
    }

    private ChangeUserLastNameRequest createChangeUserLastNameRequest(Long targetId, String newTargetName){
        return new ChangeUserLastNameRequest (targetId, newTargetName);
    }

    private Long getIdFromUser(){
        System.out.print("Enter user ID: ");
        return Long.parseLong(scr.nextLine());
    }

    private String getNewLastNameFromUser(){
        System.out.print("Enter new last name: ");
        return scr.nextLine();
    }
}
