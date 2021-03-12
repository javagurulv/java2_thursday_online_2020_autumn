package java2.application_target_list.web_ui.controllers.admin_menu.target;

import java2.application_target_list.core.requests.target.ChangeTargetDeadlineRequest;
import java2.application_target_list.core.requests.target.GetAllTargetsRequest;
import java2.application_target_list.core.responses.target.ChangeTargetDeadlineResponse;
import java2.application_target_list.core.responses.target.GetAllTargetsResponse;
import java2.application_target_list.core.services.target.ChangeTargetDeadlineService;
import java2.application_target_list.core.services.target.GetAllTargetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChangeTargetDeadlineController {

    @Autowired
    private GetAllTargetsService getAllTargetsService;
    @Autowired
    private ChangeTargetDeadlineService changeTargetDeadlineService;

    @GetMapping(value = "/admin_menu/target/changeTargetDeadline")
    public String showChangeTargetDeadlinePage(ModelMap modelMap){
        GetAllTargetsResponse getAllTargetsResponse = getAllTargetsService.execute(new GetAllTargetsRequest());
        modelMap.addAttribute("targets", getAllTargetsResponse.getTargetList());
        modelMap.addAttribute("request", new ChangeTargetDeadlineRequest());
        return "admin_menu/target/changeTargetDeadline";
    }

    @PostMapping("/admin_menu/target/changeTargetDeadline")
    public String processChangeTargetDeadline(@ModelAttribute("request") ChangeTargetDeadlineRequest changeTargetDeadlineRequest,
                                              ModelMap modelMap){
        ChangeTargetDeadlineResponse changeTargetDeadlineResponse = changeTargetDeadlineService.execute(changeTargetDeadlineRequest);

        if(changeTargetDeadlineResponse.hasErrors()) {
            modelMap.addAttribute("errors", changeTargetDeadlineResponse.getErrorList());
            return "admin_menu/target/changeTargetDeadline";
        } else {
            return "redirect:/admin_menu/";
        }
    }
}
