package dental_clinic.core.services.planned_visit;

import dental_clinic.core.domain.Doctor;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.domain.PlannedVisit;
import dental_clinic.core.requests.patient.AddPatientRequest;
import dental_clinic.core.requests.plannedVisit.AddPlannedVisitRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.planned_visit.AddPlannedVisitResponse;
import dental_clinic.core.services.patient.AddPatientService;
import dental_clinic.core.validators.planned_visit.AddPlannedVisitRequestValidator;
import dental_clinic.core.database.doctor.DoctorRepository;
import dental_clinic.core.database.patient.PatientRepository;
import dental_clinic.core.database.planned_visit.PlannedVisitsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class AddPlannedVisitService {

    @Autowired
    private AddPlannedVisitRequestValidator addPlannedVisitRequestValidator;
    @Autowired
    private PlannedVisitsRepository plannedVisitsRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private AddPatientService addPatientService;
    @Autowired
    private DoctorRepository doctorRepository;

    public AddPlannedVisitResponse execute (AddPlannedVisitRequest addPlannedVisitRequest) {

        addPlannedVisitRequest = getAddPlannedVisitRequest(addPlannedVisitRequest);

        List<CoreError> errorList = addPlannedVisitRequestValidator.validate(addPlannedVisitRequest);
        if (!errorList.isEmpty()) {
            return new AddPlannedVisitResponse(errorList);
        }

        Date visitDate = getVisitDate(addPlannedVisitRequest.getVisitDataText());
        errorList.addAll(dateInFuture(visitDate));
        if (!errorList.isEmpty()) {
            return new AddPlannedVisitResponse(errorList);
        }

        if (!doctorRepository.containsId(addPlannedVisitRequest.getId())) {
            errorList.add(new CoreError("database", "Database doesn't contain doctor with id " +
                    addPlannedVisitRequest.getId()));
            return new AddPlannedVisitResponse(errorList);
        }

        Doctor doctor = doctorRepository.getDoctorById(addPlannedVisitRequest.getId()).get();

        PlannedVisit plannedVisit = new PlannedVisit(visitDate, addPlannedVisitRequest.getPersonalData(), doctor);

        if (doctorDoesNotWorksInThisTime(plannedVisit, addPlannedVisitRequest)) {
            errorList.add(new CoreError("work graphic", "Doctor doesn't work at this time"));
            return new AddPlannedVisitResponse(errorList);
        }

        if (plannedVisitsRepository.containsPlannedVisitInTheSameTimeTheSameDoctor(plannedVisit)) {
            errorList.add(new CoreError("database", "Not empty time"));
            return new AddPlannedVisitResponse(errorList);
        }

        plannedVisitsRepository.addPlannedVisit(plannedVisit);
        return new AddPlannedVisitResponse(plannedVisit);
    }

    private AddPlannedVisitRequest getAddPlannedVisitRequest(AddPlannedVisitRequest addPlannedVisitRequest) {
        if (!addPlannedVisitRequest.getIsNewPatient()) {
            addPlannedVisitRequest = fillPersonalData(addPlannedVisitRequest);
        } else {
            AddPatientRequest addPatientRequest = new AddPatientRequest(addPlannedVisitRequest.getPersonalData());
            addPatientService.execute(addPatientRequest);
        }
        return addPlannedVisitRequest;
    }

    private boolean doctorDoesNotWorksInThisTime (PlannedVisit plannedVisit, AddPlannedVisitRequest addPlannedVisitRequest) {
        Integer index = plannedVisit.getVisitTime().getDay() - 1;
        System.out.println(index);
        if (doctorDoesNotWorkThisDay(plannedVisit, index)) {
            System.out.println("WHY????");
            return true;
        }
        LocalTime visitTime = LocalTime.parse(addPlannedVisitRequest.getVisitDataText().split(" ")[1], DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime timeFrom = LocalTime.parse(doctorRepository.getWorkGraphic(plannedVisit.getDoctor()).getTimesStart()[index], DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime timeTo = LocalTime.parse(doctorRepository.getWorkGraphic(plannedVisit.getDoctor()).getTimesEnd()[index], DateTimeFormatter.ofPattern("HH:mm"));
        System.out.println("Visit time:" + visitTime + "\nfrom: " + timeFrom + "\nTo: " + timeFrom);
        return !(timeFrom.isBefore(visitTime) && timeTo.isAfter(visitTime));
    }

    private boolean doctorDoesNotWorkThisDay (PlannedVisit plannedVisit, int index) {
        System.out.println("WorkGraphic: " + doctorRepository.getWorkGraphic(plannedVisit.getDoctor()).getTimesStart()[index]);
        return ((doctorRepository.getWorkGraphic(plannedVisit.getDoctor()).getTimesStart()[index].equals("-") ||
                doctorRepository.getWorkGraphic(plannedVisit.getDoctor()).getTimesStart()[index].isEmpty())
                || (doctorRepository.getWorkGraphic(plannedVisit.getDoctor()).getTimesEnd()[index].equals("-") ||
                doctorRepository.getWorkGraphic(plannedVisit.getDoctor()).getTimesEnd()[index].isEmpty()));
    }

    private Date getVisitDate (String visitDateText) {
        Date visitDateDateFormat = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        try {
            visitDateDateFormat = simpleDateFormat.parse(visitDateText);
        }
        catch (ParseException e) {
            System.out.println("Unexpected error!");
        }
        return visitDateDateFormat;
    }

    private List<CoreError> dateInFuture (Date visitDate) {
        List<CoreError> errors = new ArrayList<>();
        Date currentDate = new Date();
        if (visitDate.before(currentDate)) {
            errors.add(new CoreError("date", "Visit date must be in future"));
        }
        return errors;
    }

    private AddPlannedVisitRequest fillPersonalData (AddPlannedVisitRequest addPlannedVisitRequest1) {
        PersonalData personalData = patientRepository.findPatientsByPersonalCode(addPlannedVisitRequest1.getPersonalData().getPersonalCode()).get(0);
        return new AddPlannedVisitRequest(false, addPlannedVisitRequest1.getVisitDataText(), personalData, addPlannedVisitRequest1.getId());
    }
}
