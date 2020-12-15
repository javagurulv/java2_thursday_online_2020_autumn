package lv.estore.app.core.services;

import lv.estore.app.core.errors.Errors;
import lv.estore.app.core.request.CoreRequest;
import lv.estore.app.core.responses.AddResponse;
import lv.estore.app.core.responses.CoreResponse;
import lv.estore.app.core.validators.iValidator;
import lv.estore.app.dependency_injection.DIComponent;
import lv.estore.app.domain.Product;
import lv.estore.app.repository.iDatabase;

@DIComponent
public class AddService implements iService {

    private final iValidator validator;
    private final iDatabase database;

    public AddService(final iDatabase database, final iValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    /** Method to add product.
    * @param request CoreRequest
    * @return CoreResponse
    */
    @Override
    public CoreResponse execute(final CoreRequest request) {
        CoreResponse response;
        final Errors errors = validator.validate(request);
        if (errors.getAllErrors().isEmpty()) {
            Product product = new Product(request.getName(),
                                            request.getDescription(),
                                            request.getPrice());
            response = new AddResponse(database.addProduct(product));
        } else {
            response = new AddResponse(errors);
        }
        return response;
    }
}
