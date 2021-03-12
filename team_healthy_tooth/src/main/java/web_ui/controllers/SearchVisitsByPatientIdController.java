package web_ui.controllers;

import dental_clinic.core.requests.visit.SearchVisitByPatientIdRequest;
import dental_clinic.core.responses.visit.SearchVisitByPatientIdResponse;
import dental_clinic.core.services.visit.SearchVisitByPatientIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SearchVisitsByPatientIdController {

    @Autowired
    private SearchVisitByPatientIdService searchVisitByPatientIdService;

    @GetMapping(value = "/searchVisitByPatientId")
    public String showSearchVisitsById(ModelMap modelMap) {
        modelMap.addAttribute("request", new SearchVisitByPatientIdRequest());
        return "searchVisitByPatientId";
    }

    @PostMapping("/searchVisitByPatientId")
    public String processSearchVisitByIdRequest(@ModelAttribute(value = "request") SearchVisitByPatientIdRequest searchVisitByPatientIdRequest, ModelMap modelMap) {
        SearchVisitByPatientIdResponse searchVisitByPatientIdResponse = searchVisitByPatientIdService.execute(searchVisitByPatientIdRequest);
        if (searchVisitByPatientIdResponse.hasErrors()) {
            modelMap.addAttribute("errors", searchVisitByPatientIdResponse.getErrors());
        } else {
            modelMap.addAttribute("visits", searchVisitByPatientIdResponse.getVisitList());
        }
        return "/searchVisitByPatientId";
    }
}
