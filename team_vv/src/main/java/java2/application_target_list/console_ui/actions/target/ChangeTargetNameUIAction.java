package java2.application_target_list.console_ui.actions.target;

import java2.application_target_list.core.requests.target.ChangeTargetNameRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java2.application_target_list.console_ui.UIAction;
import java2.application_target_list.core.responses.target.ChangeTargetNameResponse;
import java2.application_target_list.core.services.target.ChangeTargetNameService;
import java.util.Scanner;

@Component
public class ChangeTargetNameUIAction implements UIAction {

    @Autowired
    private ChangeTargetNameService changeTargetNameService;

    private final Scanner scr = new Scanner(System.in);

    @Override
    public void execute() {
        while (true) {
            Long targetId = getIdFromUser();
            String newTargetName = getNewNameFromUser();

            ChangeTargetNameRequest changeTargetNameRequest = createChangeTargetNameRequest(targetId, newTargetName);
            ChangeTargetNameResponse changeTargetNameResponse = validateChangeTargetNameRequest(changeTargetNameRequest);

            if (changeTargetNameResponse.hasErrors()) {
                printResponseErrors(changeTargetNameResponse);
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

    private void printResponseErrors(ChangeTargetNameResponse response){
        response.getErrorList().forEach(System.out::println);
    }

    private ChangeTargetNameResponse validateChangeTargetNameRequest(ChangeTargetNameRequest changeTargetNameRequest){
        return changeTargetNameService.execute(changeTargetNameRequest);
    }

    private ChangeTargetNameRequest createChangeTargetNameRequest(Long targetId, String newTargetName){
        return new ChangeTargetNameRequest(targetId, newTargetName);
    }

    private Long getIdFromUser(){
        System.out.print("Enter target ID: ");
        return Long.parseLong(scr.nextLine());
    }

    private String getNewNameFromUser(){
        System.out.print("Enter new target name: ");
        return scr.nextLine();
    }

}
