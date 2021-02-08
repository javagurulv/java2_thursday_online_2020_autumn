package lv.tssweb.estore.core.responses.customer;

import lv.tssweb.estore.core.domain.Customer;
import lv.tssweb.estore.core.responses.CoreError;
import lv.tssweb.estore.core.responses.CoreResponse;

import java.util.List;

public class AddCustomerResponse extends CoreResponse {

    private Customer newCustomer;

    public AddCustomerResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddCustomerResponse(Customer newCustomer) {
        this.newCustomer = newCustomer;
    }

    public Customer getNewCustomer() {
        return newCustomer;
    }

}
