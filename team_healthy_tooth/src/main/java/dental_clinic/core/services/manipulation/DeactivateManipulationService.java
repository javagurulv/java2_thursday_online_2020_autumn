package dental_clinic.core.services.manipulation;

import dental_clinic.core.requests.manipulation.DeactivateManipulationRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.manipulation.DeactivateManipulationResponse;
import dental_clinic.core.validators.manipulation.DeactivateManipulationRequestValidator;
import dental_clinic.database.in_memory.manipulation.ManipulationInMemoryDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeactivateManipulationService {

    @Autowired
    private DeactivateManipulationRequestValidator deactivateManipulationRequestValidator;
    @Autowired
    private ManipulationInMemoryDatabase manipulationInMemoryDatabase;

    public DeactivateManipulationResponse execute (DeactivateManipulationRequest deactivateManipulationRequest) {

        List<CoreError> coreErrorList = deactivateManipulationRequestValidator.validate(deactivateManipulationRequest);

        if (!coreErrorList.isEmpty()) {
            return new DeactivateManipulationResponse(coreErrorList);
        }

        if (!manipulationInMemoryDatabase.containsId(deactivateManipulationRequest.getId())) {
            coreErrorList.add(new CoreError("database", "Database doesn't contain id " +
                    deactivateManipulationRequest.getId()));
            return new DeactivateManipulationResponse(coreErrorList);
        }

        manipulationInMemoryDatabase.deactivateManipulation(deactivateManipulationRequest.getId());
        return new DeactivateManipulationResponse(deactivateManipulationRequest.getId());
    }
}
