package java2.application_target_list.web_ui.controllers.admin_menu.board;

import java2.application_target_list.core.requests.board.GetUnfinishedRecordsRequest;
import java2.application_target_list.core.requests.board.SetRecordCompleteDateRequest;
import java2.application_target_list.core.responses.board.GetUnfinishedRecordsResponse;
import java2.application_target_list.core.responses.board.SetRecordCompleteDateResponse;
import java2.application_target_list.core.services.board.GetUnfinishedRecordsService;
import java2.application_target_list.core.services.board.SetRecordCompleteDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SetRecordCompleteDateController {

    @Autowired
    private SetRecordCompleteDateService setRecordCompleteDateService;
    @Autowired
    private GetUnfinishedRecordsService getUnfinishedRecordsService;

    @GetMapping(value = "/admin_menu/board/setRecordCompleteDate")
    public String showSetRecordCompleteDateAdminPage(ModelMap modelMap) {
        GetUnfinishedRecordsResponse getUnfinishedRecordsResponse = getUnfinishedRecordsService.execute(new GetUnfinishedRecordsRequest());
        modelMap.addAttribute("records", getUnfinishedRecordsResponse.getRecordList());
        modelMap.addAttribute("request", new SetRecordCompleteDateRequest());
        return "admin_menu/board/setRecordCompleteDate";
    }

    @PostMapping("/admin_menu/board/setRecordCompleteDate")
    public String processSetRecordCompleteDateAdminRequest(@ModelAttribute(value = "request") SetRecordCompleteDateRequest setRecordCompleteDateRequest,
                                   ModelMap modelMap) {

        SetRecordCompleteDateResponse response = setRecordCompleteDateService.execute(setRecordCompleteDateRequest);

        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrorList());
            return "admin_menu/board/setRecordCompleteDate";
        } else {
            return "redirect:/admin_menu/";
        }
    }
}
