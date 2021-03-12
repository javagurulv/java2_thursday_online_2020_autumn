package dental_clinic.core.validators.user;

import dental_clinic.core.requests.patient.ChangePersonalDataRequest;
import dental_clinic.core.requests.user.AddUserRequest;
import dental_clinic.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddUserRequestValidator {

    public List<CoreError> validate (AddUserRequest addUserRequest) {
        List <CoreError> errors = new ArrayList<>();
        errors.addAll(nameValidationErrors(addUserRequest.getName()));
        errors.addAll(surnameValidationErrors(addUserRequest.getSurname()));
        errors.addAll(idValidationErrors(addUserRequest.getRoleId()));
        return errors;
    }

    private List<CoreError> nameValidationErrors(String name){
        List <CoreError> errors = new ArrayList<>();
        if (name == null || name.isEmpty()) {
            errors.add(new CoreError("User : name", "Name can't be empty"));
        }else{
            if (!name.matches("[a-zA-ZēūīōāšģķļžčņĒŪĪŌĀŠĢĶĻŽČŅ]+")){
                errors.add(new CoreError("User : name", "Name can contain only letters"));
            }
        }
        return errors;
    }

    private List<CoreError> surnameValidationErrors(String surname){
        List <CoreError> errors = new ArrayList<>();
        if (surname == null || surname.isEmpty()){
            errors.add(new CoreError("User: surname", "Surname can't be empty"));
        } else{
            if (!surname.matches("[a-zA-ZēūīōāšģķļžčņĒŪĪŌĀŠĢĶĻŽČŅ]+")) {
                errors.add(new CoreError("User: surname", "Surname can contain only letters"));
            }
        }
        return errors;
    }

    private List<CoreError> idValidationErrors (Long id) {
        List<CoreError> errors = new ArrayList<>();
        if (id == null || id < 1) {
            errors.add(new CoreError("id", "Not valid id number"));
        }
        return errors;
    }
}
