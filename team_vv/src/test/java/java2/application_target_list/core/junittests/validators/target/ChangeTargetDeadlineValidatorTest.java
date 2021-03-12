package java2.application_target_list.core.junittests.validators.target;

import java2.application_target_list.core.database.target.TargetRepository;
import java2.application_target_list.core.database.target.InMemoryTargetRepositoryImpl;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.requests.target.ChangeTargetDeadlineRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.target.ChangeTargetDeadlineValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class ChangeTargetDeadlineValidatorTest {

    private ChangeTargetDeadlineValidator validator;

    @Before
    public void setup() {
        validator = new ChangeTargetDeadlineValidator();
        TargetRepository targetRepository = new InMemoryTargetRepositoryImpl();
        targetRepository.addTarget(new Target("name", "description", 1L));
    }

    @Test
    public void testValidate_validRequest() {
        ChangeTargetDeadlineRequest request = new ChangeTargetDeadlineRequest(1L,3L);
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_invalidRequestNewDeadline() {
        ChangeTargetDeadlineRequest request = new ChangeTargetDeadlineRequest(1L,-3L);
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Target new deadline"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be negative!"));
    }

    @Test
    public void testValidate_invalidRequestId() {
        ChangeTargetDeadlineRequest request = new ChangeTargetDeadlineRequest(-1L,3L);
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Target ID"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be negative!"));
    }

    @Test
    public void testValidate_invalidRequestIdAndNewDeadline() {
        ChangeTargetDeadlineRequest request = new ChangeTargetDeadlineRequest(-1L,-3L);
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 2);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Target ID"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be negative!"));
        Assert.assertTrue(actualErrors.get(1).getField().contains("Target new deadline"));
        Assert.assertTrue(actualErrors.get(1).getMessage().contains("must not be negative!"));
    }
}