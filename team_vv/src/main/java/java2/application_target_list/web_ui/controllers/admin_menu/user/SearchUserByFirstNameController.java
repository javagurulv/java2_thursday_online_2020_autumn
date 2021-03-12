package java2.application_target_list.web_ui.controllers.admin_menu.user;

import java2.application_target_list.core.requests.user.SearchUsersByFirstNameRequest;
import java2.application_target_list.core.responses.user.SearchUserByFirstNameResponse;
import java2.application_target_list.core.services.user.SearchUserByFirstNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SearchUserByFirstNameController {

    @Autowired
    private SearchUserByFirstNameService searchUserByFirstNameService;

    @GetMapping(value = "/admin_menu/user/searchUserByFirstName")
    public String showSearchUserByFirstNameAdminPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new SearchUsersByFirstNameRequest());
        return "admin_menu/user/searchUserByFirstName";
    }

    @PostMapping("/admin_menu/user/searchUserByFirstName")
    public String processSearchUserByFirstNameAdminRequest(@ModelAttribute(value = "request") SearchUsersByFirstNameRequest searchUsersByFirstNameRequest,
                                   ModelMap modelMap) {

        SearchUserByFirstNameResponse searchUserByFirstNameResponse = searchUserByFirstNameService.execute(searchUsersByFirstNameRequest);

        if (searchUserByFirstNameResponse.hasErrors()) {
            modelMap.addAttribute("errors", searchUserByFirstNameResponse.getErrorList());
        } else {
            modelMap.addAttribute("users", searchUserByFirstNameResponse.getUsersList());
        }
        return "admin_menu/user/searchUserByFirstName";
    }
}
