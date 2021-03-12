package internet_store.application.core.services.shopping_cart;

import internet_store.application.core.database.jpa.JpaShoppingCartRepository;
import internet_store.application.core.responses.shopping_cart.FindAllShoppingCartsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindAllShoppingCartsService {

    @Autowired
    JpaShoppingCartRepository shoppingCartRepository;

    public FindAllShoppingCartsResponse execute() {
        return new FindAllShoppingCartsResponse(shoppingCartRepository.findAll());
    }

}
