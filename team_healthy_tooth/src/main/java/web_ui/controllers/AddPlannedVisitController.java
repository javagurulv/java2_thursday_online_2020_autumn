package web_ui.controllers;

import dental_clinic.core.requests.plannedVisit.AddPlannedVisitRequest;
import dental_clinic.core.responses.planned_visit.AddPlannedVisitResponse;
import dental_clinic.core.services.planned_visit.AddPlannedVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddPlannedVisitController {

    @Autowired
    private AddPlannedVisitService addPlannedVisitService;

    @GetMapping(value = "/addPlannedVisit")
    public String showAddPlannedVisitPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddPlannedVisitRequest());
        return "addPlannedVisit";
    }

    @PostMapping("/addPlannedVisit")
    public String processAddPlannedVisitRequest(@ModelAttribute(value = "request") AddPlannedVisitRequest addPlannedVisitRequest, ModelMap modelMap) {
        AddPlannedVisitResponse addPlannedVisitResponse = addPlannedVisitService.execute(addPlannedVisitRequest);
        if (addPlannedVisitResponse.hasErrors()) {
            modelMap.addAttribute("errors", addPlannedVisitResponse.getErrors());
            return "/addPlannedVisit";
        } else {
            return "redirect:/";
        }
    }
}
