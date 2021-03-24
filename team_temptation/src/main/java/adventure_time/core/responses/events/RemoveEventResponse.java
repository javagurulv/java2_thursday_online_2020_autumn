package adventure_time.core.responses.events;

import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.CoreResponse;

import java.util.List;

public class RemoveEventResponse extends CoreResponse {

    public RemoveEventResponse() {}

    public RemoveEventResponse(List<CoreError> errors) {
        super(errors);
    }

}
