package java2.application_target_list.core.requests.target;

import java.math.BigInteger;

public class ChangeTargetDeadlineRequest {

    private Long targetIdToChange;
    private Long newTargetDeadline;

    public ChangeTargetDeadlineRequest(Long targetIdToChange, Long newTargetDeadline) {
        this.targetIdToChange = targetIdToChange;
        this.newTargetDeadline = newTargetDeadline;
    }

    public Long getTargetIdToChange() {
        return targetIdToChange;
    }

    public Long getNewTargetDeadline() {
        return newTargetDeadline;
    }
}
