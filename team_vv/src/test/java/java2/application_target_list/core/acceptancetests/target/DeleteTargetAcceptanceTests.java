package java2.application_target_list.core.acceptancetests.target;

import java2.application_target_list.TargetListApplication;
import java2.application_target_list.core.acceptancetests.DatabaseCleaner;
import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.requests.target.DeleteTargetRequest;
import java2.application_target_list.core.requests.target.GetAllTargetsRequest;
import java2.application_target_list.core.responses.target.AddTargetResponse;
import java2.application_target_list.core.services.target.DeleteTargetService;
import java2.application_target_list.core.responses.target.DeleteTargetResponse;
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
public class DeleteTargetAcceptanceTests {

    @Autowired
    private GetAllTargetsService getAllTargetsService;
    @Autowired
    private DeleteTargetService deleteTargetService;
    @Autowired
    private DatabaseCleaner databaseCleaner;
    @Autowired
    private AddTargetService addTargetService;

    private Long firstTargetId;
    private Long secondTargetId;

    @Before
    public void setup() {
        databaseCleaner.clean();
        addTargetsToDB();
    }

    @Test
    public void shouldDeleteTargetFromList_v1() {
        DeleteTargetRequest deleteTargetRequest = new DeleteTargetRequest(firstTargetId);
        DeleteTargetResponse deleteTargetResponse = deleteTargetService.execute(deleteTargetRequest);
        GetAllTargetsResponse response = getAllTargetsService.execute(new GetAllTargetsRequest());

        Assert.assertFalse(deleteTargetResponse.hasErrors());
        Assert.assertEquals(response.getTargetList().size(), 1);
        Assert.assertEquals(response.getTargetList().get(0).getName(), "name2");
        Assert.assertEquals(response.getTargetList().get(0).getDescription(), "description2");
    }

    @Test
    public void shouldDeleteTargetFromList_v2() {
        DeleteTargetRequest deleteTargetRequest = new DeleteTargetRequest(secondTargetId);
        DeleteTargetResponse deleteTargetResponse = deleteTargetService.execute(deleteTargetRequest);
        GetAllTargetsResponse response = getAllTargetsService.execute(new GetAllTargetsRequest());

        Assert.assertFalse(deleteTargetResponse.hasErrors());
        Assert.assertEquals(response.getTargetList().size(), 1);
        Assert.assertNull(response.getErrorList());
        Assert.assertEquals(response.getTargetList().get(0).getName(), "name");
        Assert.assertEquals(response.getTargetList().get(0).getDescription(), "description");
    }

    @Test
    public void shouldReturnErrorsList() {
        DeleteTargetRequest deleteTargetRequest = new DeleteTargetRequest(3L);
        DeleteTargetResponse deleteTargetResponse = deleteTargetService.execute(deleteTargetRequest);
        Assert.assertEquals(deleteTargetResponse.getErrorList().size(), 1);
        Assert.assertEquals(deleteTargetResponse.getErrorList().get(0).getField(), "Target ID;");
        Assert.assertEquals(deleteTargetResponse.getErrorList().get(0).getMessage(), "no target with that ID");
    }

    private void addTargetsToDB() {
        AddTargetRequest addTargetRequest1 = new AddTargetRequest("name", "description", 1L);
        AddTargetRequest addTargetRequest2 = new AddTargetRequest("name2", "description2", 4L);
        AddTargetResponse addTargetResponse1 = addTargetService.execute(addTargetRequest1);
        AddTargetResponse addTargetResponse2 = addTargetService.execute(addTargetRequest2);
        firstTargetId = addTargetResponse1.getNewTarget().getId();
        secondTargetId = addTargetResponse2.getNewTarget().getId();
    }
}
