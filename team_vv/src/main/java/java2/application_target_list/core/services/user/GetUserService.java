package java2.application_target_list.core.services.user;

import java2.application_target_list.core.database.jpa.JpaUserRepository;
import java2.application_target_list.core.requests.user.GetUserRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.user.GetUserResponse;
import java2.application_target_list.core.validators.ErrorCreator;
import java2.application_target_list.core.validators.user.GetUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GetUserService extends ErrorCreator {

    @Autowired
    private GetUserValidator getUserValidator;
    @Autowired
    private JpaUserRepository jpaUserRepository;

    public GetUserResponse execute(GetUserRequest getUserRequest) {
        List<CoreError> errors = checkRequestForErrors(getUserRequest);

        if (requestHaveErrors(errors)) {
            return createGetUserResponseWithErrors(errors);
        }

        return createGetUserResponse(getUserRequest, errors);
    }

    private GetUserResponse createGetUserResponse(GetUserRequest getUserRequest, List<CoreError> errors){
        return jpaUserRepository.findById(getUserRequest.getId())
                .map(GetUserResponse::new).orElseGet(() -> {
                    errors.add(createCoreError("id", "Not found!"));
                    return createGetUserResponseWithErrors(errors);
                });
    }

    private GetUserResponse createGetUserResponseWithErrors(List<CoreError> errors) {
        return new GetUserResponse(errors);
    }

    private boolean requestHaveErrors(List<CoreError> errors){
        return !errors.isEmpty();
    }

    private List<CoreError> checkRequestForErrors(GetUserRequest getUserRequest){
        return getUserValidator.validate(getUserRequest);
    }
}


