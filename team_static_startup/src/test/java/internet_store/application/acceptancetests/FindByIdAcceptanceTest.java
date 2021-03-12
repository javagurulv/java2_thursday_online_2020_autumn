package internet_store.application.acceptancetests;

import internet_store.application.config.SpringCoreConfiguration;
import internet_store.application.core.DatabaseCleaner;
import internet_store.application.core.database.product.ProductRepository;
import internet_store.application.core.domain.Product;
import internet_store.application.core.requests.product.FindByIdRequest;
import internet_store.application.core.responses.product.FindByProductIdResponse;
import internet_store.application.core.services.product.FindByProductIdService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Profile;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FindByIdAcceptanceTest {
/*

    private ApplicationContext appContext;
    private ProductRepository repository;

    @Before
    public void setUp() {
        appContext =
                new AnnotationConfigApplicationContext(SpringCoreConfiguration.class);
        getDatabaseCleaner().clean();
        repository = getRepository();
        repository.add(new Product("iPhone", "phone", new BigDecimal("900")));
        repository.add(new Product("iMac", "pc", new BigDecimal("4000")));
    }

    @Test
    public void shouldFindById() {
        FindByIdRequest request = new FindByIdRequest("2");
        FindByProductIdResponse response = getFindByIdService().execute(request);

        assertFalse(response.hasErrors());
        assertFalse(response.getProductFoundById().isEmpty());
        assertEquals(Optional.of(
                newProduct(2L, "iMac", "pc", new BigDecimal("4000"))),
                response.getProductFoundById());
    }

    @Test
    public void shouldNotFindWhenIdIsNotExist() {
        FindByIdRequest request = new FindByIdRequest("5");
        FindByProductIdResponse response = getFindByIdService().execute(request);

        assertFalse(response.hasErrors());
        assertEquals(Optional.empty(),
                response.getProductFoundById());
    }

    @Test
    public void shouldReturnErrorWhenIdIsEmpty() {
        FindByIdRequest request = new FindByIdRequest("");
        FindByProductIdResponse response = getFindByIdService().execute(request);

        assertTrue(response.hasErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("Product ID", response.getErrors().get(0).getField());
        assertEquals("Should not be empty.", response.getErrors().get(0).getMessage());
    }

    private Product newProduct(Long id, String name, String description, BigDecimal price) {
        Product product = new Product(name, description, price);
        product.setId(id);
        return product;
    }

    private ProductRepository getRepository() {
        return appContext.getBean(ProductRepository.class);
    }

    private FindByProductIdService getFindByIdService() {
        return appContext.getBean(FindByProductIdService.class);
    }

    private DatabaseCleaner getDatabaseCleaner() {
        return appContext.getBean(DatabaseCleaner.class);
    }
*/

}