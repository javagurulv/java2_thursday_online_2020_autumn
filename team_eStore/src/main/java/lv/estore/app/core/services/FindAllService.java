package lv.estore.app.core.services;

import lv.estore.app.core.request.CoreRequest;
import lv.estore.app.core.responses.CoreResponse;
import lv.estore.app.core.responses.FindAllResponse;
import lv.estore.app.dependency_injection.DIComponent;
import lv.estore.app.repository.iDatabase;

import java.util.ArrayList;

@DIComponent
public class FindAllService implements iService {

    private final iDatabase database;

    public FindAllService(final iDatabase database) {
        this.database = database;
    }

    /**
     * Method to find all products.
     * @param request CoreRequest
     * @return List<Product>
     */
    @Override
    public CoreResponse execute(final CoreRequest request) {
        CoreResponse response;
        if (database.getAllProducts().isEmpty()) {
            response =  new FindAllResponse(new ArrayList<>());
        } else {
            response = new FindAllResponse(database.getAllProducts());
        }
        return response;
    }
}
