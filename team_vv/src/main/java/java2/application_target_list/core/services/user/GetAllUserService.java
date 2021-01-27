package java2.application_target_list.core.services.user;

import java2.application_target_list.core.database.user.UserRepository;
import java2.application_target_list.core.requests.user.GetAllUsersRequest;
import java2.application_target_list.core.responses.user.GetAllUsersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAllUserService {

    @Autowired private UserRepository userRepository;

    public GetAllUsersResponse execute(GetAllUsersRequest request){
        return new GetAllUsersResponse(userRepository.getUsersList());
    }
}
