package web_ui.controllers;

import dental_clinic.core.requests.patient.GetAllPatientsRequest;
import dental_clinic.core.responses.patient.GetAllPatientsResponse;
import dental_clinic.core.services.patient.GetAllPatientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetAllPatientController {

    @Autowired
    private GetAllPatientsService getAllPatientsService;

    @GetMapping(value = "/getAllPatients")
    public String getAllPatients(ModelMap modelMap) {
        GetAllPatientsResponse getAllPatientsResponse = getAllPatientsService.execute(new GetAllPatientsRequest());
        modelMap.addAttribute("list", getAllPatientsResponse.getPatients());
        return "/getAllPatients";
    }
}
