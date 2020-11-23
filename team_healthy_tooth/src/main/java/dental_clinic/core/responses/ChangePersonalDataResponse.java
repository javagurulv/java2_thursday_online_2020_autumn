package dental_clinic.core.responses;

import dental_clinic.core.domain.Patient;

import java.util.List;

public class ChangePersonalDataResponse extends CoreResponse {

    private Patient updatedPatient;

    public ChangePersonalDataResponse(List<CoreError> errors) {
        super(errors);
    }

    public ChangePersonalDataResponse(Patient updatedPatient) {
        this.updatedPatient = updatedPatient;
    }

    public Patient getUpdatedPatient() {
        return updatedPatient;
    }

}
