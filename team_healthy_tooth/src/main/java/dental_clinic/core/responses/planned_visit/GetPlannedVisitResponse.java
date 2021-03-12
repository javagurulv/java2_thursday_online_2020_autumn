package dental_clinic.core.responses.planned_visit;

import dental_clinic.core.domain.PlannedVisit;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.CoreResponse;

import java.util.List;

public class GetPlannedVisitResponse extends CoreResponse {

    private PlannedVisit plannedVisit;

    public GetPlannedVisitResponse(List<CoreError> errors) {
        super(errors);
    }

    public GetPlannedVisitResponse(PlannedVisit plannedVisit) {
        this.plannedVisit = plannedVisit;
    }

    public PlannedVisit getPlannedVisit() {
        return plannedVisit;
    }
}
