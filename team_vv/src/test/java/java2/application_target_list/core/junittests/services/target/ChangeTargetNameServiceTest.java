package java2.application_target_list.core.junittests.services.target;

import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.requests.target.ChangeTargetNameRequest;
import java2.application_target_list.core.responses.target.ChangeTargetNameResponse;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.services.target.ChangeTargetNameService;
import java2.application_target_list.core.validators.target.ChangeTargetNameValidator;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ChangeTargetNameServiceTest {

    private List<CoreError> errors;
    @Mock
    private ChangeTargetNameValidator validator;
    @Mock
    private JpaTargetRepository jpaTargetRepository;
    @InjectMocks
    ChangeTargetNameService service;

    @Before
    public void setup() {
        errors = new ArrayList<>();
    }

    @Test
    public void shouldChangeTargetName() {
        Mockito.when(jpaTargetRepository.existsById(1L)).thenReturn(true);
        Mockito.when(jpaTargetRepository.changeTargetName(1L, "new name")).thenReturn(1);
        ChangeTargetNameRequest request = new ChangeTargetNameRequest(1L, "new name");
        ChangeTargetNameResponse response = service.execute(request);
        Assert.assertFalse(response.hasErrors());
    }

    @Test
    public void invalidChangeTargetNameRequest_v1() {
        ChangeTargetNameRequest request = new ChangeTargetNameRequest(1L, "new name");
        Mockito.when(validator.validate(request)).thenReturn(errors);
        ChangeTargetNameResponse response = service.execute(request);
        Assert.assertTrue(response.hasErrors());
        Assert.assertEquals(response.getErrorList().size(), 1);
        Assert.assertEquals(response.getErrorList().get(0).getField(), "Target ID;");
        Assert.assertEquals(response.getErrorList().get(0).getMessage(), "no target with that ID");
    }

    @Test
    public void invalidChangeTargetNameRequest_v2() {
        ChangeTargetNameRequest request = new ChangeTargetNameRequest(null, "new name");
        errors.add(new CoreError("Target ID;","must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        ChangeTargetNameResponse response = service.execute(request);
        Assert.assertTrue(response.hasErrors());
        Assert.assertEquals(response.getErrorList().size(), 2);
        Assert.assertEquals(response.getErrorList().get(1).getField(), "Target ID;");
        Assert.assertEquals(response.getErrorList().get(1).getMessage(), "no target with that ID");
        Assert.assertEquals(response.getErrorList().get(0).getField(), "Target ID;");
        Assert.assertEquals(response.getErrorList().get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void invalidChangeTargetNameRequest_v3() {
        ChangeTargetNameRequest request = new ChangeTargetNameRequest(-2L, "new name");
        errors.add(new CoreError("Target ID;","must not be negative!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        ChangeTargetNameResponse response = service.execute(request);
        Assert.assertTrue(response.hasErrors());
        Assert.assertEquals(response.getErrorList().size(), 2);
        Assert.assertEquals(response.getErrorList().get(1).getField(), "Target ID;");
        Assert.assertEquals(response.getErrorList().get(1).getMessage(), "no target with that ID");
        Assert.assertEquals(response.getErrorList().get(0).getField(), "Target ID;");
        Assert.assertEquals(response.getErrorList().get(0).getMessage(), "must not be negative!");
    }

    @Test
    public void invalidChangeTargetNameRequest_v4() {
        ChangeTargetNameRequest request = new ChangeTargetNameRequest(-2L, null);
        errors.add(new CoreError("Target ID;","must not be negative!"));
        errors.add(new CoreError("Target new name","must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        ChangeTargetNameResponse response = service.execute(request);
        Assert.assertTrue(response.hasErrors());
        Assert.assertEquals(response.getErrorList().size(), 3);
        Assert.assertEquals(response.getErrorList().get(2).getField(), "Target ID;");
        Assert.assertEquals(response.getErrorList().get(2).getMessage(), "no target with that ID");
        Assert.assertEquals(response.getErrorList().get(0).getField(), "Target ID;");
        Assert.assertEquals(response.getErrorList().get(0).getMessage(), "must not be negative!");
        Assert.assertEquals(response.getErrorList().get(1).getField(), "Target new name");
        Assert.assertEquals(response.getErrorList().get(1).getMessage(), "must not be empty!");
    }

    @Test
    public void invalidChangeTargetNameRequest_v5() {
        ChangeTargetNameRequest request = new ChangeTargetNameRequest(2L, "");
        errors.add(new CoreError("Target new name","must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        ChangeTargetNameResponse response = service.execute(request);
        Assert.assertTrue(response.hasErrors());
        Assert.assertEquals(response.getErrorList().size(), 2);
        Assert.assertEquals(response.getErrorList().get(1).getField(), "Target ID;");
        Assert.assertEquals(response.getErrorList().get(1).getMessage(), "no target with that ID");
        Assert.assertEquals(response.getErrorList().get(0).getField(), "Target new name");
        Assert.assertEquals(response.getErrorList().get(0).getMessage(), "must not be empty!");
    }
}