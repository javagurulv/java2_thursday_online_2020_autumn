package estore.core.service;

import estore.core.validation.CoreError;
import estore.core.validation.AddNewProductValidator;
import estore.database.ProductDB;
import estore.domain.Product;
import estore.core.requests.AddNewProductRequest;
import estore.core.responses.AddNewProductResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddNewProductService {

    private ProductDB productDB;
    private AddNewProductValidator validator;

    public AddNewProductService(ProductDB productDB, AddNewProductValidator validator) {
        this.productDB = productDB;
        this.validator = validator;
    }

    public AddNewProductResponse execute(AddNewProductRequest request) {
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new AddNewProductResponse(errors);
        }

        Product product = new Product(request.getProductName(), request.getProductDescription(), request.getProductCategory());

        productDB.addNewProduct(product);
        AddNewProductResponse response = new AddNewProductResponse(product);
        response.setSuccessfullyAdded(true);

        return response;
    }

}
