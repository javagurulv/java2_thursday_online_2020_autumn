package dental_clinic.core.services.planned_visit;

import dental_clinic.core.database.planned_visit.PlannedVisitsRepository;
import dental_clinic.core.requests.plannedVisit.GetPlannedVisitRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.planned_visit.GetPlannedVisitResponse;
import dental_clinic.core.validators.planned_visit.GetPlannedVisitValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class GetPlannedVisitService {

    @Autowired private PlannedVisitsRepository plannedVisitsRepository;
    @Autowired private GetPlannedVisitValidator getPlannedVisitValidator;

    public GetPlannedVisitResponse execute(GetPlannedVisitRequest getPlannedVisitRequest) {
        List<CoreError> errors= getPlannedVisitValidator.validate(getPlannedVisitRequest);
        if (!errors.isEmpty()) {
            return new GetPlannedVisitResponse(errors);
        }
        return plannedVisitsRepository.getPlannedVisitById(getPlannedVisitRequest.getId())
                .map(GetPlannedVisitResponse::new)
                .orElseGet(() -> {
                    errors.add(new CoreError("id", "Database doesn't contain planned visit with id " +
                            getPlannedVisitRequest.getId()));
                    return new GetPlannedVisitResponse(errors);
                });
    }
}
