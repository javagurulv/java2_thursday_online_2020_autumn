package dental_clinic_tests.core.validators_tests.doctor;

import dental_clinic.core.requests.doctor.AddDoctorRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.validators.doctor.AddDoctorRequestValidator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class AddDoctorRequestValidatorTest {

    AddDoctorRequestValidator addDoctorRequestValidator = new AddDoctorRequestValidator();

    @Test
    public void testEmptyName(){
        AddDoctorRequest addDoctorRequest = new AddDoctorRequest(null, "Bobik", "12345678");
        CoreError expectedError = new CoreError("name", "Name can't be empty");
        List<CoreError> errorList = addDoctorRequestValidator.validate(addDoctorRequest);

        assertTrue(errorList.size() == 1);
        assertTrue(errorList.contains(expectedError));
    }

    @Test
    public void testEmptySurname(){
        AddDoctorRequest addDoctorRequest = new AddDoctorRequest("Name", "", "12345678");
        CoreError expectedError = new CoreError("surname", "Surname can't be empty");
        List<CoreError> errorList = addDoctorRequestValidator.validate(addDoctorRequest);

        assertTrue(errorList.size() == 1);
        assertTrue(errorList.contains(expectedError));
    }

    @Test
    public void testEmptySurnameAndName(){
        AddDoctorRequest addDoctorRequest = new AddDoctorRequest(null, "", "12345678");
        CoreError expectedError1 = new CoreError("name", "Name can't be empty");
        CoreError expectedError2 = new CoreError("surname", "Surname can't be empty");
        List<CoreError> errorList = addDoctorRequestValidator.validate(addDoctorRequest);

        assertTrue(errorList.size() == 2);
        assertTrue(errorList.contains(expectedError1));
        assertTrue(errorList.contains(expectedError2));
    }

    @Test
    public void testNoErrors(){
        AddDoctorRequest addDoctorRequest = new AddDoctorRequest("Name", "Surname", "12345678");
        List<CoreError> errorList = addDoctorRequestValidator.validate(addDoctorRequest);

        assertTrue(errorList.isEmpty());
    }
}
