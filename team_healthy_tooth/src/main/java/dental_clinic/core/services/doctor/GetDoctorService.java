package dental_clinic.core.services.doctor;

import dental_clinic.core.database.doctor.DoctorRepository;
import dental_clinic.core.requests.doctor.GetDoctorRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.doctor.GetDoctorResponse;
import dental_clinic.core.validators.doctor.rest.GetDoctorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class GetDoctorService {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private GetDoctorValidator getDoctorValidator;

    public GetDoctorResponse execute(GetDoctorRequest getDoctorRequest) {
        List<CoreError> errorList = getDoctorValidator.validate(getDoctorRequest);
        if (!errorList.isEmpty()) {
            return new GetDoctorResponse((errorList));
        }
        return doctorRepository.getDoctorById(getDoctorRequest.getId())
                .map(GetDoctorResponse::new)
                .orElseGet(() -> {
                    errorList.add(new CoreError("id", "Database doesn't contain doctor with id " +
                            getDoctorRequest.getId()));
                    return new GetDoctorResponse(errorList);
                });
    }
}
