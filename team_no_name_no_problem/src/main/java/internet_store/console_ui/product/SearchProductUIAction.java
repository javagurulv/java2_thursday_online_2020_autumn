package internet_store.console_ui.product;

import internet_store.console_ui.UIAction;
import internet_store.core.requests.Ordering;
import internet_store.core.requests.Paging;
import internet_store.core.requests.product.SearchProductByOtherRequest;
import internet_store.core.response.product.SearchProductByOtherResponse;
import internet_store.core.services.product.SearchProductByOtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

//@Component
public class SearchProductUIAction implements UIAction {

    @Autowired private SearchProductByOtherService searchProductService;

    @Override
    public void execute (){

        Scanner in = new Scanner(System.in);

        System.out.println("Please select criteria by which you wish to search product/products from existing database");
        System.out.println("Criterias are: title, description and price range.");
        System.out.println("Each criteria may be used individually or in combination with others.");
        System.out.println("If a certain criteria is not needed, please press enter to skip and move to the next criteria.");
        System.out.println("Price range can be skipped only by entering zeros.");
        System.out.println();

        System.out.println("Please enter title to search by title");
        String title = in.nextLine();

        System.out.println("Please enter description to search by description");
        String description = in.nextLine();

        System.out.println("Please enter start price to search by start price");
        Integer startPrice = in.nextInt();

        System.out.println("Please enter end price to search by end price");
        Integer endPrice = in.nextInt();
        in.nextLine();

        System.out.println("Please enter order by: title / description / price");
        String orderBy = in.nextLine();

        System.out.println("Please enter order direction: ASC / DSC");
        String orderDirection = in.nextLine();

        System.out.println("Please enter page number");
        Integer pageNumber = in.nextInt();

        System.out.println("Please enter page size");
        Integer pageSize = in.nextInt();

        Ordering ordering = new Ordering(orderBy, orderDirection);
        Paging paging = new Paging(pageNumber, pageSize);
        SearchProductByOtherRequest searchProductRequest = new SearchProductByOtherRequest(title, description, startPrice,
                                                                             endPrice, ordering, paging);
        SearchProductByOtherResponse searchProductResponse = searchProductService.execute(searchProductRequest);

        if (searchProductResponse.hasErrors()){
            searchProductResponse.getErrors().forEach(System.out::println);
        }else{
            searchProductResponse.getProducts().forEach(System.out::println);
        }
    }
}
