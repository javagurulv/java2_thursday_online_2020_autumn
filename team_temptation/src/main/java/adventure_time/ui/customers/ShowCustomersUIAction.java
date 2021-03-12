package adventure_time.ui.customers;

import adventure_time.core.requests.Ordering;
import adventure_time.core.requests.Paging;
import adventure_time.core.requests.customers.ShowCustomersRequest;
import adventure_time.core.responses.customer.ShowCustomersResponse;
import adventure_time.core.services.customers.ShowCustomersService;
import adventure_time.ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ShowCustomersUIAction implements UIAction {

    @Autowired
    private ShowCustomersService service;

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please, define a few letters customer's name starts,");
        System.out.println("If this criteria doesn't matter just press Enter:");
        String nameStartsWith = scanner.nextLine();

        System.out.println("Please, define a few numbers of customer's phone starts (without '+'),");
        System.out.println("If this criteria doesn't matter just press Enter:");
        String phoneStartsWith = scanner.nextLine();

        System.out.println("Define the sorting criteria by: N - name, P - phone, E - email, I - ID");
        System.out.println("If the sorting doesn't need just press Enter:");
        String order = switch (scanner.nextLine()) {
            case "N" ->  " ORDER BY c.customerName";
            case "P" ->  " ORDER BY c.customerPhone";
            case "E" ->  " ORDER BY c.customerEmail";
            case "I" ->  " ORDER BY c.customerId";
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

        ShowCustomersRequest request = new ShowCustomersRequest(nameStartsWith, phoneStartsWith, ordering, paging);
        ShowCustomersResponse response = service.getCustomersList(request);

        if (response.hasError()) {
            System.out.println("Your request could not be fulfilled for the reasons: ");
            response.getErrors().forEach(System.out::println);
        } else {
            System.out.println("Here is a list of the customers under your request: ");
            if (response.getCustomersList().isEmpty()) {
                System.out.println("The database does not contain any customer under your request.");
            } else {
                response.getCustomersList().forEach(System.out::println);
            }
        }
    }
}
