package dental_clinic.core.responses.visit;

import dental_clinic.core.domain.Visit;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.CoreResponse;

import java.util.List;

public class AddVisitResponse extends CoreResponse {

    private Visit visit;

    public AddVisitResponse(List<CoreError> errors){
        super(errors);
    }

    public AddVisitResponse(Visit visit){
        this.visit = visit;
    }

    public Visit getVisit() {
        return visit;
    }

}
