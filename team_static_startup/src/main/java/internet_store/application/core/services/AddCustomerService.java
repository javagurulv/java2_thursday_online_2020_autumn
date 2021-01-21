package internet_store.application.core.services;

import internet_store.application.core.database.CustomerDatabase;
import internet_store.application.core.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddCustomerService {

    @Autowired private CustomerDatabase customerDatabase;

    public boolean execute(Customer customer) {
        return customerDatabase.add(customer) > 0;
    }

}
