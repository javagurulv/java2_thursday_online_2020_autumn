package java2.application_target_list.console_ui.actions.board;

import java2.application_target_list.console_ui.UIAction;
import java2.application_target_list.core.domain.Record;
import java2.application_target_list.core.requests.board.GetAllRecordsRequest;
import java2.application_target_list.core.responses.board.GetAllRecordsResponse;
import java2.application_target_list.core.services.board.GetAllRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAllRecordsUIActions implements UIAction {

    @Autowired
    private GetAllRecordsService getAllRecordsService;

    @Override
    public void execute() {
        GetAllRecordsRequest getAllRecordsRequest = createGetAllRecordsRequest();
        GetAllRecordsResponse getAllRecordsResponse = validateGetAllRecordRequest(getAllRecordsRequest);

        if (isRecordListEmpty(getAllRecordsResponse)){
            printResponseMessage();
        } else {
            printRecordsList(getAllRecordsResponse);
        }
    }

    private GetAllRecordsResponse validateGetAllRecordRequest(GetAllRecordsRequest getAllRecordsRequest){
        return getAllRecordsService.execute(getAllRecordsRequest);
    }

    private GetAllRecordsRequest createGetAllRecordsRequest() {
        return new GetAllRecordsRequest();
    }

    private boolean isRecordListEmpty(GetAllRecordsResponse getAllRecordsResponse){
        return getAllRecordsResponse.getRecordList().isEmpty();
    }

    private void printResponseMessage(){
        System.out.println("----------");
        System.out.println("Board is empty");
        System.out.println("----------");
    }

    private void printRecordsList(GetAllRecordsResponse getAllRecordsResponse) {
        for (Record record : getAllRecordsResponse.getRecordList()){
            System.out.println(record.getRecordId() + ". " + "Target ID: "
                    + record.getTargetId() + "; User ID: " + record.getUserId()
                    + "; Record Added Date: " + record.getDateAdded() + "; Record Complete Date: "
                    + record.getDateComplete() + ".");
        }
        System.out.println("----------");
    }
}
