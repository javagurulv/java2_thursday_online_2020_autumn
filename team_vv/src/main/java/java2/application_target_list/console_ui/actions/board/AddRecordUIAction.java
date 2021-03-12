package java2.application_target_list.console_ui.actions.board;

import java2.application_target_list.console_ui.UIAction;
import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.database.jpa.JpaUserRepository;
import java2.application_target_list.core.requests.board.AddRecordRequest;
import java2.application_target_list.core.responses.board.AddRecordResponse;
import java2.application_target_list.core.services.board.AddRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class AddRecordUIAction implements UIAction {

    @Autowired private AddRecordService addRecordService;
    @Autowired private JpaUserRepository jpaUserRepository;
    @Autowired private JpaTargetRepository jpaTargetRepository;
    private final Scanner scr = new Scanner(System.in);

    @Override
    public void execute() {

        while(true){

            if (isTargetOrUserListEmpty()){
                printBreakMessage();
                break;
            }

            Long targetId = getTargetIdFromUser();
            Long userId = getUserIdFromUser();

            AddRecordRequest addRecordRequest = createAddRecordRequest(targetId, userId);
            AddRecordResponse addRecordResponse = validateAddRecordRequest(addRecordRequest);

            if (addRecordResponse.hasErrors()){
                printResponseErrors(addRecordResponse);
            } else {
                printResponseResultMessage();
                break;
            }
        }
    }

    private AddRecordResponse validateAddRecordRequest(AddRecordRequest addRecordRequest){
        return addRecordService.execute(addRecordRequest);
    }

    private AddRecordRequest createAddRecordRequest(Long targetId, Long userId) {
        return  new AddRecordRequest(targetId, userId);
    }

    private void printBreakMessage(){
        System.out.println("----------");
        System.out.println("Targets list or Users list is empty!");
        System.out.println("----------");
    }

    private boolean isTargetOrUserListEmpty(){
        return jpaUserRepository.findAll().isEmpty() || jpaTargetRepository.findAll().isEmpty();
    }

    private Long getTargetIdFromUser(){
        System.out.print("Enter target ID: ");
        return Long.parseLong(scr.nextLine());
    }

    private Long getUserIdFromUser(){
        System.out.print("Enter user ID: ");
        return Long.parseLong(scr.nextLine());
    }

    private void printResponseErrors(AddRecordResponse addRecordResponse){
        addRecordResponse.getErrorList().forEach(System.out::println);
    }
    private void printResponseResultMessage(){
        System.out.println("----------");
        System.out.println("Record was added to list.");
        System.out.println("----------");
    }

}
