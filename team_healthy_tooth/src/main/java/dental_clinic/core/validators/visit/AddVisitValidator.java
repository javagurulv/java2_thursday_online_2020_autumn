package dental_clinic.core.validators.visit;

import dental_clinic.core.domain.Doctor;
import dental_clinic.core.requests.visit.AddVisitRequest;
import dental_clinic.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddVisitValidator {

    public List<CoreError> validate (AddVisitRequest addVisitRequest){

        List <CoreError> coreErrors = new ArrayList<>();

        coreErrors.addAll(idValidationErrors(addVisitRequest.getPatientsId()));

        coreErrors.addAll(toothNumberValidationErrors(addVisitRequest.getToothNumber()));

        coreErrors.addAll(doctorValidationErrors(addVisitRequest));


        return coreErrors;
    }

    private List<CoreError> idValidationErrors(Long id) {
        List <CoreError> coreErrors = new ArrayList<>();
        if ((id == null) || (id < 1)){
            coreErrors.add(new CoreError("id", "Not valid input of id"));
        }
        return coreErrors;
    }

    private List<CoreError> toothNumberValidationErrors(int toothNumber) {
        List <CoreError> coreErrors = new ArrayList<>();
        if (!isValidToothNumber(toothNumber)){
            coreErrors.add(new CoreError("tooth number", "Not valid input for tooth number"));
        }
        return coreErrors;
    }

    private boolean isValidToothNumber(int number){
        return (number >=11 && number <= 18) ||
                (number >=21 && number <= 28) ||
                (number >= 31 && number <=38) ||
                (number >= 41 && number <= 48) ||
                (number >= 51 && number <= 55) ||
                (number >= 61 && number <= 65) ||
                (number >= 71 && number <= 75) ||
                (number >= 81 && number <= 85);
    }

    private List<CoreError> doctorValidationErrors (AddVisitRequest addVisitRequest) {
        List<CoreError> coreErrors = new ArrayList<>();
        if (addVisitRequest.getDoctor() == null) {
            coreErrors.add(new CoreError("doctor", "Doctor can't be empty"));
            return coreErrors;
        }
        String text = addVisitRequest.getDoctor().getName();
        if (text == null || text.isEmpty()){
            coreErrors.add(new CoreError("doctor", "Doctor can't be empty"));
            return coreErrors;
        }
        if (isIdAdded(text)) {
            if (Long.parseLong(text) < 0)
            coreErrors.add(new CoreError("doctor", "Doctor can't be empty"));
            return coreErrors;
        }
        if (areTwoWordsEntered(text)) {
            Doctor doctor = new Doctor(text.split(" ")[0], text.split(" ")[1]);
            coreErrors.addAll(validateDoctorsPersonalData(doctor));
            return coreErrors;
        }
        coreErrors.add(new CoreError("doctor", "Not id, not name surname"));
        return coreErrors;
    }

    private boolean isIdAdded(String text) {
        try {
            Long.parseLong(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private List<CoreError> validateDoctorsPersonalData(Doctor doctor) {
        List<CoreError> errors = new ArrayList<>();
        if (!doctor.getName().matches("[a-zA-ZēūīōāšģķļžčņĒŪĪŌĀŠĢĶĻŽČŅ]+")){
            errors.add(new CoreError("doctor's name", "Name can contain only letters"));
        }
        if (!doctor.getSurname().matches("[a-zA-ZēūīōāšģķļžčņĒŪĪŌĀŠĢĶĻŽČŅ]+")){
            errors.add(new CoreError("doctor's surname", "Surname can contain only letters"));
        }
        return errors;
    }

    private boolean areTwoWordsEntered(String text) {
        return text.split(" ").length == 2;
    }
}
