package java2.application_target_list.core.validators.target;

import org.springframework.stereotype.Component;
import java2.application_target_list.core.requests.target.SearchTargetByDescriptionRequest;
import java2.application_target_list.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchTargetByDescriptionValidator {

    private List<CoreError> errors;

    public List<CoreError> validate(SearchTargetByDescriptionRequest searchTargetByDescriptionRequest){
        errors = new ArrayList<>();
        checkTargetDescription(searchTargetByDescriptionRequest);
        checkOrdering(searchTargetByDescriptionRequest);
        checkPaging(searchTargetByDescriptionRequest);
        return errors;
    }

    private void checkOrdering(SearchTargetByDescriptionRequest searchTargetByDescriptionRequest){
        if (isOrdering(searchTargetByDescriptionRequest)){
            if (isOrderByEmpty(searchTargetByDescriptionRequest)){
                errors.add(createOrderByIsEmptyError());
            }
            if (isOrderByIncorrect(searchTargetByDescriptionRequest)){
                errors.add(createOrderByIsIncorrectError());
            }
            if (isOrderDirectionEmpty(searchTargetByDescriptionRequest)){
                errors.add(createOrderDirectionIsEmptyError());
            }
            if (isOrderDirectionIncorrect(searchTargetByDescriptionRequest)){
                errors.add(createIncorrectOrderDirectionError());
            }
        }
    }

    private void checkPaging(SearchTargetByDescriptionRequest searchTargetByDescriptionRequest){
        if (isPaging(searchTargetByDescriptionRequest)){
            if (!isPageNumberCorrect(searchTargetByDescriptionRequest)) {
                errors.add(createIncorrectPageNumberError());
            }

            if (isPageNumberEmpty(searchTargetByDescriptionRequest)){
                errors.add(createPageNumberIsEmptyError());
            }

            if (!isPageSizeCorrect(searchTargetByDescriptionRequest)){
                errors.add(createIncorrectPageSizeError());
            }

            if (isPageSizeEmpty(searchTargetByDescriptionRequest)){
                errors.add(createPageSizeIsEmptyError());
            }
        }
    }

    private CoreError createPageSizeIsEmptyError() {
        return new CoreError("Page size", "must not be empty");
    }

    private CoreError createIncorrectPageSizeError(){
        return new CoreError("Page size", "must be greater then 0!");
    }

    private CoreError createPageNumberIsEmptyError(){
        return new CoreError("Page number", "must not be empty");
    }

    private CoreError createIncorrectPageNumberError(){
        return new CoreError("Page number", "must be greater then 0!");
    }

    private CoreError createIncorrectOrderDirectionError(){
        return new CoreError("Order direction", "must contain ASCENDING or DESCENDING only!");
    }

    private CoreError createOrderDirectionIsEmptyError(){
        return new CoreError("Order direction", "must not be empty");
    }

    private CoreError createOrderByIsIncorrectError(){
        return new CoreError("Order by", "must contain NAME, DESCRIPTION or DEADLINE only!");
    }

    private CoreError createOrderByIsEmptyError(){
        return new CoreError("Order by", "must not be empty");
    }

    private CoreError createDescriptionIsEmptyError(){
        return new CoreError("Target description", "must not be empty!");
    }

    private void checkTargetDescription(SearchTargetByDescriptionRequest searchTargetByDescriptionRequest){
        if (isTargetDescriptionEmpty(searchTargetByDescriptionRequest)) {
            errors.add(createDescriptionIsEmptyError());
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
