package java2.application_target_list.core.services.user;

import java2.application_target_list.core.database.jpa.JpaUserRepository;
import java2.application_target_list.core.requests.user.ChangeUserFirstNameRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.user.ChangeUserFirstNameResponse;
import java2.application_target_list.core.validators.ErrorCreator;
import java2.application_target_list.core.validators.user.ChangeUserFirstNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ChangeUserFirstNameService extends ErrorCreator {

    @Autowired
    private ChangeUserFirstNameValidator changeUserFirstNameValidator;
    @Autowired
    private JpaUserRepository jpaUserRepository;

    public ChangeUserFirstNameResponse execute(ChangeUserFirstNameRequest changeUserFirstNameRequest){
        List<CoreError> errors = checkRequestForErrors(changeUserFirstNameRequest);
        checkAvailabilityInDB(changeUserFirstNameRequest, errors);

        if (requestHaveErrors(errors)){
            return createChangeUserFirstNameResponseWithErrors(errors);
        }

        changeUserFirstName(changeUserFirstNameRequest);
        return createChangeUserFirstNameResponse(changeUserFirstNameRequest);
    }

    private ChangeUserFirstNameResponse createChangeUserFirstNameResponse(ChangeUserFirstNameRequest changeUserFirstNameRequest){
        return new ChangeUserFirstNameResponse(changeUserFirstNameRequest.getUserIdToChange(), changeUserFirstNameRequest.getNewUserFirstName());
    }

    private void changeUserFirstName(ChangeUserFirstNameRequest changeUserFirstNameRequest) {
        jpaUserRepository.changeUserFirstName(changeUserFirstNameRequest.getUserIdToChange(), changeUserFirstNameRequest.getNewUserFirstName());
    }

    private ChangeUserFirstNameResponse createChangeUserFirstNameResponseWithErrors(List<CoreError> errors) {
        return new ChangeUserFirstNameResponse(errors);
    }

    private boolean requestHaveErrors(List<CoreError> errors) {
        return !errors.isEmpty();
    }

    private boolean userDoesNotExists(ChangeUserFirstNameRequest changeUserFirstNameRequest){
        return !jpaUserRepository.existsById(changeUserFirstNameRequest.getUserIdToChange());
    }

    private void checkAvailabilityInDB(ChangeUserFirstNameRequest changeUserFirstNameRequest, List<CoreError> errors){
        if (userDoesNotExists(changeUserFirstNameRequest)){
            errors.add(createCoreError("User ID;","no user with that ID"));
        }
    }

    private List<CoreError> checkRequestForErrors(ChangeUserFirstNameRequest changeUserFirstNameRequest) {
        return changeUserFirstNameValidator.validate(changeUserFirstNameRequest);
    }
}
