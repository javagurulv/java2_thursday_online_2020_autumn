package internet_store.application.acceptancetests;

import internet_store.application.config.SpringCoreConfiguration;
import internet_store.application.core.DatabaseCleaner;
import internet_store.application.core.requests.product.FindProductsRequest;
import internet_store.application.core.requests.product.Ordering;
import internet_store.application.core.requests.product.Paging;
import internet_store.application.core.responses.product.FindProductsResponse;
import internet_store.application.core.services.product.FindProductsService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Profile;
import static org.junit.Assert.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FindProductsAcceptanceTest {

    private ApplicationContext appContext;

    @Before
    public void before(){
        appContext = new AnnotationConfigApplicationContext(SpringCoreConfiguration.class);
        getDatabaseCleaner().clean();
    }

/*
    @Test
    public void shouldReturnNoErrorsWhenNameAndDescriptionProvided() {
        FindProductsRequest request = new FindProductsRequest("A1", "B1");
        FindProductsResponse response = getFindProductsService().execute(request);
        assertFalse(response.hasErrors());
    }

    @Test
    public void shouldNotReturnErrorsWhenNameIsNotProvided() {
        FindProductsRequest request = new FindProductsRequest(null, "B1");
        FindProductsResponse response = getFindProductsService().execute(request);
        assertFalse(response.hasErrors());
    }

    @Test
    public void shouldNotReturnErrorsWhenDescriptionIsNotProvided() {
        FindProductsRequest request = new FindProductsRequest("A1", null);
        FindProductsResponse response = getFindProductsService().execute(request);
        assertFalse(response.hasErrors());
    }

    @Test
    public void shouldReturnErrorWhenBothSearchFieldsAreEmpty() {
        FindProductsRequest request = new FindProductsRequest(null, null);
        FindProductsResponse response = getFindProductsService().execute(request);
        assertTrue(response.hasErrors());
        assertEquals(2, response.getErrors().size());
        assertEquals(response.getErrors().get(0).getField(), "Name");
        assertEquals(response.getErrors().get(0).getMessage(), "Must not be empty!");
        assertEquals(response.getErrors().get(1).getField(), "Description");
        assertEquals(response.getErrors().get(1).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldNotReturnErrorsWhenBothOrderingFieldsProvided() {
        Ordering ordering = new Ordering("Name", "Ascending");
        FindProductsRequest request = new FindProductsRequest("A1", null, ordering);
        FindProductsResponse response = getFindProductsService().execute(request);
        assertFalse(response.hasErrors());
    }

    @Test
    public void shouldReturnErrorWhenOnlyOneOrderingFieldProvided() {
        Ordering ordering = new Ordering("Name", null);
        FindProductsRequest request = new FindProductsRequest("A1", null, ordering);
        FindProductsResponse response = getFindProductsService().execute(request);
        assertTrue(response.hasErrors());
        assertEquals(2, response.getErrors().size());
        assertEquals("Ordering Fields", response.getErrors().get(0).getField());
        assertEquals("Both must be empty or filled!", response.getErrors().get(0).getMessage());
        assertEquals("Direction", response.getErrors().get(1).getField());
        assertEquals("Must be Ascending or Descending.", response.getErrors().get(1).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenOnlyOneOrderingFieldProvidedv2() {
        Ordering ordering = new Ordering(null, "Ascending");
        FindProductsRequest request = new FindProductsRequest("A1", null, ordering);
        FindProductsResponse response = getFindProductsService().execute(request);
        assertTrue(response.hasErrors());
        assertEquals(2, response.getErrors().size());
        assertEquals("Ordering Fields", response.getErrors().get(0).getField());
        assertEquals("Both must be empty or filled!", response.getErrors().get(0).getMessage());
        assertEquals("Ordering by", response.getErrors().get(1).getField());
        assertEquals("Must be Name or Description.", response.getErrors().get(1).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenOrderingNameIsIncorrect() {
        Ordering ordering = new Ordering("nname", "Ascending");
        FindProductsRequest request = new FindProductsRequest("A1", null, ordering);
        FindProductsResponse response = getFindProductsService().execute(request);
        assertEquals(1, response.getErrors().size());
        assertEquals("Ordering by", response.getErrors().get(0).getField());
        assertEquals("Must be Name or Description.", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenOrderingDirectionIsIncorrect() {
        Ordering ordering = new Ordering("Name", "Up");
        FindProductsRequest request = new FindProductsRequest("A1", null, ordering);
        FindProductsResponse response = getFindProductsService().execute(request);
        assertEquals(1, response.getErrors().size());
        assertEquals("Direction", response.getErrors().get(0).getField());
        assertEquals("Must be Ascending or Descending.", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenOnlyPageNumberProvided() {
        Paging paging = new Paging(1, null);
        FindProductsRequest request = new FindProductsRequest("A1", null, paging);
        FindProductsResponse response = getFindProductsService().execute(request);
        assertEquals(1, response.getErrors().size());
        assertEquals("Page number and page size", response.getErrors().get(0).getField());
        assertEquals("Both must be empty or filled.", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenOnlyPageSizeProvided() {
        Paging paging = new Paging(null, 3);
        FindProductsRequest request = new FindProductsRequest("A1", null, paging);
        FindProductsResponse response = getFindProductsService().execute(request);
        assertEquals(1, response.getErrors().size());
        assertEquals("Page number and page size", response.getErrors().get(0).getField());
        assertEquals("Both must be empty or filled.", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnNoErrorWhenBothPagingFieldsNotProvided() {
        Paging paging = new Paging(null, null);
        FindProductsRequest request = new FindProductsRequest("A1", "B1", paging);
        FindProductsResponse response = getFindProductsService().execute(request);
        assertFalse(response.hasErrors());
    }

    @Test
    public void shouldReturnNoErrorWhenBothPagingFieldsProvided() {
        Paging paging = new Paging(1, 10);
        FindProductsRequest request = new FindProductsRequest("A1", null, paging);
        FindProductsResponse response = getFindProductsService().execute(request);
        assertFalse(response.hasErrors());
    }

    @Test
    public void shouldReturnErrorWhenPageSizeIsZero() {
        Paging paging = new Paging(1, 0);
        FindProductsRequest request = new FindProductsRequest("A1", null, paging);
        FindProductsResponse response = getFindProductsService().execute(request);
        assertEquals(1, response.getErrors().size());
        assertEquals("Page size", response.getErrors().get(0).getField());
        assertEquals("Must be bigger than zero.", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenPageNumberIsZero() {
        Paging paging = new Paging(0, 2);
        FindProductsRequest request = new FindProductsRequest("A1", null, paging);
        FindProductsResponse response = getFindProductsService().execute(request);
        assertEquals(1, response.getErrors().size());
        assertEquals("Page number", response.getErrors().get(0).getField());
        assertEquals("Must be bigger than zero.", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnTwoErrorsWhenPageNumberAndSizeAreZero() {
        Paging paging = new Paging(0, 0);
        FindProductsRequest request = new FindProductsRequest("A1", null, paging);
        FindProductsResponse response = getFindProductsService().execute(request);
        assertEquals(2, response.getErrors().size());
        assertEquals("Page size", response.getErrors().get(0).getField());
        assertEquals("Must be bigger than zero.", response.getErrors().get(0).getMessage());
        assertEquals("Page number", response.getErrors().get(1).getField());
        assertEquals("Must be bigger than zero.", response.getErrors().get(1).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenPageNumberIsLessThanZero() {
        Paging paging = new Paging(-2, 2);
        FindProductsRequest request = new FindProductsRequest("A1", null, paging);
        FindProductsResponse response = getFindProductsService().execute(request);
        assertEquals(1, response.getErrors().size());
        assertEquals("Page number", response.getErrors().get(0).getField());
        assertEquals("Must be bigger than zero.", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenPageSizeIsLessThanZero() {
        Paging paging = new Paging(2, -1);
        FindProductsRequest request = new FindProductsRequest("A1", null, paging);
        FindProductsResponse response = getFindProductsService().execute(request);
        assertEquals(1, response.getErrors().size());
        assertEquals("Page size", response.getErrors().get(0).getField());
        assertEquals("Must be bigger than zero.", response.getErrors().get(0).getMessage());
    }
*/


    private FindProductsService getFindProductsService() {
        return appContext.getBean(FindProductsService.class);
    }

    private DatabaseCleaner getDatabaseCleaner() {
        return appContext.getBean(DatabaseCleaner.class);
    }

}
