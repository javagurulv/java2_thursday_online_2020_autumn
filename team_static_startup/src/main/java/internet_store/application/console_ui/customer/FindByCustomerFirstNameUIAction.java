package internet_store.application.console_ui.customer;

import internet_store.application.console_ui.UIAction;
import internet_store.application.core.requests.customer.FindByCustomerFirstNameRequest;
import internet_store.application.core.responses.customer.FindByCustomerFirstNameResponse;
import internet_store.application.core.services.customer.FindByCustomerFirstNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

//@Component
public class FindByCustomerFirstNameUIAction implements UIAction {

    @Autowired
    FindByCustomerFirstNameService findByCustomerFirstNameService;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter customer first name to find: ");
        String customerFirstName = scanner.nextLine();
        FindByCustomerFirstNameRequest request = new FindByCustomerFirstNameRequest(customerFirstName);
        FindByCustomerFirstNameResponse response = findByCustomerFirstNameService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Validation error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            if (response.getCustomersFoundByFirstName().isEmpty()) {
                System.out.println("There is no any customer with such name");
            } else {
                System.out.println("Customer(s) found: ");
                response.getCustomersFoundByFirstName().forEach(System.out::println);
            }
        }
    }

}
