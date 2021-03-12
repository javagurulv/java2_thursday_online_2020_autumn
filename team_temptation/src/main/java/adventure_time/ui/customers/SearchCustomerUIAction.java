package adventure_time.ui.customers;

import adventure_time.core.requests.customers.SearchCustomerRequest;
import adventure_time.core.responses.customer.SearchCustomerResponse;
import adventure_time.core.services.customers.SearchCustomerService;
import adventure_time.ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class SearchCustomerUIAction implements UIAction {

    @Autowired
    private SearchCustomerService service;

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        SearchCustomerRequest request;

        System.out.println("Would you like to find the customer by: ");
        System.out.println("   1. ID ");
        System.out.println("   2. Email (login)");

        if (scanner.nextLine().equals("1")) {
            System.out.println("Enter customer ID: ");
            request = new SearchCustomerRequest(scanner.nextLong(), null);
        } else {
            System.out.println("Enter the customer email: ");
            request = new SearchCustomerRequest(null, scanner.nextLine());
        }

        SearchCustomerResponse response = service.searchCustomer(request);

        if (response.hasError()) {
            System.out.println("Your request could not be fulfilled for the reasons: ");
            response.getErrors().forEach(System.out::println);
        } else {
            if (response.getCustomer() != null) {
                System.out.println("Here is the customer: ");
                System.out.println(response.getCustomer());
            } else {
                System.out.println("Your request could not be fulfilled for the reasons: ");
                response.getErrors().forEach(System.out::println);
            }
        }
    }
}
