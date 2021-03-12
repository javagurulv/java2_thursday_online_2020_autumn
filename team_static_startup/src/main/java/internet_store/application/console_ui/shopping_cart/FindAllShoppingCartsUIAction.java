package internet_store.application.console_ui.shopping_cart;

import internet_store.application.console_ui.UIAction;
import internet_store.application.core.responses.shopping_cart.FindAllShoppingCartsResponse;
import internet_store.application.core.services.shopping_cart.FindAllShoppingCartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class FindAllShoppingCartsUIAction implements UIAction {

    @Autowired
    FindAllShoppingCartsService findAllShoppingCartsService;

    @Override
    public void execute() {
        FindAllShoppingCartsResponse response = findAllShoppingCartsService.execute();
        response.getShoppingCarts().forEach(System.out::println);
    }

}
