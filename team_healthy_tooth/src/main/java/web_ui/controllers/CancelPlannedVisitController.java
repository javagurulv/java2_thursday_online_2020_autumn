package web_ui.controllers;

import dental_clinic.core.requests.plannedVisit.CancelPlannedVisitRequest;
import dental_clinic.core.responses.planned_visit.CancelPlannedVisitResponse;
import dental_clinic.core.services.planned_visit.CancelPlannedVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CancelPlannedVisitController {

    @Autowired
    private CancelPlannedVisitService cancelPlannedVisitService;

    @GetMapping(value = "/cancelPlannedVisit")
    public String showCancelPlannedVisitPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new CancelPlannedVisitRequest());
        return "cancelPlannedVisit";
    }

    @PostMapping("/cancelPlannedVisit")
    public String procesDeactivateManipulationRequest(@ModelAttribute(value = "request") CancelPlannedVisitRequest cancelPlannedVisitRequest, ModelMap modelMap) {
        CancelPlannedVisitResponse cancelPlannedVisitResponse = cancelPlannedVisitService.execute(cancelPlannedVisitRequest);
        if (cancelPlannedVisitResponse.hasErrors()) {
            modelMap.addAttribute("errors", cancelPlannedVisitResponse.getErrors());
            return "cancelPlannedVisit";
        } else {
            return "redirect:/";
        }
    }
}
