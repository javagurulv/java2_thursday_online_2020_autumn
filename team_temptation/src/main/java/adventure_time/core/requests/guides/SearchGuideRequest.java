package adventure_time.core.requests.guides;

import adventure_time.core.requests.Ordering;
import adventure_time.core.requests.Paging;

public class SearchGuideRequest {

    private final String guideName;
    private final String guideEmail;

    private Ordering ordering;
    private Paging paging;

    public SearchGuideRequest(String guideName, String guideEmail) {
        this.guideName = guideName;
        this.guideEmail = guideEmail;
    }
    public SearchGuideRequest(String guideName, String guideEmail, Ordering ordering) {
        this.guideName = guideName;
        this.guideEmail = guideEmail;
        this.ordering = ordering;
    }
    public SearchGuideRequest(String guideName, String guideEmail, Ordering ordering, Paging paging) {
        this.guideName = guideName;
        this.guideEmail = guideEmail;
        this.ordering = ordering;
        this.paging = paging;
    }
    public String getGuideName() {
        return guideName;
    }

    public String getGuideEmail() {
        return guideEmail;
    }


    public boolean isGuideNameProvided() {
        return this.guideName != null && !this.guideName.isEmpty();
    }

    public boolean isGuideEmailProvided() {
        return this.guideEmail != null && !this.guideEmail.isEmpty();
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public Paging getPaging() {
        return paging;
    }
}