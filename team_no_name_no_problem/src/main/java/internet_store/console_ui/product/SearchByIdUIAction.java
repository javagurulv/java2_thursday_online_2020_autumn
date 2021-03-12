package internet_store.console_ui.product;

import internet_store.console_ui.UIAction;
import internet_store.core.requests.product.SearchProductByIdRequest;
import internet_store.core.response.product.SearchProductByIdResponse;
import internet_store.core.services.product.SearchProductByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

//@Component
public class SearchByIdUIAction implements UIAction {

    @Autowired private SearchProductByIdService findProductByIdService;

    @Override
    public void execute() {

        Scanner in = new Scanner(System.in);

        System.out.println("Please enter id");
        Long id = in.nextLong();

        SearchProductByIdRequest findByIdRequest = new SearchProductByIdRequest(id);
        SearchProductByIdResponse findByIdResponse = findProductByIdService.execute(findByIdRequest);

        if (findByIdResponse.hasErrors()){
            findByIdResponse.getErrors().forEach(System.out::println);
        }else{
            System.out.println("Product with id " + id);
            System.out.println(findByIdResponse.getProducts());
        }
    }
}