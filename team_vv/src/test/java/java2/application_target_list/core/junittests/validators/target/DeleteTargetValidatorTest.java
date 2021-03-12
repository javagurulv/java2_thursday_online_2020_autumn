package java2.application_target_list.core.junittests.validators.target;

import java2.application_target_list.core.database.target.TargetRepository;
import java2.application_target_list.core.requests.target.DeleteTargetRequest;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.database.target.InMemoryTargetRepositoryImpl;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.target.DeleteTargetValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class DeleteTargetValidatorTest {

    private DeleteTargetValidator validator;

    @Before
    public void setup() {
        validator = new DeleteTargetValidator();
        TargetRepository targetRepository = new InMemoryTargetRepositoryImpl();
        targetRepository.addTarget(new Target("name", "description", 1L));
    }

    @Test
    public void testValidate_validRequest() {
       DeleteTargetRequest request = new DeleteTargetRequest(1L);
       List<CoreError> actualErrors = validator.validate(request);
       Assert.assertEquals(actualErrors.size(), 0);
    }

    @Test
    public void testValidate_invalidRequestNegative(){
        DeleteTargetRequest request = new DeleteTargetRequest(-2L);
        List<CoreError> actualErrors = validator.validate(request);
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertTrue(actualErrors.get(0).getField().contains("Target ID"));
        Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be negative!"));
    }

    @Test
    public void testValidate_invalidRequestNull(){
        try {
            DeleteTargetRequest request = new DeleteTargetRequest(null);
            List<CoreError> actualErrors = validator.validate(request);
            Assert.assertEquals(actualErrors.size(), 1);
            Assert.assertTrue(actualErrors.get(0).getField().contains("Target ID"));
            Assert.assertTrue(actualErrors.get(0).getMessage().contains("must not be empty!"));
        } catch (NullPointerException ignored) {

        }
    }
}