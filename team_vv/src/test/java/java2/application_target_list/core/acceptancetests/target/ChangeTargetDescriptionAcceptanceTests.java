package java2.application_target_list.core.acceptancetests.target;

import java2.application_target_list.TargetListApplication;
import java2.application_target_list.core.acceptancetests.DatabaseCleaner;
import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.requests.target.ChangeTargetDescriptionRequest;
import java2.application_target_list.core.requests.target.GetAllTargetsRequest;
import java2.application_target_list.core.responses.target.AddTargetResponse;
import java2.application_target_list.core.responses.target.ChangeTargetDescriptionResponse;
import java2.application_target_list.core.responses.target.GetAllTargetsResponse;
import java2.application_target_list.core.services.target.AddTargetService;
import java2.application_target_list.core.services.target.ChangeTargetDescriptionService;
import java2.application_target_list.core.services.target.GetAllTargetsService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TargetListApplication.class)
public class ChangeTargetDescriptionAcceptanceTests {

    @Autowired
    private ChangeTargetDescriptionService changeTargetDescriptionService;
    @Autowired
    private GetAllTargetsService getAllTargetsService;
    @Autowired
    private DatabaseCleaner databaseCleaner;
    @Autowired
    private AddTargetService addTargetService;

    private Long targetId;

    @Before
    public void setup() {
        databaseCleaner.clean();
        addTargetToDB();
    }

    @Test
    public void shouldChangeTargetDescription() {
        ChangeTargetDescriptionRequest changeTargetNameRequest = new ChangeTargetDescriptionRequest(targetId, "New Description");
        ChangeTargetDescriptionResponse changeTargetDescriptionResponse = changeTargetDescriptionService.execute(changeTargetNameRequest);
        GetAllTargetsResponse getAllTargetsResponse = getAllTargetsService.execute(new GetAllTargetsRequest());

        Assert.assertNull(changeTargetDescriptionResponse.getErrorList());
        Assert.assertEquals(getAllTargetsResponse.getTargetList().size(), 1);
        Assert.assertEquals(getAllTargetsResponse.getTargetList().get(0).getName(), "name");
        Assert.assertEquals(getAllTargetsResponse.getTargetList().get(0).getDescription(), "New Description");
    }

    @Test
    public void shouldReturnErrorList() {
        ChangeTargetDescriptionRequest changeTargetDescriptionRequest = new ChangeTargetDescriptionRequest(targetId, null);
        ChangeTargetDescriptionResponse changeTargetDescriptionResponse = changeTargetDescriptionService.execute(changeTargetDescriptionRequest);
        GetAllTargetsResponse getAllTargetsResponse = getAllTargetsService.execute(new GetAllTargetsRequest());

        Assert.assertTrue(changeTargetDescriptionResponse.hasErrors());
        Assert.assertEquals(changeTargetDescriptionResponse.getErrorList().size(), 1);
        Assert.assertEquals(changeTargetDescriptionResponse.getErrorList().get(0).getField(), "Target new description");
        Assert.assertEquals(changeTargetDescriptionResponse.getErrorList().get(0).getMessage(), "must not be empty!");
        Assert.assertEquals(getAllTargetsResponse.getTargetList().get(0).getName(), "name");
        Assert.assertEquals(Optional.ofNullable(getAllTargetsResponse.getTargetList().get(0).getDeadline()), Optional.of(1L));
        Assert.assertEquals(getAllTargetsResponse.getTargetList().get(0).getDescription(), "description");
    }

    private void addTargetToDB() {
        AddTargetRequest addTargetRequest1 = new AddTargetRequest("name", "description", 1L);
        AddTargetResponse addTargetResponse = addTargetService.execute(addTargetRequest1);
        targetId = addTargetResponse.getNewTarget().getId();
    }
}
