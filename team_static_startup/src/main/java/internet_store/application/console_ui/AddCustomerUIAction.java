package internet_store.application.console_ui;

import internet_store.application.core.domain.Customer;
import internet_store.application.core.services.AddCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddCustomerUIAction implements UIAction{

    @Autowired AddCustomerService addCustomerService;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter customer's  name: ");
        String name = scanner.nextLine();
        System.out.println("Enter customer's surname: ");
        String surname = scanner.nextLine();
        System.out.println("Enter customer's phone: ");
        String phone = scanner.nextLine();
        Customer customer = new Customer(name, surname, phone);
        boolean result = addCustomerService.execute(customer);

        if (result) {
            System.out.println("Customer has been added successfully!");
        } else {
            System.out.println("Customer has not been added");
        }
    }

}
