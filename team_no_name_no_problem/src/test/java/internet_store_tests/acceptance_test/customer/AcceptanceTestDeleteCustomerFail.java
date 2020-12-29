package internet_store_tests.acceptance_test.customer;

import internet_store.config.MainMenuConfiguration;
import internet_store.core.domain.Customer;
import internet_store.core.requests.customer.AddCustomerRequest;
import internet_store.core.requests.customer.DeleteCustomerRequest;
import internet_store.core.requests.customer.GetAllCustomersRequest;
import internet_store.core.response.customer.GetAllCustomersResponse;
import internet_store.core.services.customer.AddCustomerService;
import internet_store.core.services.customer.DeleteCustomerService;
import internet_store.core.services.customer.GetAllCustomersService;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertTrue;

public class AcceptanceTestDeleteCustomerFail {

        private ApplicationContext appContext;

        @Before
        public void setup() {
            appContext = new AnnotationConfigApplicationContext(MainMenuConfiguration.class);
        }

        @Test
        public void test() {
            Customer customer = new Customer("Anvar", "Papawa", "11881111", "Egypt street",
                    "vozmimenyazaruku@gmail.com");
            Customer customer1 = new Customer("Jarka", "Zazigalo4ka", "10211111",
                    "AppalonSaturn", "Jazhematj@inbox.lv");
            AddCustomerRequest addCustomerRequest = new AddCustomerRequest(customer);
            AddCustomerRequest addCustomerRequest1 = new AddCustomerRequest(customer1);
            addCustomerService().execute(addCustomerRequest);
            addCustomerService().execute(addCustomerRequest1);

            DeleteCustomerRequest deleteCustomerRequest = new DeleteCustomerRequest(4L);
            deleteCustomerService().execute(deleteCustomerRequest);

            GetAllCustomersRequest getAllCustomersRequest = new GetAllCustomersRequest();
            GetAllCustomersResponse getAllCustomersResponse = getAllCustomersService().execute(getAllCustomersRequest);

            assertTrue(getAllCustomersResponse.getCustomers().size() == 1);
        }

        private DeleteCustomerService deleteCustomerService() {
            return appContext.getBean(DeleteCustomerService.class);
        }

        private AddCustomerService addCustomerService() {
            return appContext.getBean(AddCustomerService.class);
        }

        private GetAllCustomersService getAllCustomersService() {
            return appContext.getBean(GetAllCustomersService.class);
        }
    }

