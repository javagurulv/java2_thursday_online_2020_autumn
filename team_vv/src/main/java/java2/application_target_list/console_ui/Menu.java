package java2.application_target_list.console_ui;

import java2.application_target_list.console_ui.actions.*;
import java2.application_target_list.config.SpringCoreConfiguration;
import java2.application_target_list.console_ui.actions.board.*;
import java2.application_target_list.console_ui.actions.target.*;
import java2.application_target_list.console_ui.actions.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class Menu {

    private Map<Integer, UIAction> menuNumberToUIActionMap;
    private static final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringCoreConfiguration.class);


    @Autowired
    public Menu(List<UIAction> uiActions) {
        menuNumberToUIActionMap = createMenuUIActionsMap(uiActions);
    }

    public void start(){
        while (true) {
            applicationContext.getBean(MenuUIAction.class).execute();
            printText();
            int selectedMenuNumberToUIAction = getNumberFromUser();

            if(needToPrintTargetsList(selectedMenuNumberToUIAction)){
                executeSelectedMenuItem(1);
            }

            if(needToPrintUsersList(selectedMenuNumberToUIAction)){
                executeSelectedMenuItem(11);
            }

            if(needToPrintRecordList(selectedMenuNumberToUIAction)){
                executeSelectedMenuItem(21);
            }

            executeSelectedMenuItem(selectedMenuNumberToUIAction);
        }
    }

    private boolean needToPrintTargetsList(int selectedMenuNumber){
        return (selectedMenuNumber > 2 && selectedMenuNumber < 7) || selectedMenuNumber == 22;
    }

    private boolean needToPrintUsersList(int selectedMenuNumber){
        return (selectedMenuNumber > 12 && selectedMenuNumber < 16) || selectedMenuNumber == 22;
    }

    private boolean needToPrintRecordList(int selectedMenuNumber){
        return selectedMenuNumber > 22 && selectedMenuNumber < 25;
    }

    private Map<Integer, UIAction> createMenuUIActionsMap(List<UIAction> uiActions){
        Map<Integer, UIAction> menuUIActionsMap = new HashMap<>();
        menuUIActionsMap.put(1, findUIAction(uiActions, GetAllTargetsUIAction.class));
        menuUIActionsMap.put(2, findUIAction(uiActions, AddTargetUIAction.class));
        menuUIActionsMap.put(3, findUIAction(uiActions, DeleteUIAction.class));
        menuUIActionsMap.put(4, findUIAction(uiActions, ChangeTargetNameUIAction.class));
        menuUIActionsMap.put(5, findUIAction(uiActions, ChangeTargetDescriptionUIAction.class));
        menuUIActionsMap.put(6, findUIAction(uiActions, ChangeTargetDeadlineUIAction.class));
        menuUIActionsMap.put(7, findUIAction(uiActions, SearchTargetByNameUIAction.class));
        menuUIActionsMap.put(8, findUIAction(uiActions, SearchTargetByDescriptionUIAction.class));
        menuUIActionsMap.put(11, findUIAction(uiActions, GetAllUsersUIAction.class));
        menuUIActionsMap.put(12, findUIAction(uiActions, AddUserUIAction.class));
        menuUIActionsMap.put(13, findUIAction(uiActions, DeleteUserUIAction.class));
        menuUIActionsMap.put(14, findUIAction(uiActions, ChangeUserFirstNameUIAction.class));
        menuUIActionsMap.put(15, findUIAction(uiActions, ChangeUserLastNameUIAction.class));
        menuUIActionsMap.put(16, findUIAction(uiActions, SearchUserByFirstNameUIAction.class));
        menuUIActionsMap.put(17, findUIAction(uiActions, SearchUserByLastNameUIAction.class));
        menuUIActionsMap.put(21, findUIAction(uiActions, GetAllRecordsUIActions.class));
        menuUIActionsMap.put(22, findUIAction(uiActions, AddRecordUIAction.class));
        menuUIActionsMap.put(23, findUIAction(uiActions, DeleteRecordUIAction.class));
        menuUIActionsMap.put(24, findUIAction(uiActions, SetRecordCompleteDateUIAction.class));
        menuUIActionsMap.put(25, findUIAction(uiActions, GetFullInfoAboutRecordsUIAction.class));
        menuUIActionsMap.put(26, findUIAction(uiActions, GetUnfinishedRecordsUIAction.class));
        menuUIActionsMap.put(0, findUIAction(uiActions, ExitUIAction.class));
        return menuUIActionsMap;
    }

    private UIAction findUIAction(List<UIAction> uiActions, Class uiActionClass) {
        return uiActions.stream()
                .filter(uiAction -> uiAction.getClass().equals(uiActionClass))
                .findFirst()
                .get();
    }

    public void executeSelectedMenuItem(int selectedMenu) {
        menuNumberToUIActionMap.get(selectedMenu).execute();
    }

    private void printText(){
        System.out.println("----------");
        System.out.println("Choose action: ");
        System.out.println("----------");
    }

    private int getNumberFromUser(){
        return Integer.parseInt(new Scanner(System.in).nextLine());
    }

}

