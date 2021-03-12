package java2.application_target_list.core.services.user;

import java2.application_target_list.core.database.jpa.JpaUserRepository;
import java2.application_target_list.core.domain.User;
import java2.application_target_list.core.requests.Ordering;
import java2.application_target_list.core.requests.Paging;
import java2.application_target_list.core.requests.user.SearchUsersByLastNameRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.user.SearchUserByLastNameResponse;
import java2.application_target_list.core.validators.user.SearchUserByLastNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SearchUserByLastNameService {

    @Value("${search.ordering.enabled}")
    private boolean orderingEnabled;

    @Value("${search.paging.enabled}")
    private boolean pagingEnabled;

    @Autowired
    private SearchUserByLastNameValidator searchUserByLastNameValidator;
    @Autowired
    private JpaUserRepository jpaUserRepository;

    public SearchUserByLastNameResponse execute(SearchUsersByLastNameRequest searchUsersByLastNameRequest){
        List<CoreError> errors = checkRequestForErrors(searchUsersByLastNameRequest);

        if (requestHaveErrors(errors)){
            return createSearchUserByLastNameResponseWithErrors(errors);
        }

        List<User> users = findUsersInDB(searchUsersByLastNameRequest);
        users = order(users, searchUsersByLastNameRequest.getOrdering());
        users = paging(users, searchUsersByLastNameRequest.getPaging());

        return createSearchUserByLastNameResponse(users);
    }

    private SearchUserByLastNameResponse createSearchUserByLastNameResponse(List<User> users){
        return new SearchUserByLastNameResponse(null, users);
    }

    private List<User> findUsersInDB(SearchUsersByLastNameRequest searchUsersByLastNameRequest){
        return jpaUserRepository.findByLastName(searchUsersByLastNameRequest.getLastName());
    }

    private SearchUserByLastNameResponse createSearchUserByLastNameResponseWithErrors(List<CoreError> errors){
        return new SearchUserByLastNameResponse(errors, null);
    }

    private boolean requestHaveErrors(List<CoreError> errors){
        return !errors.isEmpty();
    }

    private List<CoreError> checkRequestForErrors(SearchUsersByLastNameRequest searchUsersByLastNameRequest){
        return searchUserByLastNameValidator.validate(searchUsersByLastNameRequest);
    }

    private List<User> order(List<User> users, Ordering ordering) {

        if (orderingEnabled && ordering != null) {
            if (isNeedOrderingByFirstName(ordering)) {
                if (isNeedOrderingDescending(ordering)) {
                    return sortByFirstNameDescending(users);
                }
                return sortByFirstNameAscending(users);
            }

            if (isNeedOrderingByLastName(ordering)) {
                if (isNeedOrderingDescending(ordering)) {
                    return sortByLastNameDescending(users);
                }
                return sortByLastNameAscending(users);
            }


        }
        return users;
    }

    private boolean isNeedOrderingDescending(Ordering ordering){
        return ordering.getOrderDirection().equals("DESCENDING");
    }

    private boolean isNeedOrderingByFirstName(Ordering ordering){
        return ordering.getOrderBy().equals("first name");
    }

    private boolean isNeedOrderingByLastName(Ordering ordering){
        return ordering.getOrderBy().equals("last name");
    }

    private List<User> sortByFirstNameDescending(List<User> users){
        return users.stream()
                .sorted(Comparator.comparing(User::getFirstName).reversed())
                .collect(Collectors.toList());
    }

    private List<User> sortByLastNameDescending(List<User> users){
        return users.stream()
                .sorted(Comparator.comparing(User::getLastName).reversed())
                .collect(Collectors.toList());
    }


    private List<User> sortByFirstNameAscending(List<User> users){
        return users.stream()
                .sorted(Comparator.comparing(User::getFirstName))
                .collect(Collectors.toList());
    }

    private List<User> sortByLastNameAscending(List<User> users){
        return users.stream()
                .sorted(Comparator.comparing(User::getLastName))
                .collect(Collectors.toList());
    }

    private List<User> paging(List<User> users, Paging paging) {
        if (pagingEnabled && paging != null) {
            int skip = (paging.getPageNumber() - 1) * paging.getPageSize();
            return users.stream()
                    .skip(skip)
                    .limit(paging.getPageSize())
                    .collect(Collectors.toList());
        } else {
            return users;
        }
    }


}
