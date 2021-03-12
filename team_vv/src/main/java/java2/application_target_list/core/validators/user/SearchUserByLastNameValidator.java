package java2.application_target_list.core.validators.user;

import java2.application_target_list.core.requests.user.SearchUsersByLastNameRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.ErrorCreator;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class SearchUserByLastNameValidator extends ErrorCreator {

    public List<CoreError> validate(SearchUsersByLastNameRequest searchUsersByLastNameRequest){
        List<CoreError> errors = new ArrayList<>();
        checkUserLastName(searchUsersByLastNameRequest, errors);
        checkOrdering(searchUsersByLastNameRequest, errors);
        checkPaging(searchUsersByLastNameRequest, errors);
        return errors;
    }

    private void checkUserLastName(SearchUsersByLastNameRequest searchUsersByLastNameRequest, List<CoreError> errors){
        if (isUserLastNameEmpty(searchUsersByLastNameRequest)) {
            errors.add(createCoreError("User last name", "must not be empty!"));
        }
    }

    private void checkOrdering(SearchUsersByLastNameRequest searchUsersByLastNameRequest, List<CoreError> errors){
        if (isOrdering(searchUsersByLastNameRequest)){
            if (isOrderByEmpty(searchUsersByLastNameRequest)){
                errors.add(createCoreError("Order by", "must not be empty"));
            }
            if (isOrderByIncorrect(searchUsersByLastNameRequest)){
                errors.add(createCoreError("Order by", "must contain FIRST NAME or LAST NAME only!"));
            }
            if (isOrderDirectionEmpty(searchUsersByLastNameRequest)){
                errors.add(createCoreError("Order direction", "must not be empty"));
            }
            if (isOrderDirectionIncorrect(searchUsersByLastNameRequest)){
                errors.add(createCoreError("Order direction", "must contain ASCENDING or DESCENDING only!"));
            }
        }
    }

    private void checkPaging(SearchUsersByLastNameRequest searchUsersByLastNameRequest, List<CoreError> errors){
        if (isPaging(searchUsersByLastNameRequest)){
            if (!isPageNumberCorrect(searchUsersByLastNameRequest)) {
                errors.add(createCoreError("Page number", "must be greater then 0!"));
            }

            if (isPageNumberEmpty(searchUsersByLastNameRequest)){
                errors.add(createCoreError("Page number", "must not be empty"));
            }

            if (!isPageSizeCorrect(searchUsersByLastNameRequest)){
                errors.add(createCoreError("Page size", "must be greater then 0!"));
            }

            if (isPageSizeEmpty(searchUsersByLastNameRequest)){
                errors.add(createCoreError("Page size", "must not be empty"));
            }
        }
    }

    private boolean isOrderDirectionIncorrect(SearchUsersByLastNameRequest request){
        return !request.getOrdering().getOrderDirection().equals("ASCENDING") &&
                !request.getOrdering().getOrderDirection().equals("DESCENDING");
    }

    private boolean isOrderDirectionEmpty(SearchUsersByLastNameRequest request){
        return request.getOrdering().getOrderDirection().isEmpty() || request.getOrdering().getOrderDirection() == null;
    }

    private boolean isOrderByIncorrect(SearchUsersByLastNameRequest request){
        return !request.getOrdering().getOrderBy().equals("first name") && !request.getOrdering().getOrderBy().equals("last name");
    }

    private boolean isOrderByEmpty(SearchUsersByLastNameRequest request){
        return request.getOrdering().getOrderBy().isEmpty() || request.getOrdering().getOrderBy() == null;
    }

    private boolean isPageNumberEmpty(SearchUsersByLastNameRequest request){
        return request.getPaging().getPageNumber() == null;
    }

    private boolean isPageSizeEmpty(SearchUsersByLastNameRequest request){
        return request.getPaging().getPageSize() == null;
    }

    private boolean isPageNumberCorrect(SearchUsersByLastNameRequest request){
        return request.getPaging().getPageNumber() > 0;
    }

    private boolean isPageSizeCorrect(SearchUsersByLastNameRequest request){
        return request.getPaging().getPageSize() > 0;
    }

    private boolean isPaging(SearchUsersByLastNameRequest request){
        return request.getPaging() != null;
    }

    private boolean isOrdering(SearchUsersByLastNameRequest request){
        return request.getOrdering() != null;
    }

    private boolean isUserLastNameEmpty(SearchUsersByLastNameRequest request) {
        return request.getLastName() == null || request.getLastName().isEmpty();
    }
}
