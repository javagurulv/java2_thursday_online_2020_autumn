package java2.application_target_list.web_ui.controllers.user_menu.board;

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
public class SetRecordCompleteDateUserController {

    @Autowired
    private SetRecordCompleteDateService setRecordCompleteDateService;
    @Autowired
    private GetUnfinishedRecordsService getUnfinishedRecordsService;

    @GetMapping(value = "/user_menu/board/setRecordCompleteDate")
    public String showSetRecordCompleteDateUserPage(ModelMap modelMap) {
        GetUnfinishedRecordsResponse getUnfinishedRecordsResponse = getUnfinishedRecordsService.execute(new GetUnfinishedRecordsRequest());
        modelMap.addAttribute("records", getUnfinishedRecordsResponse.getRecordList());
        modelMap.addAttribute("request", new SetRecordCompleteDateRequest());
        return "user_menu/board/setRecordCompleteDate";
    }

    @PostMapping("/user_menu/board/setRecordCompleteDate")
    public String processSetRecordCompleteDateUserRequest(@ModelAttribute(value = "request") SetRecordCompleteDateRequest setRecordCompleteDateRequest,
                                                           ModelMap modelMap) {

        SetRecordCompleteDateResponse response = setRecordCompleteDateService.execute(setRecordCompleteDateRequest);

        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrorList());
            return "user_menu/board/setRecordCompleteDate";
        } else {
            return "redirect:/user_menu/";
        }
    }
}
