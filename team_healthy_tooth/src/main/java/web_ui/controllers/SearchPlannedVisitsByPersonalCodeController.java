package web_ui.controllers;

import dental_clinic.core.requests.plannedVisit.SearchPlannedVisitsByPersonalCodeRequest;
import dental_clinic.core.responses.planned_visit.SearchPlannedVisitsByPersonalCodeResponse;
import dental_clinic.core.services.planned_visit.SearchPlannedVisitsByPersonalCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SearchPlannedVisitsByPersonalCodeController {

    @Autowired
    private SearchPlannedVisitsByPersonalCodeService searchPlannedVisitsByPersonalCodeService;

    @GetMapping(value = "searchPlannedVisitsByPersonalCode")
    public String showSearchPlannedVisitsByPersonalCodePage(ModelMap modelMap) {
        modelMap.addAttribute("request", new SearchPlannedVisitsByPersonalCodeRequest());
        return "searchPlannedVisitsByPersonalCode";
    }

    @PostMapping("/searchPlannedVisitsByPersonalCode")
    public String processSearchPlannedVisitsByPersonalCodeRequest(@ModelAttribute(value = "request")SearchPlannedVisitsByPersonalCodeRequest searchPatientRequest, ModelMap modelMap) {
        SearchPlannedVisitsByPersonalCodeResponse searchPlannedVisitsByPersonalCodeResponse = searchPlannedVisitsByPersonalCodeService.execute(searchPatientRequest);
        if (searchPlannedVisitsByPersonalCodeResponse.hasErrors()) {
            modelMap.addAttribute("errors", searchPlannedVisitsByPersonalCodeResponse.getErrors());
        } else {
            modelMap.addAttribute("visits", searchPlannedVisitsByPersonalCodeResponse.getPlannedVisitList());
        }
        return "/searchPlannedVisitsByPersonalCode";
    }

}
