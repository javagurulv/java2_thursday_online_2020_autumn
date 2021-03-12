package dental_clinic.core.validators.manipulation;

import dental_clinic.core.requests.manipulation.AddManipulationRequest;
import dental_clinic.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddManipulationRequestValidator {

    public List<CoreError> validate (AddManipulationRequest addManipulationRequest) {
        List <CoreError> errorList = new ArrayList<>();

        if (addManipulationRequest.getManipulationType() == null ||
            addManipulationRequest.getManipulationType().isEmpty()) {
            errorList.add(new CoreError("manipulation type", "Not valid manipulation type"));
        }

        if (addManipulationRequest.getPrice() == null ||
        addManipulationRequest.getPrice() < 0) {
            errorList.add(new CoreError("price", "Not valid price"));
        }

        return errorList;
    }
}
