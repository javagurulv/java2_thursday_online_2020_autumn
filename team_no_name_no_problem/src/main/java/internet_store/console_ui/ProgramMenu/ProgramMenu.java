package internet_store.console_ui.ProgramMenu;

import internet_store.console_ui.UIAction;
import internet_store.console_ui.customer.AddCustomerUIAction;
import internet_store.console_ui.customer.DeleteCustomerUIAction;
import internet_store.console_ui.customer.FindCustomerByIdUIAction;
import internet_store.console_ui.customer.SearchCustomerUIAction;
//import internet_store.console_ui.order.GetOrdersUIAction;
import internet_store.console_ui.order.GetOrdersUIAction;
import internet_store.console_ui.product.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

//@Component
public class ProgramMenu {

    private Map<Integer, UIAction> menuNumberToActionMap = new HashMap<>();

    @Autowired
    public ProgramMenu (List<UIAction> uiActionList) {
        menuNumberToActionMap.put(1, findUIAction(uiActionList, AddProductUIAction.class));
        menuNumberToActionMap.put(2, findUIAction(uiActionList, SearchByIdUIAction.class));
        menuNumberToActionMap.put(3, findUIAction(uiActionList, SearchProductUIAction.class));
        menuNumberToActionMap.put(4, findUIAction(uiActionList, ChangeProductUIAction.class));
        menuNumberToActionMap.put(5, findUIAction(uiActionList, DeleteProductByIdUIAction.class));
        menuNumberToActionMap.put(6, findUIAction(uiActionList, DeleteProductByOtherUIAction.class));
        menuNumberToActionMap.put(7, findUIAction(uiActionList, GetAllProductsUIAction.class));
        menuNumberToActionMap.put(8, findUIAction(uiActionList, GetOrdersUIAction.class));
        // Deletecustomer ui

        menuNumberToActionMap.put(9, findUIAction(uiActionList,  AddCustomerUIAction.class));
        menuNumberToActionMap.put(10, findUIAction(uiActionList, DeleteCustomerUIAction.class));
        menuNumberToActionMap.put(11, findUIAction(uiActionList, GetAllProductsUIAction.class));
        menuNumberToActionMap.put(12, findUIAction(uiActionList, BuyProductUIAction.class));
        // Get my order list

        menuNumberToActionMap.put(13, findUIAction(uiActionList, FindCustomerByIdUIAction.class));
        menuNumberToActionMap.put(14, findUIAction(uiActionList, SearchCustomerUIAction.class));

        menuNumberToActionMap.put(0, new ExitFromApplicationUIAction());
    }

    public void executeUIAction (int selectedMenuNumber) {
        menuNumberToActionMap.get(selectedMenuNumber).execute();
    }

    private UIAction findUIAction(List<UIAction> uiActions, Class uiActionClass) {
        return uiActions.stream()
                .filter(uiAction -> uiAction.getClass().equals(uiActionClass))
                .findFirst()
                .get();
    }

    public void printMenu () {

        System.out.println("\nAdministrator Menu\n\n" +
                "1   Add product\n" +
                "2   Search product by ID\n" +
                "3   Search product by other criteria\n"+
                "4   Change product\n" +
                "5   Delete product by ID\n" +
                "6   Delete product by other criteria\n" +
                "                                    \n" +
                "7   Get list of products\n" +
                "8   Get order list\n" +
                "*****************************");

        System.out.println("\nCustomer Menu\n\n" +
                "9   Sign in\n" +
                "10  Delete account by ID\n" +
                "11  See product list\n" +
                "12  Buy product\n\n"+
                "13  Search customer by ID\n" +
                "14  Search customer by other criteria\n" +
                "*****************************\n");

        System.out.println("0   Exit\n");
    }

    public int inputValidInteger(String message) {
        int input;
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println(message);
            while (true) {
                try {
                    input = Integer.parseInt(in.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("It's not valid number! Please input valid number!");
                }
            }

            if (input >= 0) {
                break;
            } else {
                System.out.println("Please enter valid value!");
            }
        }
        return input;
    }
}
