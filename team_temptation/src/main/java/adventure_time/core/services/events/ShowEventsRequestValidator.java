package adventure_time.core.services.events;

import adventure_time.core.requests.events.ShowEventsRequest;
import adventure_time.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShowEventsRequestValidator {

    private final static int MIN_PAGE_SIZE = 8;
    private final static int MAX_PAGE_SIZE = 20;

    public List<CoreError> validate (ShowEventsRequest request) {

        List<CoreError> errors = new ArrayList<>();

        if (request.getPaging().getPageNumber() <= 0) {
            CoreError error = new CoreError("pageNumber", "Must be above zero");
            errors.add(error);
        }

        if (request.getPaging().getPageSize() < MIN_PAGE_SIZE || request.getPaging().getPageSize() > MAX_PAGE_SIZE) {
            CoreError error = new CoreError("pageSize", "Must be between 8 and 20");
            errors.add(error);
        }

        return errors;
    }
}
