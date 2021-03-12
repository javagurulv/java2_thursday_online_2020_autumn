package internet_store.application.console_ui.customer;

import internet_store.application.console_ui.UIAction;
import internet_store.application.core.domain.Customer;
import internet_store.application.core.requests.customer.ChangeCustomerFirstNameRequest;
import internet_store.application.core.requests.customer.FindByCustomerIdRequest;
import internet_store.application.core.responses.customer.ChangeCustomerFirstNameResponse;
import internet_store.application.core.responses.customer.FindByCustomerIdResponse;
import internet_store.application.core.services.customer.ChangeCustomerFirstNameService;
import internet_store.application.core.services.customer.FindByCustomerIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

//@Component
public class ChangeCustomerFirstNameUIAction implements UIAction {

    @Autowired ChangeCustomerFirstNameService changeNameService;
    @Autowired FindByCustomerIdService findByIdService;

    @Override
    public void execute() {
        Scanner myInput = new Scanner(System.in);
        System.out.print("Enter customer ID to change name : ");
        String id = myInput.nextLine();

        FindByCustomerIdRequest idRequest = new FindByCustomerIdRequest(id);
        FindByCustomerIdResponse idResponse = findByIdService.execute(idRequest);

        Optional<Customer> foundCustomer = idResponse.getCustomerFindById();

        if (idResponse.hasErrors()) {
            idResponse.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            if (idResponse.getCustomerFindById().isEmpty()) {
                System.out.println("\nNo customer with ID = " + id + " in the DataBase");
            } else {
                Customer customer = foundCustomer.get();
                System.out.println("Found customer in the database :");
                System.out.print(customer.toString() + "\n");
            }
        }

        System.out.print("Enter new name for customer: ");
        String newName = myInput.nextLine();
        ChangeCustomerFirstNameRequest changeCustomerFirstNameRequest =
                new ChangeCustomerFirstNameRequest(foundCustomer.get().getCustomerId(), newName);
        ChangeCustomerFirstNameResponse changeCustomerFirstNameResponse =
                changeNameService.execute(changeCustomerFirstNameRequest);

        if (changeCustomerFirstNameResponse.isNameChanged()) {
            System.out.println("Customer name changed successfully!");
        } else {
            System.out.println("Could not change name.");
        }
    }

}
