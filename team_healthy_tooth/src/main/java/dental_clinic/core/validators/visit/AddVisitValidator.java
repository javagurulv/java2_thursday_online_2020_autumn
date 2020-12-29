package dental_clinic.core.validators.visit;

import dental_clinic.core.requests.visit.AddVisitRequest;
import dental_clinic.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddVisitValidator {

    public List<CoreError> validate (AddVisitRequest addVisitRequest){

        List <CoreError> coreErrors = new ArrayList<>();

        if ((addVisitRequest.getId() == null) || (addVisitRequest.getId() < 1)){
            coreErrors.add(new CoreError("id", "Not valid input of id"));
        }

        if (!isValidToothNumber(addVisitRequest.getToothNumber())){
            coreErrors.add(new CoreError("tooth number", "Not valid input for tooth number"));
        }

        if (addVisitRequest.getDoctor() == null || !addVisitRequest.getDoctor().filledCorrect()){
            coreErrors.add(new CoreError("doctor", "Not valid input for doctor"));
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
}
