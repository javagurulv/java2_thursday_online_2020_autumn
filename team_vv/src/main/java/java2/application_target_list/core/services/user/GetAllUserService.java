package java2.application_target_list.core.services.user;

import java2.application_target_list.core.database.jpa.JpaUserRepository;
import java2.application_target_list.core.domain.User;
import java2.application_target_list.core.requests.user.GetAllUsersRequest;
import java2.application_target_list.core.responses.user.GetAllUsersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GetAllUserService {

    @Autowired
    private JpaUserRepository jpaUserRepository;

    public GetAllUsersResponse execute(GetAllUsersRequest getAllUsersRequest){
        List<User> users = getAllUsersFromDB();
        return createGetAllUsersResponse(users);
    }

    private List<User> getAllUsersFromDB(){
        return jpaUserRepository.findAll();
    }

    private GetAllUsersResponse createGetAllUsersResponse(List<User> users) {
        return new GetAllUsersResponse(users);
    }
}
