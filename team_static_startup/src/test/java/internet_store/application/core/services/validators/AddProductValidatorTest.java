package internet_store.application.core.services.validators;

import internet_store.application.core.requests.product.AddProductRequest;
import internet_store.application.core.responses.product.CoreError;
import internet_store.application.core.services.product.validators.AddProductValidator;
import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.Assert.*;

public class AddProductValidatorTest {

    private AddProductValidator validator;

    @Before
    public void setUp() {
        validator = new AddProductValidator();
    }

    @Test
    public void shouldNotPassProductName_whenItIsNull() {
        List<CoreError> result = validator.validate(new AddProductRequest(null,
                                                    "description",
                                                    new BigDecimal("10.00")));
        assertEquals(1, result.size());
        assertEquals("Name", result.get(0).getField());
    }

    @Test
    public void shouldNotPassProductName_whenItIsEmpty() {
        List<CoreError> result = validator.validate(new AddProductRequest("",
                "description",
                new BigDecimal("10.00")));
        assertEquals(1, result.size());
        assertEquals("Name", result.get(0).getField());
    }

    @Test
    public void shouldNotPassProductDescription_whenItIsNull() {
        List<CoreError> result = validator.validate(new AddProductRequest("name",
                null,
                new BigDecimal("10.00")));
        assertEquals(1, result.size());
        assertEquals("Description", result.get(0).getField());
    }

    @Test
    public void shouldNotPassProductDescription_whenItIsEmpty() {
        List<CoreError> result = validator.validate(new AddProductRequest("name",
                "",
                new BigDecimal("10.00")));
        assertEquals(1, result.size());
        assertEquals("Description", result.get(0).getField());
    }

    @Test
    public void shouldNotPassProductPrice_whenItIsNull() {
        List<CoreError> result = validator.validate(new AddProductRequest("name",
                "description",
                null));
        assertEquals(1, result.size());
        assertEquals("Price", result.get(0).getField());
    }

    @Test
    public void shouldNotPassProductPrice_whenItIsZero() {
        List<CoreError> result = validator.validate(new AddProductRequest("name",
                "description",
                new BigDecimal("00.00")));
        assertEquals(1, result.size());
        assertEquals("Price", result.get(0).getField());
    }

}