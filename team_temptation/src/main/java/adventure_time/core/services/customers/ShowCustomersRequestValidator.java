package adventure_time.core.services.customers;

import adventure_time.core.requests.customers.ShowCustomersRequest;
import adventure_time.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class ShowCustomersRequestValidator {

    private static final Pattern PATTERN_NAME = Pattern.compile("\\p{Alnum}{1,50}", Pattern.CASE_INSENSITIVE);
    private static final Pattern PATTERN_PHONE = Pattern.compile("\\d{1,11}");
    private final static int MIN_PAGE_SIZE = 8;
    private final static int MAX_PAGE_SIZE = 20;

    public List<CoreError> validate (ShowCustomersRequest request) {

        List<CoreError> errors = new ArrayList<>();

//        boolean orderNotDefined = request.getOrdering().getOrderBy().equals("");
//        boolean directNotDefined = request.getOrdering().getOrderDirection().equals("");
//
//        // mandatory: if orderBy is defined then orderDirect must be defined too
//        if (directNotDefined && !orderNotDefined) {
//            CoreError error = new CoreError("orderDirect", "Must be specified as the sort criteria is specified");
//            errors.add(error);
//        }
//        // mandatory: if orderBy is not defined then orderDirect must be not defined too
//        if (orderNotDefined && !directNotDefined) {
//            CoreError error = new CoreError("orderBy", "Must be specified as the sort order is specified");
//            errors.add(error);
//        }

        if (request.getPaging().getPageNumber() <= 0) {
            CoreError error = new CoreError("pageNumber", "Must be above zero");
            errors.add(error);
        }

        if (request.getPaging().getPageSize() < MIN_PAGE_SIZE || request.getPaging().getPageSize() > MAX_PAGE_SIZE) {
            CoreError error = new CoreError("pageSize", "Must be between 8 and 20");
            errors.add(error);
        }

        if (!PATTERN_PHONE.matcher(request.getPhoneStartsWith()).matches() && !request.getPhoneStartsWith().equals("")) {
            CoreError error = new CoreError("customerPhoneStartWith", "Should only contain numbers without '+'");
            errors.add(error);
        }

        if (!PATTERN_NAME.matcher(request.getNameStartsWith()).matches() && !request.getNameStartsWith().equals("")) {
            CoreError error = new CoreError("customerName StartWith", "Should only contain alphanumeric characters");
            errors.add(error);
        }

        return errors;
    }
}
