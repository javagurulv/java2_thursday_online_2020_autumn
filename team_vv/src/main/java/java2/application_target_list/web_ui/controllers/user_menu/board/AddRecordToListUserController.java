package java2.application_target_list.web_ui.controllers.user_menu.board;

import java2.application_target_list.core.requests.board.AddRecordRequest;
import java2.application_target_list.core.requests.target.GetAllTargetsRequest;
import java2.application_target_list.core.requests.user.GetAllUsersRequest;
import java2.application_target_list.core.responses.board.AddRecordResponse;
import java2.application_target_list.core.responses.target.GetAllTargetsResponse;
import java2.application_target_list.core.responses.user.GetAllUsersResponse;
import java2.application_target_list.core.services.board.AddRecordService;
import java2.application_target_list.core.services.target.GetAllTargetsService;
import java2.application_target_list.core.services.user.GetAllUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddRecordToListUserController {

    @Autowired
    private AddRecordService addRecordService;
    @Autowired
    private GetAllTargetsService getAllTargetsService;
    @Autowired
    private GetAllUserService getAllUserService;

    @GetMapping(value = "/user_menu/board/addRecordToList")
    public String showAddRecordToListUserPage(ModelMap modelMap) {
        GetAllTargetsResponse getAllTargetsResponse = getAllTargetsService.execute(new GetAllTargetsRequest());
        GetAllUsersResponse getAllUsersResponse = getAllUserService.execute(new GetAllUsersRequest());
        modelMap.addAttribute("targets", getAllTargetsResponse.getTargetList());
        modelMap.addAttribute("users", getAllUsersResponse.getUsersList());
        modelMap.addAttribute("request", new AddRecordRequest());
        return "user_menu/board/addRecordToList";
    }

    @PostMapping("/user_menu/board/addRecordToList")
    public String processAddRecordToListUserRequest(@ModelAttribute(value = "request") AddRecordRequest addRecordRequest,
                                                     ModelMap modelMap) {

        AddRecordResponse addRecordResponse = addRecordService.execute(addRecordRequest);

        if (addRecordResponse.hasErrors()) {
            modelMap.addAttribute("errors", addRecordResponse.getErrorList());
            return "user_menu/board/addRecordToList";
        } else {
            return "redirect:/user_menu/";
        }
    }
}
