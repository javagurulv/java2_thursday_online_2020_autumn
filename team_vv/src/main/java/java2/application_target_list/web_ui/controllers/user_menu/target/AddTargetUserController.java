package java2.application_target_list.web_ui.controllers.user_menu.target;

import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.responses.target.AddTargetResponse;
import java2.application_target_list.core.services.target.AddTargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddTargetUserController {

    @Autowired
    private AddTargetService addTargetService;

    @GetMapping(value = "/user_menu/target/addTargetToList")
    public String showAddTargetUserPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddTargetRequest());
        return "user_menu/target/addTargetToList";
    }


    @PostMapping("/user_menu/target/addTargetToList")
    public String processAddTargetUserRequest(@ModelAttribute(value = "request") AddTargetRequest addTargetRequest,
                                               ModelMap modelMap) {
        AddTargetResponse addTargetResponse = addTargetService.execute(addTargetRequest);

        if (addTargetResponse.hasErrors()) {
            modelMap.addAttribute("errors", addTargetResponse.getErrorList());
            return "user_menu/target/addTargetToList";
        } else {
            return "redirect:/user_menu";
        }
    }
}
