package java2.application_target_list.web_ui.controllers.user_menu.user;


import java2.application_target_list.core.requests.user.SearchUsersByLastNameRequest;
import java2.application_target_list.core.responses.user.SearchUserByLastNameResponse;
import java2.application_target_list.core.services.user.SearchUserByLastNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SearchUserByLastNameUserController {

    @Autowired
    private SearchUserByLastNameService searchUserByLastNameService;

    @GetMapping(value = "/user_menu/user/searchUserByLastName")
    public String showSearchUserByFirstNameUserPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new SearchUsersByLastNameRequest());
        return "user_menu/user/searchUserByLastName";
    }

    @PostMapping("/user_menu/user/searchUserByLastName")
    public String processSearchUserByFirstNameUserRequest(@ModelAttribute(value = "request") SearchUsersByLastNameRequest searchUsersByFirstNameRequest,
                                                           ModelMap modelMap) {

        SearchUserByLastNameResponse searchUserByLastNameResponse = searchUserByLastNameService.execute(searchUsersByFirstNameRequest);

        if (searchUserByLastNameResponse.hasErrors()) {
            modelMap.addAttribute("errors", searchUserByLastNameResponse.getErrorList());
        } else {
            modelMap.addAttribute("users", searchUserByLastNameResponse.getUsersList());
        }
        return "user_menu/user/searchUserByLastName";
    }
}
