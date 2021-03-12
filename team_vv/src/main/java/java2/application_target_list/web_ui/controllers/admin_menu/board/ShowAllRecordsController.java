package java2.application_target_list.web_ui.controllers.admin_menu.board;

import java2.application_target_list.core.requests.board.GetFullInfoAboutRecordsRequest;
import java2.application_target_list.core.responses.board.GetFullInfoAboutRecordsResponse;
import java2.application_target_list.core.services.board.GetFullInfoAboutRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowAllRecordsController {

    @Autowired
    private GetFullInfoAboutRecordsService getFullInfoAboutRecordsService;

    @GetMapping(value = "/admin_menu/board/showAllRecords")
    public String showAllRecordsAdminPage(ModelMap modelMap) {
        GetFullInfoAboutRecordsResponse getFullInfoAboutRecordsResponse = getFullInfoAboutRecordsService.execute(new GetFullInfoAboutRecordsRequest());
        modelMap.addAttribute("records", getFullInfoAboutRecordsResponse.getRecordList());
        return "admin_menu/board/showAllRecords";
    }
}
