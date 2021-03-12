package dental_clinic_tests.core.services_tests.doctor;

import dental_clinic.core.domain.Doctor;
import dental_clinic.core.requests.doctor.AddDoctorRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.doctor.AddDoctorResponse;
import dental_clinic.core.services.doctor.AddDoctorService;
import dental_clinic.core.validators.doctor.AddDoctorRequestValidator;
import dental_clinic.core.database.doctor.DoctorRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class AddDoctorServiceTest {

    @Mock
    private AddDoctorRequestValidator addDoctorRequestValidator;
    @Mock
    private DoctorRepository doctorRepository;
    @InjectMocks
    AddDoctorService addDoctorService;

    @Test
    public void testNotValidInput(){
        CoreError expectedError = new CoreError("name", "Name can't be empty");
        List<CoreError> errorList = new ArrayList<>();
        errorList.add(expectedError);

        AddDoctorRequest addDoctorRequest = new AddDoctorRequest("", null, "12345678");
        Mockito.when(addDoctorRequestValidator.validate(addDoctorRequest)).thenReturn(errorList);

        AddDoctorResponse addDoctorResponse = addDoctorService.execute(addDoctorRequest);

        assertTrue(addDoctorResponse.hasErrors());
        assertTrue(addDoctorResponse.getErrors().contains(expectedError));
        assertTrue(addDoctorResponse.getErrors().size() == 1);
    }

    @Test
    public void testDatabaseContainsTheSameDoctor(){
        CoreError expectedError = new CoreError("database", "Database contains the same doctor");
        List<CoreError> errorList = new ArrayList<>();
        errorList.add(expectedError);

        AddDoctorRequest addDoctorRequest = new AddDoctorRequest("Name", "Surname", "12345678");
        Mockito.when(addDoctorRequestValidator.validate(addDoctorRequest)).thenReturn(new ArrayList<>());
        Mockito.when(doctorRepository.containsDoctor(new Doctor("Name", "Surname", "12345678"))).thenReturn(true);


        AddDoctorResponse addDoctorResponse = addDoctorService.execute(addDoctorRequest);

        assertTrue(addDoctorResponse.hasErrors());
        assertTrue(addDoctorResponse.getErrors().contains(expectedError));
        assertTrue(addDoctorResponse.getErrors().size() == 1);
    }

    @Test
    public void testDoctorAddedSuccessfully(){
        AddDoctorRequest addDoctorRequest = new AddDoctorRequest("Name", "Surname", "12345678");
        Mockito.when(addDoctorRequestValidator.validate(addDoctorRequest)).thenReturn(new ArrayList<>());
        Mockito.when(doctorRepository.containsDoctor(new Doctor("Name", "Surname", "12345678"))).thenReturn(false);


        AddDoctorResponse addDoctorResponse = addDoctorService.execute(addDoctorRequest);

        assertFalse(addDoctorResponse.hasErrors());
        assertTrue(addDoctorResponse.getDoctor().getName().equals("Name"));
    }

}
