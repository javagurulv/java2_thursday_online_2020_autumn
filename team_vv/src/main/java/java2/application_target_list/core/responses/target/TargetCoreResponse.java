package java2.application_target_list.core.responses.target;


import java2.application_target_list.core.responses.CoreError;
import java.util.List;

abstract class TargetCoreResponse {

    private List<CoreError> errorList;

    public TargetCoreResponse(List<CoreError> errorList) {
        this.errorList = errorList;
    }

    public TargetCoreResponse() {
    }

    public List<CoreError> getErrorList() {
        return errorList;
    }

    public boolean hasErrors() {
        return errorList != null && !errorList.isEmpty();
    }


}
