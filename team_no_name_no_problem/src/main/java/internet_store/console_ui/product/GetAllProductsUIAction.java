package internet_store.console_ui.product;

import internet_store.console_ui.UIAction;
import internet_store.core.requests.product.GetProductsRequest;
import internet_store.core.response.product.GetProductsResponse;
import internet_store.core.services.product.GetAllProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class GetAllProductsUIAction implements UIAction {

    @Autowired private GetAllProductsService getAllProductsService;

    @Override
    public void execute(){

        GetProductsRequest getProductsRequest = new GetProductsRequest();
        GetProductsResponse getProductsResponse = getAllProductsService.execute(getProductsRequest);

        if (getProductsResponse.hasErrors()){
            getProductsResponse.getErrors().forEach(System.out::println);
        }else
            getProductsResponse.getProducts().forEach(System.out::println);
    }

}

