package web_ui.controllers;

import dental_clinic.core.requests.patient.GetPatientCardRequest;
import dental_clinic.core.responses.patient.GetPatientCardResponse;
import dental_clinic.core.services.patient.GetPatientCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GetPatientCardController {

    @Autowired
    private GetPatientCardService getPatientCardService;

    @GetMapping(value = "/getPatientCard")
    public String showGetPatientCardPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new GetPatientCardRequest());
        return "getPatientCard";
    }

    @PostMapping("/getPatientCard")
    public String processGetPatientCardRequest(@ModelAttribute(value = "request") GetPatientCardRequest getPatientCardRequest, ModelMap modelMap) {
        GetPatientCardResponse getPatientCardResponse = getPatientCardService.execute(getPatientCardRequest);
        if (getPatientCardResponse.hasErrors()) {
            modelMap.addAttribute("errors", getPatientCardResponse.getErrors());
        } else {
            modelMap.addAttribute("card", getPatientCardResponse.getPatient());
        }
        return "/getPatientCard";
    }
}
