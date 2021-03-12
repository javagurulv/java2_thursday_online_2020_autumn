package web_ui.controllers;

import dental_clinic.core.requests.patient.ChangePersonalDataRequest;
import dental_clinic.core.responses.patient.ChangePersonalDataResponse;
import dental_clinic.core.services.patient.ChangePersonalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChangePersonalDataController {

    @Autowired private ChangePersonalDataService changePersonalDataService;

    @GetMapping(value = "/changePersonalData")
    public String showChangePersonaldataPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new ChangePersonalDataRequest());
        return "changePersonalData";
    }

    @PostMapping("/changePersonalData")
    public String processChangePersonalDataRequest(@ModelAttribute(value = "request") ChangePersonalDataRequest changePersonalDataRequest, ModelMap modelMap) {
        ChangePersonalDataResponse changePersonalDataResponse = changePersonalDataService.execute(changePersonalDataRequest);
        if (changePersonalDataResponse.hasErrors()) {
            modelMap.addAttribute("errors", changePersonalDataResponse.getErrors());
            return "changePersonalData";
        } else {
            return "redirect:/";
        }
    }
}
