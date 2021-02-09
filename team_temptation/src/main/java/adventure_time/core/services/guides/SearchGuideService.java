package adventure_time.core.services.guides;

import adventure_time.core.requests.Ordering;
import adventure_time.core.requests.Paging;
import adventure_time.core.requests.guides.SearchGuideRequest;
import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.guides.SearchGuideResponse;
import adventure_time.database.guides.DatabaseGuides;
import adventure_time.core.domain.Guides;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchGuideService {

	@Autowired
	private final DatabaseGuides database;
	@Autowired
	private final adventure_time.core.services.guides.SearchGuideRequestValidator validator;

//	public SearchGuideService(DatabaseGuides database,
//							  adventure_time.core.services.guides.SearchGuideRequestValidator validator) {
//		this.database = database;
//		this.validator = validator;
//	}

	public SearchGuideResponse execute(SearchGuideRequest request) {
		List<CoreError> errors = validator.validate(request);
		if (!errors.isEmpty()) {
			return new SearchGuideResponse(null, errors);
		}
		List<Guides> guides = search(request);
		guides = order(guides, request.getOrdering());
		guides = paging(guides, request.getPaging());

		return new SearchGuideResponse(guides, null);
	}

	private List<Guides> order(List<Guides> guides, Ordering ordering) {
		if (ordering != null) {
			Comparator<Guides> comparator = ordering.getOrderBy().equals("Name")
					? Comparator.comparing(Guides::getGuideName)
					: Comparator.comparing(Guides::getGuideEmail);
			if (ordering.getOrderDirection().equals("DESC")) {
				comparator = comparator.reversed();
			}
			return guides.stream().sorted(comparator).collect(Collectors.toList());
		} else {
			return guides;
		}
	}
	private List<Guides> search(SearchGuideRequest request) {
		List<Guides> guides = new ArrayList<>();
		if (request.isGuideNameProvided() && !request.isGuideEmailProvided()) {
			guides = database.findByGuideName(request.getGuideName());
		}
		if (!request.isGuideNameProvided() && request.isGuideEmailProvided()) {
			guides = database.findByGuideEmail(request.getGuideEmail());
		}
		if (request.isGuideNameProvided() && request.isGuideEmailProvided()) {
			guides = database.findByGuideNameAndEmail(request.getGuideName(), request.getGuideEmail());
		}
		return guides;
	}
	private List<Guides> paging(List<Guides> guides, Paging paging) {
		if (paging != null) {
			int skip = (paging.getPageNumber() - 1) * paging.getPageSize();
			return guides.stream()
					.skip(skip)
					.limit(paging.getPageSize())
					.collect(Collectors.toList());
		} else {
			return guides;
		}
	}
}