package java2.application_target_list.core.validators;

import java2.application_target_list.core.responses.CoreError;

public class ErrorCreator {

    public CoreError createCoreError(String field, String message){
        return new CoreError(field, message);
    }
}
