package internet_store.application.console_ui.shopping_cart;

import internet_store.application.console_ui.UIAction;
import internet_store.application.core.database.shopping_cart.ShoppingCartRepository;
import internet_store.application.core.domain.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindAllShoppingCartsUIAction implements UIAction {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Override
    public void execute() {
        List<ShoppingCart> shoppingCarts = shoppingCartRepository.findAll();
        shoppingCarts.forEach(System.out::println);
    }

}
