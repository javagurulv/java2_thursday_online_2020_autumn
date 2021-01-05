package dental_clinic.core.services.patient;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.requests.Ordering;
import dental_clinic.core.requests.Paging;
import dental_clinic.core.requests.patient.SearchPatientRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.patient.SearchPatientResponse;
import dental_clinic.core.validators.patient.SearchPatientRequestValidator;
import dental_clinic.database.in_memory.patient.PatientDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchPatientService {

    @Value("${search.ordering.enabled}")
    private boolean orderingEnabled;

    @Value("${search.paging.enabled}")
    private boolean pagingEnabled;

    @Autowired
    private PatientDatabase patientDatabase;
    @Autowired private SearchPatientRequestValidator searchPatientRequestValidator;

    public SearchPatientResponse execute (SearchPatientRequest searchPatientRequest){
        List<CoreError> errors = searchPatientRequestValidator.validate(searchPatientRequest);
        if (!errors.isEmpty()){
            return new SearchPatientResponse(errors, new ArrayList<>());
        }
        return provideSearchResultAccordingToRequest(searchPatientRequest);
    }

    private SearchPatientResponse provideSearchResultAccordingToRequest(SearchPatientRequest searchPatientRequest){
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
        List <Patient> patients = patientDatabase.findPatientsByNameAndSurname(searchPatientRequest.getName(),
                searchPatientRequest.getSurname());
        if (patients.isEmpty()){
            errors.add(new CoreError("database", "Database doesn't contain patient with name " +
                    searchPatientRequest.getName() + " and surname " + searchPatientRequest.getSurname()));
            return new SearchPatientResponse(errors, new ArrayList<>());
        }
        patients = order(patients, searchPatientRequest.getOrdering());
        patients = paging(patients, searchPatientRequest.getPaging());
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
        patients = order(patients, searchPatientRequest.getOrdering());
        patients = paging(patients, searchPatientRequest.getPaging());
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
        patients = order(patients, searchPatientRequest.getOrdering());
        patients = paging(patients, searchPatientRequest.getPaging());
        return new SearchPatientResponse(patients);
    }

    private List<Patient> order(List<Patient> patients, Ordering ordering) {
        if (orderingEnabled)
        if (ordering.filledBoth()){
            if (ordering.getOrderBy().equals("name")){
                return sortByName(patients, ordering);
            }else{
                return sortBySurname(patients, ordering);
            }
        }
        return patients;
    }

    private List<Patient> sortByName(List<Patient> patients, Ordering ordering){
        if (ordering.getOrderDirection().equals("ASC")){
            return patients.stream()
                    .sorted(Comparator.comparing(patient -> patient.getPersonalData().getName()))
                    .collect(Collectors.toList());
        }else{
            return patients.stream()
                    .sorted((o1,o2) -> o2.getPersonalData().getName().compareTo(o1.getPersonalData().getName()))
                    .collect(Collectors.toList());
        }
    }

    private List<Patient> sortBySurname(List<Patient> patients, Ordering ordering){
        if (ordering.getOrderDirection().equals("ASC")){
            return patients.stream()
                    .sorted(Comparator.comparing(patient -> patient.getPersonalData().getSurname()))
                    .collect(Collectors.toList());
        }else{
            return patients.stream()
                    .sorted((o1,o2) -> o2.getPersonalData().getSurname().compareTo(o1.getPersonalData().getSurname()))
                    .collect(Collectors.toList());
        }
    }

    private List<Patient> paging(List<Patient> patients, Paging paging) {
        if (pagingEnabled)
        if (paging != null) {
            Integer skip = (paging.getPageNumber() - 1) * paging.getPageSize();
            return patients.stream()
                    .skip(skip)
                    .limit(paging.getPageSize())
                    .collect(Collectors.toList());
        }
        return patients;
    }
}
