package adventure_time.core.services.guides;

import adventure_time.core.requests.guides.RemoveGuideRequest;
import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.guides.RemoveGuideResponse;
import adventure_time.database.guides.DatabaseGuides;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RemoveGuideService {

    @Autowired
    private DatabaseGuides databaseGuides;
    @Autowired
    private RemoveGuideRequestValidator validator;

//    public RemoveGuideService(DatabaseGuides databaseGuides, RemoveGuideRequestValidator validator) {
//        this.databaseGuides = databaseGuides;
//        this.validator = validator;
//    }

    public RemoveGuideResponse removeGuide(RemoveGuideRequest request) {

        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new RemoveGuideResponse(errors);
        }

        boolean isSuccessRemoval;
        if (request.getDeletionWay().equals("byName")) {
            isSuccessRemoval = databaseGuides.removeByName(request.getGuideName());
        } else {
            isSuccessRemoval = databaseGuides.removeById(request.getGuideId());
        }

        return new RemoveGuideResponse(isSuccessRemoval);
    }
}