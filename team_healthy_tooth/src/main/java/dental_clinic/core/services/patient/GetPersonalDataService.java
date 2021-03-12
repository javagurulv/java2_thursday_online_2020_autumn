package dental_clinic.core.services.patient;

import dental_clinic.core.database.patient.PatientRepository;
import dental_clinic.core.requests.patient.GetPersonalDataRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.patient.GetPersonalDataResponse;
import dental_clinic.core.validators.patient.GetPersonalDataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class GetPersonalDataService {

    @Autowired private PatientRepository patientRepository;
    @Autowired private GetPersonalDataValidator getPersonalDataValidator;

    public GetPersonalDataResponse execute(GetPersonalDataRequest getPersonalDataRequest) {
        List<CoreError> errors = getPersonalDataValidator.validate(getPersonalDataRequest);
        if (!errors.isEmpty()) {
            return new GetPersonalDataResponse(errors);
        }
        return patientRepository.getPersonalDataById(getPersonalDataRequest.getId())
                .map(GetPersonalDataResponse::new)
                .orElseGet(() -> {
                    errors.add(new CoreError("id", "Database doesn't contain id" +
                            getPersonalDataRequest.getId()));
                    return new GetPersonalDataResponse(errors);
                });
    }
}
