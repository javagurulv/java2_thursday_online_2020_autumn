package internet_store.application.console_ui.customer;

import internet_store.application.console_ui.UIAction;
import internet_store.application.core.requests.customer.AddCustomerRequest;
import internet_store.application.core.responses.customer.AddCustomerResponse;
import internet_store.application.core.services.customer.AddCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

//@Component
public class AddCustomerUIAction implements UIAction {

    @Autowired
    private AddCustomerService addCustomerService;

    public void execute() {
        Scanner myInput = new Scanner(System.in);
        System.out.println("Adding Customer to database : ");
        System.out.print("Enter Customer First Name : ");
        String customerFirstName = myInput.nextLine();
        System.out.print("Enter Customer Second Name : ");
        String customerSecondName = myInput.nextLine();
        System.out.print("Enter Customer Phone : ");
        String customerPhone = myInput.nextLine();
        System.out.print("Enter Customer e-mail : ");
        String customerEMail = myInput.nextLine();
        System.out.print("Enter Customer Address : ");
        String customerAddress = myInput.nextLine();

        AddCustomerRequest request = new AddCustomerRequest(customerFirstName, customerSecondName);
        request.setCustomerPhone(customerPhone);
        request.setCustomerEMail(customerEMail);
        request.setCustomerAddress(customerAddress);

        AddCustomerResponse response = addCustomerService.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
        } else { System.out.println("Added -> " + response.getNewCustomer()); }
    }
}
