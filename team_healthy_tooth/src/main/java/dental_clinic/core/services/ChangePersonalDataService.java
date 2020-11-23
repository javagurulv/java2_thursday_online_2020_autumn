package dental_clinic.core.services;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.requests.ChangePersonalDataRequest;
import dental_clinic.core.responses.ChangePersonalDataResponse;
import dental_clinic.core.responses.CoreError;
import dental_clinic.database.PatientDatabase;

import java.util.List;
import java.util.Optional;

public class ChangePersonalDataService {

    private PatientDatabase patientDatabase;

    private ChangePersonalDataValidator validator;

    public ChangePersonalDataService(PatientDatabase patientDatabase,
                                     ChangePersonalDataValidator validator) {
        this.patientDatabase = patientDatabase;
        this.validator = validator;
    }

    public ChangePersonalDataResponse execute (ChangePersonalDataRequest request) {
        List<CoreError> errors = validator.validate(request);

        if (errors.isEmpty()) {
            long patientId = request.getPatientIdNumber();
            String updatedSurname = request.getUpdatedSurname();
            String updatedPhone = request.getUpdatedPhoneNumber();

            Optional<Patient> updatedPatient =
                    patientDatabase.changePersonalData(patientId, updatedSurname, updatedPhone);

            if (updatedPatient.isPresent()) {
                Patient result = updatedPatient.get();
                return new ChangePersonalDataResponse(result);
            }
            else {
                errors.add(new CoreError("Personal data : id",
                        "Could not find patient with this Id"));
                return new ChangePersonalDataResponse(errors);
            }
        }
        return new ChangePersonalDataResponse(errors);
    }

}
