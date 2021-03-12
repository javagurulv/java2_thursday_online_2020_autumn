package java2.application_target_list.web_ui.controllers.admin_menu.target;

import java2.application_target_list.core.requests.target.SearchTargetByNameRequest;
import java2.application_target_list.core.responses.target.SearchTargetByNameResponse;
import java2.application_target_list.core.services.target.SearchTargetByNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SearchTargetByNameController {

    @Autowired
    private SearchTargetByNameService searchTargetByNameService;

    @GetMapping(value = "/admin_menu/target/searchTargetByName")
    public String showSearchTargetByNameAdminPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new SearchTargetByNameRequest());
        return "admin_menu/target/searchTargetByName";
    }

    @PostMapping("/admin_menu/target/searchTargetByName")
    public String processSearchTargetByNameAdminRequest(@ModelAttribute(value = "request") SearchTargetByNameRequest searchTargetByNameRequest,
                                   ModelMap modelMap) {

        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);

        if (searchTargetByNameResponse.hasErrors()) {
            modelMap.addAttribute("errors", searchTargetByNameResponse.getErrorList());
        } else {
            modelMap.addAttribute("targets", searchTargetByNameResponse.getTargetsList());
        }
        return "admin_menu/target/searchTargetByName";
    }
}
