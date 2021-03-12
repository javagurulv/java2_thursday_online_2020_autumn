package web_ui.controllers;

import dental_clinic.core.requests.patient.SearchPatientRequest;
import dental_clinic.core.responses.patient.SearchPatientResponse;
import dental_clinic.core.services.patient.SearchPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SearchPatientController {

    @Autowired
    private SearchPatientService searchPatientService;

    @GetMapping(value = "/searchPatient")
    public String showSearchPatientPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new SearchPatientRequest());
        return "searchPatient";
    }

    @PostMapping("/searchPatient")
    public String processSearchPatientRequest(@ModelAttribute(value = "request") SearchPatientRequest searchPatientRequest, ModelMap modelMap) {
        SearchPatientResponse searchPatientResponse = searchPatientService.execute(searchPatientRequest);
        if (searchPatientResponse.hasErrors()) {
            modelMap.addAttribute("errors", searchPatientResponse.getErrors());
        } else {
            modelMap.addAttribute("patients", searchPatientResponse.getPatients());
        }
        return "/searchPatient";
    }
}
