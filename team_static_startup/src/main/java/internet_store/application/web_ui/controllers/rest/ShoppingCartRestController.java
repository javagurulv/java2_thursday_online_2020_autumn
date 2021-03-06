package internet_store.application.web_ui.controllers.rest;

import internet_store.application.core.requests.shopping_cart.*;
import internet_store.application.core.responses.shopping_cart.*;
import internet_store.application.core.services.shopping_cart.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopping_cart")
public class ShoppingCartRestController {

    @Autowired
    private AddShoppingCartService addShoppingCartService;
    @Autowired
    private FindAllShoppingCartsService findAllShoppingCartsService;
    @Autowired
    private FindShoppingCartByIdService findShoppingCartByIdService;

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public AddShoppingCartResponse addShoppingCart(@RequestBody AddShoppingCartRequest request) {
        return addShoppingCartService.execute(request);
    }

    @GetMapping(path = "/", produces = "application/json")
    public FindAllShoppingCartsResponse findAllShoppingCarts() {
        return findAllShoppingCartsService.execute();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public FindShoppingCartByIdResponse findShoppingCartById(@PathVariable Long id) {
        FindShoppingCartByIdRequest request = new FindShoppingCartByIdRequest(id);
        return findShoppingCartByIdService.execute(request);
    }

}
