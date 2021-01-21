package internet_store.application.core.services;

import internet_store.application.core.database.ProductDatabase;
import internet_store.application.core.requests.DeleteByProductNameRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.DeleteByProductNameResponse;
import internet_store.application.core.services.validators.DeleteByProductNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeleteByProductNameService {

    @Autowired
    private ProductDatabase database;
    @Autowired
    private DeleteByProductNameValidator deleteByNameValidator;

    public DeleteByProductNameResponse execute(DeleteByProductNameRequest productNameRequest) {
        List<CoreError> errors = deleteByNameValidator.validate(productNameRequest);
        if (!errors.isEmpty()) {
            return new DeleteByProductNameResponse(errors);
        }
        boolean isRemoved = database.deleteByProductName(productNameRequest.getProductName());
        return new DeleteByProductNameResponse(isRemoved);
    }

}
