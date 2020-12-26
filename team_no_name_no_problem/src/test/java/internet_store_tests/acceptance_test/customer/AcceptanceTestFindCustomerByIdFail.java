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


import static org.junit.Assert.*;

public class AcceptanceTestFindCustomerByIdFail {

    private ApplicationContext appContext;

    @Before
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(MainMenuConfiguration.class);
    }
    @Test
    public void test(){
        Customer customer = new Customer("Anton", "Saveljev", "29876472", "address",
                "email@email.lv");
        Customer customer1 = new Customer("Sasha", "Gogin","29384901", "Matisa",
                "tr3vis@Inbox.lv");
        Customer customer2 = new Customer("Valerija", "Lobanova","27817263",
                "Ukraina", "privetpoka@tikto.lv");

        AddCustomerRequest addCustomerRequest = new AddCustomerRequest(customer);
        AddCustomerRequest addCustomerRequest1 = new AddCustomerRequest(customer1);
        AddCustomerRequest addCustomerRequest2 = new AddCustomerRequest(customer2);
        addCustomerService().execute(addCustomerRequest);
        addCustomerService().execute(addCustomerRequest1);
        addCustomerService().execute(addCustomerRequest2);


        FindCustomerByIdRequest findCustomerByIdRequest = new FindCustomerByIdRequest(2L);
        FindCustomerByIdRequest findCustomerByIdRequest1 = new FindCustomerByIdRequest(1L);
        FindCustomerByIdRequest findCustomerByIdRequest2 = new FindCustomerByIdRequest(3L);
        FindCustomerByIdResponse findCustomerByIdResponse = findCustomerByIdService().execute(findCustomerByIdRequest);
        FindCustomerByIdResponse findCustomerByIdResponse1 = findCustomerByIdService().execute(findCustomerByIdRequest1);
        FindCustomerByIdResponse findCustomerByIdResponse2 = findCustomerByIdService().execute(findCustomerByIdRequest2);

        GetAllCustomersRequest getAllCustomersRequest = new GetAllCustomersRequest();
        GetAllCustomersResponse getAllCustomersResponse = getAllCustomersService().execute(getAllCustomersRequest);

        assertTrue(getAllCustomersResponse.getCustomers().size() == 3);
        assertFalse(findCustomerByIdResponse.getCustomer().get().equals(customer));
        assertFalse(findCustomerByIdResponse1.getCustomer().get().equals(customer1));
        assertTrue(findCustomerByIdResponse2.getCustomer().get().equals(customer2));
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
