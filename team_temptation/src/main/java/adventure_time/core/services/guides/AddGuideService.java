package adventure_time.core.services.guides;

import adventure_time.core.requests.guides.AddGuideRequest;
import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.guides.AddGuideResponse;
import adventure_time.database.guides.DatabaseGuides;
import adventure_time.core.domain.Guides;

import java.util.List;

public class AddGuideService {

    private final DatabaseGuides databaseGuides;
    private final adventure_time.core.services.guides.AddGuideRequestValidator validator;

    public AddGuideService(DatabaseGuides databaseGuides, adventure_time.core.services.guides.AddGuideRequestValidator validator) {
        this.databaseGuides = databaseGuides;
        this.validator = validator;
    }

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