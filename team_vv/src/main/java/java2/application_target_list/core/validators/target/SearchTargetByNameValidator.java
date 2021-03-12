package java2.application_target_list.core.validators.target;

import java2.application_target_list.core.requests.target.SearchTargetByNameRequest;
import java2.application_target_list.core.validators.ErrorCreator;
import org.springframework.stereotype.Component;
import java2.application_target_list.core.responses.CoreError;
import java.util.ArrayList;
import java.util.List;

@Component
public class SearchTargetByNameValidator extends ErrorCreator {

    public List<CoreError> validate(SearchTargetByNameRequest searchTargetByNameRequest){
        List<CoreError> errors = new ArrayList<>();
        checkTargetName(searchTargetByNameRequest, errors);
        checkOrdering(searchTargetByNameRequest, errors);
        checkPaging(searchTargetByNameRequest, errors);
        return errors;
    }

    private void checkOrdering(SearchTargetByNameRequest searchTargetByNameRequest, List<CoreError> errors){
        if (isOrdering(searchTargetByNameRequest)){
            if (isOrderByEmpty(searchTargetByNameRequest)){
                errors.add(createCoreError("Order by", "must not be empty"));
            }
            if (isOrderByIncorrect(searchTargetByNameRequest)){
                errors.add(createCoreError("Order by", "must contain NAME, DESCRIPTION or DEADLINE only!"));
            }
            if (isOrderDirectionEmpty(searchTargetByNameRequest)){
                errors.add(createCoreError("Order direction", "must not be empty"));
            }
            if (isOrderDirectionIncorrect(searchTargetByNameRequest)){
                errors.add(createCoreError("Order direction", "must contain ASCENDING or DESCENDING only!"));
            }
        }
    }

    private void checkPaging(SearchTargetByNameRequest searchTargetByNameRequest, List<CoreError> errors){
        if (isPaging(searchTargetByNameRequest)){
            if (!isPageNumberCorrect(searchTargetByNameRequest)) {
                errors.add(createCoreError("Page number", "must be greater then 0!"));
            }

            if (isPageNumberEmpty(searchTargetByNameRequest)){
                errors.add(createCoreError("Page number", "must not be empty"));
            }

            if (!isPageSizeCorrect(searchTargetByNameRequest)){
                errors.add(createCoreError("Page size", "must be greater then 0!"));
            }

            if (isPageSizeEmpty(searchTargetByNameRequest)){
                errors.add(createCoreError("Page size", "must not be empty"));
            }
        }
    }

    private void checkTargetName(SearchTargetByNameRequest searchTargetByNameRequest, List<CoreError> errors){
        if (isTargetNameEmpty(searchTargetByNameRequest)) {
            errors.add(createCoreError("Target name", "must not be empty!"));
        }
    }

    private boolean isOrderDirectionIncorrect(SearchTargetByNameRequest request){
        return !request.getOrdering().getOrderDirection().equals("ASCENDING") &&
                !request.getOrdering().getOrderDirection().equals("DESCENDING");
    }

    private boolean isOrderDirectionEmpty(SearchTargetByNameRequest request){
        return request.getOrdering().getOrderDirection().isEmpty() || request.getOrdering().getOrderDirection() == null;
    }

    private boolean isOrderByIncorrect(SearchTargetByNameRequest request){
        return !request.getOrdering().getOrderBy().equals("name") && !request.getOrdering().getOrderBy().equals("description") &&
                !request.getOrdering().getOrderBy().equals("deadline");
    }

    private boolean isOrderByEmpty(SearchTargetByNameRequest request){
        return request.getOrdering().getOrderBy().isEmpty() || request.getOrdering().getOrderBy() == null;
    }

    private boolean isPageNumberEmpty(SearchTargetByNameRequest request){
        return request.getPaging().getPageNumber() == null;
    }

    private boolean isPageSizeEmpty(SearchTargetByNameRequest request){
        return request.getPaging().getPageSize() == null;
    }

    private boolean isPageNumberCorrect(SearchTargetByNameRequest request){
        return request.getPaging().getPageNumber() > 0;
    }

    private boolean isPageSizeCorrect(SearchTargetByNameRequest request){
        return request.getPaging().getPageSize() > 0;
    }

    private boolean isPaging(SearchTargetByNameRequest request){
        return request.getPaging() != null;
    }

    private boolean isOrdering(SearchTargetByNameRequest request){
        return request.getOrdering() != null;
    }

    private boolean isTargetNameEmpty(SearchTargetByNameRequest request) {
        return request.getName() == null || request.getName().isEmpty();
    }
}
