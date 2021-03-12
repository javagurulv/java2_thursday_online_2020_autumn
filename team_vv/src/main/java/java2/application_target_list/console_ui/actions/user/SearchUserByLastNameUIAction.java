package java2.application_target_list.console_ui.actions.user;

import java2.application_target_list.console_ui.UIAction;
import java2.application_target_list.core.domain.User;
import java2.application_target_list.core.requests.Ordering;
import java2.application_target_list.core.requests.Paging;
import java2.application_target_list.core.requests.user.SearchUsersByLastNameRequest;
import java2.application_target_list.core.responses.user.SearchUserByLastNameResponse;
import java2.application_target_list.core.services.user.SearchUserByLastNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class SearchUserByLastNameUIAction implements UIAction {

    @Autowired SearchUserByLastNameService searchUserByLastNameService;
    private final Scanner scr = new Scanner(System.in);


    @Override
    public void execute() {
        while (true){
            String userName = getLastNameFromUser();

            printOrderingMessage();
            int orderingFromUser = getNumberFromUser();

            printPagingMessage();
            int pagingFromUser = getNumberFromUser();

            SearchUserByLastNameResponse searchUserByLastNameResponse;

            if (isOrderingNeeded(orderingFromUser) && isPagingNeeded(pagingFromUser)){
                String orderByFromUser = getOrderByFromUser();
                String orderDirectionFromUser = getOrderingDirectionFromUser();
                Integer pageNumber = getPageNumberFromUser();
                Integer pageSize = getPageSizeFromUser();
                SearchUsersByLastNameRequest searchUsersByLastNameRequest = createRequestWithOrderingAndPaging(userName,
                        orderByFromUser, orderDirectionFromUser,
                        pageNumber, pageSize);
                searchUserByLastNameResponse = validateSearchUserByLastNameRequest(searchUsersByLastNameRequest);
            } else if (isOrderingNeeded(orderingFromUser) && !isPagingNeeded(pagingFromUser)){
                String orderByFromUser = getOrderByFromUser();
                String orderDirectionFromUser = getOrderingDirectionFromUser();
                SearchUsersByLastNameRequest searchUsersByLastNameRequest = createRequestWithOrdering(userName, orderByFromUser, orderDirectionFromUser);
                searchUserByLastNameResponse = validateSearchUserByLastNameRequest(searchUsersByLastNameRequest);
            } else if (isPagingNeeded(pagingFromUser) && !isOrderingNeeded(orderingFromUser)){
                Integer pageNumber = getPageNumberFromUser();
                Integer pageSize = getPageSizeFromUser();
                SearchUsersByLastNameRequest searchUsersByLastNameRequest = createRequestWithPaging(userName, pageNumber, pageSize);
                searchUserByLastNameResponse = validateSearchUserByLastNameRequest(searchUsersByLastNameRequest);
            } else {
                SearchUsersByLastNameRequest searchUsersByLastNameRequest = createRequest(userName);
                searchUserByLastNameResponse = validateSearchUserByLastNameRequest(searchUsersByLastNameRequest);
            }


            if (searchUserByLastNameResponse.hasErrors()) {
                printResponseErrors(searchUserByLastNameResponse);
            } else {
                printResponseResultMessage(searchUserByLastNameResponse);
                break;
            }
        }
    }

    private SearchUsersByLastNameRequest createRequestWithOrdering(String userFirstName,String  orderBy,String orderDirection){
        return new SearchUsersByLastNameRequest(userFirstName,
                new Ordering(orderBy, orderDirection));
    }

    private SearchUsersByLastNameRequest createRequestWithPaging(String userFirstName,Integer pageNumber, Integer pageSize){
        return new SearchUsersByLastNameRequest(userFirstName,
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

    private void printResponseResultMessage(SearchUserByLastNameResponse searchUserByFirstNameResponse){
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

    private void printResponseErrors(SearchUserByLastNameResponse searchUserByFirstNameResponse){
        searchUserByFirstNameResponse.getErrorList().forEach(System.out::println);
    }

    private SearchUserByLastNameResponse validateSearchUserByLastNameRequest(SearchUsersByLastNameRequest searchUsersByFirstNameRequest){
        return searchUserByLastNameService.execute(searchUsersByFirstNameRequest);
    }

    private SearchUsersByLastNameRequest createRequest(String userFirstName){
        return new SearchUsersByLastNameRequest(userFirstName);
    }

    private SearchUsersByLastNameRequest createRequestWithOrderingAndPaging(String userFirstName,
                                                                             String orderBy, String orderDirection,
                                                                             Integer pageNumber, Integer pageSize){
        return new SearchUsersByLastNameRequest(userFirstName,
                new Paging(pageNumber, pageSize),
                new Ordering(orderBy, orderDirection));
    }

    private String getLastNameFromUser(){
        System.out.print("Enter last name: ");
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
