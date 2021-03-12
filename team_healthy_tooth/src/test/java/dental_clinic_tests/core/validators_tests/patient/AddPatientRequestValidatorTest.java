package dental_clinic_tests.core.validators_tests.patient;

import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.patient.AddPatientRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.validators.patient.AddPatientRequestValidator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AddPatientRequestValidatorTest {

    AddPatientRequestValidator addPatientRequestValidator = new AddPatientRequestValidator();

    @Test
    public void testNotValidName(){
        CoreError coreError = new CoreError("Personal data : name", "Name can't be empty");

        AddPatientRequest addPatientRequest = new AddPatientRequest(null, "Surname", "12345678", "25024512345");

        List<CoreError> errors = addPatientRequestValidator.validate(addPatientRequest);
        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(coreError));
    }

    @Test
    public void testNotValidSurname(){
        CoreError coreError = new CoreError("Personal data : surname", "Surname can't be empty");

        AddPatientRequest addPatientRequest = new AddPatientRequest("name", "", "12345678", "25024512345");

        List<CoreError> errors = addPatientRequestValidator.validate(addPatientRequest);
        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(coreError));
    }

    @Test
    public void testNotValidPhone(){
        CoreError coreError = new CoreError("Personal data : phone", "Phone must contain 8 or 11 or 12 digits");

        AddPatientRequest addPatientRequest = new AddPatientRequest("name", "Surname", "avbd4", "25024512345");

        List<CoreError> errors = addPatientRequestValidator.validate(addPatientRequest);
        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(coreError));
    }

    @Test
    public void testNotValidPhoneWrongLength(){
        CoreError coreError = new CoreError("Personal data : phone", "Phone must contain 8 or 11 or 12 digits");

        AddPatientRequest addPatientRequest = new AddPatientRequest("name", "Surname", "12546", "25024512345");

        List<CoreError> errors = addPatientRequestValidator.validate(addPatientRequest);
        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(coreError));
    }

    @Test
    public void testNotValidPersonalCodeLetters(){
        CoreError coreError = new CoreError("Personal data : personal code", "Valid personal format is DDMMYYNNNNN or DDMMYY-NNNNN, where N is digit");

        AddPatientRequest addPatientRequest = new AddPatientRequest("name", "Surname", "12345678", "adfds");

        List<CoreError> errors = addPatientRequestValidator.validate(addPatientRequest);
        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(coreError));
    }

    @Test
    public void testNotValidPersonalCodeWrongLength(){
        CoreError coreError = new CoreError("Personal data : personal code", "Valid personal format is DDMMYYNNNNN or DDMMYY-NNNNN, where N is digit");

        AddPatientRequest addPatientRequest = new AddPatientRequest("name", "Surname", "12345678", "1235");

        List<CoreError> errors = addPatientRequestValidator.validate(addPatientRequest);
        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(coreError));
    }

    @Test
    public void validRequest(){
        PersonalData personalData = new PersonalData("name", "Surname", "12345678", "25024512345");
        AddPatientRequest addPatientRequest = new AddPatientRequest("name", "Surname", "12345678", "25024512345");

        List<CoreError> errors = addPatientRequestValidator.validate(addPatientRequest);
        assertTrue(errors.size() == 0);
    }

}