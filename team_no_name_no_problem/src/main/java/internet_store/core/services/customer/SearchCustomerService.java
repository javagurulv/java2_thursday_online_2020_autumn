package internet_store.core.services.customer;


import internet_store.core.domain.Customer;
import internet_store.core.requests.Ordering;
import internet_store.core.requests.Paging;
import internet_store.core.requests.customer.SearchCustomerRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.customer.SearchCustomerResponse;
import internet_store.core.services.customer.validators.SearchCustomerRequestValidator;
import internet_store.database.jpa.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Component
@Transactional
public class SearchCustomerService {

    @Value("${search.ordering.enabled}")
    private boolean orderingEnabled;

    @Value("${search.paging.enabled}")
    private boolean pagingEnabled;

    @Autowired private CustomerRepository customerDatabase;
    @Autowired private SearchCustomerRequestValidator searchCustomerRequestValidator;

    public SearchCustomerResponse execute(SearchCustomerRequest searchCustomerRequest) {

        List<CoreError> errors = searchCustomerRequestValidator.validate(searchCustomerRequest);

        if (!errors.isEmpty()) {
            return new SearchCustomerResponse(errors, new ArrayList<>());
        }
        return provideSearchResultAccordingToRequest(searchCustomerRequest);
    }


        private SearchCustomerResponse provideSearchResultAccordingToRequest (SearchCustomerRequest
        searchCustomerRequest){
            if (isNameAndSurnameNotEmpty(searchCustomerRequest.getName(), searchCustomerRequest.getSurname())) {
                return searchByNameAndSurnameIsProvided(searchCustomerRequest);
            }
            if (isNameFilled(searchCustomerRequest.getName())) {
                return searchByNameIsProvided(searchCustomerRequest);
            }
            if (isSurnameFilled(searchCustomerRequest.getSurname())) {
                return searchBySurnameIsProvided(searchCustomerRequest);
            }
            return searchByNameAndSurnameIsProvided(searchCustomerRequest);
        }

        private SearchCustomerResponse searchByNameAndSurnameIsProvided (SearchCustomerRequest searchCustomerRequest){
            List<CoreError> errors = new ArrayList<>();
            List<Customer> customers = customerDatabase.findCustomersByNameAndSurname(searchCustomerRequest.getName(),
                    searchCustomerRequest.getSurname());
            if (customers.isEmpty()) {
                errors.add(new CoreError("database", "Database doesn't contain customer with name " +
                        searchCustomerRequest.getName() + " and surname " + searchCustomerRequest.getSurname()));
                return new SearchCustomerResponse(errors, new ArrayList<>());
            }

            customers = order(customers, searchCustomerRequest.getOrdering());
            customers = paging(customers, searchCustomerRequest.getPaging());
            return new SearchCustomerResponse(customers);
        }


        private boolean isNameAndSurnameNotEmpty (String name, String surname){
            return name!= null && !name.isEmpty() && surname != null && !surname.isEmpty();
        }

        private boolean isNameFilled (String name){
            return name!=null && !name.isEmpty();
        }

        private SearchCustomerResponse searchByNameIsProvided (SearchCustomerRequest searchCustomerRequest){
            List<CoreError> errors = new ArrayList<>();
            List<Customer> customers = customerDatabase.findAllCustomersByName(searchCustomerRequest.getName());
            if (customers.isEmpty()) {
                errors.add(new CoreError("database", "Database doesn't contain customer with name: " +
                        searchCustomerRequest.getName()));
                return new SearchCustomerResponse(errors, new ArrayList<>());
            }
            customers = order(customers, searchCustomerRequest.getOrdering());
            customers = paging(customers,searchCustomerRequest.getPaging());
            return new SearchCustomerResponse(customers);
        }

        private boolean isSurnameFilled(String surname){
        return  surname !=null && !surname.isEmpty();
        }

        private SearchCustomerResponse searchBySurnameIsProvided (SearchCustomerRequest searchCustomerRequest){
            List<CoreError> errors = new ArrayList<>();
            List<Customer> customers = customerDatabase.findAllCustomersBySurname((searchCustomerRequest.getSurname()));
            if (customers.isEmpty()) {
                errors.add(new CoreError("database", "Database doesn't contain customer with surname: " +
                        searchCustomerRequest.getSurname()));
                return new SearchCustomerResponse(errors, new ArrayList<>());
            }
            customers = order(customers, searchCustomerRequest.getOrdering());
            customers = paging(customers, searchCustomerRequest.getPaging());
            return new SearchCustomerResponse(customers);
        }

        private List<Customer> order (List < Customer > customers, Ordering ordering){
            if (ordering.filledBoth()) {
                if (ordering.getOrderBy().equals("name")) {
                    return sortByName(customers, ordering);
                } else {
                    return sortBySurname(customers, ordering);
                }
            } else {
                return customers;
            }
        }

        private List<Customer> sortByName (List < Customer > customers, Ordering ordering){
            if (ordering.getOrderDirection().equals("ASC")) {
                return customers.stream()
                        .sorted(Comparator.comparing(customer -> customer.getName()))
                        .collect(Collectors.toList());
            } else {
                return customers.stream()
                        .sorted((o1, o2) -> o2.getName().compareTo(o1.getName()))
                        .collect(Collectors.toList());
            }
        }

        private List<Customer> sortBySurname (List < Customer > customers, Ordering ordering){
            if (ordering.getOrderDirection().equals("ASC")) {
                return customers.stream()
                        .sorted(Comparator.comparing(customer -> customer.getSurname()))
                        .collect(Collectors.toList());
            } else {
                return customers.stream()
                        .sorted((o1, o2) -> o2.getSurname().compareTo(o1.getSurname()))
                        .collect(Collectors.toList());
            }
        }

        private List<Customer> paging(List<Customer> customers, Paging paging){
        if (paging != null){
            int skip = (paging.getPageNumber() -1) * paging.getPageSize();
            return customers.stream()
                    .skip(skip)
                    .limit(paging.getPageSize())
                    .collect(Collectors.toList());
        } else {
            return customers;
        }
        }

}
