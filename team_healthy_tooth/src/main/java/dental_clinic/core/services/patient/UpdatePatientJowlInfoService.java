package dental_clinic.core.services.patient;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.requests.patient.UpdatePatientsJowlInfoRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.patient.UpdatePatientJowlInfoResponse;
import dental_clinic.core.validators.patient.UpdatePatientJowlInfoRequestValidator;
import dental_clinic.core.database.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdatePatientJowlInfoService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private UpdatePatientJowlInfoRequestValidator updatePatientJowlInfoRequestValidator;

    public UpdatePatientJowlInfoResponse execute (UpdatePatientsJowlInfoRequest updatePatientsJowlInfoRequest) {
        List<CoreError> errors = updatePatientJowlInfoRequestValidator.validate(updatePatientsJowlInfoRequest);

        if (!errors.isEmpty()) {
            return new UpdatePatientJowlInfoResponse(errors);
        }

        if (!patientRepository.containsPatientWithSpecificId(updatePatientsJowlInfoRequest.getId())) {
            errors.add(new CoreError("database", "Database doesn't contain patient with id " +
                    updatePatientsJowlInfoRequest.getId()));
            return new UpdatePatientJowlInfoResponse(errors);
        }

        updatePatientsJowl(updatePatientsJowlInfoRequest);
        return new UpdatePatientJowlInfoResponse(updatePatientsJowlInfoRequest.getId());
    }

    private void updatePatientsJowl (UpdatePatientsJowlInfoRequest updatePatientsJowlInfoRequest) {
    /*    for (Patient patient : patientRepository.getPatients()) {
            if (patient.getPersonalData().getId().equals(updatePatientsJowlInfoRequest.getId())) {
                for (Integer key : updatePatientsJowlInfoRequest.getJowlInfo().keySet()) {
                    patient.updateJowl(key, updatePatientsJowlInfoRequest.getJowlInfo().get(key));
                }
                break;
            }
        }*/
    }

}
