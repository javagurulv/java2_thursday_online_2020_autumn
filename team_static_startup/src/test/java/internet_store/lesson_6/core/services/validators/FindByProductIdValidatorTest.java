package internet_store.lesson_6.core.services.validators;


import internet_store.lesson_6.core.requests.FindByIdRequest;
import internet_store.lesson_6.core.responses.CoreError;
import internet_store.lesson_6.core.services.validators.FindByIdValidator;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class FindByProductIdValidatorTest {

    private FindByIdValidator validator;

    @Before

    public void setUp () {
        validator = new FindByIdValidator();
    }

   @Test
    public void shallNotPassWhenIdIsNull() {
        FindByIdRequest request = new FindByIdRequest(null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Product ID", errors.get(0).getField());
        assertEquals("Should not be empty.", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnNoErrorWhenIdIsNotNull() {
        FindByIdRequest request = new FindByIdRequest("1");
        List<CoreError> errors = validator.validate(request);
        assertEquals(0, errors.size());
    }

    @Test
    public void shallNotPassWhenIdIsEmpty() {
        FindByIdRequest request = new FindByIdRequest(" ");
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Product ID", errors.get(0).getField());
        assertEquals("Should not be empty.", errors.get(0).getMessage());
    }

    @Test
    public void shallNotPassWhenIdIsNotNumber() {
        FindByIdRequest request = new FindByIdRequest("MISTAKE");
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Product ID", errors.get(0).getField());
        assertEquals("Should be valid.", errors.get(0).getMessage());
    }

}