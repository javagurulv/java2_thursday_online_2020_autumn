package internet_store_tests.acceptance_test.product;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AcceptanceTestChangeProduct2 {
/*
    private ApplicationContext appContext;

    @Before
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(MainMenuConfiguration.class);
    }
    @Test
    public void test() {

        Product mobilePhone = new Product("Mobile phone", "Nokia", 45);

        AddProductRequest addMobilePhoneRequest = new AddProductRequest(mobilePhone);
        addProductService().execute(addMobilePhoneRequest);

        ChangeProductRequest changeProductRequest = new ChangeProductRequest(1L,"",
                "Huawei",20);
        ChangeProductResponse changeProductResponse = changeProductService().execute(changeProductRequest);

        GetProductsRequest getProductsRequest = new GetProductsRequest();
        GetProductsResponse getProductsResponse = getAllProductsService().execute(getProductsRequest);

        assertEquals(getProductsResponse.getProducts().size(), 1);
        assertTrue(getProductsResponse.getProducts().get(0).getDescription().equals("Huawei"));
        assertTrue(!changeProductResponse.hasErrors());
    }

    private AddProductService addProductService() {
        return appContext.getBean(AddProductService.class);
    }

    private ChangeProductService changeProductService() {
        return appContext.getBean(ChangeProductService.class);
    }

    private GetAllProductsService getAllProductsService() {
        return appContext.getBean(GetAllProductsService.class);
    }

 */
}
