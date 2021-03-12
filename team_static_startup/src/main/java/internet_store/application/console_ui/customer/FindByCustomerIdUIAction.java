package internet_store.application.console_ui.customer;

import internet_store.application.console_ui.UIAction;
import internet_store.application.core.domain.Customer;
import internet_store.application.core.requests.customer.FindByCustomerIdRequest;
import internet_store.application.core.responses.customer.FindByCustomerIdResponse;
import internet_store.application.core.services.customer.FindByCustomerIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

//@Component
public class FindByCustomerIdUIAction implements UIAction {

    @Autowired FindByCustomerIdService findService;

    public void execute() {
        Scanner myInput = new Scanner(System.in);
        System.out.print("Enter Customer ID for searching : ");
        String id = myInput.nextLine();

        FindByCustomerIdRequest request = new FindByCustomerIdRequest(id);
        FindByCustomerIdResponse response = findService.execute(request);

        Optional<Customer> foundProduct = response.getCustomerFindById();

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            if (response.getCustomerFindById().isEmpty()) {
                System.out.println("\nNo customer with ID = " + id + " in the DataBase");
            } else {
                Customer customer = foundProduct.get();
                System.out.println("Found product in the database :");
                System.out.print(customer.toString() + "\n");
            }
        }
    }

}
