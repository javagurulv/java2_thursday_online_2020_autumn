package dental_clinic.core.services.patient;

import dental_clinic.core.domain.Jowl;
import dental_clinic.core.domain.Patient;
import dental_clinic.core.domain.ToothStatus;
import dental_clinic.core.requests.patient.GetPatientCardRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.patient.GetPatientCardResponse;
import dental_clinic.core.validators.patient.GetPatientCardRequestValidator;
import dental_clinic.core.database.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class GetPatientCardService {

    @Autowired private PatientRepository patientRepository;
    @Autowired
    private GetPatientCardRequestValidator getPatientCardRequestValidator;
    
    public GetPatientCardResponse execute (GetPatientCardRequest getPatientCardRequest){
        List<CoreError> errors = getPatientCardRequestValidator.validate(getPatientCardRequest);
        
        if (!errors.isEmpty()){
            return new GetPatientCardResponse(errors);
        }
        
        if (!patientRepository.containsPatientWithSpecificId(getPatientCardRequest.getId())){
            errors.add(new CoreError("database", "Database doesn't contain patient with id " +
                    getPatientCardRequest.getId()));
            return new GetPatientCardResponse(errors);
        }
        
        Patient patientCard = createPatientCard(getPatientCardRequest);
        return new GetPatientCardResponse(patientCard);
    }

    private Patient createPatientCard(GetPatientCardRequest getPatientCardRequest){
        Patient newPatient = new Patient();
        newPatient.setPersonalData(patientRepository.getPatientCard(getPatientCardRequest.getId()).get().getPersonalData());
        Jowl specificPatientJowl = new Jowl();
    /*    specificPatientJowl = getSpecificPatientJowl(getPatientCardRequest);
        Jowl currentStatusOfJowl = new Jowl();
        currentStatusOfJowl = getCurrentPatientJowl(specificPatientJowl);
        newPatient.setJowl(currentStatusOfJowl);*/
        return newPatient;
    }
/*
    private Jowl getCurrentPatientJowl(Jowl specificPatientJowl) {
        Jowl currentStatusOfJowl = new Jowl();
        for (Integer key : specificPatientJowl.getJowl().keySet()) {
            currentStatusOfJowl.getJowl().put(key,
                    new ArrayList<>(addLastStatus(specificPatientJowl, key)));
        }
        return currentStatusOfJowl;
    }

    private Jowl getSpecificPatientJowl(GetPatientCardRequest getPatientCardRequest) {
        Jowl specificPatientJowl = new Jowl();

        for (Integer key : specificPatientJowl.getJowl().keySet()) {
            specificPatientJowl.getJowl().put(key,
                    new ArrayList<>(addLastStatus(patientRepository.getPatientCard(getPatientCardRequest.getId()).get().getJowl(), key)));
        }
        return specificPatientJowl;
    }

    private List<ToothStatus> addLastStatus(Jowl specificPatientJowl, Integer key) {
        return Arrays.asList(specificPatientJowl.getJowl().get(key).get(specificPatientJowl.getJowl().get(key).size() - 1));
    }*/

}
