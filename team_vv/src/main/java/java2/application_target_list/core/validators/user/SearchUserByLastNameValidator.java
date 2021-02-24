package java2.application_target_list.core.validators.user;


import java2.application_target_list.core.requests.user.SearchUsersByLastNameRequest;
import java2.application_target_list.core.responses.CoreError;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class SearchUserByLastNameValidator {

    public List<CoreError> validate(SearchUsersByLastNameRequest request){
        List<CoreError> errors = new ArrayList<>();

        if (isUserLastNameEmpty(request)) {
            errors.add(new CoreError("User last name", "must not be empty!"));
        }

        if (isOrdering(request)){
            if (isOrderByEmpty(request)){
                errors.add(new CoreError("Order by", "must not be empty"));
            }
            if (isOrderByIncorrect(request)){
                errors.add(new CoreError("Order by", "must contain FIRST NAME or LAST NAME only!"));
            }
            if (isOrderDirectionEmpty(request)){
                errors.add(new CoreError("Order direction", "must not be empty"));
            }
            if (isOrderDirectionIncorrect(request)){
                errors.add(new CoreError("Order direction", "must contain ASCENDING or DESCENDING only!"));
            }
        }

        if (isPaging(request)){
            if (!isPageNumberCorrect(request)) {
                errors.add(new CoreError("Page number", "must be greater then 0!"));
            }
            if (isPageNumberEmpty(request)){
                errors.add(new CoreError("Page number", "must not be empty"));
            }
            if (!isPageSizeCorrect(request)){
                errors.add(new CoreError("Page size", "must be greater then 0!"));
            }
            if (isPageSizeEmpty(request)){
                errors.add(new CoreError("Page size", "must not be empty"));
            }
        }
        return errors;
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
