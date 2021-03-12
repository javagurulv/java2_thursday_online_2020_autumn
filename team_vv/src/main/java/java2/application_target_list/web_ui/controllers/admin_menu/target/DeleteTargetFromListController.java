package java2.application_target_list.web_ui.controllers.admin_menu.target;

import java2.application_target_list.core.requests.target.DeleteTargetRequest;
import java2.application_target_list.core.requests.target.GetAllTargetsRequest;
import java2.application_target_list.core.responses.target.DeleteTargetResponse;
import java2.application_target_list.core.responses.target.GetAllTargetsResponse;
import java2.application_target_list.core.services.target.DeleteTargetService;
import java2.application_target_list.core.services.target.GetAllTargetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeleteTargetFromListController {

    @Autowired
    private GetAllTargetsService getAllTargetsService;
    @Autowired
    private DeleteTargetService deleteTargetService;

    @GetMapping(value = "/admin_menu/target/deleteTargetFromList")
    public String showDeleteTargetPage(ModelMap modelMap) {
        GetAllTargetsResponse getAllTargetsResponse = getAllTargetsService.execute(new GetAllTargetsRequest());
        modelMap.addAttribute("targets", getAllTargetsResponse.getTargetList());
        modelMap.addAttribute("request", new DeleteTargetRequest());
        return "admin_menu/target/deleteTargetFromList";
    }

    @PostMapping("/admin_menu/target/deleteTargetFromList")
    public String processDeleteTargetRequest(@ModelAttribute(value = "request") DeleteTargetRequest deleteTargetRequest, ModelMap modelMap){
        DeleteTargetResponse deleteTargetResponse = deleteTargetService.execute(deleteTargetRequest);

        if (deleteTargetResponse.hasErrors()) {
            modelMap.addAttribute("errors", deleteTargetResponse.getErrorList());
            return "admin_menu/target/deleteTargetFromList";
        } else {
            return "redirect:/admin_menu/";
        }
    }

}
