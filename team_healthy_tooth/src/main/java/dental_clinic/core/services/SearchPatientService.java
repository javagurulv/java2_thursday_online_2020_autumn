package dental_clinic.core.services;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.requests.SearchPatientRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.SearchPatientResponse;
import dental_clinic.database.PatientDatabase;

import java.util.ArrayList;
import java.util.List;

public class SearchPatientService {

    private final PatientDatabase patientDatabase;
    private final SearchPatientRequestValidator searchPatientRequestValidator;

    public SearchPatientService(PatientDatabase patientDatabase, SearchPatientRequestValidator searchPatientRequestValidator) {
        this.patientDatabase = patientDatabase;
        this.searchPatientRequestValidator = searchPatientRequestValidator;
    }

    public SearchPatientResponse execute (SearchPatientRequest searchPatientRequest){

        List<CoreError> errors = searchPatientRequestValidator.validate(searchPatientRequest);

        if (!errors.isEmpty()){
            return new SearchPatientResponse(errors, new ArrayList<>());
        }

        if (isNameAndSurnameFilled(searchPatientRequest.getName(), searchPatientRequest.getSurname())){
            return searchByNameAndSurnameIsProvided(searchPatientRequest);
        }

        if (isNameFilled(searchPatientRequest.getName())){
            return searchByNameIsProvided(searchPatientRequest);
        }

        return searchBySurnameIsProvided(searchPatientRequest);
    }

    private boolean isNameAndSurnameFilled(String name, String surname){
        return name != null && !name.isEmpty() &&
                surname != null && !surname.isEmpty();
    }

    private SearchPatientResponse searchByNameAndSurnameIsProvided(SearchPatientRequest searchPatientRequest){
        List<CoreError>errors = new ArrayList<>();
        List <Patient> patients = patientDatabase.findPatientsByNameAndSurname(searchPatientRequest.getName(), searchPatientRequest.getSurname());
        if (patients.isEmpty()){
            errors.add(new CoreError("database", "Database doesn't contain patient with name " +
                    searchPatientRequest.getName() + " and surname " + searchPatientRequest.getSurname()));
            return new SearchPatientResponse(errors, new ArrayList<>());
        }
        return new SearchPatientResponse(patients);
    }

    private boolean isNameFilled(String name){
        return name != null && !name.isEmpty();
    }

    private SearchPatientResponse searchByNameIsProvided(SearchPatientRequest searchPatientRequest){
        List<CoreError>errors = new ArrayList<>();
        List <Patient> patients = patientDatabase.findPatientByName(searchPatientRequest.getName());
        if (patients.isEmpty()){
            errors.add(new CoreError("database", "Database doesn't contain patient with name " +
                    searchPatientRequest.getName()));
            return new SearchPatientResponse(errors, new ArrayList<>());
        }
        return new SearchPatientResponse(patients);
    }

    private SearchPatientResponse searchBySurnameIsProvided(SearchPatientRequest searchPatientRequest){
        List<CoreError>errors = new ArrayList<>();
        List<Patient>patients = patientDatabase.findPatientsBySurname(searchPatientRequest.getSurname());
        if (patients.isEmpty()){
            errors.add(new CoreError("database", "Database doesn't contain patient with surname " +
                    searchPatientRequest.getSurname()));
            return new SearchPatientResponse(errors, new ArrayList<>());
        }

        return new SearchPatientResponse(patients);
    }
}