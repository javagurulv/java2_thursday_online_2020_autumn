package internet_store.application.console_ui.product;

import internet_store.application.console_ui.UIAction;
import internet_store.application.core.requests.product.FindProductsRequest;
import internet_store.application.core.requests.product.Ordering;
import internet_store.application.core.requests.product.Paging;
import internet_store.application.core.responses.product.FindProductsResponse;
import internet_store.application.core.services.product.FindProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

//@Component
public class FindProductsUIAction implements UIAction {

    @Autowired
    private FindProductsService findProductsService;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product description: ");
        String description = scanner.nextLine();

        System.out.print("Enter ordering. By 'Name' or by 'Description' ");
        String orderingType = scanner.nextLine();
        System.out.print("Enter ordering 'Ascending' or by 'Descending' ");
        String orderingDirection = scanner.nextLine();

        System.out.print("Enter number of products to display on page: ");
        Integer pageSize = scanner.nextInt();
        System.out.print("Enter number of page to show: ");
        Integer pageNumber = scanner.nextInt();

        Ordering ordering = new Ordering(orderingType, orderingDirection);
        Paging paging = new Paging(pageNumber, pageSize);
        FindProductsRequest request = new FindProductsRequest(name, description, ordering, paging);
        FindProductsResponse response = findProductsService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {

            if (!response.getProducts().isEmpty()) {
                System.out.println("\nFound " + response.getProducts().size() + " product(s) in the database : ");
                response.getProducts().forEach(System.out::println);
            } else {
                System.out.println("\nNo product with name = " + name + " found in the database");

            }
        }
    }

}
