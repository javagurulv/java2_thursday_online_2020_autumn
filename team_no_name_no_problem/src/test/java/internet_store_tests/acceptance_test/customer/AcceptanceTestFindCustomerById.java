package internet_store_tests.acceptance_test.customer;

import internet_store.config.MainMenuConfiguration;
import internet_store.core.domain.Customer;
import internet_store.core.requests.customer.AddCustomerRequest;
import internet_store.core.requests.customer.FindCustomerByIdRequest;
import internet_store.core.requests.customer.GetAllCustomersRequest;
import internet_store.core.response.customer.FindCustomerByIdResponse;
import internet_store.core.response.customer.GetAllCustomersResponse;
import internet_store.core.services.customer.AddCustomerService;
import internet_store.core.services.customer.FindCustomerByIdService;
import internet_store.core.services.customer.GetAllCustomersService;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertTrue;

public class AcceptanceTestFindCustomerById {

    private ApplicationContext appContext;

    @Before
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(MainMenuConfiguration.class);
    }
    @Test
    public void test(){
        Customer customer = new Customer("name", "surname", "28736278", "address",
                "email@mail.lv");
        Customer customer1 = new Customer("name", "surname","20987423", "Matisa",
                "tr3vis@Inbox.lv");
        Customer customer2 = new Customer("Valerija", "Lobanova","27812623",
                "Ukraina", "privetpoka@tikto.lv");
        AddCustomerRequest addCustomerRequest = new AddCustomerRequest(customer);
        AddCustomerRequest addCustomerRequest1 = new AddCustomerRequest(customer1);
        AddCustomerRequest addCustomerRequest2 = new AddCustomerRequest(customer2);
        addCustomerService().execute(addCustomerRequest);
        addCustomerService().execute(addCustomerRequest1);
        addCustomerService().execute(addCustomerRequest2);

        FindCustomerByIdRequest findCustomerByIdRequest = new FindCustomerByIdRequest(1L);
        FindCustomerByIdRequest findCustomerByIdRequest1 = new FindCustomerByIdRequest(2L);
        FindCustomerByIdResponse findCustomerByIdResponse = findCustomerByIdService().execute(findCustomerByIdRequest);
        FindCustomerByIdResponse findCustomerByIdResponse1 = findCustomerByIdService().execute(findCustomerByIdRequest1);

        GetAllCustomersRequest getAllCustomersRequest = new GetAllCustomersRequest();
        GetAllCustomersResponse getAllCustomersResponse = getAllCustomersService().execute(getAllCustomersRequest);

        assertTrue(getAllCustomersResponse.getCustomers().size() == 3);
        assertTrue(findCustomerByIdResponse.getCustomer().get().equals(customer));
        assertTrue(findCustomerByIdResponse1.getCustomer().get().equals(customer1));
    }

    private AddCustomerService addCustomerService(){
        return appContext.getBean(AddCustomerService.class);
    }

    private FindCustomerByIdService findCustomerByIdService(){
        return appContext.getBean(FindCustomerByIdService.class);
    }

    private GetAllCustomersService getAllCustomersService(){
        return appContext.getBean(GetAllCustomersService.class);
    }
}
