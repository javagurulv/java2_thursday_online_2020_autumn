package adventure_time.ui.events;

import adventure_time.core.requests.Ordering;
import adventure_time.core.requests.Paging;
import adventure_time.core.requests.customers.SearchCustomerRequest;
import adventure_time.core.requests.events.SearchEventRequest;
import adventure_time.core.responses.events.SearchEventResponse;
import adventure_time.core.services.events.SearchEventService;
import adventure_time.dependencies.DIComponent;
import adventure_time.dependencies.DIDependency;
import adventure_time.ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static java.lang.Integer.valueOf;

@Component
public class SearchEventUIAction implements UIAction {

    @Autowired
    private SearchEventService searchEventService;

     @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
         SearchEventRequest request;

         System.out.println("Would you like to find an events by: ");
         System.out.println("   1. ID ");
         System.out.println("   2. Event's name ");

         if (scanner.nextLine().equals("1")) {
             System.out.println("Enter the event's ID: ");
             request = new SearchEventRequest(scanner.nextLong(), null);
         } else {
             System.out.println("Enter the event's name: ");
             request = new SearchEventRequest(null, scanner.nextLine());
         }

         SearchEventResponse response = searchEventService.searchEvent(request);

         if (response.hasError()) {
             System.out.println("Your request could not be fulfilled for the reasons: ");
             response.getErrors().forEach(System.out::println);
         } else {
             if (response.getEvents() != null) {
                 System.out.println("Here is the event: ");
                 System.out.println(response.getEvents());
             } else {
                 System.out.println("Your request could not be fulfilled for the reasons: ");
                 response.getErrors().forEach(System.out::println);
             }
         }
    }
}