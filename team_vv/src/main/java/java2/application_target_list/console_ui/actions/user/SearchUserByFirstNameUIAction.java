package java2.application_target_list.console_ui.actions.user;

import java2.application_target_list.console_ui.UIAction;
import java2.application_target_list.core.domain.User;
import java2.application_target_list.core.requests.Ordering;
import java2.application_target_list.core.requests.Paging;
import java2.application_target_list.core.requests.user.SearchUsersByFirstNameRequest;
import java2.application_target_list.core.responses.user.SearchUserByFirstNameResponse;
import java2.application_target_list.core.services.user.SearchUserByFirstNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class SearchUserByFirstNameUIAction implements UIAction {

    @Autowired
    private SearchUserByFirstNameService searchUserByFirstNameService;

    private final Scanner scr = new Scanner(System.in);

    @Override
    public void execute() {
        while (true){
            String userName = getFirstNameFromUser();

            printOrderingMessage();
            int orderingFromUser = getNumberFromUser();

            printPagingMessage();
            int pagingFromUser = getNumberFromUser();

            SearchUserByFirstNameResponse searchUserByFirstNameResponse;

            if (isOrderingNeeded(orderingFromUser) && isPagingNeeded(pagingFromUser)){
                String orderByFromUser = getOrderByFromUser();
                String orderDirectionFromUser = getOrderingDirectionFromUser();
                Integer pageNumber = getPageNumberFromUser();
                Integer pageSize = getPageSizeFromUser();
                SearchUsersByFirstNameRequest searchUsersByFirstNameRequest = createRequestWithOrderingAndPaging(userName,
                        orderByFromUser, orderDirectionFromUser,
                        pageNumber, pageSize);
                searchUserByFirstNameResponse = validateSearchUserFirstNameRequest(searchUsersByFirstNameRequest);
            } else if (isOrderingNeeded(orderingFromUser) && !isPagingNeeded(pagingFromUser)){
                String orderByFromUser = getOrderByFromUser();
                String orderDirectionFromUser = getOrderingDirectionFromUser();
                SearchUsersByFirstNameRequest searchUsersByFirstNameRequest = createRequestWithOrdering(userName, orderByFromUser, orderDirectionFromUser);
                searchUserByFirstNameResponse = validateSearchUserFirstNameRequest(searchUsersByFirstNameRequest);
            } else if (isPagingNeeded(pagingFromUser) && !isOrderingNeeded(orderingFromUser)){
                Integer pageNumber = getPageNumberFromUser();
                Integer pageSize = getPageSizeFromUser();
                SearchUsersByFirstNameRequest searchUsersByFirstNameRequest = createRequestWithPaging(userName, pageNumber, pageSize);
                searchUserByFirstNameResponse = validateSearchUserFirstNameRequest(searchUsersByFirstNameRequest);
            } else {
                SearchUsersByFirstNameRequest searchUsersByFirstNameRequest = createRequest(userName);
                searchUserByFirstNameResponse = validateSearchUserFirstNameRequest(searchUsersByFirstNameRequest);
            }


            if (searchUserByFirstNameResponse.hasErrors()) {
                printResponseErrors(searchUserByFirstNameResponse);
            } else {
                printResponseResultMessage(searchUserByFirstNameResponse);
                break;
            }
        }
    }

    private SearchUsersByFirstNameRequest createRequestWithOrdering(String userFirstName,String  orderBy,String orderDirection){
        return new SearchUsersByFirstNameRequest(userFirstName,
                new Ordering(orderBy, orderDirection));
    }

    private SearchUsersByFirstNameRequest createRequestWithPaging(String userFirstName,Integer pageNumber, Integer pageSize){
        return new SearchUsersByFirstNameRequest(userFirstName,
                new Paging(pageNumber, pageSize));
    }

    private boolean isOrderingNeeded(int number){
        return number == 1;
    }

    private boolean isPagingNeeded(int number){
        return number == 2;
    }

    private void printPagingMessage(){
        System.out.println("Show all user list?");
        System.out.println("[1] YES");
        System.out.println("[2] NO");
    }

    private void printOrderingMessage(){
        System.out.println("Need to sort user list?");
        System.out.println("[1] YES");
        System.out.println("[2] NO");
    }

    private String getOrderByFromUser(){
        System.out.println("ENTER sort by (first name or last name):  ");
        return scr.nextLine();
    }


    private String getOrderingDirectionFromUser(){
        System.out.println("ENTER sort direction (ASCENDING or DESCENDING): ");
        return scr.nextLine();
    }

    private Integer getNumberFromUser(){
        return Integer.parseInt(scr.nextLine());
    }

    private void printResponseResultMessage(SearchUserByFirstNameResponse searchUserByFirstNameResponse){
        if (searchUserByFirstNameResponse.getUsersList().isEmpty()){
            System.out.println("----------");
            System.out.println("Users not found!");
            System.out.println("----------");
        } else {

            for (User user : searchUserByFirstNameResponse.getUsersList()) {
                System.out.println(user.getId() + ". " +
                        user.getFirstName() + " " + user.getLastName());
            }
            System.out.println("----------");
        }
    }

    private void printResponseErrors(SearchUserByFirstNameResponse searchUserByFirstNameResponse){
        searchUserByFirstNameResponse.getErrorList().forEach(System.out::println);
    }

    private SearchUserByFirstNameResponse validateSearchUserFirstNameRequest(SearchUsersByFirstNameRequest searchUsersByFirstNameRequest){
        return searchUserByFirstNameService.execute(searchUsersByFirstNameRequest);
    }

    private SearchUsersByFirstNameRequest createRequest(String userFirstName){
        return new SearchUsersByFirstNameRequest(userFirstName);
    }

    private SearchUsersByFirstNameRequest createRequestWithOrderingAndPaging(String userFirstName,
                                                                         String orderBy, String orderDirection,
                                                                         Integer pageNumber, Integer pageSize){
        return new SearchUsersByFirstNameRequest(userFirstName,
                new Paging(pageNumber, pageSize),
                new Ordering(orderBy, orderDirection));
    }

    private String getFirstNameFromUser(){
        System.out.print("Enter first name: ");
        return scr.nextLine();
    }

    private Integer getPageNumberFromUser(){
        System.out.print("Enter page number: ");
        return Integer.parseInt(scr.nextLine());
    }

    private Integer getPageSizeFromUser(){
        System.out.print("Enter page size: ");
        return Integer.parseInt(scr.nextLine());
    }
}
