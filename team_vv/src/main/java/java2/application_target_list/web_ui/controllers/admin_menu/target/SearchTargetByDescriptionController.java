package java2.application_target_list.web_ui.controllers.admin_menu.target;

import java2.application_target_list.core.requests.target.SearchTargetByDescriptionRequest;
import java2.application_target_list.core.responses.target.SearchTargetByDescriptionResponse;
import java2.application_target_list.core.services.target.SearchTargetByDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SearchTargetByDescriptionController {

    @Autowired
    private SearchTargetByDescriptionService searchTargetByDescriptionService;

    @GetMapping(value = "/admin_menu/target/searchTargetByDescription")
    public String showSearchTargetByDescriptionAdminPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new SearchTargetByDescriptionRequest());
        return "admin_menu/target/searchTargetByDescription";
    }

    @PostMapping("/admin_menu/target/searchTargetByDescription")
    public String processSearchTargetByDescriptionAdminRequest(@ModelAttribute(value = "request") SearchTargetByDescriptionRequest searchTargetByDescriptionRequest,
                                                   ModelMap modelMap) {

        SearchTargetByDescriptionResponse searchTargetByDescriptionResponse = searchTargetByDescriptionService.execute(searchTargetByDescriptionRequest);

        if (searchTargetByDescriptionResponse.hasErrors()) {
            modelMap.addAttribute("errors", searchTargetByDescriptionResponse.getErrorList());
        } else {
            modelMap.addAttribute("targets", searchTargetByDescriptionResponse.getTargetList());
        }
        return "admin_menu/target/searchTargetByDescription";
    }

}
