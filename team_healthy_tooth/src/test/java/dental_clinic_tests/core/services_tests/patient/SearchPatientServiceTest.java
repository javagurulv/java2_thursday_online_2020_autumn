package dental_clinic_tests.core.services_tests.patient;

import dental_clinic.core.domain.OrderingDirection;
import dental_clinic.core.domain.Patient;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.Ordering;
import dental_clinic.core.requests.Paging;
import dental_clinic.core.requests.patient.SearchPatientRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.patient.SearchPatientResponse;
import dental_clinic.core.services.patient.SearchPatientService;
import dental_clinic.core.validators.patient.SearchPatientRequestValidator;
import dental_clinic.core.database.patient.PatientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class SearchPatientServiceTest {
/*
    @Mock
    private PatientRepository patientRepository;
    @Mock
    private SearchPatientRequestValidator searchPatientRequestValidator;
    @InjectMocks
    private SearchPatientService searchPatientService;

    private Ordering validOrdering = new Ordering("surname", OrderingDirection.ASC);
    private Paging validPaging = new Paging(1, 2);

    @Test
    public void testSearchRequestEmpty(){
        CoreError expectedError = new CoreError("search", "Not valid input for search");
        List<CoreError> errors = new ArrayList<>();
        errors.add(expectedError);

        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("", validOrdering, validPaging);

        Mockito.when(searchPatientRequestValidator.validate(searchPatientRequest)).thenReturn(errors);

        SearchPatientResponse searchPatientResponse = searchPatientService.execute(searchPatientRequest);

        assertTrue(searchPatientResponse.hasErrors());
        assertTrue(searchPatientResponse.getErrors().contains(expectedError));
        assertTrue(searchPatientResponse.getErrors().size() == 1);
    }

    @Test
    public void testNoPatientWithSpecificSurname(){
        CoreError expectedError =
                new CoreError("database", "Database doesn't contain patient with surname Bobbins");
        List<CoreError> errors = new ArrayList<>();
        errors.add(expectedError);

        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("Bobbins", validOrdering, validPaging);

        Mockito.when(searchPatientRequestValidator.validate(searchPatientRequest)).thenReturn(new ArrayList<>());
        Mockito.when(patientRepository.findPatientsBySurname(searchPatientRequest.getInputForSearch())).thenReturn(new ArrayList<>());

        SearchPatientResponse searchPatientResponse = searchPatientService.execute(searchPatientRequest);

        assertTrue(searchPatientResponse.hasErrors());
        assertTrue(searchPatientResponse.getErrors().contains(expectedError));
        assertTrue(searchPatientResponse.getErrors().size() == 1);
    }

    @Test
    public void testOrderingDescending(){
        ReflectionTestUtils.setField(searchPatientService, "orderingEnabled", true);
        ReflectionTestUtils.setField(searchPatientService, "pagingEnabled", false);
        Ordering ordering = new Ordering("name", OrderingDirection.DESC);
        PersonalData personalData1 = new PersonalData("Bobik", "A", "12345678", "25052512345");
        PersonalData personalData2 = new PersonalData("Bob", "A", "12345678", "25052712345");
        PersonalData personalData3 = new PersonalData("Robert", "A", "12345678", "25062712345");
        Patient patient1 = new Patient(personalData1);
        Patient patient2 = new Patient(personalData2);
        Patient patient3 = new Patient(personalData3);
        List<Patient>patients = new ArrayList<>();
        patients.add(patient1);
        patients.add(patient2);
        patients.add(patient3);

        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("A", ordering, validPaging);

        Mockito.when(searchPatientRequestValidator.validate(searchPatientRequest)).thenReturn(new ArrayList<>());
        Mockito.when(patientRepository.findPatientsBySurname(searchPatientRequest.getInputForSearch())).thenReturn(patients);

        SearchPatientResponse searchPatientResponse = searchPatientService.execute(searchPatientRequest);

        assertFalse(searchPatientResponse.hasErrors());
        assertTrue(searchPatientResponse.getPatients().size() == 3);
        assertTrue(searchPatientResponse.getPatients().contains(patient1));
        assertTrue(searchPatientResponse.getPatients().contains(patient2));
        assertTrue(searchPatientResponse.getPatients().contains(patient3));
        assertTrue(searchPatientResponse.getPatients().get(0).equals(patient3));
    }

    @Test
    public void testOrderingDescendingPagingTrue(){
        ReflectionTestUtils.setField(searchPatientService, "orderingEnabled", true);
        ReflectionTestUtils.setField(searchPatientService, "pagingEnabled", true);
        Ordering ordering = new Ordering("name", OrderingDirection.DESC);
        PersonalData personalData1 = new PersonalData("Bobik", "A", "12345678", "25052512345");
        PersonalData personalData2 = new PersonalData("Bob", "A", "12345678", "25052712345");
        PersonalData personalData3 = new PersonalData("Robert", "A", "12345678", "25062712345");
        Patient patient1 = new Patient(personalData1);
        Patient patient2 = new Patient(personalData2);
        Patient patient3 = new Patient(personalData3);
        List<Patient>patients = new ArrayList<>();
        patients.add(patient1);
        patients.add(patient2);
        patients.add(patient3);

        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("A", ordering, validPaging);

        Mockito.when(searchPatientRequestValidator.validate(searchPatientRequest)).thenReturn(new ArrayList<>());
        Mockito.when(patientRepository.findPatientsBySurname(searchPatientRequest.getInputForSearch())).thenReturn(patients);

        SearchPatientResponse searchPatientResponse = searchPatientService.execute(searchPatientRequest);

        assertFalse(searchPatientResponse.hasErrors());
        assertTrue(searchPatientResponse.getPatients().size() == 2);
        assertTrue(searchPatientResponse.getPatients().contains(patient1));
        assertFalse(searchPatientResponse.getPatients().contains(patient2));
        assertTrue(searchPatientResponse.getPatients().contains(patient3));
        assertTrue(searchPatientResponse.getPatients().get(0).equals(patient3));
    }

    @Test
    public void testOrderingAndPagingFalse(){
        ReflectionTestUtils.setField(searchPatientService, "orderingEnabled", false);
        ReflectionTestUtils.setField(searchPatientService, "pagingEnabled", false);
        PersonalData personalData1 = new PersonalData("Bobik", "A", "12345678", "25052512345");
        PersonalData personalData2 = new PersonalData("Bob", "A", "12345678", "25052712345");
        PersonalData personalData3 = new PersonalData("Robert", "A", "12345678", "25062712345");
        Patient patient1 = new Patient(personalData1);
        Patient patient2 = new Patient(personalData2);
        Patient patient3 = new Patient(personalData3);
        List<Patient>patients = new ArrayList<>();
        patients.add(patient1);
        patients.add(patient2);
        patients.add(patient3);

        SearchPatientRequest searchPatientRequest = new SearchPatientRequest("A", validOrdering, validPaging);

        Mockito.when(searchPatientRequestValidator.validate(searchPatientRequest)).thenReturn(new ArrayList<>());
        Mockito.when(patientRepository.findPatientsBySurname(searchPatientRequest.getInputForSearch())).thenReturn(patients);

        SearchPatientResponse searchPatientResponse = searchPatientService.execute(searchPatientRequest);

        assertFalse(searchPatientResponse.hasErrors());
        assertTrue(searchPatientResponse.getPatients().size() == 3);
        assertTrue(searchPatientResponse.getPatients().contains(patient1));
        assertTrue(searchPatientResponse.getPatients().contains(patient2));
        assertTrue(searchPatientResponse.getPatients().contains(patient3));
        assertTrue(searchPatientResponse.getPatients().get(0).equals(patient1));
    }

*/
}