package java2.application_target_list.core.junittests.services.board;

import java2.application_target_list.core.database.jpa.JpaBoardRepository;
import java2.application_target_list.core.domain.Record;
import java2.application_target_list.core.requests.board.GetAllRecordsRequest;
import java2.application_target_list.core.responses.board.GetAllRecordsResponse;
import java2.application_target_list.core.services.board.GetAllRecordsService;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.Silent.class)
public class GetAllRecordsServiceTest {

    private List<Record> recordList;
    @Mock
    private JpaBoardRepository jpaBoardRepository;
    @InjectMocks
    GetAllRecordsService getAllRecordsService;

    @Before
    public void setup() {
        recordList = new ArrayList<>();
    }

    @Test
    public void shouldReturnDB_v1() {
        recordList.add(new Record(1L, 1L));
        Mockito.when(jpaBoardRepository.findAll()).thenReturn(recordList);
        GetAllRecordsRequest getAllRecordsRequest = new GetAllRecordsRequest();
        GetAllRecordsResponse getAllRecordsResponse = getAllRecordsService.execute(getAllRecordsRequest);
        Assert.assertFalse(getAllRecordsResponse.hasErrors());
        Assert.assertEquals(getAllRecordsResponse.getRecordList().size(), 1);
        Assert.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getTargetId()), Optional.of(1L));
        Assert.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getUserId()), Optional.of(1L));
    }

    @Test
    public void shouldReturnDB_v2() {
        recordList.add(new Record(1L, 1L));
        recordList.add(new Record(2L, 1L));
        Mockito.when(jpaBoardRepository.findAll()).thenReturn(recordList);
        GetAllRecordsRequest getAllRecordsRequest = new GetAllRecordsRequest();
        GetAllRecordsResponse getAllRecordsResponse = getAllRecordsService.execute(getAllRecordsRequest);
        Assert.assertFalse(getAllRecordsResponse.hasErrors());
        Assert.assertEquals(getAllRecordsResponse.getRecordList().size(), 2);
        Assert.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getTargetId()), Optional.of(1L));
        Assert.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getUserId()), Optional.of(1L));
        Assert.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(1).getTargetId()), Optional.of(2L));
        Assert.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(1).getUserId()), Optional.of(1L));
    }

    @Test
    public void shouldReturnDB_v3() {
        recordList.add(new Record(1L, 1L));
        recordList.add(new Record(2L, 1L));
        recordList.add(new Record(3L, 2L));
        Mockito.when(jpaBoardRepository.findAll()).thenReturn(recordList);
        GetAllRecordsRequest getAllRecordsRequest = new GetAllRecordsRequest();
        GetAllRecordsResponse getAllRecordsResponse = getAllRecordsService.execute(getAllRecordsRequest);
        Assert.assertFalse(getAllRecordsResponse.hasErrors());
        Assert.assertEquals(getAllRecordsResponse.getRecordList().size(), 3);
        Assert.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getTargetId()), Optional.of(1L));
        Assert.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getUserId()), Optional.of(1L));
        Assert.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(1).getTargetId()), Optional.of(2L));
        Assert.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(1).getUserId()), Optional.of(1L));
        Assert.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(2).getTargetId()), Optional.of(3L));
        Assert.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(2).getUserId()), Optional.of(2L));
    }
}