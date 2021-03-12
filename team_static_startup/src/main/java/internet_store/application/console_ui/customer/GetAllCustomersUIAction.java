package internet_store.application.console_ui.customer;

import internet_store.application.console_ui.UIAction;
import internet_store.application.core.domain.Customer;
import internet_store.application.core.responses.customer.GetAllCustomersResponse;
import internet_store.application.core.services.customer.GetAllCustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class GetAllCustomersUIAction implements UIAction {

    @Autowired
    GetAllCustomersService getAllCustomersService;

    @Override
    public void execute() {
        GetAllCustomersResponse customersResponse = getAllCustomersService.execute();
        List<Customer> customerList = customersResponse.getCustomerList();
        if (customerList.isEmpty()) {
            System.out.println("Customer repository is empty!");
        } else {
            customerList.forEach(System.out::println);
        }
    }

}
