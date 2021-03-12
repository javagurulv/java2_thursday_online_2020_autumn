package adventure_time.core.requests.customers;

import adventure_time.core.requests.Ordering;
import adventure_time.core.requests.Paging;

public class ShowCustomersRequest {

    private String nameStartsWith;
    private String phoneStartsWith;
    private Ordering ordering;
    private Paging paging;

    public ShowCustomersRequest(String nameStartsWith, String phoneStartsWith, Ordering ordering, Paging paging) {
        this.nameStartsWith = nameStartsWith;
        this.phoneStartsWith = phoneStartsWith;
        this.ordering = ordering;
        this.paging = paging;
    }

    public String getNameStartsWith() {
        return nameStartsWith;
    }

    public String getPhoneStartsWith() {
        return phoneStartsWith;
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public Paging getPaging() {
        return paging;
    }
}
