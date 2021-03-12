package web_ui.controllers.rest;

import dental_clinic.core.requests.doctor.*;
import dental_clinic.core.responses.doctor.*;
import dental_clinic.core.services.doctor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class DoctorRestController {

    @Autowired private GetDoctorService getDoctorService;
    @Autowired private AddDoctorService addDoctorService;
    @Autowired private DeleteDoctorService deleteDoctorService;
    @Autowired private FillDoctorsWorkGraphicService fillDoctorsWorkGraphicService;
    @Autowired private GetDoctorListService getDoctorListService;

    @GetMapping(path = "/{id}", produces = "application/json")
    public GetDoctorResponse getDoctor(@PathVariable Long id) {
        GetDoctorRequest getDoctorRequest = new GetDoctorRequest(id);
        return getDoctorService.execute(getDoctorRequest);
    }

    @PostMapping(path = "/add/",
    consumes = "application/json",
    produces = "application/json")
    public AddDoctorResponse addDoctor(@RequestBody AddDoctorRequest addDoctorRequest) {
        return addDoctorService.execute(addDoctorRequest);
    }

    @PutMapping(path = "/fire/",
    consumes = "application/json",
    produces = "application/json")
    public DeleteDoctorResponse fireDoctor(@RequestBody DeleteDoctorRequest deleteDoctorRequest) {
        return deleteDoctorService.execute(deleteDoctorRequest);
    }

    @PutMapping(path = "/graphic/",
            consumes = "application/json",
            produces = "application/json")
    public FillDoctorsWorkGraphicResponse fillDoctorWorkGraphic(@RequestBody FillDoctorsWorkGraphicRequest fillDoctorsWorkGraphicRequest) {
        return fillDoctorsWorkGraphicService.execute(fillDoctorsWorkGraphicRequest);
    }

    @GetMapping(path = "/getAll/", produces = "application/json")
    public GetDoctorListResponse getDoctors() {
        GetDoctorListRequest getDoctorRequest = new GetDoctorListRequest();
        return getDoctorListService.execute(getDoctorRequest);
    }
}
