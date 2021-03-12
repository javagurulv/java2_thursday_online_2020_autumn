package internet_store.application.core.services.product;

import internet_store.application.core.database.jpa.JpaProductRepository;
import internet_store.application.core.domain.Product;
import internet_store.application.core.requests.product.DeleteByProductRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.product.DeleteByProductResponse;
import internet_store.application.core.services.product.validators.DeleteByProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeleteProductByProductService {

    @Autowired private JpaProductRepository productRepository;
    @Autowired private DeleteByProductValidator deleteByProductValidator;

    public DeleteByProductResponse execute(DeleteByProductRequest request) {
        List<CoreError> errors = deleteByProductValidator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteByProductResponse(errors);
        }

        Integer numberOfProductsDeleted = productRepository.deleteByProductNameAndProductDescriptionAndPrice(
                request.getProductName(), request.getProductDescription(), request.getProductPrice());

        if (numberOfProductsDeleted != 1) {
            errors.add(new CoreError("Product", "Not found!"));
            return new DeleteByProductResponse(errors);
        }

        return new DeleteByProductResponse(
                new Product(request.getProductName(), request.getProductDescription(), request.getProductPrice()));
    }

//    public boolean delete(Product product) {
//        Integer deletedProducts = productRepository.deleteByProductNameAndProductDescriptionAndPrice(
//                product.getName(), product.getDescription(), product.getPrice());
//        return deletedProducts ==  1;
//    }


}
