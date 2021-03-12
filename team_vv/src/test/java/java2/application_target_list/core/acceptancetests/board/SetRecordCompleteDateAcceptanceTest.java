package java2.application_target_list.core.acceptancetests.board;

import java2.application_target_list.TargetListApplication;
import java2.application_target_list.core.acceptancetests.DatabaseCleaner;
import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.database.jpa.JpaUserRepository;
import java2.application_target_list.core.requests.board.AddRecordRequest;
import java2.application_target_list.core.requests.board.GetAllRecordsRequest;
import java2.application_target_list.core.requests.board.SetRecordCompleteDateRequest;
import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.requests.user.AddUserRequest;
import java2.application_target_list.core.responses.board.AddRecordResponse;
import java2.application_target_list.core.responses.board.GetAllRecordsResponse;
import java2.application_target_list.core.responses.board.SetRecordCompleteDateResponse;
import java2.application_target_list.core.responses.target.AddTargetResponse;
import java2.application_target_list.core.responses.user.AddUserResponse;
import java2.application_target_list.core.services.board.AddRecordService;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TargetListApplication.class)
public class SetRecordCompleteDateAcceptanceTest {

    @Autowired
    private GetAllRecordsService getAllRecordsService;
    @Autowired
    private SetRecordCompleteDateService setRecordCompleteDateService;
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
    @Autowired
    private AddRecordService addRecordService;

    private Long userId;
    private Long targetId;
    private Long recordId;

    @Before
    public void setup() {
        databaseCleaner.clean();
        addTargetsToDB();
        addUsersToDB();
        addRecordsToDB();
    }

    @Test
    public void shouldSetRecordCompleteDate() {
        SetRecordCompleteDateRequest setRecordCompleteDateRequest = new SetRecordCompleteDateRequest(recordId);
        SetRecordCompleteDateResponse setRecordCompleteDateResponse = setRecordCompleteDateService.execute(setRecordCompleteDateRequest);
        GetAllRecordsResponse getAllRecordsResponse = getAllRecordsService.execute(new GetAllRecordsRequest());

        Assert.assertFalse(setRecordCompleteDateResponse.hasErrors());
        Assert.assertNotNull(getAllRecordsResponse.getRecordList().get(0).getDateComplete());
    }

    @Test
    public void shouldReturnErrorList() {
        SetRecordCompleteDateRequest setRecordCompleteDateRequest = new SetRecordCompleteDateRequest(-1L);
        SetRecordCompleteDateResponse setRecordCompleteDateResponse = setRecordCompleteDateService.execute(setRecordCompleteDateRequest);
        GetAllRecordsResponse getAllRecordsResponse = getAllRecordsService.execute(new GetAllRecordsRequest());

        Assert.assertTrue(setRecordCompleteDateResponse.hasErrors());
        Assert.assertNull(getAllRecordsResponse.getRecordList().get(0).getDateComplete());
        Assert.assertEquals(setRecordCompleteDateResponse.getErrorList().size(), 2);
        Assert.assertEquals(setRecordCompleteDateResponse.getErrorList().get(1).getField(), "Record ID");
        Assert.assertEquals(setRecordCompleteDateResponse.getErrorList().get(1).getMessage(), "no record with that ID");
        Assert.assertEquals(setRecordCompleteDateResponse.getErrorList().get(0).getField(), "Record ID");
        Assert.assertEquals(setRecordCompleteDateResponse.getErrorList().get(0).getMessage(), "must not be negative!");
    }

    private void addRecordsToDB() {
        AddRecordRequest addRecordRequest1 = new AddRecordRequest(targetId, userId);
        AddRecordResponse addRecordResponse = addRecordService.execute(addRecordRequest1);
        recordId = addRecordResponse.getNewRecord().getRecordId();
    }

    private void addUsersToDB() {
        AddUserRequest addUserRequest = new AddUserRequest("name1", "surname1");
        AddUserResponse addUserResponse = addUserService.execute(addUserRequest);
        userId = addUserResponse.getNewUser().getId();
    }

    private void addTargetsToDB() {
        AddTargetRequest addTargetRequest = new AddTargetRequest("name", "description", 1L);
        AddTargetResponse addTargetResponse = addTargetService.execute(addTargetRequest);
        targetId = addTargetResponse.getNewTarget().getId();
    }
}
