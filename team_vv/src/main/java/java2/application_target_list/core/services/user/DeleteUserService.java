package java2.application_target_list.core.services.user;

import java2.application_target_list.core.database.jpa.JpaUserRepository;
import java2.application_target_list.core.requests.user.DeleteUserRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.user.DeleteUserResponse;
import java2.application_target_list.core.validators.ErrorCreator;
import java2.application_target_list.core.validators.user.DeleteUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DeleteUserService extends ErrorCreator {

    @Autowired
    private DeleteUserValidator deleteUserValidator;
    @Autowired
    private JpaUserRepository jpaUserRepository;

    public DeleteUserResponse execute(DeleteUserRequest deleteUserRequest){
        List<CoreError> errors = checkRequestForErrors(deleteUserRequest);
        checkAvailabilityInDB(deleteUserRequest, errors);

        if(requestHaveErrors(errors)){
            return createDeleteUserResponseWithErrors(errors);
        }

        deleteUserFromDB(deleteUserRequest);
        return createDeleteUserResponse(deleteUserRequest);
    }

    private DeleteUserResponse createDeleteUserResponse(DeleteUserRequest deleteUserRequest){
        return new DeleteUserResponse(deleteUserRequest.getUserIdToDelete());
    }

    private void deleteUserFromDB(DeleteUserRequest deleteUserRequest){
        jpaUserRepository.deleteById(deleteUserRequest.getUserIdToDelete());
    }

    private DeleteUserResponse createDeleteUserResponseWithErrors(List<CoreError> errors){
        return new DeleteUserResponse(errors);
    }

    private boolean requestHaveErrors(List<CoreError> errors){
        return !errors.isEmpty();
    }

    private boolean userDoesNotExist(DeleteUserRequest deleteUserRequest) {
        return !jpaUserRepository.existsById(deleteUserRequest.getUserIdToDelete());
    }

    private void checkAvailabilityInDB(DeleteUserRequest deleteUserRequest, List<CoreError> errors){
        if (userDoesNotExist(deleteUserRequest)){
            errors.add(createCoreError("User ID;","no user with that ID"));
        }
    }

    private List<CoreError> checkRequestForErrors(DeleteUserRequest deleteUserRequest){
        return deleteUserValidator.validate(deleteUserRequest);
    }
}
