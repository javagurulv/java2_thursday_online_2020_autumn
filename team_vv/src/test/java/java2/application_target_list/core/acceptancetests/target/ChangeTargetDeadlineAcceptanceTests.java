package java2.application_target_list.core.acceptancetests.target;

import java2.application_target_list.TargetListApplication;
import java2.application_target_list.core.acceptancetests.DatabaseCleaner;
import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.requests.target.ChangeTargetDeadlineRequest;
import java2.application_target_list.core.requests.target.GetAllTargetsRequest;
import java2.application_target_list.core.responses.target.AddTargetResponse;
import java2.application_target_list.core.services.target.ChangeTargetDeadlineService;
import java2.application_target_list.core.responses.target.ChangeTargetDeadlineResponse;
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
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TargetListApplication.class)
public class ChangeTargetDeadlineAcceptanceTests {

    @Autowired
    private GetAllTargetsService getAllTargetsService;
    @Autowired
    private ChangeTargetDeadlineService changeTargetDeadlineService;
    @Autowired
    private AddTargetService addTargetService;
    @Autowired
    private DatabaseCleaner databaseCleaner;

    private Long targetId;

    @Before
    public void setup() {
        databaseCleaner.clean();
        addTargetToDB();
    }

    @Test
    public void shouldChangeTargetDeadline() {
        ChangeTargetDeadlineRequest changeTargetDeadlineRequest = new ChangeTargetDeadlineRequest(targetId, 5L);
        ChangeTargetDeadlineResponse changeTargetDeadlineResponse = changeTargetDeadlineService.execute(changeTargetDeadlineRequest);
        GetAllTargetsResponse getAllTargetsResponse = getAllTargetsService.execute(new GetAllTargetsRequest());

        Assert.assertNull(changeTargetDeadlineResponse.getErrorList());
        Assert.assertEquals(getAllTargetsResponse.getTargetList().size(), 1);
        Assert.assertEquals(getAllTargetsResponse.getTargetList().get(0).getName(), "name");
        Assert.assertEquals(getAllTargetsResponse.getTargetList().get(0).getDescription(), "description");
        Assert.assertEquals(java.util.Optional.ofNullable(getAllTargetsResponse.getTargetList().get(0).getDeadline()), Optional.of(5L));
    }

    @Test
    public void shouldReturnErrorList() {
        ChangeTargetDeadlineRequest changeTargetDeadlineRequest = new ChangeTargetDeadlineRequest(targetId, -5L);
        ChangeTargetDeadlineResponse changeTargetDeadlineResponse = changeTargetDeadlineService.execute(changeTargetDeadlineRequest);
        GetAllTargetsResponse getAllTargetsResponse = getAllTargetsService.execute(new GetAllTargetsRequest());

        Assert.assertTrue(changeTargetDeadlineResponse.hasErrors());
        Assert.assertEquals(changeTargetDeadlineResponse.getErrorList().size(), 1);
        Assert.assertEquals(changeTargetDeadlineResponse.getErrorList().get(0).getField(), "Target new deadline");
        Assert.assertEquals(changeTargetDeadlineResponse.getErrorList().get(0).getMessage(), "must not be negative!");
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
