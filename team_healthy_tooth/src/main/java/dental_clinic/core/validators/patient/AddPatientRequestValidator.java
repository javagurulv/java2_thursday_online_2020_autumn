package dental_clinic.core.validators.patient;

import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.patient.AddPatientRequest;
import dental_clinic.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class AddPatientRequestValidator {

    public List <CoreError> validate (AddPatientRequest addPatientRequest){
        List <CoreError> errors = new ArrayList<>();

        addPatientRequest.setPersonalCode(addPatientRequest.getPersonalCode().replace("-", ""));

        PersonalData personalData = new PersonalData(addPatientRequest.getName(), addPatientRequest.getSurname(),
                addPatientRequest.getPhone(), addPatientRequest.getPersonalCode());
        errors.addAll(isValidPersonalData(personalData));

        return errors;
    }

    private List <CoreError> isValidPersonalData(PersonalData personalData){
        List <CoreError> errors = new ArrayList<>();

        errors.addAll(nameValidationErrors(personalData.getName()));

        errors.addAll(surnameValidationErrors(personalData.getSurname()));

        errors.addAll(phoneValidationErrors(personalData.getPhone()));

        errors.addAll(personalCodeValidationErrors(personalData.getPersonalCode()));

        return errors;
    }

    private List<CoreError> nameValidationErrors(String name){
        List <CoreError> errors = new ArrayList<>();
        if (name == null || name.isEmpty()) {
            errors.add(new CoreError("Personal data : name", "Name can't be empty"));
        }else{
            if (!name.matches("[a-zA-ZēūīōāšģķļžčņĒŪĪŌĀŠĢĶĻŽČŅ]+")){
                errors.add(new CoreError("Personal data : name", "Name can contain only letters"));
            }
        }
        return errors;
    }

    private List<CoreError> surnameValidationErrors(String surname){
        List <CoreError> errors = new ArrayList<>();
        if (surname == null || surname.isEmpty()){
            errors.add(new CoreError("Personal data : surname", "Surname can't be empty"));
        } else{
            if (!surname.matches("[a-zA-ZēūīōāšģķļžčņĒŪĪŌĀŠĢĶĻŽČŅ]+")) {
                errors.add(new CoreError("Personal data : surname", "Surname can contain only letters"));
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

    private List<CoreError> personalCodeValidationErrors(String personalCode){
        List <CoreError> errors = new ArrayList<>();
        if (!Pattern.matches("[0-9]{2}[0,1][0-9][0-9][0-9]-?[0-9]{5}", personalCode)) {
            errors.add(new CoreError("Personal data : personal code", "Valid personal format is DDMMYYNNNNN or DDMMYY-NNNNN, where N is digit"));
        }
        return errors;
    }

}
