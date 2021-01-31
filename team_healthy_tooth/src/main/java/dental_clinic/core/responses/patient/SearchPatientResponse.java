package dental_clinic.core.responses.patient;

import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.CoreResponse;

import java.util.List;

public class SearchPatientResponse extends CoreResponse {

    List<PersonalData> patients;

    public SearchPatientResponse(List<PersonalData> patients) {
        this.patients = patients;
    }

    public SearchPatientResponse(List<CoreError> errors, List<PersonalData> patients) {
        super(errors);
        this.patients = patients;
    }

    public List<PersonalData> getPatients() {
        return patients;
    }
}
