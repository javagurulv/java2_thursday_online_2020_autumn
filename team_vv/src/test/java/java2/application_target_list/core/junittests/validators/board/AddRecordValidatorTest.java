package java2.application_target_list.core.junittests.validators.board;

import java2.application_target_list.core.database.target.InMemoryTargetRepositoryImpl;
import java2.application_target_list.core.database.target.TargetRepository;
import java2.application_target_list.core.database.user.InMemoryUserRepositoryImpl;
import java2.application_target_list.core.database.user.UserRepository;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.domain.User;
import java2.application_target_list.core.requests.board.AddRecordRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.board.AddRecordValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class AddRecordValidatorTest {

    private AddRecordValidator addRecordValidator;

    @Before
    public void setup() {
        addRecordValidator = new AddRecordValidator();
        UserRepository userDatabase = new InMemoryUserRepositoryImpl();
        TargetRepository targetDatabase = new InMemoryTargetRepositoryImpl();
        userDatabase.addUser(new User("name", "surname"));
        userDatabase.getUsersList().get(0).setId(1L);
        targetDatabase.addTarget(new Target("name", "description", 1L));
        targetDatabase.getTargetsList().get(0).setId(1L);
    }

    @Test
    public void testValidate_validRequest() {
        AddRecordRequest addRecordRequest = new AddRecordRequest(1L, 1L);
        List<CoreError> actualErrors = addRecordValidator.validate(addRecordRequest);
        Assert.assertTrue(actualErrors.isEmpty());
    }


    @Test
    public void testValidate_invalidRequest_v3() {
        AddRecordRequest addRecordRequest = new AddRecordRequest(-1L, 1L);
        List<CoreError> actualErrors = addRecordValidator.validate(addRecordRequest);
        Assert.assertFalse(actualErrors.isEmpty());
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertEquals(actualErrors.get(0).getField(), "Target ID");
        Assert.assertEquals(actualErrors.get(0).getMessage(), "must not be negative!");
    }

    @Test
    public void testValidate_invalidRequest_v4() {
        AddRecordRequest addRecordRequest = new AddRecordRequest(1L, -1L);
        List<CoreError> actualErrors = addRecordValidator.validate(addRecordRequest);
        Assert.assertFalse(actualErrors.isEmpty());
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertEquals(actualErrors.get(0).getField(), "User ID");
        Assert.assertEquals(actualErrors.get(0).getMessage(), "must not be negative!");
    }

    @Test
    public void testValidate_invalidRequest_v5() {
        AddRecordRequest addRecordRequest = new AddRecordRequest(-1L, -1L);
        List<CoreError> actualErrors = addRecordValidator.validate(addRecordRequest);
        Assert.assertFalse(actualErrors.isEmpty());
        Assert.assertEquals(actualErrors.size(), 2);
        Assert.assertEquals(actualErrors.get(0).getField(), "Target ID");
        Assert.assertEquals(actualErrors.get(0).getMessage(), "must not be negative!");
        Assert.assertEquals(actualErrors.get(1).getField(), "User ID");
        Assert.assertEquals(actualErrors.get(1).getMessage(), "must not be negative!");
    }

        @Test
    public void testValidate_invalidRequest_v1() {
        try {
            AddRecordRequest addRecordRequest = new AddRecordRequest(null, 1L);
            List<CoreError> actualErrors = addRecordValidator.validate(addRecordRequest);
            Assert.assertFalse(actualErrors.isEmpty());
            Assert.assertEquals(actualErrors.size(), 1);
            Assert.assertEquals(actualErrors.get(0).getField(), "Target ID");
            Assert.assertEquals(actualErrors.get(0).getMessage(), "must not be empty!");
        } catch (NullPointerException ignored) {

        }
    }

    @Test
    public void testValidate_invalidRequest_v2() {
        try {
            AddRecordRequest addRecordRequest = new AddRecordRequest(1L, null);
            List<CoreError> actualErrors = addRecordValidator.validate(addRecordRequest);
            Assert.assertFalse(actualErrors.isEmpty());
            Assert.assertEquals(actualErrors.size(), 1);
            Assert.assertEquals(actualErrors.get(0).getField(), "User ID");
            Assert.assertEquals(actualErrors.get(0).getMessage(), "must not be empty!");
        } catch (NullPointerException ignored) {

        }
    }
}