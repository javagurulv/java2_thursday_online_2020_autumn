package dental_clinic.core.services;

import dental_clinic.core.requests.ChangePersonalDataRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.database.PatientDatabase;
import dental_clinic.database.PatientDatabaseImpl;

import java.util.ArrayList;
import java.util.List;

public class ChangePersonalDataValidator {

    public List<CoreError> validate (ChangePersonalDataRequest request) {
        List<CoreError> errors = new ArrayList<>();

        String updatedSurnameToCheck = request.getUpdatedSurname();
        String updaterPhoneNumberToCheck = request.getUpdatedPhoneNumber();

        errors.addAll(isValidUpdatedSurname(updatedSurnameToCheck));
        errors.addAll(isValidUpdatedPhoneNumber(updaterPhoneNumberToCheck));

        return errors;
    }

    private List<CoreError> isValidUpdatedSurname(String nameToCheck) {
        List<CoreError> errors = new ArrayList<>();
        if (!nameToCheck.matches("\\w+")) {
            errors.add(new CoreError(
                    "Personal data : surname",
                    "Invalid input! Can only contain letters!"));
        }
        return errors;
    }

    private List<CoreError> isValidUpdatedPhoneNumber(String phoneNumberToCheck) {
        List<CoreError> errors = new ArrayList<>();
        if (!phoneNumberToCheck.matches("\\d{8}")) {
            errors.add(new CoreError(
                    "Personal data : phone",
                    "Invalid input! Can only contain numbers!"));
        }
        return errors;
    }
}
