package java2.application_target_list.console_ui.actions.board;

import java2.application_target_list.console_ui.UIAction;
import java2.application_target_list.core.requests.board.SetRecordCompleteDateRequest;
import java2.application_target_list.core.responses.board.SetRecordCompleteDateResponse;
import java2.application_target_list.core.services.board.SetRecordCompleteDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class SetRecordCompleteDateUIAction implements UIAction {

    @Autowired
    private SetRecordCompleteDateService setRecordCompleteDateService;

    private final Scanner scr = new Scanner(System.in);

    @Override
    public void execute() {
        while (true) {
            Long recordId = getIdFromUser();

            SetRecordCompleteDateRequest setRecordCompleteDateRequest = createSetRecordCompleteDateRequest(recordId);
            SetRecordCompleteDateResponse setRecordCompleteDateResponse = validateSetRecordCompleteDateRequest(setRecordCompleteDateRequest);

            if (setRecordCompleteDateResponse.hasErrors()) {
                printResponseErrors(setRecordCompleteDateResponse);
            } else {
                printResponseResultMessage();
                break;
            }
        }
    }

    private SetRecordCompleteDateResponse validateSetRecordCompleteDateRequest(SetRecordCompleteDateRequest setRecordCompleteDateRequest){
        return setRecordCompleteDateService.execute(setRecordCompleteDateRequest);
    }

    private SetRecordCompleteDateRequest createSetRecordCompleteDateRequest(Long recordId){
        return new SetRecordCompleteDateRequest(recordId);
    }

    private Long getIdFromUser(){
        System.out.print("Enter record ID: ");
        return Long.parseLong(scr.nextLine());
    }
    private void printResponseErrors(SetRecordCompleteDateResponse setRecordCompleteDateResponse){
        setRecordCompleteDateResponse.getErrorList().forEach(System.out::println);
    }

    private void printResponseResultMessage(){
        System.out.println("----------");
        System.out.println("Complete date was added!");
        System.out.println("----------");
    }
}
