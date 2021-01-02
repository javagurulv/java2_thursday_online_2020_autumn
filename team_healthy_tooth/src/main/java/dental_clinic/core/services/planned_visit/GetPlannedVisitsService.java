package dental_clinic.core.services.planned_visit;

import dental_clinic.core.domain.PlannedVisit;
import dental_clinic.core.requests.plannedVisit.GetPlannedVisitsRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.planned_visit.GetPlannedVisitsResponse;
import dental_clinic.core.validators.planned_visit.GetPlannedVisitsRequestValidator;
import dental_clinic.database.in_memory.planned_visit.PlannedVisitsInMemoryDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetPlannedVisitsService {

    @Autowired
    private GetPlannedVisitsRequestValidator getPlannedVisitsRequestValidator;
    @Autowired
    private PlannedVisitsInMemoryDatabase plannedVisitsInMemoryDatabase;

    public GetPlannedVisitsResponse execute (GetPlannedVisitsRequest getPlannedVisitsRequest) {

        List<CoreError> errors = getPlannedVisitsRequestValidator.validate(getPlannedVisitsRequest);

        if (!errors.isEmpty()) {
            return new GetPlannedVisitsResponse(errors, new ArrayList<>());
        }

        List<PlannedVisit> plannedVisits = plannedVisitsInMemoryDatabase.getPlannedVisits();

        if (plannedVisits.isEmpty()) {
            errors.add(new CoreError("database", "Database is empty"));
            return new GetPlannedVisitsResponse(errors, new ArrayList<>());
        }

        return new GetPlannedVisitsResponse(plannedVisits);
    }
}
