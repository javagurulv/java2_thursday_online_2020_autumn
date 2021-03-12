package java2.application_target_list.core.responses.target;

import java2.application_target_list.core.responses.CoreError;
import java.util.List;

public class UpdateTargetResponse extends TargetCoreResponse{

    private Long targetIdToChange;
    private String newTargetName;
    private String newTargetDescription;
    private Long newTargetDeadline;

    public UpdateTargetResponse(List<CoreError> errorList) {
        super(errorList);
    }

    public UpdateTargetResponse(Long targetIdToChange, String newTargetName, String newTargetDescription, Long newTargetDeadline) {
        this.targetIdToChange = targetIdToChange;
        this.newTargetName = newTargetName;
        this.newTargetDescription = newTargetDescription;
        this.newTargetDeadline = newTargetDeadline;
    }

    public Long getTargetIdToChange() {
        return targetIdToChange;
    }

    public String getNewTargetName() {
        return newTargetName;
    }

    public String getNewTargetDescription() {
        return newTargetDescription;
    }

    public Long getNewTargetDeadline() {
        return newTargetDeadline;
    }
}
