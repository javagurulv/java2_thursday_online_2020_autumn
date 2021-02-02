package internet_store.application;

import internet_store.application.config.AppConfig;
import internet_store.application.console_ui.*;
import internet_store.application.console_ui.customer.*;
import internet_store.application.console_ui.order.AddOrderUIAction;
import internet_store.application.console_ui.order.FindAllOrdersUIAction;
import internet_store.application.console_ui.order.FindOrderByIdUIAction;
import internet_store.application.console_ui.product.*;
import internet_store.application.console_ui.shopping_cart.AddShoppingCartUIAction;
import internet_store.application.console_ui.shopping_cart.FindAllShoppingCartsUIAction;
import internet_store.application.console_ui.shopping_cart.FindShoppingCartByIdUIAction;
import internet_store.application.console_ui.shopping_cart_item.AddShoppingCartItemUIAction;
import internet_store.application.console_ui.shopping_cart_item.FindAllShoppingCartItemsUIAction;
import internet_store.application.console_ui.shopping_cart_item.FindShoppingCartItemByIdUIAction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class StartApplication {

    private static final ApplicationContext applicationContext
            = new AnnotationConfigApplicationContext(AppConfig.class);

    private final Map<Integer, UIAction> menuNumberToActionMap;

    public StartApplication() {
        menuNumberToActionMap = new HashMap<>();
        menuNumberToActionMap.put(1, applicationContext.getBean(AddProductUIAction.class));
        menuNumberToActionMap.put(2, applicationContext.getBean(DeleteByProductIdUIAction.class));
        menuNumberToActionMap.put(3, applicationContext.getBean(DeleteByProductUIAction.class));
        menuNumberToActionMap.put(4, applicationContext.getBean(DeleteByProductNameUIAction.class));
        menuNumberToActionMap.put(5, applicationContext.getBean(FindByProductIdUIAction.class));
        menuNumberToActionMap.put(6, applicationContext.getBean(FindProductsUIAction.class));
        menuNumberToActionMap.put(7, applicationContext.getBean(ChangeProductNameUIAction.class));
        menuNumberToActionMap.put(8, applicationContext.getBean(GetAllProductsUIAction.class));
        menuNumberToActionMap.put(10, applicationContext.getBean(AddCustomerUIAction.class));
        menuNumberToActionMap.put(11, applicationContext.getBean(DeleteByCustomerIdUIAction.class));
        menuNumberToActionMap.put(12, applicationContext.getBean(FindByCustomerIdUIAction.class));
        menuNumberToActionMap.put(13, applicationContext.getBean(ChangeCustomerFirstNameUIAction.class));
        menuNumberToActionMap.put(14, applicationContext.getBean(GetAllCustomersUIAction.class));
        menuNumberToActionMap.put(15, applicationContext.getBean(FindByCustomerFirstNameUIAction.class));
        menuNumberToActionMap.put(16, applicationContext.getBean(AddShoppingCartUIAction.class));
        menuNumberToActionMap.put(17, applicationContext.getBean(FindShoppingCartByIdUIAction.class));
        menuNumberToActionMap.put(18, applicationContext.getBean(FindAllShoppingCartsUIAction.class));
        menuNumberToActionMap.put(19, applicationContext.getBean(AddOrderUIAction.class));
        menuNumberToActionMap.put(20, applicationContext.getBean(FindOrderByIdUIAction.class));
        menuNumberToActionMap.put(21, applicationContext.getBean(FindAllOrdersUIAction.class));
        menuNumberToActionMap.put(22, applicationContext.getBean(AddShoppingCartItemUIAction.class));
        menuNumberToActionMap.put(23, applicationContext.getBean(FindShoppingCartItemByIdUIAction.class));
        menuNumberToActionMap.put(24, applicationContext.getBean(FindAllShoppingCartItemsUIAction.class));
        menuNumberToActionMap.put(0, applicationContext.getBean(ExitProgramUIAction.class));
    }

    public static void main(String[] args) {
        StartApplication startApplication = new StartApplication();
        startApplication.run();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            printOutMenu();
            try {
                executeUIAction(Integer.parseInt(sc.nextLine()));
            } catch (NumberFormatException e) {
                System.out.println("\nIncorrect input, please enter number");
            }
        }
    }

    private void printOutMenu() {
        System.out.println("\n1. Add product to database");
        System.out.println("2. Delete product from database by ID");
        System.out.println("3. Delete product from database by name and description");
        System.out.println("4. Delete product from database by name");
        System.out.println("5. Find product(s) from database by ID");
        System.out.println("6. Find product(s) from database by name and(or) description");
        System.out.println("7. Find product from database by ID and change name");
        System.out.println("8. Print out all database products");
        System.out.println("------------------------------------------------------------");
        System.out.println("10. Add customer to database");
        System.out.println("11. Delete customer from database by ID");
        System.out.println("12. Find customer from database by ID");
        System.out.println("13. Find customer from database by ID and change FirstName");
        System.out.println("14. Print out all customers");
        System.out.println("15. Find customer(s) by first name: ");
        System.out.println("------------------------------------------------------------");
        System.out.println("16. Add shopping cart to database: ");
        System.out.println("17. Find shopping cart by ID: ");
        System.out.println("18. Find all shopping carts: ");
        System.out.println("------------------------------------------------------------");
        System.out.println("19. Add order to database: ");
        System.out.println("20. Find order by ID: ");
        System.out.println("21. Find all orders: ");
        System.out.println("------------------------------------------------------------");
        System.out.println("22. Add shopping cart item to database: ");
        System.out.println("23. Find shopping cart item by ID: ");
        System.out.println("24. Find all shopping cart items: ");
        System.out.println("------------------------------------------------------------");
        System.out.println("0. Exit the program");
        System.out.println("------------------------------------------------------------");
        System.out.print("Please enter menu number: ");
    }

    private void executeUIAction(int userSelectedMenuNumber) {
        UIAction uiAction = menuNumberToActionMap.get(userSelectedMenuNumber);
        if (uiAction != null) {
            uiAction.execute();
        } else {
            System.out.println("Menu item doesn't exist: " + userSelectedMenuNumber);
        }
    }

}
