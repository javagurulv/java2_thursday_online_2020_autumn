package internet_store.core.services.product;

import internet_store.core.requests.product.DeleteProductByIdRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.product.DeleteProductByIdResponse;
import internet_store.core.services.product.validators.DeleteProductByIdRequestValidator;

import internet_store.database.jpa.ProductRepository;
import internet_store.database.product.ProductDatabase;
import internet_store.core.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class DeleteProductByIdService {

    @Autowired private ProductRepository productDatabase;
    @Autowired private DeleteProductByIdRequestValidator deleteProductRequestValidator;

    public DeleteProductByIdResponse execute(DeleteProductByIdRequest deleteProductRequest) {

        List<CoreError> errors = deleteProductRequestValidator.validate(deleteProductRequest);

        if (!errors.isEmpty()) {
            return new DeleteProductByIdResponse(errors);
        }

        if (productDatabase.existsById(deleteProductRequest.getId())){
            for (int i = 0; i < productDatabase.findAll().size(); i++) {
                if (getCurrentProduct(i).getId() == deleteProductRequest.getId()) {
                    productDatabase.deleteById(deleteProductRequest.getId());
                    return new DeleteProductByIdResponse(deleteProductRequest.getId());
                }
            }
        }

        errors.add(new CoreError("database", "database doesn't contain product with id "
                + deleteProductRequest.getId()));
        return new DeleteProductByIdResponse(errors);
    }

    private Product getCurrentProduct (int index){
        return productDatabase.findAll().get(index);
    }
}
