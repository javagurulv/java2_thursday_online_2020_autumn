package web_ui.controllers;

import dental_clinic.core.requests.plannedVisit.GetPlannedVisitsRequest;
import dental_clinic.core.responses.planned_visit.GetPlannedVisitsResponse;
import dental_clinic.core.services.planned_visit.GetPlannedVisitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetPlannedVisitsController {

    @Autowired
    private GetPlannedVisitsService getPlannedVisitsService;

    @GetMapping(value = "/getPlannedVisits")
    public String showGetPlannedVisitsPage(ModelMap modelMap) {
        GetPlannedVisitsResponse getPlannedVisitsResponse = getPlannedVisitsService.execute(new GetPlannedVisitsRequest());
        modelMap.addAttribute("list", getPlannedVisitsResponse.getPlannedVisits());
        return "/getPlannedVisits";
    }
}
