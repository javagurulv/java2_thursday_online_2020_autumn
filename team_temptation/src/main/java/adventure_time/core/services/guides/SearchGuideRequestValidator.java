package adventure_time.core.services.guides;

import adventure_time.core.requests.Ordering;
import adventure_time.core.requests.Paging;
import adventure_time.core.requests.guides.SearchGuideRequest;
import adventure_time.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SearchGuideRequestValidator {

	public List<CoreError> validate(SearchGuideRequest request) {
		List<CoreError> errors = new ArrayList<>();
		errors.addAll(validateSearchFields(request));
		if (request.getOrdering() != null) {
			validateOrderBy(request.getOrdering()).ifPresent(errors::add);
			validateOrderDirection(request.getOrdering()).ifPresent(errors::add);
			validateMandatoryOrderBy(request.getOrdering()).ifPresent(errors::add);
			validateMandatoryOrderDirection(request.getOrdering()).ifPresent(errors::add);
		}
		if (request.getPaging() != null) {
			validatePageNumber(request.getPaging()).ifPresent(errors::add);
			validatePageSize(request.getPaging()).ifPresent(errors::add);
			validateMandatoryPageNumber(request.getPaging()).ifPresent(errors::add);
			validateMandatoryPageSize(request.getPaging()).ifPresent(errors::add);
		}
		return errors;
	}

	private List<CoreError> validateSearchFields(SearchGuideRequest request) {
		List<CoreError> errors = new ArrayList<>();
		if (isEmpty(request.getGuideName()) && isEmpty(request.getGuideEmail())) {
			errors.add(new CoreError("GuideName", "Must not be empty!"));
			errors.add(new CoreError("GuideEmail", "Must not be empty!"));
		}
		return errors;
	}

	private boolean isEmpty(String str) {
		return str == null || str.isEmpty();
	}

	private Optional<CoreError> validateOrderBy(Ordering ordering) {
		return (ordering.getOrderBy() != null
				&& !(ordering.getOrderBy().equals("Name") || ordering.getOrderBy().equals("Email")))
				? Optional.of(new CoreError("orderBy", "Must contain 'Name' or 'Email' only!"))
				: Optional.empty();
	}

	private Optional<CoreError> validateOrderDirection(Ordering ordering) {
		return (ordering.getOrderDirection() != null
				&& !(ordering.getOrderDirection().equals("ASC") || ordering.getOrderDirection().equals("DESC")))
				? Optional.of(new CoreError("orderDirection", "Must contain 'ASCENDING' or 'DESCENDING' only!"))
				: Optional.empty();
	}

	private Optional<CoreError> validateMandatoryOrderBy(Ordering ordering) {
		return (ordering.getOrderDirection() != null && ordering.getOrderBy() == null)
				? Optional.of(new CoreError("orderBy", "Must not be empty!"))
				: Optional.empty();
	}

	private Optional<CoreError> validateMandatoryOrderDirection(Ordering ordering) {
		return (ordering.getOrderBy() != null && ordering.getOrderDirection() == null)
				? Optional.of(new CoreError("orderDirection", "Must not be empty!"))
				: Optional.empty();
	}

	private Optional<CoreError> validatePageNumber(Paging paging) {
		return (paging.getPageNumber() != null
				&& paging.getPageNumber() <= 0)
				? Optional.of(new CoreError("pageNumber", "Must be greater then 0!"))
				: Optional.empty();
	}

	private Optional<CoreError> validatePageSize(Paging paging) {
		return (paging.getPageSize() != null
				&& paging.getPageSize() <= 0)
				? Optional.of(new CoreError("pageSize", "Must be greater then 0!"))
				: Optional.empty();
	}

	private Optional<CoreError> validateMandatoryPageNumber(Paging paging) {
		return (paging.getPageNumber() == null && paging.getPageSize() != null)
				? Optional.of(new CoreError("pageNumber", "Must not be empty!"))
				: Optional.empty();
	}

	private Optional<CoreError> validateMandatoryPageSize(Paging paging) {
		return (paging.getPageSize() == null && paging.getPageNumber() != null)
				? Optional.of(new CoreError("pageSize", "Must not be empty!"))
				: Optional.empty();
	}
}