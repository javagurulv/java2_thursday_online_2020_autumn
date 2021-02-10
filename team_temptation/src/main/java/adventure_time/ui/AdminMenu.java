package adventure_time.ui;

import adventure_time.ui.customers.*;
import adventure_time.ui.guides.AddGuideUIAction;
import adventure_time.ui.guides.DisplayGuideUIAction;
import adventure_time.ui.guides.RemoveGuideUIAction;
import adventure_time.ui.guides.SearchGuideUIAction;
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
        menuFunctionMap.put(11, findUIAction(uiActionList, ToMainMenuUIAction.class));
        menuFunctionMap.put(12, findUIAction(uiActionList, AddCustomerUIAction.class));
        menuFunctionMap.put(13, findUIAction(uiActionList, RemoveCustomerUIAction.class));
        menuFunctionMap.put(14, findUIAction(uiActionList, SearchCustomerUIAction.class));
        menuFunctionMap.put(15, findUIAction(uiActionList, DisplayCustomerUIAction.class));
        menuFunctionMap.put(16, findUIAction(uiActionList, UpdateCustomerUIAction.class));

        menuFunctionMap.put(21, findUIAction(uiActionList, ToMainMenuUIAction.class));
        menuFunctionMap.put(22, findUIAction(uiActionList, AddGuideUIAction.class));
        menuFunctionMap.put(23, findUIAction(uiActionList, RemoveGuideUIAction.class));
        menuFunctionMap.put(24, findUIAction(uiActionList, SearchGuideUIAction.class));
        menuFunctionMap.put(25, findUIAction(uiActionList, DisplayGuideUIAction.class));
        menuFunctionMap.put(26, findUIAction(uiActionList, ToMainMenuUIAction.class));

        menuFunctionMap.put(31, findUIAction(uiActionList, AddEventUIAction.class));
        menuFunctionMap.put(32, findUIAction(uiActionList, RemoveEventUIAction.class));
        menuFunctionMap.put(33, findUIAction(uiActionList, SearchEventUIAction.class));
        menuFunctionMap.put(34, findUIAction(uiActionList, UpdateUIAction.class));
        menuFunctionMap.put(35, findUIAction(uiActionList, DisplayEventUIAction.class));
        menuFunctionMap.put(36, findUIAction(uiActionList, ToMainMenuUIAction.class));
        menuFunctionMap.put(37, findUIAction(uiActionList, StartUpEventUIAction.class));

        menuFunctionMap.put(41, findUIAction(uiActionList, ToMainMenuUIAction.class));
        menuFunctionMap.put(42, findUIAction(uiActionList, ToMainMenuUIAction.class));
        menuFunctionMap.put(43, findUIAction(uiActionList, ToMainMenuUIAction.class));
        menuFunctionMap.put(44, findUIAction(uiActionList, ToMainMenuUIAction.class));
        menuFunctionMap.put(45, findUIAction(uiActionList, ToMainMenuUIAction.class));
        menuFunctionMap.put(46, findUIAction(uiActionList, ToMainMenuUIAction.class));

        menuFunctionMap.put(61, findUIAction(uiActionList, ToMainMenuUIAction.class));
        menuFunctionMap.put(62, findUIAction(uiActionList, ExitEventUIAction.class));
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
        System.out.println("1. CUSTOMERS");
        System.out.println("2. GUIDES");
        System.out.println("3. EVENTS");
        System.out.println("4. TOURS");
        System.out.println("5. TICKETS");
        System.out.println("6. EXIT");
        System.out.println();
    }

    public Map<Integer, SubjectMenu> subjectMenuSelect () {
        Map<Integer, SubjectMenu> menuMap = new HashMap<>();
        menuMap.put(1, new CustomerMenu());
        menuMap.put(2, new GuideMenu());
        menuMap.put(3, new EventMenu());
        menuMap.put(4, new TourMenu());
        menuMap.put(5, new ExitMenu());
        menuMap.put(6, new ExitMenu());
        return menuMap;

    }

}
