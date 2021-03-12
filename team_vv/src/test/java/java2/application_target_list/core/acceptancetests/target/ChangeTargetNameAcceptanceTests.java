package java2.application_target_list.core.acceptancetests.target;


import java2.application_target_list.TargetListApplication;
import java2.application_target_list.core.acceptancetests.DatabaseCleaner;
import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.requests.target.ChangeTargetNameRequest;
import java2.application_target_list.core.requests.target.GetAllTargetsRequest;
import java2.application_target_list.core.responses.target.AddTargetResponse;
import java2.application_target_list.core.services.target.ChangeTargetNameService;
import java2.application_target_list.core.responses.target.ChangeTargetNameResponse;
import java2.application_target_list.core.responses.target.GetAllTargetsResponse;
import java2.application_target_list.core.services.target.AddTargetService;
import java2.application_target_list.core.services.target.GetAllTargetsService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TargetListApplication.class)
public class ChangeTargetNameAcceptanceTests {

    @Autowired
    private AddTargetService addTargetService;
    @Autowired
    private ChangeTargetNameService changeTargetNameService;
    @Autowired
    private GetAllTargetsService getAllTargetsService;
    @Autowired
    private DatabaseCleaner databaseCleaner;

    private Long targetId;

    @Before
    public void setup() {
        databaseCleaner.clean();
        addTargetToDB();
    }
    @Test
    public void shouldChangeTargetName() {
        ChangeTargetNameRequest changeTargetNameRequest = new ChangeTargetNameRequest(targetId, "New Name");
        ChangeTargetNameResponse changeTargetNameResponse = changeTargetNameService.execute(changeTargetNameRequest);
        GetAllTargetsResponse getAllTargetsResponse = getAllTargetsService.execute(new GetAllTargetsRequest());

        Assert.assertNull(changeTargetNameResponse.getErrorList());
        Assert.assertEquals(getAllTargetsResponse.getTargetList().size(), 1);
        Assert.assertEquals(getAllTargetsResponse.getTargetList().get(0).getName(), "New Name");
        Assert.assertEquals(getAllTargetsResponse.getTargetList().get(0).getDescription(), "description");
    }

    @Test
    public void shouldReturnErrorList() {
        ChangeTargetNameRequest changeTargetNameRequest = new ChangeTargetNameRequest(21L, "New Name");
        ChangeTargetNameResponse changeTargetNameResponse = changeTargetNameService.execute(changeTargetNameRequest);

        Assert.assertTrue(changeTargetNameResponse.hasErrors());
        Assert.assertEquals(changeTargetNameResponse.getErrorList().size(), 1);
        Assert.assertEquals(changeTargetNameResponse.getErrorList().get(0).getField(), "Target ID;");
        Assert.assertEquals(changeTargetNameResponse.getErrorList().get(0).getMessage(), "no target with that ID");
    }

    private void addTargetToDB() {
        AddTargetRequest addTargetRequest1 = new AddTargetRequest("name", "description", 1L);
        AddTargetResponse addTargetResponse = addTargetService.execute(addTargetRequest1);
        targetId = addTargetResponse.getNewTarget().getId();
    }

}
