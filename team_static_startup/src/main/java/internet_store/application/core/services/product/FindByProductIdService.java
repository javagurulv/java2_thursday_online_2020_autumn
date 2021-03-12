package internet_store.application.core.services.product;

import internet_store.application.core.database.jpa.JpaProductRepository;
import internet_store.application.core.requests.product.FindByIdRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.product.FindByProductIdResponse;
import internet_store.application.core.services.product.validators.FindByProductIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component
public class FindByProductIdService {

    @Autowired private JpaProductRepository productRepository;
    @Autowired private FindByProductIdValidator validator;

    public FindByProductIdResponse execute(FindByIdRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new FindByProductIdResponse(errors);
        }

        Long id = Long.parseLong(request.getProductId());
        FindByProductIdResponse response = new FindByProductIdResponse(productRepository.findById(id));

        if (response.getProductFoundById().isEmpty()) {
            errors.add(new CoreError("id", "Not found!"));
            return new FindByProductIdResponse(errors);
        }
        return response;
    }

}

