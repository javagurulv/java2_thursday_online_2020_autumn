package dental_clinic.core.services.doctor;

import dental_clinic.core.requests.doctor.DeleteDoctorRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.doctor.DeleteDoctorResponse;
import dental_clinic.core.validators.doctor.DeleteDoctorRequestValidator;
import dental_clinic.core.database.doctor.DoctorDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeleteDoctorService {

    @Autowired
    private DeleteDoctorRequestValidator deleteDoctorRequestValidator;
    @Autowired
    private DoctorDatabase doctorDatabase;

    public DeleteDoctorResponse execute (DeleteDoctorRequest deleteDoctorRequest) {

        List<CoreError> errorList = deleteDoctorRequestValidator.validate(deleteDoctorRequest);

        if (!errorList.isEmpty()) {
            return new DeleteDoctorResponse(errorList);
        }

        if (!doctorDatabase.containsId(deleteDoctorRequest.getId())) {
            errorList.add(new CoreError("database", "Database doesn't contain id " +
                    deleteDoctorRequest.getId()));
            return new DeleteDoctorResponse(errorList);
        }

        doctorDatabase.deleteDoctorById(deleteDoctorRequest.getId());
        return new DeleteDoctorResponse(deleteDoctorRequest.getId());
    }

}
