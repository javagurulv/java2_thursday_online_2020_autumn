package internet_store.application.console_ui.shopping_cart_item;

import internet_store.application.console_ui.UIAction;
import internet_store.application.core.requests.shopping_cart_item.FindAllShoppingCartItemsRequest;
import internet_store.application.core.responses.shopping_cart_item.FindAllShoppingCartItemsResponse;
import internet_store.application.core.services.shopping_cart_item.FindAllShoppingCartItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class FindAllShoppingCartItemsUIService implements UIAction {


    @Autowired private FindAllShoppingCartItemsService service;

    @Override
    public void execute() {
        System.out.println("Printing all shopping cart items : ");

        FindAllShoppingCartItemsRequest request = new FindAllShoppingCartItemsRequest();

        FindAllShoppingCartItemsResponse response = service.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
        } else {
            System.out.println("Printing out all shopping cart items : " );
            response.getProductShoppingCartList()
                    .forEach(System.out::println);
        }
    }

}
