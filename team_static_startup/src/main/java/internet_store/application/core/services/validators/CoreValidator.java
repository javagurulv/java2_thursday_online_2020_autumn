package internet_store.application.core.services.validators;

import internet_store.application.core.requests.CoreRequest;
import internet_store.application.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public interface CoreValidator<T extends CoreRequest> {


    List<CoreError> validate(T request);

    default List<CoreError> validateRegex (T request){
        //doing some validation here
        return new ArrayList<>();
    }

}
