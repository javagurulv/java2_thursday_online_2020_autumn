package lv.estore.app.core.services;

import lv.estore.app.core.errors.Errors;
import lv.estore.app.core.request.CoreRequest;
import lv.estore.app.core.responses.CoreResponse;
import lv.estore.app.core.responses.UpdateResponse;
import lv.estore.app.core.validators.iValidator;
import lv.estore.app.dependency_injection.DIComponent;
import lv.estore.app.repository.iDatabase;

@DIComponent
public class UpdateByIdService implements iService {

    private iDatabase database;
    private iValidator validator;

    public UpdateByIdService(final iDatabase database, final iValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    /**
     * Method to update product info by ID.
     * @param request CoreRequest
     * @return CoreResponse
     */
    @Override
    public CoreResponse execute(final CoreRequest request) {
        CoreResponse response;
        final Errors errors = validator.validate(request);
        if (errors.getAllErrors().isEmpty()) {
            response = new UpdateResponse(database.updateById(request.getId(),
                                                    request.getName(),
                                                    request.getDescription(),
                                                    request.getPrice()));
        } else {
            response = new UpdateResponse(errors);
        }
        return response;
    }
}
