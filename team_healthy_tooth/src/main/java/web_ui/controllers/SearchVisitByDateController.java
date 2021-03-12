package web_ui.controllers;

import dental_clinic.core.requests.visit.SearchVisitByDateRequest;
import dental_clinic.core.responses.visit.SearchVisitByDateResponse;
import dental_clinic.core.services.visit.SearchVisitByDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SearchVisitByDateController {

    @Autowired
    private SearchVisitByDateService searchVisitByDateService;

    @GetMapping(value = "/searchVisitByDate")
    public String showSearchVisitByDatePage(ModelMap modelMap) {
        modelMap.addAttribute("request", new SearchVisitByDateRequest());
        return "searchVisitByDate";
    }

    @PostMapping("/searchVisitByDate")
    public String processSearchVisitByDateRequest(@ModelAttribute(value = "request") SearchVisitByDateRequest searchVisitByDateRequest, ModelMap modelMap) {
        SearchVisitByDateResponse searchVisitByDateResponse = searchVisitByDateService.execute(searchVisitByDateRequest);
        if (searchVisitByDateResponse.hasErrors()) {
            modelMap.addAttribute("errors", searchVisitByDateResponse.getErrors());
        } else {
            modelMap.addAttribute("visits", searchVisitByDateResponse.getVisitList());
        }
        return "/searchVisitByDate";
    }
}
