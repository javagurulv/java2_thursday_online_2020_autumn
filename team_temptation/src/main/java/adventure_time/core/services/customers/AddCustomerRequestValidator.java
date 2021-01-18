package adventure_time.core.services.customers;

import adventure_time.core.requests.customers.AddCustomerRequest;
import adventure_time.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class AddCustomerRequestValidator {

    private static final int MIN_NAME_LENGTH = 5;
    private static final int MAX_NAME_LENGTH = 50;
    private static final Pattern PATTERN_EMAIL = Pattern.compile("^([a-z0-9_\\.-]+)@([a-z0-9_\\.-]+)\\.([a-z\\.]{2,6})$", Pattern.CASE_INSENSITIVE);
    private static final Pattern PATTERN_PHONE_LV = Pattern.compile("^\\+371+\\d{8}$");


    public List<CoreError> validate (AddCustomerRequest request) {

        List<CoreError> errors = new ArrayList<>();

        boolean nullName = request.getCustomerName() == null || request.getCustomerName().isBlank();
        boolean nullEmail = request.getCustomerEmail() == null || request.getCustomerEmail().isBlank();
        boolean nullPhone = request.getCustomerPhone() == null || request.getCustomerPhone().isBlank();

        if (nullName) {
            CoreError error = new CoreError("customerName", "Must be not empty");
            errors.add(error);
        }

        if (!nullName && request.getCustomerName().length() < MIN_NAME_LENGTH) {
            CoreError error = new CoreError("customerName", "Must contain from 5 to 55 symbols");
            errors.add(error);
        }

        if (!nullName && request.getCustomerName().length() > MAX_NAME_LENGTH) {
            CoreError error = new CoreError("customerName", "Must contain from 5 to 55 symbols");
            errors.add(error);
        }

        if (nullEmail) {
            CoreError error = new CoreError("customerEmail", "Must be not empty");
            errors.add(error);
        }

        if (!nullEmail && (!PATTERN_EMAIL.matcher(request.getCustomerEmail()).matches())) {
            CoreError error = new CoreError("customerEmail", "Email is incorrect");
            errors.add(error);
        }

        if (nullPhone) {
            CoreError error = new CoreError("customerPhone", "Must be not empty");
            errors.add(error);
        }

        if (!nullPhone && (!PATTERN_PHONE_LV.matcher(request.getCustomerPhone()).matches())) {
            CoreError error = new CoreError("customerPhone", "Phone number is incorrect");
            errors.add(error);
        }

        return errors;
    }

}
