package internet_store.console_ui.shopping_cart;

import internet_store.console_ui.UIAction;
import internet_store.core.services.shopping_cart.AddToShoppingCartService;

import java.util.Scanner;


public class AddProductToShoppingCartUIAction implements UIAction {

    private final AddToShoppingCartService addToShoppingCarService;

    public AddProductToShoppingCartUIAction(AddToShoppingCartService addToShoppingCarService) {
        this.addToShoppingCarService = addToShoppingCarService;
    }

    @Override
    public void execute() {

        Scanner in = new Scanner(System.in);

        System.out.println("To add product in Shopping Cart, please enter ID of this product: ");
        long id = in.nextLong();

        System.out.println("Please enter quantity of this product");
        Integer quantity = in.nextInt();
    }
}
