package internet_store.application.console_ui.product;

import internet_store.application.console_ui.UIAction;
import internet_store.application.core.domain.Product;
import internet_store.application.core.requests.product.GetAllProductsRequest;
import internet_store.application.core.responses.product.GetAllProductsResponse;
import internet_store.application.core.services.product.GetAllProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class GetAllProductsUIAction implements UIAction {

    @Autowired
    private GetAllProductsService getAllProductsService;

    @Override
    public void execute() {
        GetAllProductsRequest request = new GetAllProductsRequest();
        GetAllProductsResponse productResponse = getAllProductsService.execute();
        List<Product> productList = productResponse.getProductList();
        if (productList.isEmpty()){
            System.out.println("Database is empty.");
        }else {
            productList.forEach(System.out::println);
        }
    }

}
