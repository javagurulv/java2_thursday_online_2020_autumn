package dental_clinic.core.services.doctor;

import dental_clinic.core.domain.Doctor;
import dental_clinic.core.requests.doctor.AddDoctorRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.doctor.AddDoctorResponse;
import dental_clinic.core.validators.doctor.AddDoctorRequestValidator;
import dental_clinic.core.database.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddDoctorService {

    @Autowired
    private AddDoctorRequestValidator addDoctorRequestValidator;
    @Autowired
    private DoctorRepository doctorRepository;

    public AddDoctorResponse execute (AddDoctorRequest addDoctorRequest) {

        List<CoreError> errors = addDoctorRequestValidator.validate(addDoctorRequest);

        if (!errors.isEmpty()) {
            return new AddDoctorResponse(errors);
        }

        Doctor doctor = new Doctor(addDoctorRequest.getName(), addDoctorRequest.getSurname(), addDoctorRequest.getPhone());

        if (doctorRepository.containsDoctor(doctor)) {
            errors.add(new CoreError("database", "Database contains the same doctor"));
            return new AddDoctorResponse(errors);
        }

        doctorRepository.addDoctor(doctor);
        return new AddDoctorResponse(doctor);
    }

}
