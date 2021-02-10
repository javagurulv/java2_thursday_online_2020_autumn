package adventure_time.core.services.guides;

import adventure_time.core.requests.guides.AddGuideRequest;
import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.guides.AddGuideResponse;
import adventure_time.database.guides.DatabaseGuides;
import adventure_time.core.domain.Guides;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddGuideService {

    @Autowired
    private DatabaseGuides databaseGuides;
    @Autowired
    private AddGuideRequestValidator validator;

//    public AddGuideService(DatabaseGuides databaseGuides, adventure_time.core.services.guides.AddGuideRequestValidator validator) {
//        this.databaseGuides = databaseGuides;
//        this.validator = validator;
//    }

    public AddGuideResponse addGuide(AddGuideRequest request) {
        // Validation
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new AddGuideResponse(errors);
        }
        Guides guide = new Guides(request.getGuideName(), request.getGuideEmail(),
                request.getGuidePhone());

        if (databaseGuides.add(guide)) return new AddGuideResponse();

        errors.add(new CoreError("guideName", "Guide \"" + request.getGuideName() + "\" already exists"));
        return new AddGuideResponse(errors);

    }
}