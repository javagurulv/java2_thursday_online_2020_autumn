package adventure_time.core.requests.events;

import adventure_time.core.requests.Ordering;
import adventure_time.core.requests.Paging;

public class ShowEventsRequest {

    private String nameStartsWith;
    private String kindStartsWith;
    private String routeContains;
    private Ordering ordering;
    private Paging paging;

    public ShowEventsRequest(String nameStartsWith, String kindStartsWith, String routeContains, Ordering ordering, Paging paging) {
        this.nameStartsWith = nameStartsWith;
        this.kindStartsWith = kindStartsWith;
        this.routeContains = routeContains;
        this.ordering = ordering;
        this.paging = paging;
    }

    public String getNameStartsWith() {
        return nameStartsWith;
    }

    public String getKindStartsWith() {
        return kindStartsWith;
    }

    public String getRouteContains() {
        return routeContains;
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public Paging getPaging() {
        return paging;
    }
}
