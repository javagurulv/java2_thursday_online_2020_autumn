package dental_clinic.core.responses;

import dental_clinic.core.domain.Patient;

import java.util.List;
import java.util.Optional;

public class ChangePatientPersonalDataResponse extends CoreResponse{

    private Optional<Patient> updatedPatient;

    public ChangePatientPersonalDataResponse(Optional<Patient> updatedPatient) {
        this.updatedPatient = updatedPatient;
    }

    public ChangePatientPersonalDataResponse(List<CoreError> errors) {
        super(errors);
    }


}
