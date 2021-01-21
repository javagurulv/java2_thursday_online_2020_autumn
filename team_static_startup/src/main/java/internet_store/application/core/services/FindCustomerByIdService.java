package internet_store.application.core.services;

import internet_store.application.core.database.CustomerDatabase;
import internet_store.application.core.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindCustomerByIdService {

    @Autowired CustomerDatabase customerDatabase;

    public Customer execute(Long id) {
        return customerDatabase.findById(id).get();
    }
}
