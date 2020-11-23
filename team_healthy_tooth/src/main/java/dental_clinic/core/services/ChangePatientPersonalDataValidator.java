package dental_clinic.core.services;

import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.ChangePatientPersonalDataRequest;
import dental_clinic.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ChangePatientPersonalDataValidator {

    public List<CoreError> validate (ChangePatientPersonalDataRequest changePatientPersonalDataRequest) {
        List<CoreError> errors = new ArrayList<>();

        errors.addAll(isValidNewPersonalData(changePatientPersonalDataRequest));

        return errors;
    }

    private List<CoreError> isValidNewPersonalData(ChangePatientPersonalDataRequest request) {
        List<CoreError> errors = new ArrayList<>();

        String nameToCheck = request.getUpdatedName();
        if (!isValidName(nameToCheck)) {
            errors.add(new CoreError("Personal data : name", "Not valid input for name"));
        }

        String surnameToCheck = request.getUpdatedSurname();
        if (!isValidSurname(surnameToCheck)) {
            errors.add(new CoreError("Personal data : surname", "Not valid input for surname"));
        }

        String phoneToCheck = request.getUpdatedPhoneNumber();
        if (!isValidPhone(phoneToCheck)) {
            errors.add(new CoreError("Personal data : phone", "Not valid input for phone"));
        }

        return errors;
    }


    private boolean isValidName(String nameToCheck) {
        return (nameToCheck != null && !nameToCheck.isBlank());
    }

    private boolean isValidSurname(String surnameToCheck) {
        return ( surnameToCheck != null && !surnameToCheck.isBlank());
    }

    private boolean isValidPhone(String phoneNumber) {
        // 12345678 and nothing else
        return phoneNumber.matches("\\b\\d{8}\\b");
    }
}
