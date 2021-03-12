package java2.application_target_list.web_ui.controllers.admin_menu.user;

import java2.application_target_list.core.requests.user.DeleteUserRequest;
import java2.application_target_list.core.requests.user.GetAllUsersRequest;
import java2.application_target_list.core.responses.user.DeleteUserResponse;
import java2.application_target_list.core.responses.user.GetAllUsersResponse;
import java2.application_target_list.core.services.user.DeleteUserService;
import java2.application_target_list.core.services.user.GetAllUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeleteUserController {

    @Autowired
    private DeleteUserService deleteUserService;
    @Autowired
    private GetAllUserService getAllUserService;

    @GetMapping(value = "/admin_menu/user/deleteUserFromList")
    public String showDeleteUserPage(ModelMap modelMap) {
        GetAllUsersResponse getAllUsersResponse = getAllUserService.execute(new GetAllUsersRequest());
        modelMap.addAttribute("users", getAllUsersResponse.getUsersList());
        modelMap.addAttribute("request", new DeleteUserRequest());
        return "admin_menu/user/deleteUserFromList";
    }

    @PostMapping("/admin_menu/user/deleteUserFromList")
    public String processDeleteUserRequest(@ModelAttribute(value = "request") DeleteUserRequest deleteUserRequest,
                                   ModelMap modelMap) {

        DeleteUserResponse deleteUserResponse = deleteUserService.execute(deleteUserRequest);

        if (deleteUserResponse.hasErrors()) {
            modelMap.addAttribute("errors", deleteUserResponse.getErrorList());
            return "admin_menu/user/deleteUserFromList";
        } else {
            return "redirect:/admin_menu/";
        }
    }
}
