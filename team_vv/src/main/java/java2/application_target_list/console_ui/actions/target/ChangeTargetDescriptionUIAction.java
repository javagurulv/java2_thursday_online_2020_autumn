package java2.application_target_list.console_ui.actions.target;

import java2.application_target_list.core.requests.target.ChangeTargetDescriptionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java2.application_target_list.console_ui.UIAction;
import java2.application_target_list.core.responses.target.ChangeTargetDescriptionResponse;
import java2.application_target_list.core.services.target.ChangeTargetDescriptionService;
import java.util.Scanner;

@Component
public class ChangeTargetDescriptionUIAction implements UIAction {

    @Autowired
    private ChangeTargetDescriptionService changeTargetDescriptionService;

    private final Scanner scr = new Scanner(System.in);

    @Override
    public void execute() {
        while (true) {

            Long targetId = getIdFromUser();
            String newTargetDescription = getNewDescriptionFromUser();

            ChangeTargetDescriptionRequest changeTargetDescriptionRequest = createChangeTargetDescriptionRequest(targetId, newTargetDescription);
            ChangeTargetDescriptionResponse changeTargetDescriptionResponse = validateChangeTargetDescriptionRequest(changeTargetDescriptionRequest);

            if (changeTargetDescriptionResponse.hasErrors()) {
                printResponseErrors(changeTargetDescriptionResponse);
            } else {
                printResponseResultMessage();
                break;
            }
        }
    }

    private void printResponseResultMessage(){
        System.out.println("----------");
        System.out.println("Target description was changed!");
        System.out.println("----------");
    }

    private void printResponseErrors(ChangeTargetDescriptionResponse response){
        response.getErrorList().forEach(System.out::println);
    }

    private ChangeTargetDescriptionResponse validateChangeTargetDescriptionRequest(ChangeTargetDescriptionRequest changeTargetDescriptionRequest){
        return changeTargetDescriptionService.execute(changeTargetDescriptionRequest);
    }

    private ChangeTargetDescriptionRequest createChangeTargetDescriptionRequest(Long targetId, String newTargetDescription){
        return new ChangeTargetDescriptionRequest(targetId, newTargetDescription);
    }

    private Long getIdFromUser(){
        System.out.print("Enter target ID: ");
        return Long.parseLong(scr.nextLine());
    }

    private String getNewDescriptionFromUser(){
        System.out.print("Enter new target description: ");
        return scr.nextLine();
    }
}
