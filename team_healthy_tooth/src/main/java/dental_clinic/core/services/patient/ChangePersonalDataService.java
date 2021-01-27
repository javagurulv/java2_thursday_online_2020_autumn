package dental_clinic.core.services.patient;

import dental_clinic.core.requests.patient.ChangePersonalDataRequest;
import dental_clinic.core.responses.patient.ChangePersonalDataResponse;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.validators.patient.ChangePersonalDataValidator;
import dental_clinic.core.database.patient.PatientDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChangePersonalDataService {

    @Autowired
    private PatientDatabase patientDatabase;
    @Autowired private ChangePersonalDataValidator validator;

    public ChangePersonalDataResponse execute (ChangePersonalDataRequest request) {
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new ChangePersonalDataResponse(errors);
        }

        Long patientId = request.getPatientIdNumber();
        String updatedSurname = request.getUpdatedSurname();
        String updatedPhone = request.getUpdatedPhoneNumber();

        if (updatedSurname != null && !updatedSurname.isEmpty()){
            patientDatabase.changeSurname(patientId, updatedSurname);
        }

        if (updatedPhone != null && !updatedPhone.isEmpty()){
            patientDatabase.changePhone(patientId, updatedPhone);
        }

        return new ChangePersonalDataResponse(patientId);
    }

}