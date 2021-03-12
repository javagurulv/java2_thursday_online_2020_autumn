package dental_clinic.core.validators.patient;

import dental_clinic.core.domain.OrderingDirection;
import dental_clinic.core.requests.Ordering;
import dental_clinic.core.requests.Paging;
import dental_clinic.core.requests.patient.SearchPatientRequest;
import dental_clinic.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class SearchPatientRequestValidator {

    public List<CoreError> validate (SearchPatientRequest searchPatientRequest) {
        List<CoreError> errors = new ArrayList<>();
        Ordering ordering = new Ordering(searchPatientRequest.getOrderBy(), searchPatientRequest.getOrderDirection());
        Paging paging = new Paging(searchPatientRequest.getPageNumber(), searchPatientRequest.getPageSize());

        errors.addAll(searchRequestIsEmptyError(searchPatientRequest));

        if (errors.isEmpty()) {
            errors.addAll(searchRequestIsNotValidErrors(searchPatientRequest));
        }

        if (isNotValidInputForOrdering(searchPatientRequest, ordering)){
            errors.addAll(updateErrorsListForOrdering(ordering));
        }
        if (isNotValidRequestForPaging(paging)){
            errors.addAll(updateErrorsListForPaging(paging));
        }
        return errors;
    }

    private List<CoreError> searchRequestIsEmptyError(SearchPatientRequest searchPatientRequest){
        List <CoreError> errors = new ArrayList<>();
        if (searchPatientRequest.getInputForSearch() == null || searchPatientRequest.getInputForSearch().isEmpty()) {
            errors.add(new CoreError("search", "Search request can't be empty"));
        }
        return  errors;
    }

    private List<CoreError> searchRequestIsNotValidErrors(SearchPatientRequest searchPatientRequest) {
        List<CoreError> errors = new ArrayList<>();
        if (containsOnlyDigits(searchPatientRequest.getInputForSearch())) {
            errors.addAll(personalCodeValidationErrors(searchPatientRequest.getInputForSearch()));
        } else {
            if (!searchPatientRequest.getInputForSearch().matches("[a-zA-ZēūīōāšģķļžčņĒŪĪŌĀŠĢĶĻŽČŅ]+")) {
                errors.add(new CoreError("search", "Search by surname can contain only letters"));
            }
        }
        return errors;
    }

    private boolean containsOnlyDigits(String input){
        String regex = "[0-9]+";

        Pattern p = Pattern.compile(regex);
        if (input == null) {
            return false;
        }

        Matcher m = p.matcher(input);

        return m.matches();
    }

    private List<CoreError> personalCodeValidationErrors(String personalCode) {
        List<CoreError> errors = new ArrayList<>();
        if (!Pattern.matches("[0-9]{2}[0,1][0-9][0-9][0-9]-?[0-9]{5}", personalCode)) {
            errors.add(new CoreError("personal code", "Not valid input for personal code"));
        }
        return errors;

    }

    private boolean isNotValidInputForOrdering(SearchPatientRequest searchPatientRequest, Ordering ordering){
        if (ordering.emptyBothFields()) {
            return false;
        } else if (ordering.filledBoth()) {
            return isNotValidInputForOrderBy(ordering)
                    || isNotValidInputForOrderDirection(ordering);
        } else {
            return true;
        }
    }

    private boolean isNotValidRequestForPaging(Paging paging){
        if (paging.isEmptyBoth()) {
            return false;
        } else if (paging.isFilledBoth()) {
            return (isNotValidInputForPageNumber(paging)) ||
                    isNotValidInputForPageSize(paging);
        } else {
            return true;
        }
    }

    private boolean isNotValidInputForOrderBy(Ordering ordering){
        return !ordering.getOrderBy().equals("name") &&
                !ordering.getOrderBy().equals("surname");
    }

    private boolean isNotValidInputForOrderDirection(Ordering ordering){
        return ordering.getOrderDirection().equals(OrderingDirection.NOT_VALID);
    }

    private boolean isNotValidInputForPageNumber(Paging paging){
        return paging.getPageNumber() <= 0;
    }

    private boolean isNotValidInputForPageSize(Paging paging){
        return paging.getPageSize() <= 0;
    }

    private List<CoreError> updateErrorsListForOrdering(Ordering ordering){

        List<CoreError>errors = new ArrayList<>();

        if (ordering.filledOne()) {
            errors.add(new CoreError("search", "Not valid input for ordering parameters"));
        }else {
            if (isNotValidInputForOrderBy(ordering) && ordering.filledBoth()) {
                errors.add(new CoreError("orderBy", "Not valid input for orderBy"));
            }
            if (isNotValidInputForOrderDirection(ordering) && ordering.filledBoth()) {
                errors.add(new CoreError("orderDirection", "Not valid input for orderDirection"));
            }
        }
        return  errors;
    }

    private List<CoreError> updateErrorsListForPaging(Paging paging){

        List<CoreError>errors = new ArrayList<>();

        if (paging.isFilledOne()) {
            errors.add(new CoreError("search", "Not valid input for paging parameters"));
        }else {
            if (isNotValidInputForPageNumber(paging)) {
                errors.add(new CoreError("pageNumber", "Not valid input for page number"));
            }
            if (isNotValidInputForPageSize(paging)) {
                errors.add(new CoreError("pageSize", "Not valid input for page size"));
            }
        }
        return  errors;
    }

}
