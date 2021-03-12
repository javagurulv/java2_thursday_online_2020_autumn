package java2.application_target_list.core.acceptancetests.board;

import java2.application_target_list.TargetListApplication;
import java2.application_target_list.core.acceptancetests.DatabaseCleaner;
import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.database.jpa.JpaUserRepository;
import java2.application_target_list.core.requests.board.AddRecordRequest;
import java2.application_target_list.core.requests.board.DeleteRecordRequest;
import java2.application_target_list.core.requests.board.GetAllRecordsRequest;
import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.requests.user.AddUserRequest;
import java2.application_target_list.core.responses.board.AddRecordResponse;
import java2.application_target_list.core.responses.board.DeleteRecordResponse;
import java2.application_target_list.core.responses.board.GetAllRecordsResponse;
import java2.application_target_list.core.responses.target.AddTargetResponse;
import java2.application_target_list.core.responses.user.AddUserResponse;
import java2.application_target_list.core.services.board.AddRecordService;
import java2.application_target_list.core.services.board.DeleteRecordService;
import java2.application_target_list.core.services.board.GetAllRecordsService;
import java2.application_target_list.core.services.target.AddTargetService;
import java2.application_target_list.core.services.user.AddUserService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TargetListApplication.class)
public class DeleteRecordAcceptanceTest {

    @Autowired
    private DeleteRecordService deleteRecordService;
    @Autowired
    private GetAllRecordsService getAllRecordsService;
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

    private Long firstTargetId;
    private Long secondTargetId;
    private Long userId;
    private Long firstRecordId;
    private Long secondRecordId;

    @Before
    public void setup(){
        databaseCleaner.clean();
        addUsersToDB();
        addTargetsToDB();
        addRecordsToDB();
    }

    @Test
    public void shouldDeleteRecordFromList_v1() {
        DeleteRecordRequest deleteRecordRequest = new DeleteRecordRequest(firstRecordId);
        DeleteRecordResponse deleteRecordResponse = deleteRecordService.execute(deleteRecordRequest);
        GetAllRecordsResponse getAllRecordsResponse = getAllRecordsService.execute(new GetAllRecordsRequest());

        Assert.assertFalse(deleteRecordResponse.hasErrors());
        Assert.assertEquals(getAllRecordsResponse.getRecordList().size(), 1);
        Assert.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getTargetId()), Optional.of(secondTargetId));
        Assert.assertEquals(Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getUserId()), Optional.of(userId));
    }

    @Test
    public void shouldDeleteRecordFromList_v2() {
        DeleteRecordRequest deleteRecordRequest = new DeleteRecordRequest(secondRecordId);
        DeleteRecordResponse deleteRecordResponse = deleteRecordService.execute(deleteRecordRequest);
        GetAllRecordsResponse getAllRecordsResponse = getAllRecordsService.execute(new GetAllRecordsRequest());

        Assert.assertFalse(deleteRecordResponse.hasErrors());
        Assert.assertEquals(getAllRecordsResponse.getRecordList().size(), 1);
        Assert.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getTargetId()), Optional.of(firstTargetId));
        Assert.assertEquals(Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getUserId()), Optional.of(userId));
    }

    @Test
    public void shouldReturnErrorsList() {
        DeleteRecordRequest deleteRecordRequest = new DeleteRecordRequest(-3L);
        DeleteRecordResponse deleteRecordResponse = deleteRecordService.execute(deleteRecordRequest);
        Assert.assertTrue(deleteRecordResponse.hasErrors());
        Assert.assertEquals(deleteRecordResponse.getErrorList().size(), 2);
        Assert.assertEquals(deleteRecordResponse.getErrorList().get(1).getField(), "Record ID");
        Assert.assertEquals(deleteRecordResponse.getErrorList().get(1).getMessage(), "no record with that ID");
        Assert.assertEquals(deleteRecordResponse.getErrorList().get(0).getField(), "Record ID");
        Assert.assertEquals(deleteRecordResponse.getErrorList().get(0).getMessage(), "must not be negative!");

    }

    private void addRecordsToDB() {
        AddRecordRequest addRecordRequest1 = new AddRecordRequest(firstTargetId, userId);
        AddRecordRequest addRecordRequest2 = new AddRecordRequest(secondTargetId, userId);
        AddRecordResponse addRecordResponse1 = addRecordService.execute(addRecordRequest1);
        AddRecordResponse addRecordResponse2 = addRecordService.execute(addRecordRequest2);
        firstRecordId = addRecordResponse1.getNewRecord().getRecordId();
        secondRecordId = addRecordResponse2.getNewRecord().getRecordId();
    }

    private void addUsersToDB() {
        AddUserRequest addUserRequest = new AddUserRequest("name1", "surname1");
        AddUserResponse addUserResponse = addUserService.execute(addUserRequest);
        userId = addUserResponse.getNewUser().getId();
    }

    private void addTargetsToDB() {
        AddTargetRequest addTargetRequest1 = new AddTargetRequest("name", "description", 1L);
        AddTargetRequest addTargetRequest2 = new AddTargetRequest("name2", "description2", 2L);
        AddTargetResponse addTargetResponse1 = addTargetService.execute(addTargetRequest1);
        AddTargetResponse addTargetResponse2 = addTargetService.execute(addTargetRequest2);
        firstTargetId = addTargetResponse1.getNewTarget().getId();
        secondTargetId = addTargetResponse2.getNewTarget().getId();
    }
}
