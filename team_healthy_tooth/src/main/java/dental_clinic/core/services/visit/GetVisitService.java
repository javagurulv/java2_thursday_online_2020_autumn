package dental_clinic.core.services.visit;

import dental_clinic.core.database.visit.VisitRepository;
import dental_clinic.core.requests.visit.GetVisitRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.visit.GetVisitResponse;
import dental_clinic.core.validators.visit.GetVisitValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class GetVisitService {

    @Autowired private VisitRepository visitRepository;
    @Autowired private GetVisitValidator getVisitValidator;

    public GetVisitResponse execute(GetVisitRequest getVisitRequest) {
        List<CoreError> errors = getVisitValidator.validate(getVisitRequest);
        if (!errors.isEmpty()) {
            return new GetVisitResponse(errors);
        }
        return visitRepository.getVisitById(getVisitRequest.getId())
                .map(GetVisitResponse::new)
                .orElseGet(() -> {
                    errors.add(new CoreError("id", "Database doesn't contain visit with id "
                    + getVisitRequest.getId()));
                    return new GetVisitResponse(errors);
                });
    }
}
