package java2.application_target_list.web_ui.controllers.admin_menu.user;

import java2.application_target_list.core.requests.user.ChangeUserFirstNameRequest;
import java2.application_target_list.core.requests.user.GetAllUsersRequest;
import java2.application_target_list.core.responses.user.ChangeUserFirstNameResponse;
import java2.application_target_list.core.responses.user.GetAllUsersResponse;
import java2.application_target_list.core.services.user.ChangeUserFirstNameService;
import java2.application_target_list.core.services.user.GetAllUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChangeUserFirstNameController {

    @Autowired
    private ChangeUserFirstNameService changeUserFirstNameService;
    @Autowired
    private GetAllUserService getAllUserService;

    @GetMapping(value = "/admin_menu/user/changeUserFirstName")
    public String showChangeUserFirstNamePage(ModelMap modelMap) {
        GetAllUsersResponse getAllUsersResponse = getAllUserService.execute(new GetAllUsersRequest());
        modelMap.addAttribute("users", getAllUsersResponse.getUsersList());
        modelMap.addAttribute("request", new ChangeUserFirstNameRequest());
        return "admin_menu/user/changeUserFirstName";
    }

    @PostMapping("/admin_menu/user/changeUserFirstName")
    public String processChangeUserFirstNameRequest(@ModelAttribute(value = "request") ChangeUserFirstNameRequest changeUserFirstNameRequest,
                                   ModelMap modelMap) {

        ChangeUserFirstNameResponse changeUserFirstNameResponse = changeUserFirstNameService.execute(changeUserFirstNameRequest);

        if (changeUserFirstNameResponse.hasErrors()) {
            modelMap.addAttribute("errors", changeUserFirstNameResponse.getErrorList());
            return "admin_menu/user/changeUserFirstName";
        } else {
            return "redirect:/admin_menu/";
        }
    }
}
