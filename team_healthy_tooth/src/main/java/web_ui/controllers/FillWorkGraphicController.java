package web_ui.controllers;

import dental_clinic.core.requests.doctor.FillDoctorsWorkGraphicRequest;
import dental_clinic.core.responses.doctor.FillDoctorsWorkGraphicResponse;
import dental_clinic.core.services.doctor.FillDoctorsWorkGraphicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FillWorkGraphicController {

    @Autowired
    private FillDoctorsWorkGraphicService fillDoctorsWorkGraphicService;

    @GetMapping(value = "/fillDoctorsWorkGraphic")
    public String showFillDoctorsGraphicPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new FillDoctorsWorkGraphicRequest());
        return "fillDoctorsWorkGraphic";
    }

    @PostMapping("/fillDoctorsWorkGraphic")
    public String processFillDoctorsWorkGraphicRequest(@ModelAttribute(value = "request") FillDoctorsWorkGraphicRequest fillDoctorsWorkGraphicRequest) {
        FillDoctorsWorkGraphicResponse fillDoctorsWorkGraphicResponse = fillDoctorsWorkGraphicService.execute(fillDoctorsWorkGraphicRequest);
        if (fillDoctorsWorkGraphicResponse.hasErrors()) {
            return "fillDoctorsWorkGraphic";
        } else {
            return "redirect:/";
        }
    }
}
