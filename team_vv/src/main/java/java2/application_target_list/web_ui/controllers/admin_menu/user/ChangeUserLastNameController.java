package java2.application_target_list.web_ui.controllers.admin_menu.user;

import java2.application_target_list.core.requests.user.ChangeUserLastNameRequest;
import java2.application_target_list.core.requests.user.GetAllUsersRequest;
import java2.application_target_list.core.responses.user.ChangeUserLastNameResponse;
import java2.application_target_list.core.responses.user.GetAllUsersResponse;
import java2.application_target_list.core.services.user.ChangeUserLastNameService;
import java2.application_target_list.core.services.user.GetAllUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChangeUserLastNameController {

    @Autowired
    private ChangeUserLastNameService changeUserLastNameService;
    @Autowired
    private GetAllUserService getAllUserService;

    @GetMapping(value = "/admin_menu/user/changeUserLastName")
    public String showChangeUserLastNamePage(ModelMap modelMap) {
        GetAllUsersResponse getAllUsersResponse = getAllUserService.execute(new GetAllUsersRequest());
        modelMap.addAttribute("users", getAllUsersResponse.getUsersList());
        modelMap.addAttribute("request", new ChangeUserLastNameRequest());
        return "admin_menu/user/changeUserLastName";
    }

    @PostMapping("/admin_menu/user/changeUserLastName")
    public String processChangeUserLastNameRequest(@ModelAttribute(value = "request") ChangeUserLastNameRequest changeUserLastNameRequest,
                                   ModelMap modelMap) {

        ChangeUserLastNameResponse changeUserLastNameResponse = changeUserLastNameService.execute(changeUserLastNameRequest);

        if (changeUserLastNameResponse.hasErrors()) {
            modelMap.addAttribute("errors", changeUserLastNameResponse.getErrorList());
            return "admin_menu/user/changeUserLastName";
        } else {
            return "redirect:/admin_menu/";
        }
    }
}
