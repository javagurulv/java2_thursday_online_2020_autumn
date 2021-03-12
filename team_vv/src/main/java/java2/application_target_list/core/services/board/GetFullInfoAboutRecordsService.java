package java2.application_target_list.core.services.board;

import java2.application_target_list.core.database.jpa.JpaBoardRepository;
import java2.application_target_list.core.requests.board.GetFullInfoAboutRecordsRequest;
import java2.application_target_list.core.responses.board.GetFullInfoAboutRecordsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class GetFullInfoAboutRecordsService {

    @Autowired
    private JpaBoardRepository jpaBoardRepository;

    public GetFullInfoAboutRecordsResponse execute(GetFullInfoAboutRecordsRequest getFullInfoAboutRecordsRequest){
        return createGetFullInfoAboutRecordsResponse();
    }

    private GetFullInfoAboutRecordsResponse createGetFullInfoAboutRecordsResponse() {
        return new GetFullInfoAboutRecordsResponse(jpaBoardRepository.findAll());
    }
}
