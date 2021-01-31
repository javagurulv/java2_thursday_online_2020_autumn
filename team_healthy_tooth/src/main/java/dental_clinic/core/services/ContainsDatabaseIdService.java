package dental_clinic.core.services;


import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.ContainsDatabaseIdRequest;
import dental_clinic.core.responses.ContainsDatabaseIdResponse;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.validators.ContainsDatabaseIdValidator;
import dental_clinic.core.database.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContainsDatabaseIdService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired private ContainsDatabaseIdValidator containsDatabaseIdValidator;

    public ContainsDatabaseIdResponse execute(ContainsDatabaseIdRequest containsDatabaseIdRequest){

        List<CoreError> errors = containsDatabaseIdValidator.validate(containsDatabaseIdRequest);

        if (!errors.isEmpty()){
            return new ContainsDatabaseIdResponse(errors);
        }

        if (patientRepository.containsPatientWithSpecificId(containsDatabaseIdRequest.getId())){
            return new ContainsDatabaseIdResponse(containsDatabaseIdRequest.getId());
        }

        errors.add(new CoreError("id", "Database doesn't contain patient with id"));
        return new ContainsDatabaseIdResponse(errors);
    }

    private PersonalData getCurrentPatientPersonalData(int index){
        return patientRepository.getPatients().get(index);
    }
}