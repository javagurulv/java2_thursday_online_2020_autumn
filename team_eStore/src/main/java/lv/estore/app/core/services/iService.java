package lv.estore.app.core.services;

import lv.estore.app.core.request.CoreRequest;
import lv.estore.app.core.responses.CoreResponse;

public interface iService {
    CoreResponse execute(final CoreRequest request);
}
