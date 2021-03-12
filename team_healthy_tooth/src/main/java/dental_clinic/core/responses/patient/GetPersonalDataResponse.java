package dental_clinic.core.responses.patient;

import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.CoreResponse;

import java.util.List;

public class GetPersonalDataResponse extends CoreResponse {

    private PersonalData personalData;

    public GetPersonalDataResponse(List<CoreError> errorList) {
        super(errorList);
    }

    public GetPersonalDataResponse(PersonalData personalData) {
        this.personalData = personalData;
    }

    public PersonalData getPatient() {
        return personalData;
    }
}
