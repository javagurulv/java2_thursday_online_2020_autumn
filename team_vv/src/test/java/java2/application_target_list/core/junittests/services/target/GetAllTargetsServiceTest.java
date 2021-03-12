package java2.application_target_list.core.junittests.services.target;

import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.requests.target.GetAllTargetsRequest;
import java2.application_target_list.core.responses.target.GetAllTargetsResponse;
import java2.application_target_list.core.services.target.GetAllTargetsService;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

@RunWith(MockitoJUnitRunner.class)
public class GetAllTargetsServiceTest {

    private  List<Target>targets;
    @Mock
    private JpaTargetRepository jpaTargetRepository;
    @InjectMocks
    GetAllTargetsService service;

    @Before
    public void setup() {
        targets = new ArrayList<>();
    }

    @Test
    public void shouldGetTargetsFromDB_v1() {
        targets.add(new Target("name", "description", 1L));
        Mockito.when(jpaTargetRepository.findAll()).thenReturn(targets);
        GetAllTargetsRequest request = new GetAllTargetsRequest();
        GetAllTargetsResponse response = service.execute(request);
        Assert.assertFalse(response.hasErrors());
        Assert.assertEquals(response.getTargetList().size(), 1);
        Assert.assertEquals(response.getTargetList().get(0).getName(), "name");
        Assert.assertEquals(response.getTargetList().get(0).getDescription(), "description");
    }

    @Test
    public void shouldGetTargetsFromDB_v2() {
        targets.add(new Target("name", "description", 1L));
        targets.add(new Target("n", "d", 4L));
        Mockito.when(jpaTargetRepository.findAll()).thenReturn(targets);
        GetAllTargetsRequest request = new GetAllTargetsRequest();
        GetAllTargetsResponse response = service.execute(request);
        Assert.assertFalse(response.hasErrors());
        Assert.assertEquals(response.getTargetList().size(), 2);
        Assert.assertEquals(response.getTargetList().get(0).getName(), "name");
        Assert.assertEquals(response.getTargetList().get(0).getDescription(), "description");
        Assert.assertEquals(response.getTargetList().get(1).getName(), "n");
        Assert.assertEquals(response.getTargetList().get(1).getDescription(), "d");
    }

    @Test
    public void shouldGetTargetsFromDB_v3() {
        Mockito.when(jpaTargetRepository.findAll()).thenReturn(targets);
        GetAllTargetsRequest request = new GetAllTargetsRequest();
        GetAllTargetsResponse response = service.execute(request);
        Assert.assertFalse(response.hasErrors());
        Assert.assertEquals(response.getTargetList().size(), 0);
    }
}