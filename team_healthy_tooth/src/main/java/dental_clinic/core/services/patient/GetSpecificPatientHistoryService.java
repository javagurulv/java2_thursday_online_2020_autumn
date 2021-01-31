package dental_clinic.core.services.patient;

import dental_clinic.core.requests.patient.GetSpecificPatientHistoryRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.patient.GetSpecificPatientHistoryResponse;
import dental_clinic.core.validators.patient.GetSpecificPatientHistoryRequestValidator;
import dental_clinic.core.database.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GetSpecificPatientHistoryService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired private GetSpecificPatientHistoryRequestValidator getSpecificPatientHistoryRequestValidator;

    public GetSpecificPatientHistoryResponse execute(GetSpecificPatientHistoryRequest getSpecificPatientHistoryRequest){

        List<CoreError> errors = getSpecificPatientHistoryRequestValidator.validate(getSpecificPatientHistoryRequest);

        if (!errors.isEmpty()){
            return new GetSpecificPatientHistoryResponse(errors);
        }
/*
        if (patientRepository.containsPatientWithSpecificId(getSpecificPatientHistoryRequest.getId())){
            for (int i = 0; i < patientRepository.getPatients().size(); i++){
                if (isSpecificPatient(i, getSpecificPatientHistoryRequest.getId())){
                    return new GetSpecificPatientHistoryResponse(Optional.of(patientRepository.getPatients().get(i)));
                }
            }
        }*/

        errors.add(new CoreError("id", "Database doesn't contain patient with id"));
        return new GetSpecificPatientHistoryResponse(errors);
    }

    private boolean isSpecificPatient (int index, Long id) {
        return patientRepository.getPatients().get(index).getId().equals(id);
    }
}
