package java2.application_target_list.console_ui.actions.board;

import java2.application_target_list.console_ui.UIAction;
import java2.application_target_list.core.domain.Record;
import java2.application_target_list.core.requests.board.GetFullInfoAboutRecordsRequest;
import java2.application_target_list.core.responses.board.GetFullInfoAboutRecordsResponse;
import java2.application_target_list.core.services.board.GetFullInfoAboutRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetFullInfoAboutRecordsUIAction implements UIAction {

    @Autowired
    private GetFullInfoAboutRecordsService getFullInfoAboutRecordsService;

    @Override
    public void execute() {
    GetFullInfoAboutRecordsRequest getFullInfoAboutRecordsRequest = createGetFullInfoAboutRecordsRequest();
    GetFullInfoAboutRecordsResponse getFullInfoAboutRecordsResponse = validateGetFullInfoAboutRecordsRequest(getFullInfoAboutRecordsRequest);

        if (isRecordListEmpty(getFullInfoAboutRecordsResponse)){
        printResponseMessage();
         } else {
        printRecordsList(getFullInfoAboutRecordsResponse);
    }
}

    private GetFullInfoAboutRecordsResponse validateGetFullInfoAboutRecordsRequest(GetFullInfoAboutRecordsRequest getFullInfoAboutRecordsRequest){
        return getFullInfoAboutRecordsService.execute(getFullInfoAboutRecordsRequest);
    }

    private GetFullInfoAboutRecordsRequest createGetFullInfoAboutRecordsRequest() {
        return new GetFullInfoAboutRecordsRequest();
    }

    private boolean isRecordListEmpty(GetFullInfoAboutRecordsResponse getFullInfoAboutRecordsResponse){
        return getFullInfoAboutRecordsResponse.getRecordList().isEmpty();
    }

    private void printResponseMessage(){
        System.out.println("----------");
        System.out.println("Board is empty");
        System.out.println("----------");
    }

    private void printRecordsList(GetFullInfoAboutRecordsResponse getFullInfoAboutRecordsResponse) {
        System.out.println("ID. [Target name]  [Target description] [Target deadline] [User first name; User Last name] [Record Added Date] [Record Complete Date]");
        for (Record record : getFullInfoAboutRecordsResponse.getRecordList()){
            System.out.println(record.getRecordId() + ". ["
                    + record.getTarget().getName() + "] [" + record.getTarget().getDescription() + "] [" + record.getTarget().getDeadline() + "] ["
                    + record.getUser().getFirstName() + " " + record.getUser().getLastName() + "] ["
                    + record.getDateAdded() + "] [" + record.getDateComplete() + "]");
        }
        System.out.println("----------");
    }
}
