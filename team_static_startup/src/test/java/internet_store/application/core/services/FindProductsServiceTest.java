package internet_store.application.core.services;

import internet_store.application.core.database.ProductDatabase;
import internet_store.application.core.domain.Product;
import internet_store.application.core.requests.FindProductsRequest;
import internet_store.application.core.requests.Ordering;
import internet_store.application.core.requests.Paging;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.FindProductsResponse;
import internet_store.application.core.services.validators.FindProductsRequestValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)

public class FindProductsServiceTest {
    @Mock private ProductDatabase database;
    @Mock private FindProductsRequestValidator validator;
    @InjectMocks FindProductsService service;
    private List<Product> products = new ArrayList<>();
    private List<Product> expected = new ArrayList<>();
    Product product1 = new Product("A1", "B1", new BigDecimal("1"));
    Product product2 = new Product("A2", "B2", new BigDecimal("2"));

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(service, "orderingEnabled", true);
        ReflectionTestUtils.setField(service, "pagingEnabled", true);
        products.add(product1);
        products.add(product2);
    }

    @Test
    public void shouldReturnListWithOneError () {
        FindProductsRequest request = new FindProductsRequest(null, null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Page size", "Must be bigger than zero."));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        FindProductsResponse response = service.execute(request);
        assertTrue (response.hasErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("Page size", response.getErrors().get(0).getField());
        assertEquals("Must be bigger than zero.", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnListWithTwoUnorderedProducts () {
        FindProductsRequest request = new FindProductsRequest("A", "");
        Mockito.when(database.findByProductName("A")).thenReturn(products);
        FindProductsResponse response = service.execute(request);
        assertEquals(2, response.getProducts().size());
        assertEquals(product1, response.getProducts().get(0));
        assertEquals(product2, response.getProducts().get(1));
    }

    @Test
    public void shouldReturnByDescriptionOrderedAscending () {
        Ordering ordering = new Ordering("Description", "Ascending");
        FindProductsRequest request = new FindProductsRequest("A", "", ordering);
        Mockito.when(database.findByProductName(any())).thenReturn(products);
        FindProductsResponse response = service.execute(request);
        expected.add(product1);
        expected.add(product2);
        assertEquals(expected.get(0), response.getProducts().get(0));
        assertEquals(expected.get(1), response.getProducts().get(1));
    }

    @Test
    public void shouldReturnByDescriptionOrderedDescending () {
        Ordering ordering = new Ordering("Description", "Descending");
        FindProductsRequest request = new FindProductsRequest("A", "", ordering);
        Mockito.when(database.findByProductName(any())).thenReturn(products);
        FindProductsResponse response = service.execute(request);
        expected.add(product2);
        expected.add(product1);
        assertEquals(expected.get(0), response.getProducts().get(0));
        assertEquals(expected.get(1), response.getProducts().get(1));
    }

    @Test
    public void shouldReturnByNameOrderedAscending () {
        Ordering ordering = new Ordering("Name", "Ascending");
        FindProductsRequest request = new FindProductsRequest(null, "B", ordering);
        Mockito.when(database.findByProductDescription(any())).thenReturn(products);
        FindProductsResponse response = service.execute(request);
        expected.add(product1);
        expected.add(product2);
        assertEquals(expected.get(0), response.getProducts().get(0));
        assertEquals(expected.get(1), response.getProducts().get(1));
    }

    @Test
    public void shouldReturnByNameOrderedDescending () {
        Ordering ordering = new Ordering("Name", "Descending");
        FindProductsRequest request = new FindProductsRequest(null, "B", ordering);
        Mockito.when(database.findByProductDescription(any())).thenReturn(products);
        FindProductsResponse response = service.execute(request);
        expected.add(product2);
        expected.add(product1);
        assertEquals(expected.get(0), response.getProducts().get(0));
        assertEquals(expected.get(1), response.getProducts().get(1));
    }

    @Test
    public void shouldReturnAscendingNameAndDescription () {
        Ordering ordering = new Ordering("Name", "Ascending");
        FindProductsRequest request = new FindProductsRequest("A", "B", ordering);
        Mockito.when(database.findByNameAndDescription(request.getName(),
                request.getDescription())).thenReturn(products);
        FindProductsResponse response = service.execute(request);
        expected.add(product1);
        expected.add(product2);
        assertEquals(expected.get(0), response.getProducts().get(0));
        assertEquals(expected.get(1), response.getProducts().get(1));
    }

    @Test
    public void shouldReturnDescendingNameAndDescription () {
        Ordering ordering = new Ordering("Name", "Descending");
        FindProductsRequest request = new FindProductsRequest("A", "B", ordering);
        Mockito.when(database.findByNameAndDescription(request.getName(),
                request.getDescription())).thenReturn(products);
        FindProductsResponse response = service.execute(request);
        expected.add(product2);
        expected.add(product1);
        assertEquals(expected.get(0), response.getProducts().get(0));
        assertEquals(expected.get(1), response.getProducts().get(1));
    }

    @Test
    public void shouldReturnPageOneWithOneProduct () {
        Paging paging = new Paging(1, 1);
        FindProductsRequest request = new FindProductsRequest(null, "B", paging);
        Mockito.when(database.findByProductDescription(any())).thenReturn(products);
        FindProductsResponse response = service.execute(request);
        expected.add(product1);
        assertEquals(expected.size(), response.getProducts().size());
    }

    @Test
    public void shouldReturnPageOneWithTwoProducts () {
        Paging paging = new Paging(1, 2);
        FindProductsRequest request = new FindProductsRequest(null, "B", paging);
        Mockito.when(database.findByProductDescription(any())).thenReturn(products);
        FindProductsResponse response = service.execute(request);
        expected.add(product1);
        expected.add(product2);
        assertEquals(expected.size(), response.getProducts().size());
    }

    @Test
    public void shouldReturnPageTwoWithZeroProducts () {
        Paging paging = new Paging(2, 2);
        FindProductsRequest request = new FindProductsRequest(null, "B", paging);
        Mockito.when(database.findByProductDescription(any())).thenReturn(products);
        FindProductsResponse response = service.execute(request);
        assertEquals(expected.size(), response.getProducts().size());
    }

    @Test
    public void shouldApplyOrderingAndPaging () {
        Ordering ordering = new Ordering("Name", "Descending");
        Paging paging = new Paging(1, 1);
        FindProductsRequest request = new FindProductsRequest(null, "B", ordering, paging);
        Mockito.when(database.findByProductDescription(any())).thenReturn(products);
        FindProductsResponse response = service.execute(request);
        expected.add(product2);
        assertEquals(expected.size(), response.getProducts().size());
    }

    @Test
    public void shouldSearchWhenNameIsProvided() {
        FindProductsRequest request = new FindProductsRequest("A1", "");
        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(database.findByProductName("A1")).thenReturn(productList);

        FindProductsResponse response = service.execute(request);
        assertEquals(1, response.getProducts().size());
        assertEquals("A1", response.getProducts().get(0).getName());
        assertEquals("B1", response.getProducts().get(0).getDescription());
    }

    @Test
    public void shouldSearchWhenDescriptionIsProvided() {
        FindProductsRequest request = new FindProductsRequest("", "B1");
        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(database.findByProductDescription("B1")).thenReturn(productList);

        FindProductsResponse response = service.execute(request);
        assertEquals(1, response.getProducts().size());
        assertEquals("A1", response.getProducts().get(0).getName());
        assertEquals("B1", response.getProducts().get(0).getDescription());
    }

    @Test
    public void shouldSearchWhenBothNameAndDescriptionAreProvided() {
        FindProductsRequest request = new FindProductsRequest("A1", "B1");
        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(database.findByNameAndDescription("A1", "B1")).thenReturn(productList);

        FindProductsResponse response = service.execute(request);
        assertEquals(1, response.getProducts().size());
        assertEquals("A1", response.getProducts().get(0).getName());
        assertEquals("B1", response.getProducts().get(0).getDescription());
    }

}