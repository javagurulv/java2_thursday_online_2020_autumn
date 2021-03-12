package dental_clinic.core.responses.doctor;

import dental_clinic.core.domain.Doctor;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.CoreResponse;

import java.util.List;

public class GetDoctorResponse extends CoreResponse {

    private Doctor doctor;

    public GetDoctorResponse(List<CoreError> errorList) {
        super(errorList);
    }

    public GetDoctorResponse(Doctor doctor) {
        this.doctor = doctor;
    }

    public Doctor getDoctor() {
        return doctor;
    }
}
