package adventure_time.core.responses.customer;

import adventure_time.core.domain.Customers;
import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.CoreResponse;

import java.util.List;

public class ShowCustomersResponse extends CoreResponse {

    private List<Customers> customersList;

    public ShowCustomersResponse(List<Customers> customer, List<CoreError> errors) {
        super(errors);
        this.customersList = customer;
    }

    public List<Customers> getCustomersList() {
        return customersList;
    }

}
