package java2.application_target_list.core.acceptancetests.target;

import java2.application_target_list.TargetListApplication;
import java2.application_target_list.core.acceptancetests.DatabaseCleaner;
import java2.application_target_list.core.requests.target.*;
import java2.application_target_list.core.responses.target.*;
import java2.application_target_list.core.services.target.*;
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
public class AcceptanceTests {

    @Autowired
    private ChangeTargetNameService changeTargetNameService;
    @Autowired
    private AddTargetService addTargetService;
    @Autowired
    private ChangeTargetDescriptionService changeTargetDescriptionService;
    @Autowired
    private ChangeTargetDeadlineService changeTargetDeadlineService;
    @Autowired
    private SearchTargetByNameService searchTargetByNameService;
    @Autowired
    private DatabaseCleaner databaseCleaner;
    @Autowired
    private GetAllTargetsService getAllTargetsService;
    @Autowired
    private DeleteTargetService deleteTargetService;
    @Autowired
    private SearchTargetByDescriptionService searchTargetByDescriptionService;

    private Long firstTargetId;
    private Long secondTargetId;
    private Long thirdTargetId;
    private Long fourthTargetId;

    @Before
    public void setup() {
        databaseCleaner.clean();
    }

    @Test
    public void test() {
        addTargetsToDB();
        deleteFirstTargetFromDB();
        changeFourthTargetParameters();
        searchTargetsByName();
        searchTargetsByDescription();
    }

    private void searchTargetsByDescription() {
        SearchTargetByDescriptionRequest searchTargetByDescriptionRequest = new SearchTargetByDescriptionRequest("description");
        SearchTargetByDescriptionResponse searchTargetByDescriptionResponse = searchTargetByDescriptionService.execute(searchTargetByDescriptionRequest);

        Assert.assertFalse(searchTargetByDescriptionResponse.hasErrors());
        Assert.assertEquals(searchTargetByDescriptionResponse.getTargetList().size(), 3);
        Assert.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getName(), "name2");
        Assert.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getDescription(), "description2");
        Assert.assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(0).getDeadline()), Optional.of(4L));
        Assert.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(1).getName(), "name3");
        Assert.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(1).getDescription(), "description3");
        Assert.assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(1).getDeadline()), Optional.of(6L));
        Assert.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(2).getName(), "New name");
        Assert.assertEquals(searchTargetByDescriptionResponse.getTargetList().get(2).getDescription(), "New description");
        Assert.assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(2).getDeadline()), Optional.of(100L));


    }

    private void searchTargetsByName() {
        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("New name");
        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);

        Assert.assertNull(searchTargetByNameResponse.getErrorList());
        Assert.assertEquals(searchTargetByNameResponse.getTargetsList().size(), 1);
        Assert.assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "New name");
        Assert.assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "New description");
        Assert.assertEquals(java.util.Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(100L));
    }


    private void changeFourthTargetParameters() {
        ChangeTargetNameRequest changeTargetNameRequest = new ChangeTargetNameRequest(fourthTargetId, "New name");
        ChangeTargetNameResponse changeTargetNameResponse = changeTargetNameService.execute(changeTargetNameRequest);

        ChangeTargetDeadlineRequest changeTargetDeadlineRequest = new ChangeTargetDeadlineRequest(fourthTargetId, 100L);
        ChangeTargetDeadlineResponse changeTargetDeadlineResponse = changeTargetDeadlineService.execute(changeTargetDeadlineRequest);

        ChangeTargetDescriptionRequest changeTargetDescriptionRequest = new ChangeTargetDescriptionRequest(fourthTargetId, "New description");
        ChangeTargetDescriptionResponse changeTargetDescriptionResponse = changeTargetDescriptionService.execute(changeTargetDescriptionRequest);

        GetAllTargetsResponse getAllTargetsResponse = getAllTargetsService.execute(new GetAllTargetsRequest());

        Assert.assertNull(changeTargetNameResponse.getErrorList());
        Assert.assertNull(changeTargetDeadlineResponse.getErrorList());
        Assert.assertNull(changeTargetDescriptionResponse.getErrorList());
        Assert.assertEquals(getAllTargetsResponse.getTargetList().size(), 3);
        Assert.assertEquals(getAllTargetsResponse.getTargetList().get(2).getName(), "New name");
        Assert.assertEquals(getAllTargetsResponse.getTargetList().get(2).getDescription(), "New description");
        Assert.assertEquals(Optional.ofNullable(getAllTargetsResponse.getTargetList().get(2).getDeadline()), Optional.of(100L));
    }

    private void deleteFirstTargetFromDB(){
        DeleteTargetRequest deleteTargetRequest = new DeleteTargetRequest(firstTargetId);
        DeleteTargetResponse deleteTargetResponse = deleteTargetService.execute(deleteTargetRequest);
        GetAllTargetsResponse getAllTargetsResponse = getAllTargetsService.execute(new GetAllTargetsRequest());

        Assert.assertFalse(deleteTargetResponse.hasErrors());
        Assert.assertEquals(getAllTargetsResponse.getTargetList().size(), 3);
        Assert.assertEquals(getAllTargetsResponse.getTargetList().get(0).getName(), "name2");
        Assert.assertEquals(getAllTargetsResponse.getTargetList().get(0).getDescription(), "description2");
        Assert.assertEquals(Optional.ofNullable(getAllTargetsResponse.getTargetList().get(0).getDeadline()), Optional.of(4L));
        Assert.assertEquals(getAllTargetsResponse.getTargetList().get(1).getName(), "name3");
        Assert.assertEquals(getAllTargetsResponse.getTargetList().get(1).getDescription(), "description3");
        Assert.assertEquals(Optional.ofNullable(getAllTargetsResponse.getTargetList().get(1).getDeadline()), Optional.of(6L));
        Assert.assertEquals(getAllTargetsResponse.getTargetList().get(2).getName(), "wdc");
        Assert.assertEquals(getAllTargetsResponse.getTargetList().get(2).getDescription(), "sda");
        Assert.assertEquals(Optional.ofNullable(getAllTargetsResponse.getTargetList().get(2).getDeadline()), Optional.of(156L));
    }

    private void addTargetsToDB() {
        AddTargetRequest addTargetRequest1 = new AddTargetRequest("name1", "description1", 1L);
        AddTargetRequest addTargetRequest2 = new AddTargetRequest("name2", "description2", 4L);
        AddTargetRequest addTargetRequest3 = new AddTargetRequest("name3", "description3", 6L);
        AddTargetRequest addTargetRequest4 = new AddTargetRequest("wdc", "sda", 156L);

        AddTargetResponse addTargetResponse1 = addTargetService.execute(addTargetRequest1);
        AddTargetResponse addTargetResponse2 = addTargetService.execute(addTargetRequest2);
        AddTargetResponse addTargetResponse3 = addTargetService.execute(addTargetRequest3);
        AddTargetResponse addTargetResponse4 = addTargetService.execute(addTargetRequest4);

        firstTargetId = addTargetResponse1.getNewTarget().getId();
        secondTargetId = addTargetResponse2.getNewTarget().getId();
        thirdTargetId = addTargetResponse3.getNewTarget().getId();
        fourthTargetId = addTargetResponse4.getNewTarget().getId();

        Assert.assertFalse(addTargetResponse1.hasErrors());
        Assert.assertFalse(addTargetResponse2.hasErrors());
        Assert.assertFalse(addTargetResponse3.hasErrors());
        Assert.assertFalse(addTargetResponse4.hasErrors());
    }
}
