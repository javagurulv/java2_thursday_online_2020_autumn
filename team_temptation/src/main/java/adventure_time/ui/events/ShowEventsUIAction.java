package adventure_time.ui.events;

import adventure_time.core.requests.Ordering;
import adventure_time.core.requests.Paging;
import adventure_time.core.requests.events.ShowEventsRequest;
import adventure_time.core.responses.events.ShowEventsResponse;
import adventure_time.core.services.events.ShowEventsService;

import adventure_time.ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ShowEventsUIAction implements UIAction {

    @Autowired
    private ShowEventsService service;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please, define a few letters the event's name starts,");
        System.out.println("If this criteria doesn't matter just press Enter:");
        String nameStartsWith = scanner.nextLine();

        System.out.println("Please, define a few letters the event's kind starts,");
        System.out.println("If this criteria doesn't matter just press Enter:");
        String kindStartsWith = scanner.nextLine();

        System.out.println("Please, define a word, what route must contains,");
        System.out.println("If this criteria doesn't matter just press Enter:");
        String routeContains = scanner.nextLine();

        System.out.println("Define the sorting criteria by: N - name, K - kind, I - ID");
        System.out.println("If the sorting doesn't need just press Enter:");
        String order = switch (scanner.nextLine()) {
            case "N" ->  " ORDER BY e.eventName";
            case "P" ->  " ORDER BY e.eventKind";
            case "I" ->  " ORDER BY e.eventId";
            default -> "";
        };
        System.out.println("Define the sorting order: A - ascending, D - descending,");
        System.out.println("If the order doesn't matter just press Enter:");
        String direction = scanner.nextLine().equals("D") ? " DESC" : "";
        Ordering ordering = new Ordering(order, direction);

        System.out.println("Define a displayed items number (min 8, max 20):");
        Integer pageSize = Integer.parseInt(scanner.nextLine());
        System.out.println("Define a page number:");
        Integer pageNumber = Integer.parseInt(scanner.nextLine());
        Paging paging = new Paging(pageNumber, pageSize);

        ShowEventsRequest request = new ShowEventsRequest(
                nameStartsWith,
                kindStartsWith,
                routeContains,
                ordering, paging);
        ShowEventsResponse response = service.getEventsList(request);

        if (response.hasError()) {
            System.out.println("Your request could not be fulfilled for the reasons: ");
            response.getErrors().forEach(System.out::println);
        } else {
            System.out.println("Here is a list of the events under your request: ");
            if (response.getEventsList().isEmpty()) {
                System.out.println("The database does not contain any event under your request.");
            } else {
                response.getEventsList().forEach(System.out::println);
            }
        }
    }
}
