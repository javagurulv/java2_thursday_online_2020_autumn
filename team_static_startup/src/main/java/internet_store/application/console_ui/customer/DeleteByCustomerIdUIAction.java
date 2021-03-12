package internet_store.application.console_ui.customer;

import internet_store.application.console_ui.UIAction;
import internet_store.application.core.requests.customer.DeleteByCustomerIdRequest;
import internet_store.application.core.responses.customer.DeleteByCustomerIdResponse;
import internet_store.application.core.services.customer.DeleteByCustomerIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

//@Component
public class DeleteByCustomerIdUIAction implements UIAction {

    @Autowired
    private DeleteByCustomerIdService deleteByCustomerIdService;


    public void execute() {
        Scanner myInput = new Scanner(System.in);
        System.out.print("Enter Customer ID for deleting: ");
        Long customerId = myInput.nextLong();
        DeleteByCustomerIdRequest request = new DeleteByCustomerIdRequest(customerId);
        DeleteByCustomerIdResponse response = deleteByCustomerIdService.execute(request);

        if (response.hasErrors()){
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getField()));
        } else {
            if (response.isCustomerRemoved()){
                System.out.println("Customer with Id = " + customerId + " deleted");
            } else {
                System.out.println("Customer with Id = " + customerId + " was NOT deleted");
            }
        }
    }

}
