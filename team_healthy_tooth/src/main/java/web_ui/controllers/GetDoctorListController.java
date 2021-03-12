package web_ui.controllers;

import dental_clinic.core.requests.doctor.GetDoctorListRequest;
import dental_clinic.core.responses.doctor.GetDoctorListResponse;
import dental_clinic.core.services.doctor.GetDoctorListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetDoctorListController {

    @Autowired private GetDoctorListService getDoctorListService;

    @GetMapping(value = "/getDoctorList")
    public String getDoctorList(ModelMap modelMap) {
        GetDoctorListResponse getDoctorListResponse = getDoctorListService.execute(new GetDoctorListRequest());
        modelMap.addAttribute("list", getDoctorListResponse.getDoctorAndGraphic());
        return "/getDoctorList";
    }
}
