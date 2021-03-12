package java2.application_target_list.web_ui.controllers.admin_menu.target;

import java2.application_target_list.core.requests.target.GetAllTargetsRequest;
import java2.application_target_list.core.responses.target.GetAllTargetsResponse;
import java2.application_target_list.core.services.target.GetAllTargetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowAllTargetsController {

    @Autowired
    private GetAllTargetsService getAllTargetsService;

    @GetMapping(value = "/admin_menu/target/showAllTargets")
    public String showAllTargetsAdminPage(ModelMap modelMap) {
        GetAllTargetsResponse getAllTargetsResponse = getAllTargetsService.execute(new GetAllTargetsRequest());
        modelMap.addAttribute("targets", getAllTargetsResponse.getTargetList());
        return "admin_menu/target/showAllTargets";
    }
}
