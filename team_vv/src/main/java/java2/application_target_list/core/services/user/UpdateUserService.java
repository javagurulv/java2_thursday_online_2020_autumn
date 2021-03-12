package java2.application_target_list.core.services.user;

import java2.application_target_list.core.database.jpa.JpaUserRepository;
import java2.application_target_list.core.requests.user.UpdateUserRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.user.UpdateUserResponse;
import java2.application_target_list.core.validators.ErrorCreator;
import java2.application_target_list.core.validators.user.UpdateUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UpdateUserService extends ErrorCreator {

    @Autowired
    private UpdateUserValidator updateUserValidator;
    @Autowired
    private JpaUserRepository jpaUserRepository;

    public UpdateUserResponse execute(UpdateUserRequest updateUserRequest){
        List<CoreError> errors = checkRequestForErrors(updateUserRequest);
        checkAvailabilityInDB(updateUserRequest, errors);

        if (requestHaveErrors(errors)){
            return createUpdateUserResponseWithErrors(errors);
        }

        updateUser(updateUserRequest);
        return createUpdateUserResponse(updateUserRequest);
    }

    private void updateUserLastName(UpdateUserRequest updateUserRequest){
        jpaUserRepository.changeUserLastName(updateUserRequest.getUserIdToChange(), updateUserRequest.getNewUserLastName());
    }

    private void updateUserFirstName(UpdateUserRequest updateUserRequest){
        jpaUserRepository.changeUserFirstName(updateUserRequest.getUserIdToChange(), updateUserRequest.getNewUserFirstName());
    }

    private void updateUser(UpdateUserRequest updateUserRequest){
        updateUserFirstName(updateUserRequest);
        updateUserLastName(updateUserRequest);
    }

    private UpdateUserResponse createUpdateUserResponse(UpdateUserRequest updateUserRequest){
        return new UpdateUserResponse(updateUserRequest.getUserIdToChange(),
                updateUserRequest.getNewUserFirstName(),
                updateUserRequest.getNewUserLastName());
    }

    private UpdateUserResponse createUpdateUserResponseWithErrors(List<CoreError> errors){
        return new UpdateUserResponse(errors);
    }

    private boolean requestHaveErrors(List<CoreError> errors){
        return !errors.isEmpty();
    }

    private void checkAvailabilityInDB(UpdateUserRequest updateUserRequest, List<CoreError> errors){
        if (userDoesNotExist(updateUserRequest)){
            errors.add(createCoreError("User ID;","no user with that ID"));
        }
    }

    private boolean userDoesNotExist(UpdateUserRequest updateUserRequest){
        return !jpaUserRepository.existsById(updateUserRequest.getUserIdToChange());
    }

    private List<CoreError> checkRequestForErrors(UpdateUserRequest updateUserRequest){
        return updateUserValidator.validate(updateUserRequest);
    }
}
