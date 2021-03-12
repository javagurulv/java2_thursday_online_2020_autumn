package java2.application_target_list.web_ui.controllers.admin_menu.user;

import java2.application_target_list.core.requests.user.AddUserRequest;
import java2.application_target_list.core.responses.user.AddUserResponse;
import java2.application_target_list.core.services.user.AddUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddUserController {

    @Autowired
    private AddUserService addUserService;

    @GetMapping(value = "/admin_menu/user/addUserToList")
    public String showAddUserPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddUserRequest());
        return "admin_menu/user/addUserToList";
    }

    @PostMapping("admin_menu//user/addUserToList")
    public String processAddUserRequest(@ModelAttribute(value = "request") AddUserRequest addUserRequest,
                                          ModelMap modelMap) {

        AddUserResponse addUserResponse = addUserService.execute(addUserRequest);

        if (addUserResponse.hasErrors()) {
            modelMap.addAttribute("errors", addUserResponse.getErrorList());
            return "admin_menu/user/addUserToList";
        } else {
            return "redirect:/admin_menu/";
        }
    }

}
