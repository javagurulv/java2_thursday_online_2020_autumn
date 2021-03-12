package dental_clinic.core.responses.manipulation.rest;

import dental_clinic.core.domain.Manipulation;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.CoreResponse;

import java.util.List;

public class GetManipulationResponse extends CoreResponse {

    private Manipulation manipulation;

    public GetManipulationResponse(List<CoreError> errorList) {
        super(errorList);
    }

    public GetManipulationResponse(Manipulation manipulation) {
        this.manipulation = manipulation;
    }

    public Manipulation getManipulation() {
        return manipulation;
    }
}
