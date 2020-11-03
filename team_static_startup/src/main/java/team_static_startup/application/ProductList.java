package team_static_startup.application;

import team_static_startup.application.uiaction.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class ProductList {

    private final Map<Integer, UIAction> menuNumberToActionMap;

    public ProductList() {
        ProductDatabase productDatabase = new ProductDatabaseImpl();

        menuNumberToActionMap = new HashMap<>();
        menuNumberToActionMap.put(1, new SaveProductUIAction(productDatabase));
        menuNumberToActionMap.put(2, new DeleteByIdUIAction(productDatabase));
        menuNumberToActionMap.put(3, new DeleteProductUIAction(productDatabase));
        menuNumberToActionMap.put(4, new DeleteByProductNameUIAction(productDatabase));
        menuNumberToActionMap.put(5, new PrintProductsToConsoleUIAction(productDatabase));
        menuNumberToActionMap.put(6, new FindByProductNameUIAction(productDatabase));
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add product to database");
            System.out.println("2. Delete product from database by ID");
            System.out.println("3. Delete product from database by name and description");
            System.out.println("4. Delete product from database by name");
            System.out.println("5. Print out all database products");
            System.out.println("6. Find product(s) from database by name");
            System.out.println("0. Exit the program");
            System.out.println("---------------------------------------------------------");
            System.out.print("Please enter menu number: ");
            int userSelectedMenuNumber = Integer.parseInt(sc.nextLine());
            if (userSelectedMenuNumber == 0) {
                System.out.println("Thank you! Good bye!");
                break;
            } else {
                executeUIAction(userSelectedMenuNumber);
            }
        }
    }

    private void executeUIAction(int userSelectedMenuNumber) {
        UIAction uiAction = menuNumberToActionMap.get(userSelectedMenuNumber);
        if (uiAction != null) {
            uiAction.execute();
        } else {
            System.out.println("Menu item not exist: " + userSelectedMenuNumber);
        }
    }

}
