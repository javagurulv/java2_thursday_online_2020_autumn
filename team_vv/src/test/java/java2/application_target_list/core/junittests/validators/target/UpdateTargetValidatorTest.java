package java2.application_target_list.core.junittests.validators.target;

import java2.application_target_list.core.database.target.InMemoryTargetRepositoryImpl;
import java2.application_target_list.core.database.target.TargetRepository;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.requests.target.UpdateTargetRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.target.UpdateTargetValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class UpdateTargetValidatorTest {

    private UpdateTargetValidator updateTargetValidator;

    @Before
    public void setup() {
        updateTargetValidator = new UpdateTargetValidator();
        TargetRepository targetRepository = new InMemoryTargetRepositoryImpl();
        targetRepository.addTarget(new Target("name", "description", 123L));
    }

    @Test
    public void testValidate_validRequest() {
        UpdateTargetRequest updateTargetRequest = new UpdateTargetRequest(1L,
                                                                          "new name",
                                                                          "new description",
                                                                          100L);
        List<CoreError> actualErrors = updateTargetValidator.validate(updateTargetRequest);
        Assert.assertTrue(actualErrors.isEmpty());
    }

    @Test
    public void testValidate_invalidRequest_v1() {
        try {
            UpdateTargetRequest updateTargetRequest = new UpdateTargetRequest(null,
                    "new name",
                    "new description",
                    100L);
            List<CoreError> actualErrors = updateTargetValidator.validate(updateTargetRequest);
            Assert.assertFalse(actualErrors.isEmpty());
            Assert.assertEquals(actualErrors.size(), 1);
            Assert.assertEquals(actualErrors.get(0).getField(), "Target ID");
            Assert.assertEquals(actualErrors.get(0).getMessage(), "must not be empty!");
        } catch (NullPointerException ignored) {

        }
    }

    @Test
    public void testValidate_invalidRequest_v2() {
            UpdateTargetRequest updateTargetRequest = new UpdateTargetRequest(-3L,
                    "new name",
                    "new description",
                    100L);
            List<CoreError> actualErrors = updateTargetValidator.validate(updateTargetRequest);
            Assert.assertFalse(actualErrors.isEmpty());
            Assert.assertEquals(actualErrors.size(), 1);
            Assert.assertEquals(actualErrors.get(0).getField(), "Target ID");
            Assert.assertEquals(actualErrors.get(0).getMessage(), "must not be negative!");
    }

    @Test
    public void testValidate_invalidRequest_v3() {
        UpdateTargetRequest updateTargetRequest = new UpdateTargetRequest(3L,
                null,
                "new description",
                100L);
        List<CoreError> actualErrors = updateTargetValidator.validate(updateTargetRequest);
        Assert.assertFalse(actualErrors.isEmpty());
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertEquals(actualErrors.get(0).getField(), "Target new name");
        Assert.assertEquals(actualErrors.get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void testValidate_invalidRequest_v4() {
        UpdateTargetRequest updateTargetRequest = new UpdateTargetRequest(3L,
                "new name",
                "",
                100L);
        List<CoreError> actualErrors = updateTargetValidator.validate(updateTargetRequest);
        Assert.assertFalse(actualErrors.isEmpty());
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertEquals(actualErrors.get(0).getField(), "Target new description");
        Assert.assertEquals(actualErrors.get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void testValidate_invalidRequest_v5() {
        UpdateTargetRequest updateTargetRequest = new UpdateTargetRequest(3L,
                "new name",
                "new description",
                -100L);
        List<CoreError> actualErrors = updateTargetValidator.validate(updateTargetRequest);
        Assert.assertFalse(actualErrors.isEmpty());
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertEquals(actualErrors.get(0).getField(), "Target new deadline");
        Assert.assertEquals(actualErrors.get(0).getMessage(), "must not be negative!");
    }

    @Test
    public void testValidate_invalidRequest_v6() {
        try {
            UpdateTargetRequest updateTargetRequest = new UpdateTargetRequest(1L,
                    "new name",
                    "new description",
                    null);
            List<CoreError> actualErrors = updateTargetValidator.validate(updateTargetRequest);
            Assert.assertFalse(actualErrors.isEmpty());
            Assert.assertEquals(actualErrors.size(), 1);
            Assert.assertEquals(actualErrors.get(0).getField(), "Target new deadline");
            Assert.assertEquals(actualErrors.get(0).getMessage(), "must not be empty!");
        } catch (NullPointerException ignored) {

        }
    }

    @Test
    public void testValidate_invalidRequest_v7() {
            UpdateTargetRequest updateTargetRequest = new UpdateTargetRequest(-3L,
                    "",
                    null,
                    -5L);
            List<CoreError> actualErrors = updateTargetValidator.validate(updateTargetRequest);
            Assert.assertFalse(actualErrors.isEmpty());
            Assert.assertEquals(actualErrors.size(), 4);
            Assert.assertEquals(actualErrors.get(0).getField(), "Target ID");
            Assert.assertEquals(actualErrors.get(0).getMessage(), "must not be negative!");
            Assert.assertEquals(actualErrors.get(1).getField(), "Target new name");
            Assert.assertEquals(actualErrors.get(1).getMessage(), "must not be empty!");
            Assert.assertEquals(actualErrors.get(2).getField(), "Target new description");
            Assert.assertEquals(actualErrors.get(2).getMessage(), "must not be empty!");
            Assert.assertEquals(actualErrors.get(3).getField(), "Target new deadline");
            Assert.assertEquals(actualErrors.get(3).getMessage(), "must not be negative!");

    }
}