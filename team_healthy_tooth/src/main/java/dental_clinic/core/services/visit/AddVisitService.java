package dental_clinic.core.services.visit;

import dental_clinic.core.domain.Doctor;
import dental_clinic.core.domain.Manipulation;
import dental_clinic.core.domain.ToothStatus;
import dental_clinic.core.requests.visit.AddVisitRequest;
import dental_clinic.core.responses.visit.AddVisitResponse;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.validators.visit.AddVisitValidator;
import dental_clinic.core.database.doctor.DoctorRepository;
import dental_clinic.core.database.manipulation.ManipulationRepository;
import dental_clinic.core.database.patient.PatientRepository;
import dental_clinic.core.domain.Visit;
import dental_clinic.core.database.visit.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddVisitService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private AddVisitValidator addVisitValidator;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private ManipulationRepository manipulationRepository;
    @Autowired
    private VisitRepository visitRepository;

    public AddVisitResponse execute(AddVisitRequest addVisitRequest){

        List<CoreError> errors = addVisitValidator.validate(addVisitRequest);

        if (!errors.isEmpty()){
            return new AddVisitResponse(errors);
        }

        Doctor doctor;

        if (isIdAdded(addVisitRequest.getDoctor().getName())) {
            Optional<Doctor> doctorOptional = doctorRepository.getDoctorById(Long.parseLong(addVisitRequest.getDoctor().getName()));
            if (!doctorOptional.isPresent()) {
                errors.add(new CoreError("id", "Database doesn't contain doctor with id "
                        + addVisitRequest.getDoctor().getName() + " in database"));
                return new AddVisitResponse(errors);
            }
            doctor = doctorOptional.get();
        } else {
            doctor = new Doctor(addVisitRequest.getDoctor().getName().split(" ")[0],
                    addVisitRequest.getDoctor().getName().split(" ")[1],
                    addVisitRequest.getDoctor().getName().split(" ")[2]);
            doctor.setEmployed(true);
        }

        if (!doctor.isEmployed()) {
            errors.add(new CoreError("doctor", "Doctor must be employed"));
            return new AddVisitResponse(errors);
        }

        errors.addAll(manipulationsDatabaseContainsIdAndIsActive(addVisitRequest.getManipulationsIds()));
        if (!errors.isEmpty()){
            return new AddVisitResponse(errors);
        }

        Visit visit = new Visit(addVisitRequest.getPatientsId(), addVisitRequest.getToothNumber(),
                //addVisitRequest.getComment(),
                Optional.of("No comments"),
                ToothStatus.HEALTHY,
                //addVisitRequest.getToothStatus(),
                doctor,
                manipulationList(addVisitRequest.getManipulationsIds()), addVisitRequest.getDate());

        visitRepository.addVisit(visit);

        if (isNewDoctor(doctor)){
            doctorRepository.addDoctor(doctor);
        }
/*
        if (patientRepository.containsPatientWithSpecificId(addVisitRequest.getPatientsId())){
            //addVisitToDoctor(doctor, visit);
            return addVisitToPatient(addVisitRequest, visit);
        }*/

        errors.add(new CoreError("id", "Database doesn't contain patient with id " + addVisitRequest.getPatientsId()));
        return new AddVisitResponse(errors);

    }

    private boolean isIdAdded(String text) {
        try {
            Long.parseLong(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
/*
    private void addVisitToDoctor (Doctor doctor, Visit visit) {
        for (Doctor d : doctorRepository.getDoctorList()) {
            if (d.getName().equals(doctor.getName())
            && d.getSurname().equals(doctor.getSurname())) {
                d.addVisit(visit);
            }
        }
    }*/
/*
    private AddVisitResponse addVisitToPatient (AddVisitRequest addVisitRequest, Visit visit) {
        for (int i = 0; i < patientRepository.getPatients().size(); i++) {
            if (isSpecificPatient(i, addVisitRequest.getPatientsId())) {
                patientRepository.getPatients().get(i).addVisit(visit);
                patientRepository.getPatients().get(i).updateJowl(addVisitRequest.getToothNumber(),
                        ToothStatus.HEALTHY
                        //addVisitRequest.getToothStatus()
                );
                return new AddVisitResponse();
            }
        }
        return new AddVisitResponse();
    }

    private boolean isSpecificPatient (int index, long id) {
        return patientRepository.getPatients().get(index).getPersonalData().getId().equals(id);
    }*/

    private boolean isNewDoctor(Doctor doctor) {
        return !doctorRepository.containsDoctor(doctor);
    }

    private List<CoreError> manipulationsDatabaseContainsIdAndIsActive(List<Long>ids) {
        List<CoreError>errors = new ArrayList<>();
        for (Long id : ids) {
            if (!manipulationRepository.containsId(id)) {
                errors.add(new CoreError("database", "Database doesn't contain manipulation with id " +
                        id));
            } else {
                if (!manipulationRepository.manipulationIsActive(id)) {
                    errors.add(new CoreError("manipulation", "Manipulation with id " +
                            id + " isn't active"));
                }
            }
        }
        return errors;
    }

    private List<Manipulation> manipulationList (List<Long> manipulationsIds) {
        List<Manipulation>manipulations = new ArrayList<>();
        for (Long id : manipulationsIds) {
            for (Manipulation manipulation : manipulationRepository.getManipulationsList()) {
                if (manipulation.getId().equals(id)){
                    manipulations.add(manipulation);
                    break;
                }
            }
        }
        return manipulations;
    }

}
