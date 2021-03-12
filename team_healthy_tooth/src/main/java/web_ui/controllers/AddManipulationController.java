package web_ui.controllers;

import dental_clinic.core.requests.manipulation.AddManipulationRequest;
import dental_clinic.core.responses.manipulation.AddManipulationResponse;
import dental_clinic.core.services.manipulation.AddManipulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddManipulationController {

    @Autowired
    private AddManipulationService addManipulationService;

    @GetMapping(value = "/addManipulation")
    public String showAddManipulationPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddManipulationRequest());
        return "addManipulation";
    }

    @PostMapping("/addManipulation")
    public String processAddManipulationRequest(@ModelAttribute(value = "request") AddManipulationRequest addManipulationRequest, ModelMap modelMap) {
        AddManipulationResponse addManipulationResponse = addManipulationService.execute(addManipulationRequest);
        if (addManipulationResponse.hasErrors()) {
            modelMap.addAttribute("errors", addManipulationResponse.getErrors());
            return "/addManipulation";
        } else {
            return "redirect:/";
        }
    }
}
