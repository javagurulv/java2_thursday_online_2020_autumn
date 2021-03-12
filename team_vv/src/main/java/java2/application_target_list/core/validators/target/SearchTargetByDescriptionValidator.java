package java2.application_target_list.core.validators.target;

import java2.application_target_list.core.validators.ErrorCreator;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Component;
import java2.application_target_list.core.requests.target.SearchTargetByDescriptionRequest;
import java2.application_target_list.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchTargetByDescriptionValidator extends ErrorCreator {

    public List<CoreError> validate(SearchTargetByDescriptionRequest searchTargetByDescriptionRequest){
        List<CoreError> errors = new ArrayList<>();
        checkTargetDescription(searchTargetByDescriptionRequest, errors);
        checkOrdering(searchTargetByDescriptionRequest, errors);
        checkPaging(searchTargetByDescriptionRequest, errors);
        return errors;
    }

    private void checkOrdering(SearchTargetByDescriptionRequest searchTargetByDescriptionRequest, List<CoreError> errors){
        if (isOrdering(searchTargetByDescriptionRequest)){
            if (isOrderByEmpty(searchTargetByDescriptionRequest)){
                errors.add(createCoreError("Order by", "must not be empty"));
            }
            if (isOrderByIncorrect(searchTargetByDescriptionRequest)){
                errors.add(createCoreError("Order by", "must contain NAME, DESCRIPTION or DEADLINE only!"));
            }
            if (isOrderDirectionEmpty(searchTargetByDescriptionRequest)){
                errors.add(createCoreError("Order direction", "must not be empty"));
            }
            if (isOrderDirectionIncorrect(searchTargetByDescriptionRequest)){
                errors.add(createCoreError("Order direction", "must contain ASCENDING or DESCENDING only!"));
            }
        }
    }

    private void checkPaging(SearchTargetByDescriptionRequest searchTargetByDescriptionRequest, List<CoreError> errors){
        if (isPaging(searchTargetByDescriptionRequest)){
            if (!isPageNumberCorrect(searchTargetByDescriptionRequest)) {
                errors.add(createCoreError("Page number", "must be greater then 0!"));
            }

            if (isPageNumberEmpty(searchTargetByDescriptionRequest)){
                errors.add(createCoreError("Page number", "must not be empty"));
            }

            if (!isPageSizeCorrect(searchTargetByDescriptionRequest)){
                errors.add(createCoreError("Page size", "must be greater then 0!"));
            }

            if (isPageSizeEmpty(searchTargetByDescriptionRequest)){
                errors.add(createCoreError("Page size", "must not be empty"));
            }
        }
    }

    private void checkTargetDescription(SearchTargetByDescriptionRequest searchTargetByDescriptionRequest, List<CoreError> errors){
        if (isTargetDescriptionEmpty(searchTargetByDescriptionRequest)) {
            errors.add(createCoreError("Target description", "must not be empty!"));
        }
    }

    private boolean isOrdering(SearchTargetByDescriptionRequest request){
        return request.getOrdering() != null;
    }

    private boolean isPaging(SearchTargetByDescriptionRequest request){
        return request.getPaging() != null;
    }

    private boolean isOrderDirectionIncorrect(SearchTargetByDescriptionRequest request){
        return !request.getOrdering().getOrderDirection().equals("ASCENDING") &&
                !request.getOrdering().getOrderDirection().equals("DESCENDING");
    }

    private boolean isOrderDirectionEmpty(SearchTargetByDescriptionRequest request){
        return request.getOrdering().getOrderDirection().isEmpty() || request.getOrdering().getOrderDirection() == null;
    }

    private boolean isOrderByIncorrect(SearchTargetByDescriptionRequest request){
        return !request.getOrdering().getOrderBy().equals("name") && !request.getOrdering().getOrderBy().equals("description") &&
                !request.getOrdering().getOrderBy().equals("deadline");
    }

    private boolean isOrderByEmpty(SearchTargetByDescriptionRequest request){
        return request.getOrdering().getOrderBy().isEmpty() || request.getOrdering().getOrderBy() == null;
    }

    private boolean isPageNumberCorrect(SearchTargetByDescriptionRequest request){
        return request.getPaging().getPageNumber() > 0;
    }

    private boolean isPageSizeCorrect(SearchTargetByDescriptionRequest request){
        return request.getPaging().getPageSize() > 0;
    }

    private boolean isPageSizeEmpty(SearchTargetByDescriptionRequest request){
        return request.getPaging().getPageSize() == null;
    }

    private boolean isPageNumberEmpty(SearchTargetByDescriptionRequest request){
        return request.getPaging().getPageNumber() == null;
    }

    private boolean isTargetDescriptionEmpty(SearchTargetByDescriptionRequest request) {
        return request.getDescription() == null || request.getDescription().isEmpty();
    }
}
