package web_ui.controllers.rest;

import dental_clinic.core.requests.manipulation.AddManipulationRequest;
import dental_clinic.core.requests.manipulation.DeactivateManipulationRequest;
import dental_clinic.core.requests.manipulation.GetManipulationsListRequest;
import dental_clinic.core.requests.manipulation.rest.GetManipulationRequest;
import dental_clinic.core.responses.manipulation.AddManipulationResponse;
import dental_clinic.core.responses.manipulation.DeactivateManipulationResponse;
import dental_clinic.core.responses.manipulation.GetManipulationsListResponse;
import dental_clinic.core.responses.manipulation.rest.GetManipulationResponse;
import dental_clinic.core.services.manipulation.AddManipulationService;
import dental_clinic.core.services.manipulation.DeactivateManipulationService;
import dental_clinic.core.services.manipulation.GetManipulationsListService;
import dental_clinic.core.services.manipulation.rest.GetManipulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manipulation")
public class ManipulationRestController {

    @Autowired private GetManipulationService getManipulationService;
    @Autowired private AddManipulationService addManipulationService;
    @Autowired private DeactivateManipulationService deactivateManipulationService;
    @Autowired private GetManipulationsListService getManipulationsListService;

    @GetMapping(path = "/{id}", produces = "application/json")
    public GetManipulationResponse getManipulation(@PathVariable Long id) {
        GetManipulationRequest getManipulationRequest = new GetManipulationRequest(id);
        return getManipulationService.execute(getManipulationRequest);
    }

    @PostMapping(path = "/add/",
    consumes = "application/json",
    produces = "application/json")
    public AddManipulationResponse addManipulation(@RequestBody AddManipulationRequest addManipulationRequest) {
        return addManipulationService.execute(addManipulationRequest);
    }

    @PutMapping(path = "/deactivate/",
    consumes = "application/json",
    produces = "application/json")
    public DeactivateManipulationResponse deactivateManipulation(@RequestBody DeactivateManipulationRequest deactivateManipulationRequest) {
        return deactivateManipulationService.execute(deactivateManipulationRequest);
    }

    @GetMapping(path = "/getAll/", produces = "application/json")
    public GetManipulationsListResponse getManipulations() {
        GetManipulationsListRequest getManipulationRequest = new GetManipulationsListRequest();
        return getManipulationsListService.execute(getManipulationRequest);
    }
}
