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
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Adding product to Database");
            System.out.println("2. Deleting product from Database by ID");
            System.out.println("3. Deleting product from Database by name and description");
            System.out.println("4. Deleting product from Database by name");
            System.out.println("0. Exit the program");
            System.out.println("---------------------------------------------------------");
            System.out.print("Please enter menu number: ");
            int userSelectedMenuNumber = Integer.parseInt(sc.nextLine());
            if (userSelectedMenuNumber == 0) {
                System.out.println("Thank you! Good by!");
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
