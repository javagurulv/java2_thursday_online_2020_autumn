package internet_store.application.console_ui.shopping_cart_item;

import internet_store.application.console_ui.UIAction;
import internet_store.application.core.requests.shopping_cart_item.AddShoppingCartItemRequest;
import internet_store.application.core.responses.shopping_cart_item.AddShoppingCartItemResponse;
import internet_store.application.core.services.shopping_cart_item.AddShoppingCartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

//@Component
public class AddShoppingCartItemUIService implements UIAction {


    @Autowired private AddShoppingCartItemService service;

    @Override
    public void execute() {
        Scanner myInput = new Scanner(System.in);
        System.out.println("Adding CartItem to database : ");
        System.out.print("Enter Shopping Cart Id : ");
        Long shoppingCartId = myInput.nextLong();
        System.out.print("Enter Product Id : ");
        Long productId = myInput.nextLong();
        System.out.print("Enter Quantity : ");
        Long quantity = myInput.nextLong();

        AddShoppingCartItemRequest request = new AddShoppingCartItemRequest(shoppingCartId, productId, quantity);

        AddShoppingCartItemResponse response = service.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
        } else {
            System.out.println("Product with id : " + productId
                    + ", with quantity : " + quantity
                    + ", added to cart item with id : " + shoppingCartId);
        }
    }

}
