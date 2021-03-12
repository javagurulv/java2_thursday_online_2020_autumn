package java2.application_target_list.core.services.board;

import java2.application_target_list.core.database.jpa.JpaBoardRepository;
import java2.application_target_list.core.requests.board.GetUnfinishedRecordsRequest;
import java2.application_target_list.core.responses.board.GetUnfinishedRecordsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class GetUnfinishedRecordsService {

    @Autowired
    private JpaBoardRepository jpaBoardRepository;

    public GetUnfinishedRecordsResponse execute(GetUnfinishedRecordsRequest getUnfinishedRecordsRequest){
        return createGetUnfinishedRecordsResponse();
    }

    private GetUnfinishedRecordsResponse createGetUnfinishedRecordsResponse() {
        return new GetUnfinishedRecordsResponse(jpaBoardRepository.findUnfinishedRecords());
    }
}
