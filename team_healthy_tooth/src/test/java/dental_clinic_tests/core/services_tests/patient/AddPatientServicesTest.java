package dental_clinic_tests.core.services_tests.patient;

import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.patient.AddPatientRequest;
import dental_clinic.core.responses.patient.AddPatientResponse;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.services.patient.AddPatientService;
import dental_clinic_tests.core.services_tests.matchers.PersonalDataMatcher;
import dental_clinic.core.validators.patient.AddPatientRequestValidator;
import dental_clinic.core.database.patient.PatientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.argThat;

@RunWith(MockitoJUnitRunner.class)
public class AddPatientServicesTest {

    @Mock private PatientRepository patientRepository;
    @Mock private AddPatientRequestValidator addPatientRequestValidator;
    @InjectMocks private AddPatientService addPatientService;

    @Test
    public void testNotValidSurnameRequest(){
        AddPatientRequest addPatientRequest = new AddPatientRequest("Name", null, "12345678", "25052512345");
        List<CoreError> errors = new ArrayList<>();
        CoreError expectedError = new CoreError("Personal data : surname", "Not valid input for surname");
        errors.add(expectedError);
        Mockito.when(addPatientRequestValidator.validate(addPatientRequest)).thenReturn(errors);

        AddPatientResponse addPatientResponse = addPatientService.execute(addPatientRequest);
        assertTrue(addPatientResponse.hasErrors());
        assertTrue(addPatientResponse.getErrors().size() == 1);
        assertTrue(addPatientResponse.getErrors().contains(expectedError));
        Mockito.verifyNoInteractions(patientRepository);
    }

    @Test
    public void testNotValidEmptyRequest(){
        AddPatientRequest addPatientRequest = new AddPatientRequest("", null, "", null);
        List<CoreError> errors = new ArrayList<>();
        CoreError expectedError1 = new CoreError("Personal data : name", "Not valid input for name");
        CoreError expectedError2 = new CoreError("Personal data : surname", "Not valid input for surname");
        CoreError expectedError3 = new CoreError("Personal data : phone", "Not valid input for phone");
        CoreError expectedError4 = new CoreError("Personal data : personal code", "Not valid input for personal code");
        errors.add(expectedError1);
        errors.add(expectedError2);
        errors.add(expectedError3);
        errors.add(expectedError4);
        Mockito.when(addPatientRequestValidator.validate(addPatientRequest)).thenReturn(errors);

        AddPatientResponse addPatientResponse = addPatientService.execute(addPatientRequest);
        assertTrue(addPatientResponse.hasErrors());
        assertTrue(addPatientResponse.getErrors().size() == 4);
        assertTrue(addPatientResponse.getErrors().contains(expectedError1));
        assertTrue(addPatientResponse.getErrors().contains(expectedError2));
        assertTrue(addPatientResponse.getErrors().contains(expectedError3));
        assertTrue(addPatientResponse.getErrors().contains(expectedError4));
        Mockito.verifyNoInteractions(patientRepository);
    }

    @Test
    public void testDatabaseContainsTheSamePatient(){
        PersonalData personalData = new PersonalData("Name", "Surname", "12345678", "25052512345");
        AddPatientRequest addPatientRequest = new AddPatientRequest("Name", "Surname", "12345678", "25052512345");
        List<CoreError> errors = new ArrayList<>();
        CoreError expectedError = new CoreError("database", "Database contains the same patient");
        errors.add(expectedError);
        Mockito.when(addPatientRequestValidator.validate(addPatientRequest)).thenReturn(new ArrayList<>());
        Mockito.when(patientRepository.containsSpecificPersonalData(personalData)).thenReturn(true);

        AddPatientResponse addPatientResponse = addPatientService.execute(addPatientRequest);
        assertTrue(addPatientResponse.hasErrors());
        assertTrue(addPatientResponse.getErrors().size() == 1);
        assertTrue(addPatientResponse.getErrors().contains(expectedError));
    }

    @Test
    public void testPatientAddedSuccessfully(){
        PersonalData personalData = new PersonalData("Name", "Surname", "12345678", "25052512345");
        AddPatientRequest addPatientRequest = new AddPatientRequest("Name", "Surname", "12345678", "25052512345");
        Mockito.when(addPatientRequestValidator.validate(addPatientRequest)).thenReturn(new ArrayList<>());
        Mockito.when(patientRepository.containsSpecificPersonalData(personalData)).thenReturn(false);

        AddPatientResponse addPatientResponse = addPatientService.execute(addPatientRequest);
        assertTrue(!addPatientResponse.hasErrors());
        assertTrue(addPatientResponse.getNewPatient().getPersonalData().equals(personalData));
        Mockito.verify(patientRepository).addPatient(argThat(new PersonalDataMatcher("25052512345")));
    }
}