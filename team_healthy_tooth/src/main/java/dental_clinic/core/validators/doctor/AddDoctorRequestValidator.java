package dental_clinic.core.validators.doctor;

import dental_clinic.core.requests.doctor.AddDoctorRequest;
import dental_clinic.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddDoctorRequestValidator {

    public List<CoreError> validate (AddDoctorRequest addDoctorRequest) {
        List <CoreError> errors = new ArrayList<>();

        errors.addAll(addNameValidationErrors(addDoctorRequest.getName()));
        errors.addAll(addSurnameValidationErrors(addDoctorRequest.getSurname()));
        errors.addAll(phoneValidationErrors(addDoctorRequest.getPhone()));

        return errors;
    }

    private List<CoreError> addNameValidationErrors (String name) {
        List<CoreError> errors = new ArrayList<>();
        if (name == null || name.isEmpty()) {
            errors.add(new CoreError("name", "Name can't be empty"));
        } else {
            if (!name.matches("[a-zA-ZēūīōāšģķļžčņĒŪĪŌĀŠĢĶĻŽČŅ]+")){
                errors.add(new CoreError("name", "Name can contain only letters"));
            }
        }
        return errors;
    }

    private List<CoreError> addSurnameValidationErrors (String name) {
        List<CoreError> errors = new ArrayList<>();
        if (name == null || name.isEmpty()) {
            errors.add(new CoreError("surname", "Surname can't be empty"));
        } else {
            if (!name.matches("[a-zA-ZēūīōāšģķļžčņĒŪĪŌĀŠĢĶĻŽČŅ]+")){
                errors.add(new CoreError("surname", "Surname can contain only letters"));
            }
        }
        return errors;
    }

    private List<CoreError> phoneValidationErrors(String phone){
        List <CoreError> errors = new ArrayList<>();
        if (!phone.matches("\\d{8}|\\d{11}|\\d{12}")) {
            errors.add(new CoreError("Personal data : phone", "Phone must contain 8 or 11 or 12 digits"));
        }
        return errors;
    }
}
