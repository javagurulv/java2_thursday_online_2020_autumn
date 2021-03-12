package web_ui.controllers;

import dental_clinic.core.requests.plannedVisit.SearchPlannedVisitsByDateRequest;
import dental_clinic.core.responses.planned_visit.SearchPlannedVisitsByDateResponse;
import dental_clinic.core.services.planned_visit.SearchPlannedVisitsByDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SearchPlannedVisitByDateController {

    @Autowired
    private SearchPlannedVisitsByDateService searchPlannedVisitsByDateService;

    @GetMapping(value = "searchPlannedVisitsByDate")
    public String showSearchPlannedVisitsByDatePage(ModelMap modelMap) {
        modelMap.addAttribute("request", new SearchPlannedVisitsByDateRequest());
        return "searchPlannedVisitsByDate";
    }

    @PostMapping("/searchPlannedVisitsByDate")
    public String processSearchPlannedVisitsByDateRequest(@ModelAttribute(value = "request")SearchPlannedVisitsByDateRequest searchPatientRequest, ModelMap modelMap) {
        SearchPlannedVisitsByDateResponse searchPlannedVisitsByDateResponse = searchPlannedVisitsByDateService.execute(searchPatientRequest);
        if (searchPlannedVisitsByDateResponse.hasErrors()) {
            modelMap.addAttribute("errors", searchPlannedVisitsByDateResponse.getErrors());
        } else {
            modelMap.addAttribute("visits", searchPlannedVisitsByDateResponse.getPlannedVisitList());
        }
        return "/searchPlannedVisitsByDate";
    }
}
