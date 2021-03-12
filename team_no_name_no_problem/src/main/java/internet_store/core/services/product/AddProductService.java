package internet_store.core.services.product;

import internet_store.core.requests.product.AddProductRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.product.AddProductResponse;
import internet_store.core.services.product.validators.AddProductRequestValidator;

import internet_store.database.jpa.ProductRepository;
import internet_store.database.product.ProductDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class  AddProductService {

    @Autowired private ProductRepository productDatabase;
    @Autowired private AddProductRequestValidator addProductRequestValidator;

    public AddProductResponse execute(AddProductRequest addProductRequest){

        List<CoreError>errors = addProductRequestValidator.validate(addProductRequest);
        if (!errors.isEmpty()){
            return new AddProductResponse(errors);
        }

        productDatabase.save(addProductRequest.getProduct());
        return new AddProductResponse(addProductRequest.getProduct());
    }

}
