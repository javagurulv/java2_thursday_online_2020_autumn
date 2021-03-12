package dental_clinic.core.services.patient;

import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.Ordering;
import dental_clinic.core.requests.Paging;
import dental_clinic.core.requests.patient.SearchPatientRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.patient.SearchPatientResponse;
import dental_clinic.core.validators.patient.SearchPatientRequestValidator;
import dental_clinic.core.database.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class SearchPatientService {

    @Value("${search.ordering.enabled}")
    private boolean orderingEnabled;

    @Value("${search.paging.enabled}")
    private boolean pagingEnabled;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired private SearchPatientRequestValidator searchPatientRequestValidator;

    public SearchPatientResponse execute (SearchPatientRequest searchPatientRequest){

        List<CoreError> errors = searchPatientRequestValidator.validate(searchPatientRequest);

        if (!errors.isEmpty()){
            return new SearchPatientResponse(errors, new ArrayList<>());
        }
        return provideSearchResultAccordingToRequest(searchPatientRequest);
    }

    private SearchPatientResponse provideSearchResultAccordingToRequest(SearchPatientRequest searchPatientRequest){
        if (searchByPersonalCode(searchPatientRequest.getInputForSearch())){
            return searchByPersonalCodeIsProvided(searchPatientRequest);
        }
        return searchBySurnameIsProvided(searchPatientRequest);
    }

    private boolean searchByPersonalCode(String inputForSearch){
        return Pattern.matches("[0-9]{2}[0,1][0-9][0-9][0-9]-?[0-9]{5}", inputForSearch);
    }

    private SearchPatientResponse searchByPersonalCodeIsProvided(SearchPatientRequest searchPatientRequest){
        List<CoreError>errors = new ArrayList<>();
        List <PersonalData> patients = patientRepository.findPatientsByPersonalCode(searchPatientRequest.getInputForSearch());
        if (patients.isEmpty()){
            errors.add(new CoreError("database", "Database doesn't contain patient with personal code " +
                    searchPatientRequest.getInputForSearch()));
            return new SearchPatientResponse(errors, new ArrayList<>());
        }
        Ordering ordering = new Ordering(searchPatientRequest.getOrderBy(), searchPatientRequest.getOrderDirection());
        Paging paging = new Paging(searchPatientRequest.getPageNumber(), searchPatientRequest.getPageSize());
        patients = order(patients, ordering);
        patients = paging(patients, paging);
        return new SearchPatientResponse(patients);
    }

    private SearchPatientResponse searchBySurnameIsProvided(SearchPatientRequest searchPatientRequest){
        List<CoreError>errors = new ArrayList<>();
        List<PersonalData>patients = patientRepository.findPatientsBySurname(searchPatientRequest.getInputForSearch());
        if (patients.isEmpty()){
            errors.add(new CoreError("database", "Database doesn't contain patient with surname " +
                    searchPatientRequest.getInputForSearch()));
            return new SearchPatientResponse(errors, new ArrayList<>());
        }
        Ordering ordering = new Ordering(searchPatientRequest.getOrderBy(), searchPatientRequest.getOrderDirection());
        Paging paging = new Paging(searchPatientRequest.getPageNumber(), searchPatientRequest.getPageSize());

        patients = order(patients, ordering);
        patients = paging(patients, paging);
        return new SearchPatientResponse(patients);
    }

    private List<PersonalData> order(List<PersonalData> patients, Ordering ordering) {
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

    private List<PersonalData> sortByName(List<PersonalData> patients, Ordering ordering){
        if (ordering.getOrderDirection().equals("ASC")){
            return patients.stream()
                    .sorted(Comparator.comparing(patient -> patient.getName()))
                    .collect(Collectors.toList());
        }else{
            return patients.stream()
                    .sorted((o1,o2) -> o2.getName().compareTo(o1.getName()))
                    .collect(Collectors.toList());
        }
    }

    private List<PersonalData> sortBySurname(List<PersonalData> patients, Ordering ordering){
        if (ordering.getOrderDirection().equals("ASC")){
            return patients.stream()
                    .sorted(Comparator.comparing(patient -> patient.getSurname()))
                    .collect(Collectors.toList());
        }else{
            return patients.stream()
                    .sorted((o1,o2) -> o2.getSurname().compareTo(o1.getSurname()))
                    .collect(Collectors.toList());
        }
    }

    private List<PersonalData> paging(List<PersonalData> patients, Paging paging) {
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
