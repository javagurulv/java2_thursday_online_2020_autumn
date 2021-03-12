package internet_store.application.console_ui.shopping_cart_item;

import internet_store.application.console_ui.UIAction;
import internet_store.application.core.requests.shopping_cart_item.FindShoppingCartItemByIdRequest;
import internet_store.application.core.responses.shopping_cart_item.FindShoppingCartItemByIdResponse;
import internet_store.application.core.services.shopping_cart_item.FindShoppingCartItemByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

//@Component
public class FindShoppingCartItemByIdUIService implements UIAction {


    @Autowired private FindShoppingCartItemByIdService service;

    @Override
    public void execute() {
        Scanner myInput = new Scanner(System.in);
        System.out.println("Finding shopping cart item by ID : ");
        System.out.print("Enter Shopping Item ID : ");
        Long shoppingCartItemId = myInput.nextLong();

        FindShoppingCartItemByIdRequest request = new FindShoppingCartItemByIdRequest(shoppingCartItemId);

        FindShoppingCartItemByIdResponse response = service.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
        } else {
            System.out.println("Shopping cart item found: " + request.getShoppingCartItemId());
        }
    }

}
