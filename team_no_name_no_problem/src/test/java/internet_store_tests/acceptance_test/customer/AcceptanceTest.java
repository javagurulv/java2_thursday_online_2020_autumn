package internet_store_tests.acceptance_test.customer;

import internet_store.config.MainMenuConfiguration;
import internet_store.core.domain.Customer;
import internet_store.core.requests.customer.AddCustomerRequest;
import internet_store.core.requests.customer.DeleteCustomerRequest;
import internet_store.core.requests.customer.FindCustomerByIdRequest;
import internet_store.core.requests.customer.GetAllCustomersRequest;
import internet_store.core.response.customer.FindCustomerByIdResponse;
import internet_store.core.response.customer.GetAllCustomersResponse;
import internet_store.core.services.customer.AddCustomerService;
import internet_store.core.services.customer.DeleteCustomerService;
import internet_store.core.services.customer.FindCustomerByIdService;
import internet_store.core.services.customer.GetAllCustomersService;
import internet_store_tests.DatabaseCleaner;
import org.springframework.context.ApplicationContext;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertTrue;

public class AcceptanceTest {

    private ApplicationContext appContext;

    @Before
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(MainMenuConfiguration.class);
        getDatabaseCleaner().clean();
    }
    @Test
    public void test() {
        Customer customer = new Customer("Johny", "Bravo", "28736810", "Queens",
                "bravo@gmail.com");
        Customer customer1 = new Customer("Danik", "", "82771827", "Riga",
                "ivanovd@gmail.com");
        AddCustomerRequest addCustomerRequest = new AddCustomerRequest(customer);
        AddCustomerRequest addCustomerRequest1 = new AddCustomerRequest(customer1);
        addCustomerService().execute(addCustomerRequest);
        addCustomerService().execute(addCustomerRequest1);

        DeleteCustomerRequest deleteCustomerRequest = new DeleteCustomerRequest(2L);
        deleteCustomerService().execute(deleteCustomerRequest);

        Customer customer2 = new Customer("Danik", "Ivanov", "82771827", "Riga",
                "ivanov@gmail.com");
        AddCustomerRequest addCustomerRequest2 = new AddCustomerRequest(customer2);
        addCustomerService().execute(addCustomerRequest2);

        FindCustomerByIdRequest findCustomerByIdRequest = new FindCustomerByIdRequest(1L);
        FindCustomerByIdResponse findCustomerByIdResponse = findCustomerByIdService().execute(findCustomerByIdRequest);

        GetAllCustomersRequest getAllCustomersRequest = new GetAllCustomersRequest();
        GetAllCustomersResponse getAllCustomersResponse = getAllCustomersService().execute(getAllCustomersRequest);

        assertTrue(getAllCustomersResponse.getCustomers().size() != 2);
        assertTrue(findCustomerByIdResponse.getCustomer().get().equals(customer));


    }

    private AddCustomerService addCustomerService() {
        return appContext.getBean(AddCustomerService.class);
    }

    private GetAllCustomersService getAllCustomersService() {
        return appContext.getBean(GetAllCustomersService.class);
    }

    private FindCustomerByIdService findCustomerByIdService() {
        return appContext.getBean(FindCustomerByIdService.class);
    }

    private DeleteCustomerService deleteCustomerService(){
        return appContext.getBean(DeleteCustomerService.class);
    }

   private DatabaseCleaner getDatabaseCleaner(){
       return appContext.getBean(DatabaseCleaner.class);
    }
}
