package internet_store.console_ui.customer;


import internet_store.console_ui.UIAction;
import internet_store.core.requests.customer.FindCustomerByIdRequest;
import internet_store.core.response.customer.FindCustomerByIdResponse;
import internet_store.core.services.customer.FindCustomerByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

//@Component
public class FindCustomerByIdUIAction implements UIAction {

    @Autowired private FindCustomerByIdService findCustomerByIdService;

    @Override
    public void execute(){

        Scanner in = new Scanner(System.in);

        System.out.println("Please enter customers id");
        long id = in.nextLong();

        FindCustomerByIdRequest findCustomersByIdRequest = new FindCustomerByIdRequest(id);
        FindCustomerByIdResponse findCustomerByIdResponse = findCustomerByIdService.execute(findCustomersByIdRequest);

        if (findCustomerByIdResponse.hasErrors()){
            findCustomerByIdResponse.getErrors().forEach(System.out::println);
        } else {
            System.out.println("Customer with id: " + id);
            System.out.println(findCustomerByIdResponse.getCustomers());
        }
    }
}
