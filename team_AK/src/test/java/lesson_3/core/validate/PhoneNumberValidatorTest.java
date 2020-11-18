package lesson_3.core.validate;


import lesson_3.core.core_error.CoreError;
import lesson_3.core.request.add_client.client_items.AddClientPhoneRequest;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PhoneNumberValidatorTest {
    PhoneNumberValidator phoneValidator = new PhoneNumberValidator();

    @Test
    public void checkPhoneNumberFormat_1() {
        AddClientPhoneRequest request = new AddClientPhoneRequest("24897411");
        List<CoreError> result = phoneValidator.validate(request, "LV");
        assertEquals(0, result.size());
    }

    @Test
    public void checkPhoneNumberFormat_2() {
        AddClientPhoneRequest request = new AddClientPhoneRequest("+37124897411");
        List<CoreError> result = phoneValidator.validate(request, "LV");
        assertEquals(0, result.size());
    }

    @Test
    public void checkPhoneNumberFormat_3_UnsupportedFormat() {
        AddClientPhoneRequest request = new AddClientPhoneRequest("+37224897411");
        List<CoreError> result = phoneValidator.validate(request, "LV");
        assertEquals(1, result.size());
        assertEquals("phone number unsupported format", result.get(0).getMessage());
    }

    @Test
    public void checkPhoneNumberFormat_4_UnsupportedFormat() {
        AddClientPhoneRequest request = new AddClientPhoneRequest("54897411");
        List<CoreError> result = phoneValidator.validate(request, "LV");
        assertEquals(1, result.size());
        assertEquals("phone number unsupported format", result.get(0).getMessage());
    }

    @Test
    public void checkPhoneNumberFormat_5_UnsupportedFormat() {
        AddClientPhoneRequest request = new AddClientPhoneRequest("phone_number");
        List<CoreError> result = phoneValidator.validate(request, "LV");
        assertEquals(2, result.size());
        assertEquals("phone number input error", result.get(0).getMessage());
        assertEquals("phone number unsupported format", result.get(1).getMessage());
    }

    @Test
    public void checkPhoneNumberFormat_6_UnsupportedFormat() {
        AddClientPhoneRequest request = new AddClientPhoneRequest("5489741188");
        List<CoreError> result = phoneValidator.validate(request, "LV");
        assertEquals(1, result.size());
        assertEquals("phone number unsupported format", result.get(0).getMessage());
    }

    @Test
    public void checkPhoneNumberFormat_7_UnsupportedFormat() {
        AddClientPhoneRequest request = new AddClientPhoneRequest("54897");
        List<CoreError> result = phoneValidator.validate(request, "LV");
        assertEquals(1, result.size());
        assertEquals("phone number unsupported format", result.get(0).getMessage());
    }
}