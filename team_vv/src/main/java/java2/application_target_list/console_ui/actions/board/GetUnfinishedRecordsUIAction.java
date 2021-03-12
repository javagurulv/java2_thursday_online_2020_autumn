package java2.application_target_list.console_ui.actions.board;

import java2.application_target_list.console_ui.UIAction;
import java2.application_target_list.core.domain.Record;
import java2.application_target_list.core.requests.board.GetUnfinishedRecordsRequest;
import java2.application_target_list.core.responses.board.GetUnfinishedRecordsResponse;
import java2.application_target_list.core.services.board.GetUnfinishedRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetUnfinishedRecordsUIAction implements UIAction {

    @Autowired
    private GetUnfinishedRecordsService getUnfinishedRecordsService;

    @Override
    public void execute() {
        GetUnfinishedRecordsRequest getUnfinishedRecordsRequest = createGetUnfinishedRecordsRequest();
        GetUnfinishedRecordsResponse getUnfinishedRecordsResponse = validateGetUnfinishedRecordsRequest(getUnfinishedRecordsRequest);

        if (isRecordListEmpty(getUnfinishedRecordsResponse)){
            printResponseMessage();
        } else {
            printRecordsList(getUnfinishedRecordsResponse);
        }
    }

    private GetUnfinishedRecordsResponse validateGetUnfinishedRecordsRequest(GetUnfinishedRecordsRequest getUnfinishedRecordsRequest){
        return getUnfinishedRecordsService.execute(getUnfinishedRecordsRequest);
    }

    private GetUnfinishedRecordsRequest createGetUnfinishedRecordsRequest() {
        return new GetUnfinishedRecordsRequest();
    }

    private boolean isRecordListEmpty(GetUnfinishedRecordsResponse getUnfinishedRecordsResponse){
        return getUnfinishedRecordsResponse.getRecordList().isEmpty();
    }


    private void printResponseMessage(){
        System.out.println("----------");
        System.out.println("All targets is completed!");
        System.out.println("----------");
    }

    private void printRecordsList(GetUnfinishedRecordsResponse getUnfinishedRecordsResponse) {
        System.out.println("ID. [Target name]  [Target description] [Target deadline] [User first name; User Last name] [Record Added Date] [Record Complete Date]");
        for (Record record : getUnfinishedRecordsResponse.getRecordList()){
            System.out.println(record.getRecordId() + ". ["
                    + record.getTarget().getName() + "] [" + record.getTarget().getDescription() + "] [" + record.getTarget().getDeadline() + "] ["
                    + record.getUser().getFirstName() + " " + record.getUser().getLastName() + "] ["
                    + record.getDateAdded() + "] [" + record.getDateComplete() + "]");
        }
        System.out.println("----------");
    }
}
