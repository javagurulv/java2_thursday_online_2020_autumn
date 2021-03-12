package adventure_time.ui;

import adventure_time.ui.customers.*;
import adventure_time.ui.menus.*;
import adventure_time.ui.events.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


@Component
public class AdminMenu {

    private final Map<Integer, UIAction> menuFunctionMap;

    @Autowired
    public AdminMenu(List<UIAction> uiActionList) {
        menuFunctionMap = new HashMap<>();
        //Customers
        menuFunctionMap.put(21, findUIAction(uiActionList, ToMainMenuUIAction.class));
        menuFunctionMap.put(22, findUIAction(uiActionList, AddCustomerUIAction.class));
        menuFunctionMap.put(23, findUIAction(uiActionList, RemoveCustomerUIAction.class));
        menuFunctionMap.put(24, findUIAction(uiActionList, SearchCustomerUIAction.class));
        menuFunctionMap.put(26, findUIAction(uiActionList, ShowCustomersUIAction.class));
        menuFunctionMap.put(25, findUIAction(uiActionList, UpdateCustomerUIAction.class));
        //Tickets
        menuFunctionMap.put(51, findUIAction(uiActionList, ToMainMenuUIAction.class));
        menuFunctionMap.put(52, findUIAction(uiActionList, ToMainMenuUIAction.class));
        menuFunctionMap.put(53, findUIAction(uiActionList, ToMainMenuUIAction.class));
        menuFunctionMap.put(54, findUIAction(uiActionList, ToMainMenuUIAction.class));
        menuFunctionMap.put(55, findUIAction(uiActionList, ToMainMenuUIAction.class));
        menuFunctionMap.put(56, findUIAction(uiActionList, ToMainMenuUIAction.class));
        //Events
        menuFunctionMap.put(31, findUIAction(uiActionList, ToMainMenuUIAction.class));
        menuFunctionMap.put(32, findUIAction(uiActionList, AddEventUIAction.class));
        menuFunctionMap.put(33, findUIAction(uiActionList, RemoveEventUIAction.class));
        menuFunctionMap.put(34, findUIAction(uiActionList, SearchEventUIAction.class));
        menuFunctionMap.put(35, findUIAction(uiActionList, UpdateUIAction.class));
        menuFunctionMap.put(36, findUIAction(uiActionList, ShowListOfEventsUIAction.class));
        //Tours
        menuFunctionMap.put(41, findUIAction(uiActionList, ToMainMenuUIAction.class));
        menuFunctionMap.put(42, findUIAction(uiActionList, ToMainMenuUIAction.class));
        menuFunctionMap.put(43, findUIAction(uiActionList, ToMainMenuUIAction.class));
        menuFunctionMap.put(44, findUIAction(uiActionList, ToMainMenuUIAction.class));
        menuFunctionMap.put(45, findUIAction(uiActionList, ToMainMenuUIAction.class));
        menuFunctionMap.put(46, findUIAction(uiActionList, ToMainMenuUIAction.class));
        //Exit
        menuFunctionMap.put(12, findUIAction(uiActionList, ToMainMenuUIAction.class));
        menuFunctionMap.put(11, findUIAction(uiActionList, ExitUIAction.class));
    }

    public void executeSelectedMenuItem(int selectedMenuItem) {
        menuFunctionMap.get(selectedMenuItem).execute();
    }

    private UIAction findUIAction(List<UIAction> uiActionList, Class uiActionClass) {
        return uiActionList.stream()
                .filter(uiAction -> uiAction.getClass().equals(uiActionClass))
                .findFirst()
                .get();
    }

    public Integer getUserChoice() {
        System.out.println("Enter menu item number to execute:");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    public void mainMenu() {
        System.out.println("MAIN MENU:");
        System.out.println("1. EXIT");
        System.out.println("2. CUSTOMERS");
        System.out.println("3. EVENTS");
        System.out.println("4. TOURS");
        System.out.println("5. TICKETS");

        System.out.println();
    }

    public Map<Integer, SubjectMenu> subjectMenuSelect () {
        Map<Integer, SubjectMenu> menuMap = new HashMap<>();
        menuMap.put(2, new MenuForCustomer());
        menuMap.put(3, new MenuForEvent());
        menuMap.put(4, new MenuForTour());
        menuMap.put(5, new MenuForTicket());
        menuMap.put(1, new ExitMenu());
        return menuMap;

    }

}
