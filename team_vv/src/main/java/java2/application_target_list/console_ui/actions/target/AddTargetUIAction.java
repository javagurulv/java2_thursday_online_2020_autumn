package java2.application_target_list.console_ui.actions.target;

import java2.application_target_list.core.requests.target.AddTargetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java2.application_target_list.console_ui.UIAction;
import java2.application_target_list.core.responses.target.AddTargetResponse;
import java2.application_target_list.core.services.target.AddTargetService;
import java.util.Scanner;

@Component
public class AddTargetUIAction implements UIAction {

    @Autowired
    private AddTargetService addTargetService;

    private final Scanner scr = new Scanner(System.in);

    @Override
    public void execute() {

        while (true){
            String targetName = getNameFromUser();
            String targetDescription = getDescriptionFromUser();
            Long targetDeadline = getDeadlineFromUser();

            AddTargetRequest addTargetRequest = createAddTargetRequest(targetName,targetDescription,targetDeadline);
            AddTargetResponse addTargetResponse = validateAddTargetRequest(addTargetRequest);

            if (addTargetResponse.hasErrors()) {
                printResponseErrors(addTargetResponse);
            } else {
                printResponseResultMessage();
                break;
            }
        }
    }

    private void printResponseResultMessage(){
        System.out.println("----------");
        System.out.println("Your target was added to list.");
        System.out.println("----------");
    }

    private void printResponseErrors(AddTargetResponse response){
        response.getErrorList().forEach(System.out::println);
    }

    private AddTargetResponse validateAddTargetRequest(AddTargetRequest addTargetRequest){
        return addTargetService.execute(addTargetRequest);
    }

    private AddTargetRequest createAddTargetRequest(String targetName, String targetDescription, Long targetDeadline){
        return new AddTargetRequest(targetName,targetDescription,targetDeadline);
    }

    private String getNameFromUser(){
        System.out.print("Enter target name: ");
        return scr.nextLine();
    }

    private String getDescriptionFromUser(){
        System.out.print("Enter target description: ");
        return scr.nextLine();
    }

    private Long getDeadlineFromUser(){
        System.out.print("Enter target deadline(days): ");
        return Long.parseLong(scr.nextLine());
    }
}
