package java2.application_target_list.core.services.user;

import java2.application_target_list.core.database.jpa.JpaUserRepository;
import java2.application_target_list.core.requests.user.ChangeUserLastNameRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.user.ChangeUserLastNameResponse;
import java2.application_target_list.core.validators.ErrorCreator;
import java2.application_target_list.core.validators.user.ChangeUserLastNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ChangeUserLastNameService extends ErrorCreator {

    @Autowired
    private ChangeUserLastNameValidator changeUserLastNameValidator;
    @Autowired
    private JpaUserRepository jpaUserRepository;

    public ChangeUserLastNameResponse execute(ChangeUserLastNameRequest changeUserLastNameRequest){
        List<CoreError> errors = checkRequestForErrors(changeUserLastNameRequest);
        checkAvailabilityInDB(changeUserLastNameRequest, errors);

        if (requestHaveErrors(errors)){
            return createChangeUserLastNameResponseWithErrors(errors);
        }

        changeUserLastName(changeUserLastNameRequest);
        return createChangeUserLastNameResponse(changeUserLastNameRequest);
    }

    private ChangeUserLastNameResponse createChangeUserLastNameResponse(ChangeUserLastNameRequest changeUserLastNameRequest){
        return new ChangeUserLastNameResponse(changeUserLastNameRequest.getUserIdToChange(), changeUserLastNameRequest.getNewUserLastName());
    }

    private void changeUserLastName(ChangeUserLastNameRequest changeUserLastNameRequest){
        jpaUserRepository.changeUserLastName(changeUserLastNameRequest.getUserIdToChange(), changeUserLastNameRequest.getNewUserLastName());
    }

    private ChangeUserLastNameResponse createChangeUserLastNameResponseWithErrors(List<CoreError> errors){
        return new ChangeUserLastNameResponse(errors);
    }

    private boolean requestHaveErrors(List<CoreError> errors) {
        return !errors.isEmpty();
    }

    private boolean userDoesNotExist(ChangeUserLastNameRequest changeUserLastNameRequest){
        return !jpaUserRepository.existsById(changeUserLastNameRequest.getUserIdToChange());
    }

    private void checkAvailabilityInDB(ChangeUserLastNameRequest changeUserLastNameRequest, List<CoreError> errors){
        if (userDoesNotExist(changeUserLastNameRequest)){
            errors.add(createCoreError("User ID;","no user with that ID"));
        }
    }

    private List<CoreError> checkRequestForErrors(ChangeUserLastNameRequest changeUserLastNameRequest) {
        return changeUserLastNameValidator.validate(changeUserLastNameRequest);
    }
}
