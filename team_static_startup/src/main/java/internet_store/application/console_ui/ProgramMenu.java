package internet_store.application.console_ui;

import internet_store.application.console_ui.customer.*;
import internet_store.application.console_ui.order.*;
import internet_store.application.console_ui.product.*;
import internet_store.application.console_ui.shopping_cart.*;
import internet_store.application.console_ui.shopping_cart_item.*;
import internet_store.application.core.services.ExitProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

//@Component
public class ProgramMenu {

    private final Map<Integer, UIAction> menuNumberToUIActionMap;

    @Autowired
    public ProgramMenu(List<UIAction> uiActions) {
        menuNumberToUIActionMap = new HashMap<>();
        menuNumberToUIActionMap.put(1, findUIAction(uiActions, AddProductUIAction.class));
        menuNumberToUIActionMap.put(2, findUIAction(uiActions, DeleteByProductIdUIAction.class));
        menuNumberToUIActionMap.put(3, findUIAction(uiActions, DeleteByProductUIAction.class));
        menuNumberToUIActionMap.put(4, findUIAction(uiActions, DeleteByProductNameUIAction.class));
        menuNumberToUIActionMap.put(5, findUIAction(uiActions, FindByProductIdUIAction.class));
        menuNumberToUIActionMap.put(6, findUIAction(uiActions, FindProductsUIAction.class));
        menuNumberToUIActionMap.put(7, findUIAction(uiActions, ChangeProductNameUIAction.class));
        menuNumberToUIActionMap.put(8, findUIAction(uiActions, GetAllProductsUIAction.class));
        menuNumberToUIActionMap.put(10, findUIAction(uiActions, AddCustomerUIAction.class));
        menuNumberToUIActionMap.put(11, findUIAction(uiActions, DeleteByCustomerIdUIAction.class));
        menuNumberToUIActionMap.put(12, findUIAction(uiActions, FindByCustomerIdUIAction.class));
        menuNumberToUIActionMap.put(13, findUIAction(uiActions, ChangeCustomerFirstNameUIAction.class));
        menuNumberToUIActionMap.put(14, findUIAction(uiActions, GetAllCustomersUIAction.class));
        menuNumberToUIActionMap.put(15, findUIAction(uiActions, FindByCustomerFirstNameUIAction.class));
        menuNumberToUIActionMap.put(16, findUIAction(uiActions, AddShoppingCartItemUIService.class));
        menuNumberToUIActionMap.put(17, findUIAction(uiActions, FindShoppingCartItemByIdUIService.class));
        menuNumberToUIActionMap.put(18, findUIAction(uiActions, FindAllShoppingCartItemsUIService.class));
        menuNumberToUIActionMap.put(19, findUIAction(uiActions, AddShoppingCartUIAction.class));
        menuNumberToUIActionMap.put(20, findUIAction(uiActions, FindShoppingCartByIdUIAction.class));
        menuNumberToUIActionMap.put(21, findUIAction(uiActions, FindAllShoppingCartsUIAction.class));
        menuNumberToUIActionMap.put(22, findUIAction(uiActions, AddOrderUIAction.class));
        menuNumberToUIActionMap.put(23, findUIAction(uiActions, FindOrderByIdUIAction.class));
        menuNumberToUIActionMap.put(24, findUIAction(uiActions, FindAllOrdersUIAction.class));
        menuNumberToUIActionMap.put(0, findUIAction(uiActions, ExitProgramService.class));
    }

    private UIAction findUIAction(List<UIAction> uiActions, Class uiActionClass) {
        return uiActions.stream()
                .filter(uiAction -> uiAction.getClass().equals(uiActionClass))
                .findFirst()
                .get();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void runMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            printMenu();
            try {
                executeSelectedMenuItem(Integer.parseInt(sc.nextLine()));
            } catch (NumberFormatException e) {
                System.out.println("\nIncorrect input, please enter number");
            }
        }
    }

    public void printMenu() {
        System.out.println("1. Add product to database");
        System.out.println("2. Delete product from database by ID");
        System.out.println("3. Delete product from database by name and description");
        System.out.println("4. Delete product from database by name");
        System.out.println("5. Find product(s) from database by ID");
        System.out.println("\n6. Find product(s) from database by name and(or) description");
        System.out.println("7. Find product from database by ID and change name");
        System.out.println("8. Print out all database products");
        System.out.println("------------------------------------------------------------");
        System.out.println("10. Add customer to database");
        System.out.println("11. Delete customer from database by ID");
        System.out.println("12. Find customer from database by ID");
        System.out.println("13. Find customer from database by ID and change FirstName");
        System.out.println("14. Print out all customers");
        System.out.println("15. Find customer(s) by first name");
        System.out.println("------------------------------------------------------------");
        System.out.println("16. Add shopping cart item: ");
        System.out.println("17. Find shopping cart item by ID: ");
        System.out.println("18. Print all shopping cart items: ");
        System.out.println("------------------------------------------------------------");
        System.out.println("19. Add shopping cart");
        System.out.println("20. Find shopping cart by ID");
        System.out.println("21. Find all shopping carts");
        System.out.println("------------------------------------------------------------");
        System.out.println("22. Add order");
        System.out.println("23. Find order by ID");
        System.out.println("24. Find all orders");
        System.out.println("------------------------------------------------------------");
        System.out.println("0. Exit the program");
        System.out.println("------------------------------------------------------------");
        System.out.print("Please enter menu number: ");
    }

    public void executeSelectedMenuItem(int selectedMenu) {
        menuNumberToUIActionMap.get(selectedMenu).execute();
    }

}