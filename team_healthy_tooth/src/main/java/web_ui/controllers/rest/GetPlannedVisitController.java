package web_ui.controllers.rest;

import dental_clinic.core.requests.plannedVisit.*;
import dental_clinic.core.responses.planned_visit.*;
import dental_clinic.core.services.planned_visit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plannedVisit")
public class GetPlannedVisitController {

    @Autowired private GetPlannedVisitService getPlannedVisitService;
    @Autowired private AddPlannedVisitService addPlannedVisitService;
    @Autowired private CancelPlannedVisitService cancelPlannedVisitService;
    @Autowired private ChangePlannedVisitTimeService changePlannedVisitTimeService;
    @Autowired private GetPlannedVisitsService getPlannedVisitsService;
    @Autowired private SearchPlannedVisitsByPersonalCodeService searchPlannedVisitsByPersonalCodeService;
    @Autowired private SearchPlannedVisitsByDateService searchPlannedVisitsByDateService;

    @GetMapping(path = "/{id}", produces = "application/json")
    public GetPlannedVisitResponse getPlannedVisit(@PathVariable Long id) {
        GetPlannedVisitRequest getPlannedVisitRequest = new GetPlannedVisitRequest(id);
        return getPlannedVisitService.execute(getPlannedVisitRequest);
    }

    @PostMapping(path = "/add/",
    consumes = "application/json",
    produces = "application/json")
    public AddPlannedVisitResponse addPlannedVisit(@RequestBody AddPlannedVisitRequest addPlannedVisitRequest) {
        return addPlannedVisitService.execute(addPlannedVisitRequest);
    }

    @DeleteMapping(path = "/cancel/{id}", produces = "application/json")
    public CancelPlannedVisitResponse cancelVisit(@PathVariable Long id) {
        CancelPlannedVisitRequest cancelPlannedVisitRequest = new CancelPlannedVisitRequest(id);
        return cancelPlannedVisitService.execute(cancelPlannedVisitRequest);
    }

    @PutMapping(path = "/changeTime/",
    consumes = "application/json",
    produces = "application/json")
    public ChangePlannedVisitTimeResponse changeTime(@RequestBody ChangePlannedVisitTimeRequest changePlannedVisitTimeRequest) {
        return changePlannedVisitTimeService.execute(changePlannedVisitTimeRequest);
    }

    @GetMapping(path = "/getAll/", produces = "application/json")
    public GetPlannedVisitsResponse getPlannedVisits() {
        GetPlannedVisitsRequest getPlannedVisitsRequest = new GetPlannedVisitsRequest();
        return getPlannedVisitsService.execute(getPlannedVisitsRequest);
    }

    @GetMapping(path = "/searchByPersonalCode/", produces = "application/json")
    public SearchPlannedVisitsByPersonalCodeResponse searchByPersonalCode(@RequestBody SearchPlannedVisitsByPersonalCodeRequest searchPlannedVisitsByPersonalCodeRequest) {
        return searchPlannedVisitsByPersonalCodeService.execute(searchPlannedVisitsByPersonalCodeRequest);
    }

    @GetMapping(path = "/searchByDate/", produces = "application/json")
    public SearchPlannedVisitsByDateResponse searchByDate(@RequestBody SearchPlannedVisitsByDateRequest searchPlannedVisitsByPersonalCodeRequest) {
        return searchPlannedVisitsByDateService.execute(searchPlannedVisitsByPersonalCodeRequest);
    }
}
