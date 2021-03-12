package java2.application_target_list.web_ui.controllers.admin_menu.target;

import java2.application_target_list.core.requests.target.ChangeTargetDescriptionRequest;
import java2.application_target_list.core.requests.target.GetAllTargetsRequest;
import java2.application_target_list.core.responses.target.ChangeTargetDescriptionResponse;
import java2.application_target_list.core.responses.target.GetAllTargetsResponse;
import java2.application_target_list.core.services.target.ChangeTargetDescriptionService;
import java2.application_target_list.core.services.target.GetAllTargetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChangeTargetDescriptionController {

    @Autowired
    private GetAllTargetsService getAllTargetsService;
    @Autowired
    private ChangeTargetDescriptionService changeTargetDescriptionService;

    @GetMapping(value = "/admin_menu/target/changeTargetDescription")
    public String showChangeTargetDescriptionPage(ModelMap modelMap) {
        GetAllTargetsResponse getAllTargetsResponse = getAllTargetsService.execute(new GetAllTargetsRequest());
        modelMap.addAttribute("targets", getAllTargetsResponse.getTargetList());
        modelMap.addAttribute("request", new ChangeTargetDescriptionRequest());
        return "admin_menu/target/changeTargetDescription";
    }

    @PostMapping("/admin_menu/target/changeTargetDescription")
    public String processChangeTargetDescription(@ModelAttribute("request") ChangeTargetDescriptionRequest changeTargetDescriptionRequest,
                                                 ModelMap modelMap) {
        ChangeTargetDescriptionResponse changeTargetDescriptionResponse = changeTargetDescriptionService.execute(changeTargetDescriptionRequest);

        if (changeTargetDescriptionResponse.hasErrors()){
            modelMap.addAttribute("errors", changeTargetDescriptionResponse.getErrorList());
            return "admin_menu/target/changeTargetDescription";
        } else {
            return "redirect:/admin_menu/";
        }
    }
}
