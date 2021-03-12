package java2.application_target_list.console_ui.actions.target;

import java2.application_target_list.core.requests.Ordering;
import java2.application_target_list.core.requests.Paging;
import java2.application_target_list.core.requests.target.SearchTargetByDescriptionRequest;
import java2.application_target_list.core.services.target.SearchTargetByDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java2.application_target_list.console_ui.UIAction;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.responses.target.SearchTargetByDescriptionResponse;
import java.util.Scanner;

@Component
public class SearchTargetByDescriptionUIAction implements UIAction {

    @Autowired
    private SearchTargetByDescriptionService searchTargetByDescriptionService;

    private final Scanner scr = new Scanner(System.in);

    @Override
    public void execute() {
        while (true){
            String descriptionFromUser = getDescriptionFromUser();
            printOrderingMessage();
            int orderingFromUser = getNumberFromUser();
            printPagingMessage();
            int pagingFromUser = getNumberFromUser();
            SearchTargetByDescriptionResponse searchTargetByDescriptionResponse;

            if (isOrderingNeeded(orderingFromUser) && isPagingNeeded(pagingFromUser)){
                String orderByFromUser = getOrderByFromUser();
                String orderDirectionFromUser = getOrderingDirectionFromUser();
                Integer pageNumber = getPageNumberFromUser();
                Integer pageSize = getPageSizeFromUser();
                SearchTargetByDescriptionRequest searchTargetByDescriptionRequest = createRequestWithPagingAndOrdering(descriptionFromUser,
                                                                                              orderByFromUser, orderDirectionFromUser,
                                                                                              pageNumber, pageSize);
                searchTargetByDescriptionResponse = validateSearchTargetByDescriptionRequest(searchTargetByDescriptionRequest);

            } else if (isOrderingNeeded(orderingFromUser) && !isPagingNeeded(pagingFromUser)){
                String orderByFromUser = getOrderByFromUser();
                String orderDirectionFromUser = getOrderingDirectionFromUser();
                SearchTargetByDescriptionRequest searchTargetByDescriptionRequest = createRequestWithOrdering(descriptionFromUser, orderByFromUser, orderDirectionFromUser);
                searchTargetByDescriptionResponse = validateSearchTargetByDescriptionRequest(searchTargetByDescriptionRequest);
            } else if (!isOrderingNeeded(orderingFromUser) && isPagingNeeded(pagingFromUser)){
                Integer pageNumber = getPageNumberFromUser();
                Integer pageSize = getPageSizeFromUser();
                SearchTargetByDescriptionRequest request = createRequestWithPaging(descriptionFromUser, pageNumber, pageSize);
                searchTargetByDescriptionResponse = validateSearchTargetByDescriptionRequest(request);
            } else {
                SearchTargetByDescriptionRequest request = createRequest(descriptionFromUser);
                searchTargetByDescriptionResponse = validateSearchTargetByDescriptionRequest(request);
            }


            if (searchTargetByDescriptionResponse.hasErrors()) {
                printResponseErrors(searchTargetByDescriptionResponse);
            } else {
                printResponseResultMessage(searchTargetByDescriptionResponse);
                break;
            }
        }
    }

    private boolean isOrderingNeeded(int number){
        return number == 1;
    }

    private boolean isPagingNeeded(int number){
        return number == 2;
    }

    private void printPagingMessage(){
        System.out.println("Show all target list?");
        System.out.println("[1] YES");
        System.out.println("[2] NO");
    }

    private void printOrderingMessage(){
        System.out.println("Need to sort target list?");
        System.out.println("[1] YES");
        System.out.println("[2] NO");
    }

    private String getOrderByFromUser(){
        System.out.println("ENTER sort by (name, description or deadline):  ");
        return scr.nextLine();
    }


    private String getOrderingDirectionFromUser(){
        System.out.println("ENTER sort direction (ASCENDING or DESCENDING): ");
        return scr.nextLine();
    }

    private Integer getNumberFromUser(){
        return Integer.parseInt(scr.nextLine());
    }



    private void printResponseResultMessage(SearchTargetByDescriptionResponse response){
        if (response.getTargetList().isEmpty()){
            System.out.println("----------");
            System.out.println("Target not found!");
            System.out.println("----------");
        } else {

            for (Target target : response.getTargetList()) {
                System.out.println(target.getId() + ". " +
                        target.getName() + " [" + target.getDescription() + "] " +
                        target.getDeadline() + " days");
            }
            System.out.println("----------");
        }
    }

    private void printResponseErrors(SearchTargetByDescriptionResponse response){
        response.getErrorList().forEach(System.out::println);
    }

    private SearchTargetByDescriptionResponse validateSearchTargetByDescriptionRequest(SearchTargetByDescriptionRequest request){
        return searchTargetByDescriptionService.execute(request);
    }

    private SearchTargetByDescriptionRequest createRequest(String targetName){
        return new SearchTargetByDescriptionRequest(targetName);
    }

    private SearchTargetByDescriptionRequest createRequestWithPaging(String targetName, Integer pageNumber, Integer pageSize){
        Paging paging = new Paging(pageNumber, pageSize);
        return new SearchTargetByDescriptionRequest(targetName, paging);
    }

    private SearchTargetByDescriptionRequest createRequestWithPagingAndOrdering(String targetName,String orderBy, String orderDirection, Integer pageNumber, Integer pageSize){
        Ordering ordering = new Ordering(orderBy, orderDirection);
        Paging paging = new Paging(pageNumber, pageSize);
        return new SearchTargetByDescriptionRequest(targetName, ordering, paging);
    }

    private SearchTargetByDescriptionRequest createRequestWithOrdering(String targetName,String orderBy, String orderDirection){
        Ordering ordering = new Ordering(orderBy, orderDirection);
        return new SearchTargetByDescriptionRequest(targetName, ordering);
    }

    private String getDescriptionFromUser(){
        System.out.print("Enter target description: ");
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
