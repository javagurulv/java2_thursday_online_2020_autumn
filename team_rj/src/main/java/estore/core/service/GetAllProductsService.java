package estore.core.service;

import estore.database.ProductDB;
import estore.domain.Product;
import estore.core.requests.GetAllProductsRequest;
import estore.core.responses.GetAllProductsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllProductsService {

    @Autowired
    private ProductDB productDB;

//    public ShowAllProductsService(ProductDB productDB) {
//        this.productDB = productDB;
//    }

    public GetAllProductsResponse execute(GetAllProductsRequest request) {
        List<Product> foundProducts = productDB.getDatabase();
        return new GetAllProductsResponse(foundProducts);
    }

}