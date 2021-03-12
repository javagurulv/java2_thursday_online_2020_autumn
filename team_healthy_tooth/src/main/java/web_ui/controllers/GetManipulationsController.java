package web_ui.controllers;

import dental_clinic.core.requests.manipulation.GetManipulationsListRequest;
import dental_clinic.core.responses.manipulation.GetManipulationsListResponse;
import dental_clinic.core.services.manipulation.GetManipulationsListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetManipulationsController {

    @Autowired
    private GetManipulationsListService getManipulationsListService;

    @GetMapping(value = "/getManipulations")
    public String getManipulations(ModelMap modelMap) {
        GetManipulationsListResponse getManipulationsListResponse = getManipulationsListService.execute(new GetManipulationsListRequest());
        modelMap.addAttribute("list", getManipulationsListResponse.getManipulationList());
        return "/getManipulations";
    }
}
