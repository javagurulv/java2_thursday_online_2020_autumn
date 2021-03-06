package java2.application_target_list.web_ui.controllers.user_menu.board;

import java2.application_target_list.core.requests.board.GetUnfinishedRecordsRequest;
import java2.application_target_list.core.responses.board.GetUnfinishedRecordsResponse;
import java2.application_target_list.core.services.board.GetUnfinishedRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowUnfinishedRecordsUserController {

    @Autowired
    private GetUnfinishedRecordsService getUnfinishedRecordsService;

    @GetMapping(value = "/user_menu/board/showUnfinishedRecords")
    public String showUnfinishedRecordsUserPage(ModelMap modelMap) {
        GetUnfinishedRecordsResponse getUnfinishedRecordsResponse = getUnfinishedRecordsService.execute(new GetUnfinishedRecordsRequest());
        modelMap.addAttribute("records", getUnfinishedRecordsResponse.getRecordList());
        return "user_menu/board/showAllRecords";
    }
}
