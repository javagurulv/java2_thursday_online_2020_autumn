package lv.estore.app.core.services;

import lv.estore.app.core.errors.Errors;
import lv.estore.app.core.request.CoreRequest;
import lv.estore.app.core.responses.CoreResponse;
import lv.estore.app.core.responses.RemoveResponse;
import lv.estore.app.core.validators.iValidator;
import lv.estore.app.dependency_injection.DIComponent;
import lv.estore.app.repository.iDatabase;

@DIComponent
public class RemoveByNameService implements iService {

    private final iDatabase database;
    private final iValidator validator;

    public RemoveByNameService(final iDatabase database, final iValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    /**
     * Method to remove product by name.
     * @param request CoreRequest
     * @return CoreResponse
     */
    @Override
    public CoreResponse execute(final CoreRequest request) {
        CoreResponse response;
        final Errors errors = validator.validate(request);
        if (errors.getAllErrors().isEmpty()) {
            response = new RemoveResponse(database.removeByName(request.getName()));
        } else {
            response = new RemoveResponse(errors);
        }
        return response;
    }
}
