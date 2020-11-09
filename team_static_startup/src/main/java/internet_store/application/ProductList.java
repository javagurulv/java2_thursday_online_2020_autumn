package internet_store.application;

import internet_store.application.database.Database;
import internet_store.application.database.InMemoryDatabase;
import internet_store.application.services.DeleteProductService;
import internet_store.application.services.FindProductService;
import internet_store.application.services.GetProductListService;
import internet_store.application.ui.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class ProductList {

    private final Map<Integer, UIAction> menuNumberToActionMap;
    private final Database database = new InMemoryDatabase();

    FindProductService findProductService = new FindProductService(database);
    GetProductListService getProductListService = new GetProductListService(database);
    DeleteProductService deleteProductService = new DeleteProductService(database);

    public ProductList() {

        menuNumberToActionMap = new HashMap<>();
        menuNumberToActionMap.put(1, new AddProductUIAction(database));
        menuNumberToActionMap.put(2, new DeleteByIdUIAction(deleteProductService));
        menuNumberToActionMap.put(3, new DeleteProductUIAction(deleteProductService));
        menuNumberToActionMap.put(4, new DeleteByProductNameUIAction(deleteProductService));
        menuNumberToActionMap.put(5, new PrintProductsToConsoleUIAction(getProductListService));
        menuNumberToActionMap.put(6, new FindByProductNameUIAction(findProductService));
        menuNumberToActionMap.put(7, new FindByIdUIAction(findProductService));
        menuNumberToActionMap.put(8, new ChangeProductNameUIAction(database));
        menuNumberToActionMap.put(0, new ExitProgramUIAction());
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            printOutMenu();
            try {
                int userSelectedMenuNumber = Integer.parseInt(sc.nextLine());
                if (userSelectedMenuNumber == 0) {
                    System.out.println("Thank you! Good bye!");
                    break;
                } else {
                    executeUIAction(userSelectedMenuNumber);
                }
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
        System.out.println("5. Print out all database products");
        System.out.println("6. Find product(s) from database by name");
        System.out.println("7. Find product(s) from database by ID");
        System.out.println("8. Find product(s) from database by ID and change name");
        System.out.println("0. Exit the program");
        System.out.println("---------------------------------------------------------");
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
