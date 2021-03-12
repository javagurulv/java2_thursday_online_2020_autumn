package web_ui.controllers;

import dental_clinic.core.requests.visit.AddVisitRequest;
import dental_clinic.core.responses.visit.AddVisitResponse;
import dental_clinic.core.services.visit.AddVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddVisitController {

    @Autowired
    private AddVisitService addVisitService;

    @GetMapping(value = "addVisit")
    public String showAddVisitPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddVisitRequest());
        return "addVisit";
    }

    @PostMapping("/addVisit")
    public String processAddVisitRequest(@ModelAttribute(value = "request") AddVisitRequest addVisitRequest, ModelMap modelMap) {
        AddVisitResponse addVisitResponse = addVisitService.execute(addVisitRequest);
        if (addVisitResponse.hasErrors()) {
            modelMap.addAttribute("errors", addVisitResponse.getErrors());
            return "/addVisit";
        } else {
            return "redirect:/";
        }
    }
}
