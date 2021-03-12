package internet_store_tests.core.services_tests.product;

import internet_store.core.domain.Product;
import internet_store.core.requests.Ordering;
import internet_store.core.requests.Paging;
import internet_store.core.requests.product.SearchProductByOtherRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.product.SearchProductByOtherResponse;
import internet_store.core.services.product.validators.SearchProductRequestValidator;
import internet_store.core.services.product.SearchProductByOtherService;
import internet_store.database.product.ProductDatabase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.Silent.class)

public class SearchProductServiceTest {

    @Mock
    private ProductDatabase productDatabase;
    @Mock
    private SearchProductRequestValidator searchProductRequestValidator;
    @InjectMocks
    SearchProductByOtherService searchProductService;

    private Ordering ordering = new Ordering("title", "ASC");
    private Paging paging = new Paging(1, 2);

    @Test
    public void notValidSearch() {

        SearchProductByOtherRequest request = new SearchProductByOtherRequest("", "",
                                           null,null,ordering, paging);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("search", "Not valid input for search"));

        Mockito.when(searchProductRequestValidator.validate(request)).thenReturn(errors);

        SearchProductByOtherResponse response = searchProductService.execute(request);
        assertEquals(response.hasErrors(), true);
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "search");
    }
    @Test
    public void databaseDoesNotContainsSuchProductTitleAndDescription() {

        SearchProductByOtherRequest request1 = new SearchProductByOtherRequest("Mobile phone", "Nokia",
                                                          0,0, ordering, paging);
        List<CoreError> errors = new ArrayList<>();
        CoreError expectedError =
                new CoreError("database", "Database doesn't contain product with title: " +
                                                                  "Mobile phone and description: Nokia");
        errors.add(expectedError);

        Mockito.when(searchProductRequestValidator.validate(request1)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.searchAllByTitleAndDescription(request1.getTitle(),
                                     request1.getDescription())).thenReturn(new ArrayList<>());

        SearchProductByOtherResponse response = searchProductService.execute(request1);
        assertEquals(response.hasErrors(), true);
        assertEquals(response.getErrors().size(), 1);
    }

    @Test
    public void databaseDoesNotContainsSuchProductTitleAndDescriptionAndPriceRange() {

        SearchProductByOtherRequest request1 = new SearchProductByOtherRequest("Mobile phone", "Nokia",
                1,3, ordering, paging);
        List<CoreError> errors = new ArrayList<>();
        CoreError expectedError =
                new CoreError("database", "Database doesn't contain product with title: " +
                        "Mobile phone, description: Nokia and price: 3");
        errors.add(expectedError);

        Mockito.when(searchProductRequestValidator.validate(request1)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.searchAllByTitleAndDescriptionAndPriceRange(request1.getTitle(),
                request1.getDescription(),request1.getStartPrice(),request1.getEndPrice())).thenReturn(new ArrayList<>());

        SearchProductByOtherResponse response = searchProductService.execute(request1);
        assertEquals(response.hasErrors(), true);
        assertEquals(response.getErrors().size(), 1);
        assertFalse(productDatabase.containsTitleAndDescription("Mobile phone","Nokia"));
        assertFalse(productDatabase.containsPrice(2));
    }

    @Test
    public void databaseContainsSuchProductTitleAndDescriptionAndPriceRange() {

        Product mobilePhone = new Product("Mobile phone","Nokia",50);
        List<Product> products = new ArrayList<>();
        products.add(mobilePhone);

        SearchProductByOtherRequest request1 = new SearchProductByOtherRequest("Mobile phone", "Nokia",
                1,51, ordering, paging);

        Mockito.when(searchProductRequestValidator.validate(request1)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.searchAllByTitleAndDescriptionAndPriceRange(request1.getTitle(),
                request1.getDescription(),request1.getStartPrice(),request1.getEndPrice())).thenReturn(products);

        SearchProductByOtherResponse response = searchProductService.execute(request1);
        assertTrue(products.size() == 1);
        assertTrue(response.getProducts().contains(mobilePhone));
        assertFalse(response.hasErrors());
    }

    @Test
    public void databaseContainsSuchProductTitleAndDescription() {

        Product mobilePhone = new Product("Mobile phone","Nokia",40);
        List<Product> products = new ArrayList<>();
        products.add(mobilePhone);
        SearchProductByOtherRequest request1 = new SearchProductByOtherRequest("Mobile phone", "Nokia",
                                                          0,0,ordering, paging);

        Mockito.when(searchProductRequestValidator.validate(request1)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.searchAllByTitleAndDescription(request1.getTitle(),
                                     request1.getDescription())).thenReturn(products);

        SearchProductByOtherResponse response = searchProductService.execute(request1);
        assertTrue(response.getProducts().contains(mobilePhone));
        assertFalse(response.hasErrors());
    }

    @Test
    public void databaseContainsSuchProductTitleAndPriceRange() {

        Product mobilePhone = new Product("Mobile phone", "Nokia", 50);
        List<Product> products = new ArrayList<>();
        products.add(mobilePhone);

        SearchProductByOtherRequest request1 = new SearchProductByOtherRequest("Mobile phone", "",
            1, 51, ordering, paging);

        Mockito.when(searchProductRequestValidator.validate(request1)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.searchAllByTitleAndPriceRange(request1.getTitle(), request1.getStartPrice(),
                                                                   request1.getEndPrice())).thenReturn(products);

        SearchProductByOtherResponse response = searchProductService.execute(request1);
        assertTrue(products.size() == 1);
        assertTrue(response.getProducts().contains(mobilePhone));
        assertFalse(response.hasErrors());
    }

    @Test
    public void databaseContainsSuchProductDescriptionAndPriceRange() {

        Product mobilePhone = new Product("Mobile phone", "Nokia", 50);
        List<Product> products = new ArrayList<>();
        products.add(mobilePhone);

        SearchProductByOtherRequest request1 = new SearchProductByOtherRequest("", "Nokia",
                1, 51, ordering, paging);

        Mockito.when(searchProductRequestValidator.validate(request1)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.searchAllByDescriptionAndPriceRange(request1.getDescription(), request1.getStartPrice(),
                request1.getEndPrice())).thenReturn(products);

        SearchProductByOtherResponse response = searchProductService.execute(request1);
        assertTrue(products.size() == 1);
        assertTrue(response.getProducts().contains(mobilePhone));
        assertFalse(response.hasErrors());
    }

    @Test
    public void databaseContainsSuchProductTitle() {

        Product pen = new Product("Pen","Parker",35);
        List<Product> products = new ArrayList<>();
        products.add(pen);
        SearchProductByOtherRequest request = new SearchProductByOtherRequest("Pen", null,
                                             0,0,ordering, paging);

        Mockito.when(searchProductRequestValidator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.searchAllByTitle(request.getTitle())).thenReturn(products);

        SearchProductByOtherResponse response = searchProductService.execute(request);
        assertTrue(products.size() == 1);
        assertTrue(response.getProducts().contains(pen));
        assertFalse(response.hasErrors());
    }

    @Test
    public void databaseContainsSuchProductDescription() {

        Product pen = new Product("Pen","Parker",35);
        List<Product> products = new ArrayList<>();
        products.add(pen);
        SearchProductByOtherRequest request = new SearchProductByOtherRequest(null, "Parker",
                                                0,0,ordering, paging);

        Mockito.when(searchProductRequestValidator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.searchAllByDescription(request.getDescription())).thenReturn(products);

        SearchProductByOtherResponse response = searchProductService.execute(request);
        assertTrue(response.getProducts().contains(pen));
        assertFalse(response.hasErrors());
    }

    @Test
    public void databaseDoesNotContainsSuchProductDescription() {

        SearchProductByOtherRequest request1 = new SearchProductByOtherRequest("", "Nokia",
                0,0,ordering, paging);
        List<CoreError> errors = new ArrayList<>();
        CoreError expectedError = new CoreError("database", "Database doesn't contain products wits description: Nokia");
        errors.add(expectedError);

        Mockito.when(searchProductRequestValidator.validate(request1)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.searchAllByDescription(request1.getDescription())).thenReturn(new ArrayList<>());

        SearchProductByOtherResponse response = searchProductService.execute(request1);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
    }

    @Test
    public void databaseDoesNotContainsSuchProductTitle() {

        SearchProductByOtherRequest request1 = new SearchProductByOtherRequest("Mobile phone", "",
                                                      0,0,ordering, paging);
        List<CoreError> errors = new ArrayList<>();
        CoreError expectedError = new CoreError("database", "Database doesn't contain products wits title: Mobile phone");
        errors.add(expectedError);

        Mockito.when(searchProductRequestValidator.validate(request1)).thenReturn(new ArrayList<>());

        SearchProductByOtherResponse response = searchProductService.execute(request1);
        assertEquals(response.hasErrors(), true);
        assertEquals(response.getErrors().size(), 1);
    }

    @Test
    public void ascendingOrdering() {

        List<Product> products = new ArrayList<>();

        Product mobilePhone1 = new Product("Mobile phone", "SonyEricson",50);
        products.add(mobilePhone1);

        Product mobilePhone2 = new Product("Mobile phone","Sony",400);
        products.add(mobilePhone2);

        SearchProductByOtherRequest searchProductRequest = new SearchProductByOtherRequest("Mobile phone",
                null,0,0, new Ordering("description","ASC"), paging);

        Mockito.when(searchProductRequestValidator.validate(searchProductRequest)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.searchAllByTitle(searchProductRequest.getTitle())).thenReturn(products);

        SearchProductByOtherResponse response  = searchProductService.execute(searchProductRequest);

        assertTrue(!response.hasErrors());
        assertEquals(response.getProducts().get(0).getDescription(),"Sony");
        assertEquals(response.getProducts().get(1).getDescription(),"SonyEricson");
    }

    @Test
    public void descendingOrdering() {

        List<Product> products = new ArrayList<>();

        Product mobilePhone1 = new Product("Mobile phone", "SonyEricson",50);
        products.add(mobilePhone1);

        Product mobilePhone2 = new Product("Mobile phone","Sony",400);
        products.add(mobilePhone2);

        SearchProductByOtherRequest searchProductRequest = new SearchProductByOtherRequest("Mobile phone",
                null,0,0, new Ordering("description","DSC"), paging);

        Mockito.when(searchProductRequestValidator.validate(searchProductRequest)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.searchAllByTitle(searchProductRequest.getTitle())).thenReturn(products);

        SearchProductByOtherResponse response  = searchProductService.execute(searchProductRequest);

        assertTrue(!response.hasErrors());
        assertEquals(response.getProducts().get(0).getDescription(),"SonyEricson");
        assertEquals(response.getProducts().get(1).getDescription(),"Sony");
    }

    @Test
    public void pagingSecondPage() {

        List<Product> products = new ArrayList<>();

        Product mobilePhone1 = new Product("Mobile phone", "SonyEricson",50);
        products.add(mobilePhone1);

        Product mobilePhone2 = new Product("Mobile phone","Sony",400);
        products.add(mobilePhone2);

        SearchProductByOtherRequest searchProductRequest = new SearchProductByOtherRequest("Mobile phone",
            null,0,0, new Ordering("description","ASC"), new Paging(2,1));

        Mockito.when(searchProductRequestValidator.validate(searchProductRequest)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.searchAllByTitle(searchProductRequest.getTitle())).thenReturn(products);

        SearchProductByOtherResponse response  = searchProductService.execute(searchProductRequest);

        assertTrue(!response.hasErrors());
        assertEquals(response.getProducts().size(),1);
        assertTrue(response.getProducts().get(0).getDescription().equals("SonyEricson"));
    }

    @Test
    public void pagingFirstPage() {

        List<Product> products = new ArrayList<>();

        Product mobilePhone1 = new Product("Mobile phone", "SonyEricson",50);
        products.add(mobilePhone1);

        Product mobilePhone2 = new Product("Mobile phone","Sony",400);
        products.add(mobilePhone2);

        SearchProductByOtherRequest searchProductRequest = new SearchProductByOtherRequest("Mobile phone",
                null,0,0, new Ordering("description","ASC"),
                          new Paging(1,2));

        Mockito.when(searchProductRequestValidator.validate(searchProductRequest)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.searchAllByTitle(searchProductRequest.getTitle())).thenReturn(products);

        SearchProductByOtherResponse response  = searchProductService.execute(searchProductRequest);

        assertTrue(!response.hasErrors());
        assertEquals(response.getProducts().size(),2);
        assertTrue(response.getProducts().get(0).getDescription().equals("Sony"));
    }

    @Test
    public void pagingNotFullPage() {

        List<Product> products = new ArrayList<>();

        Product mobilePhone1 = new Product("Mobile phone", "SonyEricson",50);
        products.add(mobilePhone1);

        Product mobilePhone2 = new Product("Mobile phone","Sony",400);
        products.add(mobilePhone2);

        Product mobilePhone3 = new Product("Mobile phone","Good",4000);
        products.add(mobilePhone3);

        SearchProductByOtherRequest searchProductRequest = new SearchProductByOtherRequest("Mobile phone",
                null,0,0, new Ordering("description","ASC"),
                new Paging(2,2));

        Mockito.when(searchProductRequestValidator.validate(searchProductRequest)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.searchAllByTitle(searchProductRequest.getTitle())).thenReturn(products);

        SearchProductByOtherResponse response  = searchProductService.execute(searchProductRequest);

        assertTrue(!response.hasErrors());
        assertEquals(response.getProducts().size(),1);
        assertTrue(response.getProducts().get(0).getDescription().equals("SonyEricson"));
    }

    @Test
    public void pagingEmptyPage() {

        List<Product> products = new ArrayList<>();

        Product mobilePhone1 = new Product("Mobile phone", "SonyEricson",50);
        products.add(mobilePhone1);

        Product mobilePhone2 = new Product("Mobile phone","Sony",400);
        products.add(mobilePhone2);

        Product mobilePhone3 = new Product("Mobile phone","Good",4000);
        products.add(mobilePhone3);

        SearchProductByOtherRequest searchProductRequest = new SearchProductByOtherRequest("Mobile phone",
                null,0,0, new Ordering("description","ASC"),
                new Paging(3,2));

        Mockito.when(searchProductRequestValidator.validate(searchProductRequest)).thenReturn(new ArrayList<>());
        Mockito.when(productDatabase.searchAllByTitle(searchProductRequest.getTitle())).thenReturn(products);

        SearchProductByOtherResponse response  = searchProductService.execute(searchProductRequest);

        assertTrue(!response.hasErrors());
        assertTrue(response.getProducts().isEmpty());
    }

}
