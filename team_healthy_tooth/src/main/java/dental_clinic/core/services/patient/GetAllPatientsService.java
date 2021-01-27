package dental_clinic.core.services.patient;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.requests.patient.GetAllPatientsRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.patient.GetAllPatientsResponse;
import dental_clinic.core.validators.patient.GetAllPatientsRequestValidator;
import dental_clinic.core.database.patient.PatientDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetAllPatientsService {

    @Autowired
    private PatientDatabase patientDatabase;
    @Autowired private GetAllPatientsRequestValidator getAllPatientsRequestValidator;

    public GetAllPatientsResponse execute(GetAllPatientsRequest getAllPatientsRequest){
        List <CoreError> errors = getAllPatientsRequestValidator.validate(getAllPatientsRequest);

        if (!errors.isEmpty()){
            return new GetAllPatientsResponse(errors, new ArrayList<>());
        }

        if (patientDatabase.getPatients().isEmpty()){
            errors.add(new CoreError("database", "Database is empty"));
            return new GetAllPatientsResponse(errors, new ArrayList<>());
        }

        List<Patient>patients = patientDatabase.getPatients();
        return new GetAllPatientsResponse(patients);
    }
}
