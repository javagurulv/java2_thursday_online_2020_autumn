package java2.application_target_list.web_ui.controllers.admin_menu.target;

import java2.application_target_list.core.requests.target.ChangeTargetNameRequest;
import java2.application_target_list.core.requests.target.GetAllTargetsRequest;
import java2.application_target_list.core.responses.target.ChangeTargetNameResponse;
import java2.application_target_list.core.responses.target.GetAllTargetsResponse;
import java2.application_target_list.core.services.target.ChangeTargetNameService;
import java2.application_target_list.core.services.target.GetAllTargetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChangeTargetNameController {

    @Autowired
    private GetAllTargetsService getAllTargetsService;
    @Autowired
    private ChangeTargetNameService changeTargetNameService;

    @GetMapping(value = "/admin_menu/target/changeTargetName")
    public String showChangeTargetNamePage(ModelMap modelMap) {
        GetAllTargetsResponse getAllTargetsResponse = getAllTargetsService.execute(new GetAllTargetsRequest());
        modelMap.addAttribute("targets", getAllTargetsResponse.getTargetList());
        modelMap.addAttribute("request", new ChangeTargetNameRequest());
        return "admin_menu/target/changeTargetName";
    }

    @PostMapping("/admin_menu/target/changeTargetName")
    public String processChangeTargetName(@ModelAttribute("request") ChangeTargetNameRequest changeTargetNameRequest,
                                          ModelMap modelMap) {
        ChangeTargetNameResponse changeTargetNameResponse = changeTargetNameService.execute(changeTargetNameRequest);

        if (changeTargetNameResponse.hasErrors()) {
            modelMap.addAttribute("errors", changeTargetNameResponse.getErrorList());
            return "admin_menu/target/changeTargetName";
        } else {
            return "redirect:/admin_menu/";
        }
    }
}
