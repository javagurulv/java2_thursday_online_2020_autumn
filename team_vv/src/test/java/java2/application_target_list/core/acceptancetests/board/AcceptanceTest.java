package java2.application_target_list.core.acceptancetests.board;

import java2.application_target_list.TargetListApplication;
import java2.application_target_list.core.acceptancetests.DatabaseCleaner;
import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.database.jpa.JpaUserRepository;
import java2.application_target_list.core.requests.board.AddRecordRequest;
import java2.application_target_list.core.requests.board.DeleteRecordRequest;
import java2.application_target_list.core.requests.board.GetAllRecordsRequest;
import java2.application_target_list.core.requests.board.SetRecordCompleteDateRequest;
import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.requests.user.AddUserRequest;
import java2.application_target_list.core.responses.board.AddRecordResponse;
import java2.application_target_list.core.responses.board.DeleteRecordResponse;
import java2.application_target_list.core.responses.board.GetAllRecordsResponse;
import java2.application_target_list.core.responses.board.SetRecordCompleteDateResponse;
import java2.application_target_list.core.responses.target.AddTargetResponse;
import java2.application_target_list.core.responses.user.AddUserResponse;
import java2.application_target_list.core.services.board.AddRecordService;
import java2.application_target_list.core.services.board.DeleteRecordService;
import java2.application_target_list.core.services.board.GetAllRecordsService;
import java2.application_target_list.core.services.board.SetRecordCompleteDateService;
import java2.application_target_list.core.services.target.AddTargetService;
import java2.application_target_list.core.services.user.AddUserService;
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
public class AcceptanceTest {

    @Autowired
    private GetAllRecordsService getAllRecordsService;
    @Autowired
    private SetRecordCompleteDateService setRecordCompleteDateService;
    @Autowired
    private DeleteRecordService deleteRecordService;
    @Autowired
    private AddRecordService addRecordService;
    @Autowired
    private JpaTargetRepository jpaTargetRepository;
    @Autowired
    private JpaUserRepository jpaUserRepository;
    @Autowired
    private DatabaseCleaner databaseCleaner;
    @Autowired
    private AddTargetService addTargetService;
    @Autowired
    private AddUserService addUserService;

    private Long firstUserId;
    private Long secondUserId;
    private Long thirdUserId;
    private Long firstTargetId;
    private Long secondTargetId;
    private Long thirdTargetId;
    private Long fourthTargetId;
    private Long firstRecordId;
    private Long secondRecordId;
    private Long thirdRecordId;
    private Long fourthRecordId;


    @Before
    public void setup() {
        databaseCleaner.clean();
        addUsersToDB();
        addTargetsToDB();
    }

    @Test
    public void acceptanceTest() {
        addRecordsToDB();
        deleteRecordFromDB();
        setRecordCompleteDate();
        checkChanges();
    }

    private void checkChanges() {
        GetAllRecordsResponse getAllRecordsResponse = getAllRecordsService.execute(new GetAllRecordsRequest());
        Assert.assertFalse(getAllRecordsResponse.hasErrors());

        Assert.assertEquals(getAllRecordsResponse.getRecordList().size(), 3);
        Assert.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getTargetId()), Optional.of(firstTargetId));
        Assert.assertEquals(Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getUserId()), Optional.of(firstUserId));
        Assert.assertNotNull(getAllRecordsResponse.getRecordList().get(0).getDateAdded());
        Assert.assertNotNull(getAllRecordsResponse.getRecordList().get(0).getDateComplete());

        Assert.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(1).getTargetId()), Optional.of(secondTargetId));
        Assert.assertEquals(Optional.ofNullable(getAllRecordsResponse.getRecordList().get(1).getUserId()), Optional.of(secondUserId));
        Assert.assertNotNull(getAllRecordsResponse.getRecordList().get(1).getDateAdded());
        Assert.assertNull(getAllRecordsResponse.getRecordList().get(1).getDateComplete());

        Assert.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(2).getTargetId()), Optional.of(fourthTargetId));
        Assert.assertEquals(Optional.ofNullable(getAllRecordsResponse.getRecordList().get(2).getUserId()), Optional.of(thirdUserId));
        Assert.assertNotNull(getAllRecordsResponse.getRecordList().get(2).getDateAdded());
        Assert.assertNotNull(getAllRecordsResponse.getRecordList().get(2).getDateComplete());
    }

    private void setRecordCompleteDate() {
        SetRecordCompleteDateRequest setRecordCompleteDateRequest1 = new SetRecordCompleteDateRequest(firstRecordId);
        SetRecordCompleteDateResponse setRecordCompleteDateResponse1 = setRecordCompleteDateService.execute(setRecordCompleteDateRequest1);
        Assert.assertFalse(setRecordCompleteDateResponse1.hasErrors());

        SetRecordCompleteDateRequest setRecordCompleteDateRequest2 = new SetRecordCompleteDateRequest(fourthRecordId);
        SetRecordCompleteDateResponse setRecordCompleteDateResponse2 = setRecordCompleteDateService.execute(setRecordCompleteDateRequest2);
        Assert.assertFalse(setRecordCompleteDateResponse2.hasErrors());
    }

    private void deleteRecordFromDB() {
        DeleteRecordRequest deleteRecordRequest = new DeleteRecordRequest(thirdRecordId);
        DeleteRecordResponse deleteRecordResponse = deleteRecordService.execute(deleteRecordRequest);
        GetAllRecordsRequest getAllRecordsRequest = new GetAllRecordsRequest();
        GetAllRecordsResponse getAllRecordsResponse = getAllRecordsService.execute(getAllRecordsRequest);

        Assert.assertFalse(deleteRecordResponse.hasErrors());
        Assert.assertEquals(getAllRecordsResponse.getRecordList().size(), 3);
    }

    private void addRecordsToDB() {
        AddRecordRequest addRecordRequest1 = new AddRecordRequest(firstTargetId, firstUserId);
        AddRecordResponse addRecordResponse1 = addRecordService.execute(addRecordRequest1);
        Assert.assertFalse(addRecordResponse1.hasErrors());
        firstRecordId = addRecordResponse1.getNewRecord().getRecordId();

        AddRecordRequest addRecordRequest2 = new AddRecordRequest(secondTargetId, secondUserId);
        AddRecordResponse addRecordResponse2 = addRecordService.execute(addRecordRequest2);
        Assert.assertFalse(addRecordResponse2.hasErrors());
        secondRecordId = addRecordResponse2.getNewRecord().getRecordId();

        AddRecordRequest addRecordRequest3 = new AddRecordRequest(thirdTargetId, firstUserId);
        AddRecordResponse addRecordResponse3 = addRecordService.execute(addRecordRequest3);
        Assert.assertFalse(addRecordResponse3.hasErrors());
        thirdRecordId = addRecordResponse3.getNewRecord().getRecordId();

        AddRecordRequest addRecordRequest4 = new AddRecordRequest(fourthTargetId, thirdUserId);
        AddRecordResponse addRecordResponse4 = addRecordService.execute(addRecordRequest4);
        Assert.assertFalse(addRecordResponse4.hasErrors());
        fourthRecordId = addRecordResponse4.getNewRecord().getRecordId();
    }

    private void addUsersToDB() {
        AddUserRequest addUserRequest1 = new AddUserRequest("name1", "surname1");
        AddUserRequest addUserRequest2 = new AddUserRequest("name2", "surname2");
        AddUserRequest addUserRequest3 = new AddUserRequest("name3", "surname3");

        AddUserResponse addUserResponse1 = addUserService.execute(addUserRequest1);
        AddUserResponse addUserResponse2 = addUserService.execute(addUserRequest2);
        AddUserResponse addUserResponse3 = addUserService.execute(addUserRequest3);

        firstUserId = addUserResponse1.getNewUser().getId();
        secondUserId = addUserResponse2.getNewUser().getId();
        thirdUserId = addUserResponse3.getNewUser().getId();
    }

    private void addTargetsToDB() {
        AddTargetRequest addTargetRequest1 = new AddTargetRequest("name1", "description1", 1L);
        AddTargetRequest addTargetRequest2 = new AddTargetRequest("name2", "description2", 2L);
        AddTargetRequest addTargetRequest3 = new AddTargetRequest("name3", "description3", 3L);
        AddTargetRequest addTargetRequest4 = new AddTargetRequest("name4", "description4", 4L);

        AddTargetResponse addTargetResponse1 = addTargetService.execute(addTargetRequest1);
        AddTargetResponse addTargetResponse2 = addTargetService.execute(addTargetRequest2);
        AddTargetResponse addTargetResponse3 = addTargetService.execute(addTargetRequest3);
        AddTargetResponse addTargetResponse4 = addTargetService.execute(addTargetRequest4);

        firstTargetId = addTargetResponse1.getNewTarget().getId();
        secondTargetId = addTargetResponse2.getNewTarget().getId();
        thirdTargetId = addTargetResponse3.getNewTarget().getId();
        fourthTargetId = addTargetResponse4.getNewTarget().getId();
    }
}
