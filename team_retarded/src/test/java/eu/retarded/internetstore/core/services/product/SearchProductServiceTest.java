package eu.retarded.internetstore.core.services.product;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.product.Ordering;
import eu.retarded.internetstore.core.requests.product.Paging;
import eu.retarded.internetstore.core.requests.product.SearchProductRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.product.SearchProductResponse;
import eu.retarded.internetstore.core.services.validators.product.SearchProductValidator;
import eu.retarded.internetstore.database.product.ProductDatabase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class SearchProductServiceTest {


    @Mock
    private SearchProductValidator validator;
    @Mock
    private ProductDatabase db;
    @InjectMocks
    private SearchProductService service;


    @Test
    public void shouldReturnResponseWithErrorsWhenValidatorFails() {
        SearchProductRequest request = new SearchProductRequest(null, null);

        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("name", "Must not be empty!"));
        errors.add(new CoreError("description", "Must not be empty!"));
        Mockito.when((validator.validate(request))).thenReturn(errors);

        SearchProductResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 2);
        assertEquals(response.getErrors().get(0).getField(), "name");

        Mockito.verify(validator).validate(request);
        Mockito.verify(validator).validate(any());
        Mockito.verifyNoInteractions(db);
    }

    @Test
    public void shouldSearchByName() {
        SearchProductRequest request = new SearchProductRequest("Title", null);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Product> products = new ArrayList<>();
        products.add(new Product("Title", "Author123456789", 345));

        Mockito.when(db.filter(any())).thenReturn(products);

        SearchProductResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getProducts().size(), 1);
        assertEquals(response.getProducts().get(0).getName(), "Title");
        assertEquals(response.getProducts().get(0).getDescription(), "Author123456789");
    }

    @Test
    public void shouldSearchByDescription() {
        SearchProductRequest request = new SearchProductRequest(null, "Author");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Product> products = new ArrayList<>();
        products.add(new Product("Title", "Author123456789", 345));
        Mockito.when(db.filter(any())).thenReturn(products);

        SearchProductResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getProducts().size(), 1);
        assertEquals(response.getProducts().get(0).getName(), "Title");
        assertEquals(response.getProducts().get(0).getDescription(), "Author123456789");
    }

    @Test
    public void shouldSearchByNameAndDescription() {
        SearchProductRequest request = new SearchProductRequest("Title", "Author");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Product> products = new ArrayList<>();
        products.add(new Product("Title", "Author123456789", 345));
        Mockito.when(db.filter(any())).thenReturn(products);

        SearchProductResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getProducts().size(), 1);
        assertEquals(response.getProducts().get(0).getName(), "Title");
        assertEquals(response.getProducts().get(0).getDescription(), "Author123456789");
    }

    @Test
    public void shouldSearchByNameWithOrderingAscending() {
        Ordering ordering = new Ordering("name", "ASCENDING");
        SearchProductRequest request = new SearchProductRequest("Title", null, ordering);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Product> products = new ArrayList<>();
        products.add(new Product("Title1", "Author123456789", 345));
        products.add(new Product("Title2", "Author123456789", 345));
        Mockito.when(db.filter(any())).thenReturn(products);

        SearchProductResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getProducts().size(), 2);
        assertEquals(response.getProducts().get(0).getName(), "Title1");
        assertEquals(response.getProducts().get(1).getName(), "Title2");
    }

    @Test
    public void shouldSearchByNameWithOrderingDescending() {
        ReflectionTestUtils.setField(service, "orderingEnabled", true);
        ReflectionTestUtils.setField(service, "pagingEnabled", true);
        Ordering ordering = new Ordering("name", "DESCENDING");
        SearchProductRequest request = new SearchProductRequest("Title", null, ordering);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Product> products = new ArrayList<>();
        products.add(new Product("Author1", "Author123456789", 345));
        products.add(new Product("Author2", "Author123456789", 345));
        Mockito.when(db.filter(any())).thenReturn(products);

        SearchProductResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getProducts().size(), 2);
        assertEquals(response.getProducts().get(0).getName(), "Author2");
        assertEquals(response.getProducts().get(1).getName(), "Author1");
    }

    @Test
    public void shouldSearchByNameWithPagingFirstPage() {
        ReflectionTestUtils.setField(service, "orderingEnabled", true);
        ReflectionTestUtils.setField(service, "pagingEnabled", true);
        Paging paging = new Paging(1, 1);
        SearchProductRequest request = new SearchProductRequest("Title", null, null, paging);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Product> products = new ArrayList<>();
        products.add(new Product("Author1", "Author123456789", 345));
        products.add(new Product("Author2", "Author123456789", 345));
        Mockito.when(db.filter(any())).thenReturn(products);

        SearchProductResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getProducts().size(), 1);
        assertEquals(response.getProducts().get(0).getName(), "Author1");
        assertEquals(response.getProducts().get(0).getDescription(), "Author123456789");
    }

    @Test
    public void shouldSearchByNameWithPagingSecondPage() {
        ReflectionTestUtils.setField(service, "orderingEnabled", true);
        ReflectionTestUtils.setField(service, "pagingEnabled", true);
        Paging paging = new Paging(2, 1);
        SearchProductRequest request = new SearchProductRequest("Title", null, null, paging);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Product> products = new ArrayList<>();
        products.add(new Product("Author1", "Author123456789", 345));
        products.add(new Product("Author2", "Author123456789", 345));
        Mockito.when(db.filter(any())).thenReturn(products);

        SearchProductResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getProducts().size(), 1);
        assertEquals(response.getProducts().get(0).getName(), "Author2");
        assertEquals(response.getProducts().get(0).getDescription(), "Author123456789");
    }

    @Test
    public void onlyNameFiled() {
        ReflectionTestUtils.setField(service, "orderingEnabled", true);
        ReflectionTestUtils.setField(service, "pagingEnabled", true);
        Paging paging = new Paging(null, null);
        Ordering ordering = new Ordering(null, null);
        SearchProductRequest request = new SearchProductRequest("Author2", null, ordering, paging);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Product> products = new ArrayList<>();
        products.add(new Product("Author2", "Author1234567899", 345));
        Mockito.when(db.filter(any())).thenReturn(products);

        SearchProductResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getProducts().size(), 1);
        assertEquals(response.getProducts().get(0).getName(), "Author2");
        assertEquals(response.getProducts().get(0).getDescription(), "Author1234567899");
    }
}