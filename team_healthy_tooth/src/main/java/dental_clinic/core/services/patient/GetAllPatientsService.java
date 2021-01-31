package dental_clinic.core.services.patient;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.patient.GetAllPatientsRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.patient.GetAllPatientsResponse;
import dental_clinic.core.validators.patient.GetAllPatientsRequestValidator;
import dental_clinic.core.database.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetAllPatientsService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired private GetAllPatientsRequestValidator getAllPatientsRequestValidator;

    public GetAllPatientsResponse execute(GetAllPatientsRequest getAllPatientsRequest){
        List <CoreError> errors = getAllPatientsRequestValidator.validate(getAllPatientsRequest);

        if (!errors.isEmpty()){
            return new GetAllPatientsResponse(errors, new ArrayList<>());
        }

        if (patientRepository.getPatients().isEmpty()){
            errors.add(new CoreError("database", "Database is empty"));
            return new GetAllPatientsResponse(errors, new ArrayList<>());
        }

        List<PersonalData>patients = patientRepository.getPatients();
        return new GetAllPatientsResponse(patients);
    }
}
