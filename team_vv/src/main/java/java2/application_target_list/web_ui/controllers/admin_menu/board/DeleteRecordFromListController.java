package java2.application_target_list.web_ui.controllers.admin_menu.board;

import java2.application_target_list.core.requests.board.DeleteRecordRequest;
import java2.application_target_list.core.requests.board.GetFullInfoAboutRecordsRequest;
import java2.application_target_list.core.responses.board.DeleteRecordResponse;
import java2.application_target_list.core.responses.board.GetFullInfoAboutRecordsResponse;
import java2.application_target_list.core.services.board.DeleteRecordService;
import java2.application_target_list.core.services.board.GetFullInfoAboutRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeleteRecordFromListController {

    @Autowired
    private DeleteRecordService deleteRecordService;
    @Autowired
    private GetFullInfoAboutRecordsService getFullInfoAboutRecordsService;

    @GetMapping(value = "/admin_menu/board/deleteRecordFromList")
    public String showDeleteRecordFromListPage(ModelMap modelMap) {
        GetFullInfoAboutRecordsResponse getFullInfoAboutRecordsResponse = getFullInfoAboutRecordsService.execute(new GetFullInfoAboutRecordsRequest());
        modelMap.addAttribute("records", getFullInfoAboutRecordsResponse.getRecordList());
        modelMap.addAttribute("request", new DeleteRecordRequest());
        return "admin_menu/board/deleteRecordFromList";
    }

    @PostMapping("/admin_menu/board/deleteRecordFromList")
    public String processDeleteRecordFromListRequest(@ModelAttribute(value = "request") DeleteRecordRequest deleteRecordRequest,
                                   ModelMap modelMap) {

        DeleteRecordResponse deleteRecordResponse = deleteRecordService.execute(deleteRecordRequest);

        if (deleteRecordResponse.hasErrors()) {
            modelMap.addAttribute("errors", deleteRecordResponse.getErrorList());
            return "/admin_menu/board/deleteRecordFromList";
        } else {
            return "redirect:/admin_menu/";
        }
    }
}
