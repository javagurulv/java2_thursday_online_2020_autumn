package java2.application_target_list.web_ui.controllers.admin_menu.rest;

import java2.application_target_list.core.requests.board.*;
import java2.application_target_list.core.responses.board.*;
import java2.application_target_list.core.services.board.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin_menu/board")
public class BoardRestController {

    @Autowired
    private GetRecordService getRecordService;
    @Autowired
    private AddRecordService addRecordService;
    @Autowired
    private DeleteRecordService deleteRecordService;
    @Autowired
    private SetRecordCompleteDateService setRecordCompleteDateService;
    @Autowired
    private GetUnfinishedRecordsService getUnfinishedRecordsService;

    @GetMapping(path = "{id}", produces = "application/json")
    public GetRecordResponse getRecord(@PathVariable Long id){
        GetRecordRequest getRecordRequest = new GetRecordRequest(id);
        return getRecordService.execute(getRecordRequest);
    }

    @GetMapping(path = "/get_unfinished_records",
            produces = "application/json")
    public GetUnfinishedRecordsResponse getUnfinishedRecords(){
        return getUnfinishedRecordsService.execute(new GetUnfinishedRecordsRequest());
    }

    @PostMapping(path = "/add_record",
            consumes = "application/json",
            produces = "application/json")
    public AddRecordResponse addRecord(@RequestBody AddRecordRequest addRecordRequest){
        return addRecordService.execute(addRecordRequest);
    }

    @DeleteMapping(path = "/{recordIdToDelete}", produces = "application/json")
    public DeleteRecordResponse deleteRecord(@PathVariable Long recordIdToDelete){
        DeleteRecordRequest deleteRecordRequest = new DeleteRecordRequest(recordIdToDelete);
        return deleteRecordService.execute(deleteRecordRequest);
    }

    @PutMapping(path = "/{recordIdToSetCompleteDate}",
            produces = "application/json")
    public SetRecordCompleteDateResponse setRecordCompleteDate(@PathVariable Long recordIdToSetCompleteDate){
        return setRecordCompleteDateService.execute(new SetRecordCompleteDateRequest(recordIdToSetCompleteDate));
    }

}
