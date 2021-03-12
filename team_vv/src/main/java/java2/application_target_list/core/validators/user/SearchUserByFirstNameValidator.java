package java2.application_target_list.core.validators.user;

import java2.application_target_list.core.requests.user.SearchUsersByFirstNameRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.ErrorCreator;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class SearchUserByFirstNameValidator extends ErrorCreator {

    public List<CoreError> validate(SearchUsersByFirstNameRequest searchUsersByFirstNameRequest){
        List<CoreError> errors = new ArrayList<>();
        checkUserFirstName(searchUsersByFirstNameRequest, errors);
        checkOrdering(searchUsersByFirstNameRequest, errors);
        checkPaging(searchUsersByFirstNameRequest, errors);
        return errors;
    }

    private void checkUserFirstName(SearchUsersByFirstNameRequest searchUsersByFirstNameRequest, List<CoreError> errors){
        if (isUserFirstNameEmpty(searchUsersByFirstNameRequest)) {
            errors.add(createCoreError("User first name", "must not be empty!"));
        }
    }

    private void checkOrdering(SearchUsersByFirstNameRequest searchUsersByFirstNameRequest, List<CoreError> errors){
        if (isOrdering(searchUsersByFirstNameRequest)){
            if (isOrderByEmpty(searchUsersByFirstNameRequest)){
                errors.add(createCoreError("Order by", "must not be empty"));
            }
            if (isOrderByIncorrect(searchUsersByFirstNameRequest)){
                errors.add(createCoreError("Order by", "must contain FIRST NAME or LAST NAME only!"));
            }
            if (isOrderDirectionEmpty(searchUsersByFirstNameRequest)){
                errors.add(createCoreError("Order direction", "must not be empty"));
            }
            if (isOrderDirectionIncorrect(searchUsersByFirstNameRequest)){
                errors.add(createCoreError("Order direction", "must contain ASCENDING or DESCENDING only!"));
            }
        }
    }

    private void checkPaging(SearchUsersByFirstNameRequest searchUsersByFirstNameRequest, List<CoreError> errors){
        if (isPaging(searchUsersByFirstNameRequest)){
            if (!isPageNumberCorrect(searchUsersByFirstNameRequest)) {
                errors.add(createCoreError("Page number", "must be greater then 0!"));
            }

            if (isPageNumberEmpty(searchUsersByFirstNameRequest)){
                errors.add(createCoreError("Page number", "must not be empty"));
            }

            if (!isPageSizeCorrect(searchUsersByFirstNameRequest)){
                errors.add(createCoreError("Page size", "must be greater then 0!"));
            }

            if (isPageSizeEmpty(searchUsersByFirstNameRequest)){
                errors.add(createCoreError("Page size", "must not be empty"));
            }
        }
    }

    private boolean isOrderDirectionIncorrect(SearchUsersByFirstNameRequest request){
        return !request.getOrdering().getOrderDirection().equals("ASCENDING") &&
                !request.getOrdering().getOrderDirection().equals("DESCENDING");
    }

    private boolean isOrderDirectionEmpty(SearchUsersByFirstNameRequest request){
        return request.getOrdering().getOrderDirection().isEmpty() || request.getOrdering().getOrderDirection() == null;
    }

    private boolean isOrderByIncorrect(SearchUsersByFirstNameRequest request){
        return !request.getOrdering().getOrderBy().equals("first name") && !request.getOrdering().getOrderBy().equals("last name");
    }

    private boolean isOrderByEmpty(SearchUsersByFirstNameRequest request){
        return request.getOrdering().getOrderBy().isEmpty() || request.getOrdering().getOrderBy() == null;
    }

    private boolean isPageNumberEmpty(SearchUsersByFirstNameRequest request){
        return request.getPaging().getPageNumber() == null;
    }

    private boolean isPageSizeEmpty(SearchUsersByFirstNameRequest request){
        return request.getPaging().getPageSize() == null;
    }

    private boolean isPageNumberCorrect(SearchUsersByFirstNameRequest request){
        return request.getPaging().getPageNumber() > 0;
    }

    private boolean isPageSizeCorrect(SearchUsersByFirstNameRequest request){
        return request.getPaging().getPageSize() > 0;
    }

    private boolean isPaging(SearchUsersByFirstNameRequest request){
        return request.getPaging() != null;
    }

    private boolean isOrdering(SearchUsersByFirstNameRequest request){
        return request.getOrdering() != null;
    }

    private boolean isUserFirstNameEmpty(SearchUsersByFirstNameRequest request) {
        return request.getFirstName() == null || request.getFirstName().isEmpty();
    }
}
