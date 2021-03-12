package java2.application_target_list.core.junittests.validators.user;

import java2.application_target_list.core.database.user.UserRepository;
import java2.application_target_list.core.database.user.InMemoryUserRepositoryImpl;
import java2.application_target_list.core.domain.User;
import java2.application_target_list.core.requests.Ordering;
import java2.application_target_list.core.requests.Paging;
import java2.application_target_list.core.requests.user.SearchUsersByFirstNameRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.user.SearchUserByFirstNameValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class SearchUserByFirstNameValidatorTest {

    private SearchUserByFirstNameValidator searchUserByFirstNameValidator;

    @Before
    public void setup() {
        searchUserByFirstNameValidator = new SearchUserByFirstNameValidator();
        UserRepository userRepository = new InMemoryUserRepositoryImpl();
        userRepository.addUser(new User("name", "surname"));
    }

    @Test
    public void testValidate_validRequest() {
        SearchUsersByFirstNameRequest request = new SearchUsersByFirstNameRequest("name");
        List<CoreError> actualErrors = searchUserByFirstNameValidator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_validRequestWithPaging() {
        SearchUsersByFirstNameRequest request = new SearchUsersByFirstNameRequest("name",
                new Paging(1,1));
        List<CoreError> actualErrors = searchUserByFirstNameValidator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_validRequestWithOrderingASC_v1() {
        SearchUsersByFirstNameRequest request = new SearchUsersByFirstNameRequest("name",
                new Ordering("first name", "ASCENDING"));
        List<CoreError> actualErrors = searchUserByFirstNameValidator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_validRequestWithOrderingASC_v2() {
        SearchUsersByFirstNameRequest request = new SearchUsersByFirstNameRequest("name",
                new Ordering("last name", "ASCENDING"));
        List<CoreError> actualErrors = searchUserByFirstNameValidator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_validRequestWithOrderingDSC_v1() {
        SearchUsersByFirstNameRequest request = new SearchUsersByFirstNameRequest("name",
                new Ordering("last name", "DESCENDING"));
        List<CoreError> actualErrors = searchUserByFirstNameValidator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_validRequestWithOrderingDSC_v2() {
        SearchUsersByFirstNameRequest request = new SearchUsersByFirstNameRequest("name",
                new Ordering("first name", "DESCENDING"));
        List<CoreError> actualErrors = searchUserByFirstNameValidator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_validRequestWithPagingAndOrderingDSC_v1() {
        SearchUsersByFirstNameRequest request = new SearchUsersByFirstNameRequest("name",
                new Paging(1,1),
                new Ordering("first name", "DESCENDING"));
        List<CoreError> actualErrors = searchUserByFirstNameValidator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_validRequestWithPagingAndOrderingDSC_v2() {
        SearchUsersByFirstNameRequest request = new SearchUsersByFirstNameRequest("name",
                new Paging(1,1),
                new Ordering("last name", "DESCENDING"));
        List<CoreError> actualErrors = searchUserByFirstNameValidator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_validRequestWithPagingAndOrderingASC_v1() {
        SearchUsersByFirstNameRequest request = new SearchUsersByFirstNameRequest("name",
                new Paging(1,1),
                new Ordering("last name", "ASCENDING"));
        List<CoreError> actualErrors = searchUserByFirstNameValidator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_validRequestWithPagingAndOrderingASC_v2() {
        SearchUsersByFirstNameRequest request = new SearchUsersByFirstNameRequest("name",
                new Paging(1,1),
                new Ordering("first name", "ASCENDING"));
        List<CoreError> actualErrors = searchUserByFirstNameValidator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_invalidRequestWithPaging_v1() {
        SearchUsersByFirstNameRequest request = new SearchUsersByFirstNameRequest("name",
                new Paging(0,1));
        List<CoreError> actualErrors = searchUserByFirstNameValidator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Page number"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must be greater then 0!"));
    }

    @Test
    public void testValidate_invalidRequestWithPaging_v2() {
        SearchUsersByFirstNameRequest request = new SearchUsersByFirstNameRequest("name",
                new Paging(4,0));
        List<CoreError> actualErrors = searchUserByFirstNameValidator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Page size"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must be greater then 0!"));
    }

    @Test
    public void testValidate_invalidRequestWithPaging_v3() {
        SearchUsersByFirstNameRequest request = new SearchUsersByFirstNameRequest("name",
                new Paging(0,0));
        List<CoreError> actualErrors = searchUserByFirstNameValidator.validate(request);
        Assert.assertEquals(actualErrors.size(), 2);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Page number"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must be greater then 0!"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("Page size"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must be greater then 0!"));
    }

    @Test
    public void testValidate_invalidRequestWithOrdering_v1() {
        SearchUsersByFirstNameRequest request = new SearchUsersByFirstNameRequest("name",
                new Ordering("", "ASCENDING"));
        List<CoreError> actualErrors = searchUserByFirstNameValidator.validate(request);
        Assert.assertEquals(actualErrors.size(), 2);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Order by"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("Order by"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must contain FIRST NAME or LAST NAME only!"));
    }

    @Test
    public void testValidate_invalidRequestWithOrdering_v2() {
        SearchUsersByFirstNameRequest request = new SearchUsersByFirstNameRequest("name",
                new Ordering("name", "ASCENDING"));
        List<CoreError> actualErrors = searchUserByFirstNameValidator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Order by"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must contain FIRST NAME or LAST NAME only!"));
    }

    @Test
    public void testValidate_invalidRequestWithOrdering_v3() {
        SearchUsersByFirstNameRequest request = new SearchUsersByFirstNameRequest("name",
                new Ordering("first name", "ASC"));
        List<CoreError> actualErrors = searchUserByFirstNameValidator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Order direction"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must contain ASCENDING or DESCENDING only!"));
    }

    @Test
    public void testValidate_invalidRequestWithOrdering_v4() {
        SearchUsersByFirstNameRequest request = new SearchUsersByFirstNameRequest("name",
                new Ordering("last name", ""));
        List<CoreError> actualErrors = searchUserByFirstNameValidator.validate(request);
        Assert.assertEquals(actualErrors.size(), 2);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Order direction"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("Order direction"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must contain ASCENDING or DESCENDING only!"));
    }

    @Test
    public void testValidate_invalidRequestWithOrdering_v5() {
        SearchUsersByFirstNameRequest request = new SearchUsersByFirstNameRequest("name",
                new Ordering("", ""));
        List<CoreError> actualErrors = searchUserByFirstNameValidator.validate(request);
        Assert.assertEquals(actualErrors.size(), 4);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Order by"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("Order by"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must contain FIRST NAME or LAST NAME only!"));
        Assert.assertTrue(actualErrors.get(2).getField().contains("Order direction"));
        Assert.assertTrue(actualErrors.get(2).getMessage().contains("must not be empty"));
        Assert.assertTrue(actualErrors.get(3).getField().contains("Order direction"));
        Assert.assertTrue(actualErrors.get(3).getMessage().contains("must contain ASCENDING or DESCENDING only!"));
    }

    @Test
    public void testValidate_invalidRequestWithOrderingAndOrdering() {
        SearchUsersByFirstNameRequest request = new SearchUsersByFirstNameRequest("name",
                new Paging(0,0),
                new Ordering("", ""));
        List<CoreError> actualErrors = searchUserByFirstNameValidator.validate(request);
        Assert.assertEquals(actualErrors.size(), 6);
        Assert.assertTrue(actualErrors.get(4).getField().contains("Page number"));
        Assert.assertTrue(actualErrors.get(4).getMessage().contains("must be greater then 0!"));
        Assert.assertTrue(actualErrors.get(5).getField().contains("Page size"));
        Assert.assertTrue(actualErrors.get(5).getMessage().contains("must be greater then 0!"));
        Assert.assertTrue(actualErrors.get(0).getField().contains("Order by"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("Order by"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must contain FIRST NAME or LAST NAME only!"));
        Assert.assertTrue(actualErrors.get(2).getField().contains("Order direction"));
        Assert.assertTrue(actualErrors.get(2).getMessage().contains("must not be empty"));
        Assert.assertTrue(actualErrors.get(3).getField().contains("Order direction"));
        Assert.assertTrue(actualErrors.get(3).getMessage().contains("must contain ASCENDING or DESCENDING only!"));
    }

    @Test
    public void testValidate_invalidRequest_v1() {
        SearchUsersByFirstNameRequest request = new SearchUsersByFirstNameRequest("");
        List<CoreError> actualErrors = searchUserByFirstNameValidator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("User first name"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
    }

    @Test
    public void testValidate_invalidRequest_v2() {
        SearchUsersByFirstNameRequest request = new SearchUsersByFirstNameRequest(null);
        List<CoreError> actualErrors = searchUserByFirstNameValidator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("User first name"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
    }


}