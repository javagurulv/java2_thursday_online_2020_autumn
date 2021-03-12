package java2.application_target_list.console_ui.actions.target;

import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.requests.target.DeleteTargetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java2.application_target_list.console_ui.UIAction;
import java2.application_target_list.core.responses.target.DeleteTargetResponse;
import java2.application_target_list.core.services.target.DeleteTargetService;


import java.util.Scanner;

@Component
public class DeleteUIAction implements UIAction {

    @Autowired
    private DeleteTargetService deleteTargetService;
    @Autowired
    private JpaTargetRepository jpaTargetRepository;

    private final Scanner scr = new Scanner(System.in);


    @Override
    public void execute() {
        while (true) {

            if (targetListIsEmpty()){
                printBreakMessage();
                break;
            }

            Long targetId = getIdFromUser();


            DeleteTargetRequest deleteTargetRequest = createDeleteTargetRequest(targetId);
            DeleteTargetResponse deleteTargetResponse = validateDeleteTargetRequest(deleteTargetRequest);

            if (deleteTargetResponse.hasErrors()) {
                printResponseErrors(deleteTargetResponse);
            } else {
                printResponseResultMessage();
                break;
            }
        }
    }

    private void printResponseResultMessage(){
        System.out.println("----------");
        System.out.println("Target was deleted!");
        System.out.println("----------");
    }

    private void printResponseErrors(DeleteTargetResponse response){
        response.getErrorList().forEach(System.out::println);
    }

    private DeleteTargetResponse validateDeleteTargetRequest(DeleteTargetRequest deleteTargetRequest){
        return deleteTargetService.execute(deleteTargetRequest);
    }

    private DeleteTargetRequest createDeleteTargetRequest(Long targetId){
        return new DeleteTargetRequest(targetId);
    }

    private Long getIdFromUser(){
        System.out.print("Enter target ID: ");
        return Long.parseLong(scr.nextLine());
    }

    private void printBreakMessage(){
        System.out.println("----------");
        System.out.println("Targets list is empty!");
        System.out.println("----------");
    }

    private boolean targetListIsEmpty(){
        return jpaTargetRepository.findAll().isEmpty();
    }

}
