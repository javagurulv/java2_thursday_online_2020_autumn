package java2.application_target_list.core.acceptancetests.target;

import java2.application_target_list.TargetListApplication;
import java2.application_target_list.core.acceptancetests.DatabaseCleaner;
import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.requests.target.GetAllTargetsRequest;
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
public class GetAllTargetsListAcceptanceTests {

    @Autowired
    private AddTargetService addTargetService;
    @Autowired
    private GetAllTargetsService getAllTargetsService;
    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Before
    public void setup() {
        databaseCleaner.clean();
    }

    @Test
    public void shouldReturnTargetsList() {
        AddTargetRequest request1 = new AddTargetRequest("name", "description", 1L);
        addTargetService.execute(request1);
        GetAllTargetsResponse response = getAllTargetsService.execute(new GetAllTargetsRequest());

        Assert.assertFalse(response.hasErrors());
        Assert.assertEquals(response.getTargetList().size(), 1);
        Assert.assertNull(response.getErrorList());
        Assert.assertEquals(response.getTargetList().get(0).getName(), "name");
        Assert.assertEquals(response.getTargetList().get(0).getDescription(), "description");
    }
}
