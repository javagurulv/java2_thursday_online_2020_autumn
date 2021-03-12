package internet_store.application.console_ui.shopping_cart;

import internet_store.application.console_ui.UIAction;
import internet_store.application.core.requests.shopping_cart.FindShoppingCartByIdRequest;
import internet_store.application.core.responses.shopping_cart.FindShoppingCartByIdResponse;
import internet_store.application.core.services.shopping_cart.FindShoppingCartByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

//@Component
public class FindShoppingCartByIdUIAction implements UIAction {

    @Autowired
    FindShoppingCartByIdService findShoppingCartByIdService;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter shopping cart's ID to search: ");
        Long id = scanner.nextLong();
        FindShoppingCartByIdRequest request = new FindShoppingCartByIdRequest(id);
        FindShoppingCartByIdResponse response = findShoppingCartByIdService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> {
                System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage());
            });
        } else {
            System.out.println("Shopping cart found by ID: " + id);
            System.out.println(response.getShoppingCart());
        }
    }

}
