package internet_store.application;

import internet_store.application.config.ProductListConfiguration;
import internet_store.application.console_ui.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class ProductListApplication {

    private static final ApplicationContext applicationContext
            = new AnnotationConfigApplicationContext(ProductListConfiguration.class);

    private final Map<Integer, UIAction> menuNumberToActionMap;

    public ProductListApplication() {
        menuNumberToActionMap = new HashMap<>();
        menuNumberToActionMap.put(1, applicationContext.getBean(AddProductUIAction.class));
        menuNumberToActionMap.put(2, applicationContext.getBean(DeleteByIdUIAction.class));
        menuNumberToActionMap.put(3, applicationContext.getBean(DeleteByProductUIAction.class));
        menuNumberToActionMap.put(4, applicationContext.getBean(DeleteByProductNameUIAction.class));
        menuNumberToActionMap.put(5, applicationContext.getBean(FindByIdUIAction.class));
        menuNumberToActionMap.put(6, applicationContext.getBean(FindProductsUIAction.class));
        menuNumberToActionMap.put(7, applicationContext.getBean(ChangeProductNameUIAction.class));
        menuNumberToActionMap.put(8, applicationContext.getBean(GetAllProductsUIAction.class));
        menuNumberToActionMap.put(9, applicationContext.getBean(AddOrderUIAction.class));
        menuNumberToActionMap.put(10, applicationContext.getBean(FindOrderByIdUIAction.class));
        menuNumberToActionMap.put(11, applicationContext.getBean(FindAllOrdersUIAction.class));
        menuNumberToActionMap.put(12, applicationContext.getBean(AddCustomerUIAction.class));
        menuNumberToActionMap.put(13, applicationContext.getBean(FindCustomerByIdUIAction.class));
        menuNumberToActionMap.put(14, applicationContext.getBean(FindAllCustomersUIAction.class));
        menuNumberToActionMap.put(0, applicationContext.getBean(ExitProgramUIAction.class));
    }

    public static void main(String[] args) {
        ProductListApplication productListApplication = new ProductListApplication();
        productListApplication.run();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            printOutMenu();
            try {
                int userSelectedMenuNumber = Integer.parseInt(sc.nextLine());
                executeUIAction(userSelectedMenuNumber);
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
        System.out.println("7. Find product(s) from database by ID and change name");
        System.out.println("8. Print out all database products");
        System.out.println("9. Add order to database");
        System.out.println("10. Find order by ID in database");
        System.out.println("11. Print out all orders");
        System.out.println("12. Add customer to database");
        System.out.println("13. Find customer by ID in database");
        System.out.println("14. Print out all customers");
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
