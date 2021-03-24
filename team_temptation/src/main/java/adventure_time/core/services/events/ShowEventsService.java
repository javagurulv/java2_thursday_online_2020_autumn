package adventure_time.core.services.events;

import adventure_time.core.database.events.EventRepository;
import adventure_time.core.domain.Events;
import adventure_time.core.requests.events.ShowEventsRequest;
import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.events.ShowEventsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShowEventsService {
@Autowired
private EventRepository database;
@Autowired
private ShowEventsRequestValidator validator;

    public ShowEventsResponse getEventsList (ShowEventsRequest request) {

            List<CoreError> errors = validator.validate(request);
            if (!errors.isEmpty()) {
                return new ShowEventsResponse(null, errors);
            }

            String query = queryMaker(request);

            List<Events> result = database.findEvents(query, request.getPaging());

            return new ShowEventsResponse(result,null);
    }

    private String queryMaker (ShowEventsRequest request) {
        return "FROM Events AS e" +
                isWhere(request) +
                isNameStartWith(request.getNameStartsWith()) +
                isAnd(request.getNameStartsWith(), request.getKindStartsWith()) +
                isKindStartsWith(request.getKindStartsWith()) +
                isAnd(request.getNameStartsWith() + request.getKindStartsWith(), request.getRouteContains()) +
                isRouteContains(request.getRouteContains()) +
                request.getOrdering().getOrderBy() +
                request.getOrdering().getOrderDirection();
    }

    private String isWhere (ShowEventsRequest request) {
        return !(request.getNameStartsWith().equals("") && request.getKindStartsWith().equals("") && request.getRouteContains().equals("")) ?
                " WHERE" : "";
    }

    private String isAnd (String before, String that) {
        return !(before.equals("") || that.equals("")) ?
                " AND" : "";
    }

    private String isKindStartsWith(String get) {
        return get.equals("") ? "" : " e.eventKind LIKE '" + get + "%'";
    }

    private String isNameStartWith (String get) {
        return get.equals("") ? "" : " e.eventName LIKE '" + get + "%'";
    }

    private String isRouteContains (String get) {
        return get.equals("") ? "" : " e.route LIKE '%" + get + "%'";
    }
}

