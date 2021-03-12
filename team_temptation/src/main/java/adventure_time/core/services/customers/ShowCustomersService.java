package adventure_time.core.services.customers;

import adventure_time.core.database.customers.CustomerRepository;
import adventure_time.core.domain.Customers;
import adventure_time.core.requests.customers.ShowCustomersRequest;
import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.customer.ShowCustomersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShowCustomersService {

    @Autowired
    private CustomerRepository database;
    @Autowired
    ShowCustomersRequestValidator validator;

    public ShowCustomersResponse getCustomersList(ShowCustomersRequest request) {

        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new ShowCustomersResponse(null, errors);
        }

        String query = queryMaker(request);

        List<Customers> result = database.findCustomers(query, request.getPaging());

        return new ShowCustomersResponse(result,null);
    }

    private String queryMaker (ShowCustomersRequest request) {
        return "FROM Customers AS c" +
                isWhere(request) +
                isNameStartWith(request.getNameStartsWith()) +
                isAnd(request) +
                isPhoneStartsWith(request.getPhoneStartsWith()) +
                request.getOrdering().getOrderBy() +
                request.getOrdering().getOrderDirection();
    }

    private String isWhere (ShowCustomersRequest request) {
        return !(request.getNameStartsWith().equals("") && request.getPhoneStartsWith().equals("")) ?
                " WHERE" : "";
    }

    private String isAnd (ShowCustomersRequest request) {
        return !(request.getNameStartsWith().equals("") || request.getPhoneStartsWith().equals("")) ?
                " AND" : "";
    }

    private String isPhoneStartsWith (String get) {
        return get.equals("") ? "" : " c.customerPhone LIKE '+" + get + "%'";
    }

    private String isNameStartWith (String get) {
        return get.equals("") ? "" : " c.customerName LIKE '" + get + "%'";
    }

}
