package web_ui.controllers;

import dental_clinic.core.requests.patient.AddPatientRequest;
import dental_clinic.core.responses.patient.AddPatientResponse;
import dental_clinic.core.services.patient.AddPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddPatientController {

    @Autowired
    private AddPatientService addPatientService;

    @GetMapping(value = "/addPatient")
    public String showAddPatientPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddPatientRequest());
        return "addPatient";
    }

    @PostMapping("/addPatient")
    public String processAddPatientRequest(@ModelAttribute(value = "request") AddPatientRequest addPatientRequest, ModelMap modelMap) {
        AddPatientResponse addPatientResponse = addPatientService.execute(addPatientRequest);
        if (addPatientResponse.hasErrors()) {
            modelMap.addAttribute("errors", addPatientResponse.getErrors());
            return "/addPatient";
        } else {
            return "redirect:/";
        }
    }
}

