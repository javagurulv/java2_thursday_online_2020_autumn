package eu.retarded.internetstore.core.services.user;

import eu.retarded.internetstore.core.domain.Cart;
import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.user.AddProductToUserCartRequest;
import eu.retarded.internetstore.core.responses.user.AddProductToUserCartResponse;
import eu.retarded.internetstore.database.CartRepository;
import eu.retarded.internetstore.database.ProductRepository;
import eu.retarded.internetstore.database.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class AddProductToUserCartServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private Validator validator;

    @InjectMocks
    private AddProductToUserCartService subject;

    @Test
    void add_product_to_user_cart_success() {
        AddProductToUserCartRequest request = new AddProductToUserCartRequest(1L, 1L, 4);
        Mockito.when(validator.validate(request))
                .thenReturn(new HashSet<ConstraintViolation<AddProductToUserCartRequest>>());
        Product product = new Product("Igor12345", "1234567890qwertyuiopasdfghjklzxcvbnm1234567890",
                345,100);
        Cart cart = new Cart();
        cart.setId(1L);
        User user = new User();
        user.setCart(cart);
        Map<Product, Integer> products= new HashMap<>();
        products.put(product,5);
        cart.setProducts(products);
        Cart result = new Cart();
        result.setId(1L);
        result.setProducts(products);


        Mockito.when(userRepository.getOne(1L)).thenReturn(user);
        Mockito.when(productRepository.getOne(1L)).thenReturn(product);
        Mockito.when(cartRepository.getOne(1L)).thenReturn(cart);


        AddProductToUserCartResponse addProductToUserCartResponse = subject.execute(request);
        assertThat(addProductToUserCartResponse.getCart()).isEqualTo(result);
    }

}