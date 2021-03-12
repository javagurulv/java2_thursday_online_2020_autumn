package java2.application_target_list.console_ui.actions.target;

import java2.application_target_list.core.requests.target.GetAllTargetsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.console_ui.UIAction;
import java2.application_target_list.core.responses.target.GetAllTargetsResponse;
import java2.application_target_list.core.services.target.GetAllTargetsService;


@Component
public class GetAllTargetsUIAction implements UIAction {

    @Autowired
    private GetAllTargetsService getAllTargetsService;

    @Override
    public void execute() {
        GetAllTargetsRequest getAllTargetsRequest = createGetAllTargetsRequest();
        GetAllTargetsResponse getAllTargetsResponse = validateGetAllTargetsRequest(getAllTargetsRequest);

        if (isTargetListEmpty(getAllTargetsResponse)) {
            printResponseMessage();
        } else {
           printTargetList(getAllTargetsResponse);
        }

    }

    private void printTargetList(GetAllTargetsResponse response){
        System.out.println("Targets: ");
        for (Target target : response.getTargetList()){
            System.out.println(target.getId() + ". " +
                    target.getName() + " [" + target.getDescription() + "] " +
                    target.getDeadline() + " days");
        }
        System.out.println("----------");
    }

    private void printResponseMessage(){
        System.out.println("----------");
        System.out.println("Targets list is empty");
        System.out.println("----------");
    }

    private boolean isTargetListEmpty(GetAllTargetsResponse response){
        return response.getTargetList().isEmpty();
    }

    private GetAllTargetsResponse validateGetAllTargetsRequest(GetAllTargetsRequest getAllTargetsRequest){
        return getAllTargetsService.execute(getAllTargetsRequest);
    }

    private GetAllTargetsRequest createGetAllTargetsRequest(){
        return new GetAllTargetsRequest();
    }

}
