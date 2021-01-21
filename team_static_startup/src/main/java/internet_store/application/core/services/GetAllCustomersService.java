package internet_store.application.core.services;

import internet_store.application.core.database.CustomerDatabase;
import internet_store.application.core.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllCustomersService {

    @Autowired private CustomerDatabase customerDatabase;

    public List<Customer> execute() {
        return customerDatabase.findAll();
    }

}
