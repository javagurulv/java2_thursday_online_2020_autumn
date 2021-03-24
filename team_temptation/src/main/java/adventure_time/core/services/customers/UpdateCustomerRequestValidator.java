package adventure_time.core.services.customers;

import adventure_time.core.requests.customers.UpdateCustomerRequest;
import adventure_time.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class UpdateCustomerRequestValidator {
    private static final int MIN_NAME_LENGTH = 5;
    private static final int MAX_NAME_LENGTH = 50;
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MAX_PASSWORD_LENGTH = 20;
    private static final Pattern PATTERN_EMAIL = Pattern.compile("^([a-z0-9_\\.-]+)@([a-z0-9_\\.-]+)\\.([a-z\\.]{2,6})$", Pattern.CASE_INSENSITIVE);
    private static final Pattern PATTERN_PASSWORD = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");
    private static final Pattern PATTERN_PHONE_LV = Pattern.compile("^\\++\\d{8,11}$");


    public List<CoreError> validate (UpdateCustomerRequest request) {

        List<CoreError> errors = new ArrayList<>();

        boolean nullName = request.getName() == null || request.getName().isBlank();
        boolean nullEmail = request.getEmail() == null || request.getEmail().isBlank();
        boolean nullPhone = request.getPhone() == null || request.getPhone().isBlank();
        boolean nullPassword = request.getPassword() == null || request.getPassword().isBlank();

        if (!nullName && request.getName().length() < MIN_NAME_LENGTH) {
            CoreError error = new CoreError("customerName", "Must contain from 5 to 55 symbols");
            errors.add(error);
        }

        if (!nullName && request.getName().length() > MAX_NAME_LENGTH) {
            CoreError error = new CoreError("customerName", "Must contain from 5 to 55 symbols");
            errors.add(error);
        }

        if (!nullEmail && (!PATTERN_EMAIL.matcher(request.getEmail()).matches())) {
            CoreError error = new CoreError("customerEmail", "Email is incorrect");
            errors.add(error);
        }

        if (!nullPhone && (!PATTERN_PHONE_LV.matcher(request.getPhone()).matches())) {
            CoreError error = new CoreError("customerPhone", "Phone number is incorrect");
            errors.add(error);
        }

        if ((!nullPassword) && request.getPassword().length() < MIN_PASSWORD_LENGTH) {
            CoreError error = new CoreError("customerPassword", "Passwords too short");
            errors.add(error);
        }

        if ((!nullPassword) && request.getPassword().length() > MAX_PASSWORD_LENGTH) {
            CoreError error = new CoreError("customerPassword", "Passwords too long");
            errors.add(error);
        }

        if ((!nullPassword) && !(PATTERN_PASSWORD.matcher(request.getPassword()).matches())) {
            CoreError error = new CoreError("customerPassword", "Passwords does not meet security requirements");
            errors.add(error);
        }

        return errors;
    }
}
