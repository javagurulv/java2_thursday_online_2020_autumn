package adventure_time.core.domain;

import java.util.Objects;

public class Guides {

    private Long guideId;
    private final String guideName;
    private final String guideEmail;
    private final String guidePhone;
    private boolean activity;

    public Guides(String guideName, String guideEmail, String guidePhone) {

        this.guideName = guideName;
        this.guideEmail = guideEmail;
        this.guidePhone = guidePhone;
        this.activity = true;
    }

    public Long getGuideId() {
        return guideId;
    }

    public String getGuideName() {
        return guideName;
    }

    public String getGuideEmail() {
        return guideEmail;
    }

    public String getGuidePhone() {
        return guidePhone;
    }

    public void setGuideId(Long guideId) {
        this.guideId = guideId;
    }

    public boolean isActivity() {
        return activity;
    }

    public void activityOn(boolean activity) {
        this.activity = true;
    }

    public void activityOff(boolean activity) {
        this.activity = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guides guides = (Guides) o;
        return guideId == guides.guideId && activity == guides.activity && Objects.equals(guideName, guides.guideName) && Objects.equals(guideEmail, guides.guideEmail) && Objects.equals(guidePhone, guides.guidePhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guideId, guideName, guideEmail, guidePhone, activity);
    }

    @Override
    public String toString() {
        return "Guides{" +
                "guideId=" + guideId +
                ", guideName='" + guideName + '\'' +
                ", guideEmail='" + guideEmail + '\'' +
                ", guidePhone='" + guidePhone + '\'' +
                ", activity=" + activity +
                '}';
    }
}