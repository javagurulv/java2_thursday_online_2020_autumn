package internet_store.application.core.services;

import internet_store.application.core.database.ProductDatabase;
import internet_store.application.core.requests.DeleteByProductIdRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.DeleteByProductIdResponse;
import internet_store.application.core.services.validators.DeleteByProductIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeleteByProductIdService {

    @Autowired
    private ProductDatabase database;
    @Autowired
    private DeleteByProductIdValidator validator;

    public DeleteByProductIdResponse execute(DeleteByProductIdRequest productIdRequest) {
        List<CoreError> errors = validator.validate(productIdRequest);
        Long id = productIdRequest.getProductId();

        if (!errors.isEmpty()) {
            return new DeleteByProductIdResponse(errors);
        } else return new DeleteByProductIdResponse(database.deleteByProductId(id));
    }

}
