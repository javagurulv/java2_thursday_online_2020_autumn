package web_ui.controllers;

import dental_clinic.core.requests.patient.GetSpecificPatientHistoryRequest;
import dental_clinic.core.responses.patient.GetSpecificPatientHistoryResponse;
import dental_clinic.core.services.patient.GetSpecificPatientHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GetSpecificPatientHistoryController {

    @Autowired
    private GetSpecificPatientHistoryService getSpecificPatientHistoryService;

    @GetMapping(value = "/getSpecificPatientHistory")
    public String showGetSpecificPatientHistoryPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new GetSpecificPatientHistoryRequest());
        return "getSpecificPatientHistory";
    }

    @PostMapping("/getSpecificPatientHistory")
    public String processGetSpecificPatientHistoryRequest(@ModelAttribute(value = "request") GetSpecificPatientHistoryRequest getPatientCardRequest, ModelMap modelMap) {
        GetSpecificPatientHistoryResponse getSpecificPatientHistoryResponse = getSpecificPatientHistoryService.execute(getPatientCardRequest);
        if (getSpecificPatientHistoryResponse.hasErrors()) {
            modelMap.addAttribute("errors", getSpecificPatientHistoryResponse.getErrors());
        } else {
            modelMap.addAttribute("card", getSpecificPatientHistoryResponse.getSpecificPatient().get());
            modelMap.addAttribute("visits", getSpecificPatientHistoryResponse.getSpecificPatient().get().visits);
        }
        return "/getSpecificPatientHistory";
    }
}
