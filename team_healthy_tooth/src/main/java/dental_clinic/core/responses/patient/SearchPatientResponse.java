package dental_clinic.core.responses.patient;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.CoreResponse;

import java.util.List;

public class SearchPatientResponse extends CoreResponse {

    List<Patient> patients;

    public SearchPatientResponse(List<Patient> patients) {
        this.patients = patients;
    }

    public SearchPatientResponse(List<CoreError> errors, List<Patient> patients) {
        super(errors);
        this.patients = patients;
    }

    public List<Patient> getPatients() {
        return patients;
    }
}
