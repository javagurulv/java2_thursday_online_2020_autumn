package internet_store.application.core.services.shopping_cart;

import internet_store.application.core.database.jpa.JpaCustomerRepository;
import internet_store.application.core.database.jpa.JpaShoppingCartRepository;
import internet_store.application.core.domain.Customer;
import internet_store.application.core.domain.ShoppingCart;
import internet_store.application.core.requests.shopping_cart.AddShoppingCartRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.shopping_cart.AddShoppingCartResponse;
import internet_store.application.core.services.shopping_cart.validators.AddShoppingCartValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component
@Transactional
public class AddShoppingCartService {

    @Autowired JpaShoppingCartRepository shoppingCartRepository;
    @Autowired JpaCustomerRepository customerRepository;
    @Autowired AddShoppingCartValidator validator;

    public AddShoppingCartResponse execute(AddShoppingCartRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddShoppingCartResponse(errors);
        }
        Customer customer = customerRepository.findById(request.getCustomerId()).get();
        ShoppingCart shoppingCart = new ShoppingCart(customer, true);
        shoppingCartRepository.save(shoppingCart);
        return new AddShoppingCartResponse(shoppingCart);
    }

}
