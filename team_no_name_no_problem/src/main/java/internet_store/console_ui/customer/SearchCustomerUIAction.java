package internet_store.console_ui.customer;

import internet_store.console_ui.UIAction;
import internet_store.core.requests.Ordering;
import internet_store.core.requests.Paging;
import internet_store.core.requests.customer.SearchCustomerRequest;
import internet_store.core.response.customer.SearchCustomerResponse;
import internet_store.core.services.customer.SearchCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

//@Component
public class SearchCustomerUIAction implements UIAction {

    @Autowired private SearchCustomerService searchCustomerService;

    @Override
    public void execute(){

        Scanner in = new Scanner(System.in);

        System.out.println("Please enter name to search by name");
        String name = in.nextLine();

        System.out.println("Please enter surname to search by surname");
        String surname = in.nextLine();

        System.out.println("Please enter order by: name / surname");
        String orderBy = in.nextLine();

        System.out.println("Please enter order direction");
        String orderDirection = in.nextLine();

        System.out.println("Please enter page number");
        Integer pageNumber = in.nextInt();

        System.out.println("Please enter page size");
        Integer pageSize = in.nextInt();

        Ordering ordering = new Ordering(orderBy, orderDirection);
        Paging paging = new Paging(pageNumber, pageSize);
        SearchCustomerRequest searchCustomerRequest = new SearchCustomerRequest(name, surname, ordering, paging);
        SearchCustomerResponse searchCustomerResponse = searchCustomerService.execute(searchCustomerRequest);

        if (searchCustomerResponse.hasErrors()){
            searchCustomerResponse.getErrors().forEach(System.out::println);
        } else {
            searchCustomerResponse.getCustomers().forEach(System.out::println);
        }
    }
}
