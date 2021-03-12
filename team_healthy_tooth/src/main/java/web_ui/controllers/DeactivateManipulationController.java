package web_ui.controllers;

import dental_clinic.core.requests.manipulation.DeactivateManipulationRequest;
import dental_clinic.core.responses.manipulation.DeactivateManipulationResponse;
import dental_clinic.core.services.manipulation.DeactivateManipulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeactivateManipulationController {

    @Autowired
    private DeactivateManipulationService deactivateManipulationService;

    @GetMapping(value = "/deactivateManipulation")
    public String showDeactivateManipulationPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new DeactivateManipulationRequest());
        return "deactivateManipulation";
    }

    @PostMapping("/deactivateManipulation")
    public String processDeactivateManipulationRequest(@ModelAttribute(value = "request") DeactivateManipulationRequest deactivateManipulationRequest, ModelMap modelMap) {
        DeactivateManipulationResponse deactivateManipulationResponse = deactivateManipulationService.execute(deactivateManipulationRequest);
        if (deactivateManipulationResponse.hasErrors()) {
            modelMap.addAttribute("errors", deactivateManipulationResponse.getErrors());
            return "deactivateManipulation";
        } else {
            return "redirect:/";
        }
    }
}
