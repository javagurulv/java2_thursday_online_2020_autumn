package web_ui.controllers.rest;

import dental_clinic.core.requests.visit.AddVisitRequest;
import dental_clinic.core.requests.visit.GetVisitRequest;
import dental_clinic.core.requests.visit.SearchVisitByDateRequest;
import dental_clinic.core.requests.visit.SearchVisitByPatientIdRequest;
import dental_clinic.core.responses.visit.AddVisitResponse;
import dental_clinic.core.responses.visit.GetVisitResponse;
import dental_clinic.core.responses.visit.SearchVisitByDateResponse;
import dental_clinic.core.responses.visit.SearchVisitByPatientIdResponse;
import dental_clinic.core.services.visit.AddVisitService;
import dental_clinic.core.services.visit.GetVisitService;
import dental_clinic.core.services.visit.SearchVisitByDateService;
import dental_clinic.core.services.visit.SearchVisitByPatientIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/visit")
public class GetVisitController {

    @Autowired private GetVisitService getVisitService;
    @Autowired private AddVisitService addVisitService;
    @Autowired private SearchVisitByDateService searchVisitByDateService;
    @Autowired private SearchVisitByPatientIdService searchVisitByPatientIdService;

    @GetMapping(path = "/{id}", produces = "application/json")
    public GetVisitResponse getVisit(@PathVariable Long id) {
        GetVisitRequest getVisitRequest = new GetVisitRequest(id);
        return getVisitService.execute(getVisitRequest);
    }

    @PostMapping(path = "/add/",
    consumes = "application/json",
    produces = "application/json")
    public AddVisitResponse addVisit(@RequestBody AddVisitRequest addVisitRequest) {
        return addVisitService.execute(addVisitRequest);
    }

    @GetMapping(path = "/searchByDate/")
    public SearchVisitByDateResponse searchByDate(@RequestBody SearchVisitByDateRequest searchVisitByDateRequest) {
        return searchVisitByDateService.execute(searchVisitByDateRequest);
    }

    @GetMapping(path = "/searchByPatientId/")
    public SearchVisitByPatientIdResponse searchById(@RequestBody SearchVisitByPatientIdRequest searchVisitByPatientIdRequest) {
        return searchVisitByPatientIdService.execute(searchVisitByPatientIdRequest);
    }
}
