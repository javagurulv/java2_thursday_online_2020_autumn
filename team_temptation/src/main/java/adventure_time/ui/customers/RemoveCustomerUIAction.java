package adventure_time.ui.customers;

import adventure_time.core.requests.customers.RemoveCustomerRequest;
import adventure_time.core.responses.customer.RemoveCustomerResponse;
import adventure_time.core.services.customers.RemoveCustomerService;
import adventure_time.ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class RemoveCustomerUIAction implements UIAction {

    @Autowired
    private RemoveCustomerService service;

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a customer ID:");
        RemoveCustomerRequest request = new RemoveCustomerRequest(scanner.nextLong());

        RemoveCustomerResponse response = service.remove(request);
        if (response.hasError()) {
            response.getErrors().forEach(System.out::println);
        } else {
            System.out.println("The customer was deleted");
        }

        System.out.println();
    }
}
