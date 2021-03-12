package adventure_time.ui.customers;

import adventure_time.core.domain.Customers;
import adventure_time.core.requests.customers.LoginCustomerRequest;
import adventure_time.core.requests.customers.UpdateCustomerRequest;
import adventure_time.core.responses.customer.LoginCustomerResponse;
import adventure_time.core.responses.customer.UpdateCustomerResponse;
import adventure_time.core.services.customers.LoginCustomerService;
import adventure_time.core.services.customers.UpdateCustomerService;
import adventure_time.ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class UpdateCustomerUIAction implements UIAction {

    @Autowired
    private LoginCustomerService loginService;
    @Autowired
    private UpdateCustomerService updateService;

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please, enter login (email):");
        String email = scanner.nextLine();
        System.out.println("Please, enter password:");
        String password = scanner.nextLine();

        LoginCustomerRequest requestLogin = new LoginCustomerRequest(email, password);
        LoginCustomerResponse responseLogin = loginService.loginCustomer(requestLogin);

        if (responseLogin.hasError()) {
            System.out.println("Ooops! Something went wrong! ");
            System.out.println("Your request could not be fulfilled for the reasons: ");
            responseLogin.getErrors().forEach(System.out::println);
            System.out.println();
            return;
        }

        Customers customer = (Customers) responseLogin.getObject();
        System.out.println("Successful authentication!");
        System.out.println();

        System.out.println("If you would like to change the name, enter new one (min 5, max 50 characters).");
        System.out.println("If not - just press Enter:");
        String name = scanner.nextLine();

        System.out.println("If you would like to change the email, enter new one (max 30 characters ).");
        System.out.println("If not - just press Enter:");
        email = scanner.nextLine();

        System.out.println("If you would like to change the phone number, enter new one (without +).");
        System.out.println("If not - just press Enter:");
        String phone = scanner.nextLine();

        System.out.println("If you would like to change the password, enter new one (considering the following password security requirements):");
        System.out.println("  at least one digit [0-9], at least one lowercase Latin character [a-z],");
        System.out.println("  at least one uppercase Latin character [A-Z], at least one special character like ! @ # & ( ).");
        System.out.println("  Password must contain a length of at least 8 characters and a maximum of 20 characters.");
        System.out.println("If not - just press Enter:");
        password = scanner.nextLine();

        UpdateCustomerRequest requestUpdate = new UpdateCustomerRequest(name, email, phone, password, customer);
        UpdateCustomerResponse responseUpdate = updateService.updateCustomer(requestUpdate);

        if (responseUpdate.hasError()) {
            System.out.println("Ooops! Sooooomething went wrong! ");
            System.out.println("Your request could not be fulfilled for the reasons: ");
            responseUpdate.getErrors().forEach(System.out::println);
        } else {
            System.out.println("Your data is updated");
        }
        System.out.println();
    }
}
