package java2.application_target_list.core.requests.target;

public class ChangeTargetNameRequest {

    private Long targetIdToChange;
    private String newTargetName;

    public ChangeTargetNameRequest(Long targetIdToChange, String newTargetName) {
        this.targetIdToChange = targetIdToChange;
        this.newTargetName = newTargetName;
    }

    public Long getTargetIdToChange() {
        return targetIdToChange;
    }

    public String getNewTargetName() {
        return newTargetName;
    }
}
