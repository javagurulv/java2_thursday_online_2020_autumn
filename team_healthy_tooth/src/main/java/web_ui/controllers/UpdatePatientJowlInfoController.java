package web_ui.controllers;

import dental_clinic.core.requests.patient.UpdatePatientsJowlInfoRequest;
import dental_clinic.core.responses.patient.UpdatePatientJowlInfoResponse;
import dental_clinic.core.services.patient.UpdatePatientJowlInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UpdatePatientJowlInfoController {

    @Autowired
    private UpdatePatientJowlInfoService updatePatientJowlInfoService;

    @GetMapping(value = "/updatePatientJowlInfo")
    public String showUpdatePatientJowlInfoPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new UpdatePatientsJowlInfoRequest());
        return "updatePatientJowlInfo";
    }

    @PostMapping("/updatePatientJowlInfo")
    public String processUpdatePatientJowlInfoRequest(@ModelAttribute(value = "request") UpdatePatientsJowlInfoRequest updatePatientsJowlInfoRequest, ModelMap modelMap) {
        UpdatePatientJowlInfoResponse updatePatientJowlInfoResponse = updatePatientJowlInfoService.execute(updatePatientsJowlInfoRequest);
        if (updatePatientJowlInfoResponse.hasErrors()) {
            modelMap.addAttribute("errors", updatePatientJowlInfoResponse.getErrors());
            return "/updatePatientJowlInfo";
        }
        return "redirect:/";
    }
}
