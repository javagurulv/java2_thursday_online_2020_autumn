package dental_clinic.core.services;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.requests.ChangePatientPersonalDataRequest;
import dental_clinic.core.responses.ChangePatientPersonalDataResponse;
import dental_clinic.core.responses.CoreError;
import dental_clinic.database.PatientDatabase;

import java.util.List;
import java.util.Optional;

public class ChangePatientPersonalDataService {

    private PatientDatabase patientDatabase;

    private ChangePatientPersonalDataValidator validator;

    public ChangePatientPersonalDataService(
            PatientDatabase database, ChangePatientPersonalDataValidator validator) {
        this.patientDatabase = database;
        this.validator = validator;
    }

    public ChangePatientPersonalDataResponse execute (ChangePatientPersonalDataRequest request) {
        List<CoreError> errors = validator.validate(request);

        if (errors.isEmpty()) {
            Optional<Patient> updatedPatient = patientDatabase.changePatientPersonalData(request);
            if (updatedPatient.isPresent()) {
                return new ChangePatientPersonalDataResponse(updatedPatient);
            }
            else {
                errors.add(new CoreError("search ID", "Could not find patient with this ID"));
                return new ChangePatientPersonalDataResponse(errors);
            }
        }
        else {
            return new ChangePatientPersonalDataResponse(errors);
        }
    }

    public boolean canFindId(long idToSearch) {
        boolean result = false;
        List<Patient> patientList = patientDatabase.getPatients();

        for (Patient patient : patientList) {
            long foundId = patient.getPersonalData().getId();
            if (foundId == idToSearch) {
                result = true;
            }
        }
        return result;
    }
}
