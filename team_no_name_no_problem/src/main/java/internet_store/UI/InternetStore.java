package internet_store.UI;

import internet_store.ProductDatabase;
import internet_store.ProductDatabaseImpl;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InternetStore {

    private Map<Integer, UIAction> menuNumberToAction;

    public InternetStore() {
        ProductDatabase productDatabase = new ProductDatabaseImpl();

        menuNumberToAction = new HashMap();

        menuNumberToAction.put(1, new AddItemUIAction(productDatabase));
        menuNumberToAction.put(2, new DeleteByIdUIAction(productDatabase));
        menuNumberToAction.put(3, new PrintProductsUIAction(productDatabase));
        menuNumberToAction.put(4, new ChangeTitleUIAction(productDatabase));
        menuNumberToAction.put(5, new ChangeDescriptionUIAction(productDatabase));
        menuNumberToAction.put(6, new FindAnyByTitleUIAction(productDatabase));
        menuNumberToAction.put(7, new FindAllByTitleUIAction(productDatabase));
    }

    public void run() {

        while (true) {
            System.out.println("\nMenu\n" +
                    "1   Add item\n" +
                    "2   Delete by id\n" +
                    "3   Print products\n" +
                    "4   Change title\n" +
                    "5   Change description\n" +
                    "6   Find any by title\n" +
                    "7   Find all by title\n" +
                    "0   Exit");

            Scanner in = new Scanner(System.in);
            int userSelectedMenuNumber;

            while (true) {
                try {
                    System.out.println("Please enter menu number: ");
                    userSelectedMenuNumber = Integer.parseInt(in.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("It's not valid number!");
                }
            }

            if (userSelectedMenuNumber == 0) {
                System.out.println(":) End of work day!");
                break;
            } else {
                executeUIAction(userSelectedMenuNumber);
            }
        }
    }

    private void executeUIAction (int userSelectedMenuNumber) {
        UIAction uiAction = menuNumberToAction.get(userSelectedMenuNumber);
        if (uiAction != null) {
            uiAction.execute();
        } else {
            System.out.println("Menu item does not exist: " + userSelectedMenuNumber);
        }
    }

}

