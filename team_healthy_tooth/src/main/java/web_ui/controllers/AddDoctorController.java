package web_ui.controllers;

import dental_clinic.core.requests.doctor.AddDoctorRequest;
import dental_clinic.core.responses.doctor.AddDoctorResponse;
import dental_clinic.core.services.doctor.AddDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddDoctorController {

    @Autowired private AddDoctorService addDoctorService;

    @GetMapping(value = "/addDoctor")
    public String showAddDoctorPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddDoctorRequest());
        return "addDoctor";
    }

    @PostMapping("/addDoctor")
    public String processAddDoctorRequest(@ModelAttribute(value = "request") AddDoctorRequest addDoctorRequest, ModelMap modelMap) {
        AddDoctorResponse addDoctorResponse = addDoctorService.execute(addDoctorRequest);
        if (addDoctorResponse.hasErrors()) {
            modelMap.addAttribute("errors", addDoctorResponse.getErrors());
            return "/addDoctor";
        } else {
            return "redirect:/";
        }
    }
}
