package internet_store.application.console_ui.order;

import internet_store.application.console_ui.UIAction;
import internet_store.application.core.requests.order.FindOrderByIdRequest;
import internet_store.application.core.responses.order.FindOrderByIdResponse;
import internet_store.application.core.services.order.FindOrderByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

//@Component
public class FindOrderByIdUIAction implements UIAction {

    @Autowired
    FindOrderByIdService findOrderByIdService;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter order's ID to search: ");
        Long id = scanner.nextLong();
        FindOrderByIdRequest request = new FindOrderByIdRequest(id);
        FindOrderByIdResponse response = findOrderByIdService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> {
                System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage());
            });
        } else {
            System.out.println("Order found by ID: " + id);
            System.out.println(response.getOrder());
        }
    }

}
