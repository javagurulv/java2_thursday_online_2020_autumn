package internet_store.console_ui;

import internet_store.console_ui.customer.*;
import internet_store.console_ui.product.ExitUIAction;
import internet_store.core.services.customer.*;
import internet_store.database.customer.CustomerDatabase;
import internet_store.database.customer.CustomerDatabaseImpl;


import java.util.HashMap;
import java.util.Map;

public class InternetStoreCustomerSide {

    private Map<Integer, UIAction> menuNumberToAction;

    InputCheckUtility inputCheckUtility = new InputCheckUtility();
    CustomerDatabase customerDatabase = new CustomerDatabaseImpl();

    AddCustomerRequestValidator addCustomerRequestValidator = new AddCustomerRequestValidator();
    AddCustomerService addCustomerService = new AddCustomerService(customerDatabase, addCustomerRequestValidator);

    DeleteCustomerRequestValidator deleteCustomerRequestValidator = new DeleteCustomerRequestValidator();
    DeleteCustomerService deleteCustomerService = new DeleteCustomerService(customerDatabase, deleteCustomerRequestValidator);

    GetAllCustomersService getAllCustomersService = new GetAllCustomersService(customerDatabase);

    FindCustomerByIdRequestValidator findCustomerByIdRequestValidator = new FindCustomerByIdRequestValidator();
    FindCustomerByIdService findCustomerByIdService =
            new FindCustomerByIdService(customerDatabase, findCustomerByIdRequestValidator);

    SearchCustomerRequestValidator searchCustomerRequestValidator = new SearchCustomerRequestValidator();
    SearchCustomerService searchCustomerService =
            new SearchCustomerService(customerDatabase, searchCustomerRequestValidator);



    public InternetStoreCustomerSide() {

        menuNumberToAction = new HashMap();

        menuNumberToAction.put(1, new AddCustomerUIAction(addCustomerService));
        menuNumberToAction.put(2, new DeleteCustomerUIAction(deleteCustomerService));
        menuNumberToAction.put(3, new PrintCustomersInfoUIAction(getAllCustomersService));
        menuNumberToAction.put(4, new FindCustomerByIdUIAction(findCustomerByIdService));
        menuNumberToAction.put(5, new SearchCustomerUIAction(searchCustomerService));
        menuNumberToAction.put(0, new ExitUIAction());
    }

    public void run() {

        while (true) {

            printMenu();

            int userSelectedMenuNumber = inputCheckUtility.inputValidInteger("Please enter menu number: ");

            executeUIAction(userSelectedMenuNumber);
        }
    }

    private void printMenu(){
        System.out.println("\nMenu\n" +
                "1   Sign in\n" +
                "2   Delete account by id\n" +
                "3   SEARCH\n" + // добавть в Product Menu
                "4   Buy product\n"+ //будет вызвать Шоппинг Карт
                "0   Exit");
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

