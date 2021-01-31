package dental_clinic.core.responses.patient;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.CoreResponse;

import java.util.List;

public class GetAllPatientsResponse extends CoreResponse {

    private List<PersonalData> patients;

    public GetAllPatientsResponse(List<PersonalData> patients) {
        this.patients = patients;
    }

    public GetAllPatientsResponse(List<CoreError> errors, List<PersonalData> patients) {
        super(errors);
    }


    public List<PersonalData>getPatients(){
        return patients;
    }

}