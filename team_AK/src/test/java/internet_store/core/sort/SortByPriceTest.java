package internet_store.core.sort;

import internet_store.core.domain.Product;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SortByPriceTest {

    @Test
    public void test() {
        Product product = new Product();
        Product product1 = new Product();
        Product product2 = new Product();
        product.setPrice(new BigDecimal("28.05"));
        product1.setPrice(new BigDecimal("18.15"));
        product2.setPrice(new BigDecimal("0.15"));

        List<Product> list = new ArrayList<>();
        list.add(product);
        list.add(product1);
        list.add(product2);

        SortByPriceService sortByPrice = new SortByPriceService();

        List<Product> result = sortByPrice.sort(list);
        assertEquals(new BigDecimal("0.15"),result.get(0).getPrice());
        assertEquals(new BigDecimal("18.15"),result.get(1).getPrice());
        assertEquals(new BigDecimal("28.05"),result.get(2).getPrice());
    }
}