package java2.application_target_list.core.junittests.validators.target;

import java2.application_target_list.core.database.target.TargetRepository;
import java2.application_target_list.core.database.target.InMemoryTargetRepositoryImpl;
import java2.application_target_list.core.requests.Ordering;
import java2.application_target_list.core.requests.Paging;
import java2.application_target_list.core.requests.target.SearchTargetByDescriptionRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.target.SearchTargetByDescriptionValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class SearchTargetByDescriptionValidatorTest {

    private SearchTargetByDescriptionValidator validator;

    @Before
    public void setup() {
        validator = new SearchTargetByDescriptionValidator();
        TargetRepository targetRepository = new InMemoryTargetRepositoryImpl();
    }

    @Test
    public void testValidate_validRequest_v1() {
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description");
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_validRequest_v2() {
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description",
                                                    new Paging(1,1));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_validRequest_v3() {
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description",
                                                    new Ordering("name", "ASCENDING"));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_validRequest_v4() {
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description",
                                                    new Ordering("name", "DESCENDING"));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_validRequest_v5() {
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description",
                new Ordering("description", "ASCENDING"));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_validRequest_v6() {
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description",
                new Ordering("description", "DESCENDING"));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_validRequest_v7() {
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description",
                new Ordering("deadline", "ASCENDING"));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_validRequest_v8() {
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description",
                new Ordering("deadline", "DESCENDING"));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_invalidRequestWithPaging_v1() {
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description",
                new Paging(0,0));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 2);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Page number"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must be greater then 0!"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("Page size"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must be greater then 0!"));
    }

    @Test
    public void testValidate_invalidRequestWithPaging_v2() {
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description",
                new Paging(3,0));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Page size"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must be greater then 0!"));
    }

    @Test
    public void testValidate_invalidRequestWithPaging_v3() {
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description",
                new Paging(0,1));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Page number"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must be greater then 0!"));
    }

    @Test
    public void testValidate_invalidRequestWithOrdering_v1() {
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description",
                new Ordering("", ""));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 4);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Order by"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("Order by"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must contain NAME, DESCRIPTION or DEADLINE only!"));
        Assert.assertTrue(actualErrors.get(2).getField().contains("Order direction"));
        Assert.assertTrue(actualErrors.get(2).getMessage().contains("must not be empty"));
        Assert.assertTrue(actualErrors.get(3).getField().contains("Order direction"));
        Assert.assertTrue(actualErrors.get(3).getMessage().contains("must contain ASCENDING or DESCENDING only!"));
    }

    @Test
    public void testValidate_invalidRequestWithOrdering_v2() {
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description",
                new Ordering("", "ASCENDING"));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 2);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Order by"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("Order by"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must contain NAME, DESCRIPTION or DEADLINE only!"));
    }

    @Test
    public void testValidate_invalidRequestWithOrdering_v3() {
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description",
                new Ordering("na", "DESCENDING"));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Order by"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must contain NAME, DESCRIPTION or DEADLINE only!"));
    }

    @Test
    public void testValidate_invalidRequestWithOrdering_v4() {
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description",
                new Ordering("name", "DESCEND"));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Order direction"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must contain ASCENDING or DESCENDING only!"));
    }

    @Test
    public void testValidate_invalidRequestWithOrdering_v5() {
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description",
                new Ordering("name", ""));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 2);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Order direction"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("Order direction"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must contain ASCENDING or DESCENDING only!"));
    }

    @Test
    public void testValidate_invalidRequestWithOrderingAndPaging_v1() {
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description",
                new Ordering("", ""),
                new Paging(0,0));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 6);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Order by"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("Order by"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must contain NAME, DESCRIPTION or DEADLINE only!"));
        Assert.assertTrue(actualErrors.get(2).getField().contains("Order direction"));
        Assert.assertTrue(actualErrors.get(2).getMessage().contains("must not be empty"));
        Assert.assertTrue(actualErrors.get(3).getField().contains("Order direction"));
        Assert.assertTrue(actualErrors.get(3).getMessage().contains("must contain ASCENDING or DESCENDING only!"));
        Assert.assertTrue(actualErrors.get(4).getField().contains("Page number"));
        Assert.assertTrue(actualErrors.get(4).getMessage().contains("must be greater then 0!"));
        Assert.assertTrue(actualErrors.get(5).getField().contains("Page size"));
        Assert.assertTrue(actualErrors.get(5).getMessage().contains("must be greater then 0!"));
    }

    @Test
    public void testValidate_invalidRequestWithOrderingAndPaging_v2() {
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description",
                new Ordering("", ""),
                new Paging(0,1));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 5);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Order by"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("Order by"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must contain NAME, DESCRIPTION or DEADLINE only!"));
        Assert.assertTrue(actualErrors.get(2).getField().contains("Order direction"));
        Assert.assertTrue(actualErrors.get(2).getMessage().contains("must not be empty"));
        Assert.assertTrue(actualErrors.get(3).getField().contains("Order direction"));
        Assert.assertTrue(actualErrors.get(3).getMessage().contains("must contain ASCENDING or DESCENDING only!"));
        Assert.assertTrue(actualErrors.get(4).getField().contains("Page number"));
        Assert.assertTrue(actualErrors.get(4).getMessage().contains("must be greater then 0!"));
    }

    @Test
    public void testValidate_invalidRequestWithOrderingAndPaging_v3() {
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description",
                new Ordering("name", ""),
                new Paging(0,1));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 3);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Order direction"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("Order direction"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must contain ASCENDING or DESCENDING only!"));
        Assert.assertTrue(actualErrors.get(2).getField().contains("Page number"));
        Assert.assertTrue(actualErrors.get(2).getMessage().contains("must be greater then 0!"));
    }

    @Test
    public void testValidate_invalidRequestWithOrderingAndPaging_v4() {
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("description",
                new Ordering("name", ""),
                new Paging(1,1));
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 2);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Order direction"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("Order direction"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must contain ASCENDING or DESCENDING only!"));
    }

    @Test
    public void testValidate_invalidRequest_v1() {
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest(null);
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Target description"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
    }

    @Test
    public void testValidate_invalidRequest_v2() {
        SearchTargetByDescriptionRequest request = new SearchTargetByDescriptionRequest("");
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Target description"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
    }
}