package web_ui.controllers;

import dental_clinic.core.requests.doctor.DeleteDoctorRequest;
import dental_clinic.core.responses.doctor.DeleteDoctorResponse;
import dental_clinic.core.services.doctor.DeleteDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeleteDoctorController {

    @Autowired
    private DeleteDoctorService deleteDoctorService;

    @GetMapping(value = "/deleteDoctor")
    public String showDeleteDoctorPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new DeleteDoctorRequest());
        return "deleteDoctor";
    }

    @PostMapping("/deleteDoctor")
    public String processDeleteDoctorRequest(@ModelAttribute(value = "request") DeleteDoctorRequest deleteDoctorRequest, ModelMap modelMap) {
        DeleteDoctorResponse deleteDoctorResponse = deleteDoctorService.execute(deleteDoctorRequest);
        if (deleteDoctorResponse.hasErrors()) {
            modelMap.addAttribute("errors", deleteDoctorResponse.getErrors());
            return "/deleteDoctor";
        } else {
            return "redirect:/";
        }
    }
}
