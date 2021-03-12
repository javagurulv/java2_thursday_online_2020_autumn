package web_ui.controllers.rest;

import dental_clinic.core.requests.patient.*;
import dental_clinic.core.responses.patient.*;
import dental_clinic.core.services.patient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientRestController {

    @Autowired private GetPersonalDataService getPersonalDataService;
    @Autowired private AddPatientService addPatientService;
    @Autowired private ChangePersonalDataService changePersonalDataService;
    @Autowired private GetAllPatientsService getAllPatientsService;
    @Autowired private GetPatientCardService getPatientCardService;
    @Autowired private GetSpecificPatientHistoryService getSpecificPatientHistoryService;
    @Autowired private SearchPatientService searchPatientService;
    @Autowired private UpdatePatientJowlInfoService updatePatientJowlInfoService;

    @GetMapping(path = "/{id}", produces = "application/json")
    public GetPersonalDataResponse getPersonalData(@PathVariable Long id) {
        GetPersonalDataRequest getPersonalDataRequest = new GetPersonalDataRequest(id);
        return getPersonalDataService.execute(getPersonalDataRequest);
    }

    @PostMapping(path = "/add/",
    consumes = "application/json",
    produces = "application/json")
    public AddPatientResponse addPatient(@RequestBody AddPatientRequest addPatientRequest) {
        return addPatientService.execute(addPatientRequest);
    }

    @PutMapping(path = "/change/",
    consumes = "application/json",
    produces = "application/json")
    public ChangePersonalDataResponse changePersonalData(@RequestBody ChangePersonalDataRequest changePersonalDataRequest) {
        return changePersonalDataService.execute(changePersonalDataRequest);
    }

    @GetMapping(path = "/getAll/", produces = "application/json")
    public GetAllPatientsResponse getAllPatients() {
        GetAllPatientsRequest getAllPatientsRequest = new GetAllPatientsRequest();
        return getAllPatientsService.execute(getAllPatientsRequest);
    }

    @GetMapping(path = "/card/{id}", produces = "application/json")
    public GetPatientCardResponse getPatientCard(@PathVariable Long id) {
        GetPatientCardRequest getPatientCardRequest = new GetPatientCardRequest(id);
        return getPatientCardService.execute(getPatientCardRequest);
    }

    @GetMapping(path = "/history/{id}", produces = "application/json")
    public GetSpecificPatientHistoryResponse getPatientHistory(@PathVariable Long id) {
        GetSpecificPatientHistoryRequest getSpecificPatientHistoryRequest = new GetSpecificPatientHistoryRequest(id);
        return getSpecificPatientHistoryService.execute(getSpecificPatientHistoryRequest);
    }

    @GetMapping(path = "/search", produces = "application/json")
    public SearchPatientResponse searchPatient(@RequestBody SearchPatientRequest searchPatientRequest) {
        return searchPatientService.execute(searchPatientRequest);
    }

    @PutMapping(path = "/jowl/{id}",
            consumes = "application/json",
            produces = "application/json")
    public UpdatePatientJowlInfoResponse updateJowl(@RequestBody UpdatePatientsJowlInfoRequest updatePatientsJowlInfoRequest) {
        return updatePatientJowlInfoService.execute(updatePatientsJowlInfoRequest);
    }
}
