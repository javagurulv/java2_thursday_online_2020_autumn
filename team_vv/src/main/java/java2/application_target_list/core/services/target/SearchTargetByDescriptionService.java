package java2.application_target_list.core.services.target;

import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.requests.Ordering;
import java2.application_target_list.core.requests.Paging;
import java2.application_target_list.core.validators.ErrorCreator;
import java2.application_target_list.core.validators.target.SearchTargetByDescriptionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.requests.target.SearchTargetByDescriptionRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.target.SearchTargetByDescriptionResponse;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SearchTargetByDescriptionService {

    @Value("${search.ordering.enabled}")
    private boolean orderingEnabled;

    @Value("${search.paging.enabled}")
    private boolean pagingEnabled;

    @Autowired
    private SearchTargetByDescriptionValidator validator;
    @Autowired
    private JpaTargetRepository jpaTargetRepository;

    public SearchTargetByDescriptionResponse execute(SearchTargetByDescriptionRequest searchTargetByDescriptionRequest){
        List<CoreError> errors = checkRequestForErrors(searchTargetByDescriptionRequest);

        if (requestHaveErrors(errors)) {
            return createSearchTargetByDescriptionResponseWithErrors(errors);
        }

        List<Target> targets = findAllTargetsByDescription(searchTargetByDescriptionRequest);
        targets = order(targets, searchTargetByDescriptionRequest.getOrdering());
        targets = paging(targets, searchTargetByDescriptionRequest.getPaging());

        return createSearchTargetByDescriptionResponse(targets);
    }

    private SearchTargetByDescriptionResponse createSearchTargetByDescriptionResponse(List<Target> targets) {
        return new SearchTargetByDescriptionResponse(null, targets);
    }

    private List<Target> findAllTargetsByDescription(SearchTargetByDescriptionRequest searchTargetByDescriptionRequest){
        return jpaTargetRepository.findByDescription(searchTargetByDescriptionRequest.getDescription());
    }

    private SearchTargetByDescriptionResponse createSearchTargetByDescriptionResponseWithErrors(List<CoreError> errors){
        return new SearchTargetByDescriptionResponse(errors, null);
    }

    private boolean requestHaveErrors(List<CoreError> errors) {
        return !errors.isEmpty();
    }

    private List<CoreError> checkRequestForErrors(SearchTargetByDescriptionRequest searchTargetByDescriptionRequest){
        return validator.validate(searchTargetByDescriptionRequest);
    }


    private List<Target> paging(List<Target> targets, Paging paging) {
        if (pagingEnabled && paging != null) {
            int skip = (paging.getPageNumber() - 1) * paging.getPageSize();
            return targets.stream()
                    .skip(skip)
                    .limit(paging.getPageSize())
                    .collect(Collectors.toList());
        } else {
            return targets;
        }
    }

    private List<Target> order(List<Target> targets, Ordering ordering) {

        if (orderingEnabled && ordering != null) {
            if (isNeedOrderingByName(ordering)) {
                if (isNeedOrderingDescending(ordering)) {
                    return sortByNameDescending(targets);
                }
                return sortByNameAscending(targets);
            }

            if (isNeedOrderingByDescription(ordering)) {
                if (isNeedOrderingDescending(ordering)) {
                    return sortByDescriptionDescending(targets);
                }
                return sortByDescriptionAscending(targets);
            }

            if (isNeedOrderingByDeadline(ordering)) {
                if (isNeedOrderingDescending(ordering)) {
                    return sortByDeadlineDescending(targets);
                }
                return sortByDeadlineAscending(targets);
            }

        }
        return targets;
    }

    private boolean isNeedOrderingDescending(Ordering ordering){
        return ordering.getOrderDirection().equals("DESCENDING");
    }

    private boolean isNeedOrderingByName(Ordering ordering){
        return ordering.getOrderBy().equals("name");
    }

    private boolean isNeedOrderingByDescription(Ordering ordering){
        return ordering.getOrderBy().equals("description");
    }

    private boolean isNeedOrderingByDeadline(Ordering ordering){
        return ordering.getOrderBy().equals("deadline");
    }

    private List<Target> sortByNameDescending(List<Target> targets){
        return targets.stream()
                .sorted(Comparator.comparing(Target::getName).reversed())
                .collect(Collectors.toList());
    }

    private List<Target> sortByDescriptionDescending(List<Target> targets){
        return targets.stream()
                .sorted(Comparator.comparing(Target::getDescription).reversed())
                .collect(Collectors.toList());
    }

    private List<Target> sortByDeadlineDescending(List<Target> targets){
        return targets.stream()
                .sorted(Comparator.comparing(Target::getDeadline).reversed())
                .collect(Collectors.toList());
    }

    private List<Target> sortByNameAscending(List<Target> targets){
        return targets.stream()
                .sorted(Comparator.comparing(Target::getName))
                .collect(Collectors.toList());
    }

    private List<Target> sortByDescriptionAscending(List<Target> targets){
        return targets.stream()
                .sorted(Comparator.comparing(Target::getDescription))
                .collect(Collectors.toList());
    }

    private List<Target> sortByDeadlineAscending(List<Target> targets){
        return targets.stream()
                .sorted(Comparator.comparing(Target::getDeadline))
                .collect(Collectors.toList());
    }
}
