package java2.application_target_list.console_ui.actions.target;

import java2.application_target_list.core.requests.target.ChangeTargetDeadlineRequest;
import java2.application_target_list.core.services.target.ChangeTargetDeadlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java2.application_target_list.console_ui.UIAction;
import java2.application_target_list.core.responses.target.ChangeTargetDeadlineResponse;
import java.util.Scanner;

@Component
public class ChangeTargetDeadlineUIAction implements UIAction {

    @Autowired
    private ChangeTargetDeadlineService changeTargetDeadlineService;

    private final Scanner scr = new Scanner(System.in);

    @Override
    public void execute() {
        while (true) {
            Long targetId = getIdFromUser();
            Long newTargetDeadline = getNewDeadlineFromUser();

            ChangeTargetDeadlineRequest changeTargetDeadlineRequest = createChangeTargetDeadlineRequest(targetId, newTargetDeadline);
            ChangeTargetDeadlineResponse changeTargetDeadlineResponse = validateChangeTargetDeadlineRequest(changeTargetDeadlineRequest);

            if (changeTargetDeadlineResponse.hasErrors()) {
                printResponseErrors(changeTargetDeadlineResponse);
            } else {
                printResponseResultMessage();
                break;
            }
        }
    }

    private void printResponseResultMessage(){
        System.out.println("----------");
        System.out.println("Target deadline was changed!");
        System.out.println("----------");
    }

    private void printResponseErrors(ChangeTargetDeadlineResponse response){
        response.getErrorList().forEach(System.out::println);
    }

    private ChangeTargetDeadlineResponse validateChangeTargetDeadlineRequest(ChangeTargetDeadlineRequest changeTargetDeadlineRequest){
        return changeTargetDeadlineService.execute(changeTargetDeadlineRequest);
    }

    private ChangeTargetDeadlineRequest createChangeTargetDeadlineRequest(Long targetId, Long newTargetDeadline){
        return new ChangeTargetDeadlineRequest(targetId, newTargetDeadline);
    }

    private Long getIdFromUser(){
        System.out.print("Enter target ID: ");
        return Long.parseLong(scr.nextLine());
    }

    private Long getNewDeadlineFromUser(){
        System.out.print("Enter new target deadline: ");
       return Long.parseLong(scr.nextLine());
    }

}
