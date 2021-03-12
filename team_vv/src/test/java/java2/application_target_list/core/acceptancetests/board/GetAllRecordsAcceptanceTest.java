package java2.application_target_list.core.acceptancetests.board;

import java2.application_target_list.TargetListApplication;
import java2.application_target_list.core.acceptancetests.DatabaseCleaner;
import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.database.jpa.JpaUserRepository;
import java2.application_target_list.core.requests.board.AddRecordRequest;
import java2.application_target_list.core.requests.board.GetAllRecordsRequest;
import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.requests.user.AddUserRequest;
import java2.application_target_list.core.responses.board.GetAllRecordsResponse;
import java2.application_target_list.core.responses.target.AddTargetResponse;
import java2.application_target_list.core.responses.user.AddUserResponse;
import java2.application_target_list.core.services.board.AddRecordService;
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
public class GetAllRecordsAcceptanceTest {

    @Autowired
    private AddRecordService addRecordService;
    @Autowired
    private GetAllRecordsService getAllRecordsService;
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

    private Long targetId;
    private Long userId;

    @Before
    public void setup() {
        databaseCleaner.clean();
        addTargetsToDB();
        addUsersToDB();
    }

    @Test
    public void shouldReturnRecordsList() {
        AddRecordRequest addRecordRequest = new AddRecordRequest(targetId, userId);
        addRecordService.execute(addRecordRequest);
        GetAllRecordsResponse getAllRecordsResponse = getAllRecordsService.execute(new GetAllRecordsRequest());

        Assert.assertFalse(getAllRecordsResponse.hasErrors());
        Assert.assertEquals(getAllRecordsResponse.getRecordList().size(), 1);
        Assert.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getTargetId()), Optional.of(targetId));
        Assert.assertEquals(Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getUserId()), Optional.of(userId));
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
