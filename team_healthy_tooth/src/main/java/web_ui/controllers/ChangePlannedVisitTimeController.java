package web_ui.controllers;

import dental_clinic.core.requests.plannedVisit.ChangePlannedVisitTimeRequest;
import dental_clinic.core.responses.planned_visit.ChangePlannedVisitTimeResponse;
import dental_clinic.core.services.planned_visit.ChangePlannedVisitTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChangePlannedVisitTimeController {

    @Autowired
    private ChangePlannedVisitTimeService changePlannedVisitTimeService;

    @GetMapping(value = "/changePlannedVisitTime")
    public String showChangePlannedVisitTimePage(ModelMap modelMap) {
        modelMap.addAttribute("request", new ChangePlannedVisitTimeRequest());
        return "changePlannedVisitTime";
    }

    @PostMapping("/changePlannedVisitTime")
    public String processChangePlannedVisitRequest(@ModelAttribute(value = "request") ChangePlannedVisitTimeRequest changePlannedVisitTimeRequest, ModelMap modelMap) {
        ChangePlannedVisitTimeResponse changePlannedVisitTimeResponse = changePlannedVisitTimeService.execute(changePlannedVisitTimeRequest);
        if (changePlannedVisitTimeResponse.hasErrors()) {
            modelMap.addAttribute("errors", changePlannedVisitTimeResponse.getErrors());
            return "/changePlannedVisitTime";
        } else {
            return "redirect:/";
        }
    }
}
