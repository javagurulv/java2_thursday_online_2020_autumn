package internet_store.console_ui;

import internet_store.console_ui.product.*;
import internet_store.core.services.product.*;
import internet_store.database.product.ProductDatabase;
import internet_store.database.product.ProductDatabaseImpl;


import java.util.HashMap;
import java.util.Map;

public class InternetStoreBusinessSide {

    private Map<Integer, UIAction> menuNumberToAction;

    InputCheckUtility inputCheckUtility = new InputCheckUtility();
    ProductDatabase productDatabase = new ProductDatabaseImpl();

    AddProductRequestValidator addProductRequestValidator = new AddProductRequestValidator();
    AddProductService addProductService = new AddProductService(productDatabase, addProductRequestValidator);
    DeleteProductRequestValidator deleteProductRequestValidator = new DeleteProductRequestValidator();
    DeleteByIdService deleteByIdService = new DeleteByIdService( productDatabase, deleteProductRequestValidator);
    GetAllProductsService getAllProductsService = new GetAllProductsService(productDatabase);
    ChangeTitleRequestValidator changeTitleRequestValidator = new ChangeTitleRequestValidator();
    ChangeTitleService changeTitleService = new ChangeTitleService(productDatabase, changeTitleRequestValidator);
    ChangeDescriptionRequestValidator changeDescriptionRequestValidator = new ChangeDescriptionRequestValidator();
    ChangeDescriptionService changeDescriptionService = new ChangeDescriptionService(productDatabase, changeDescriptionRequestValidator);
    FindByIdRequestValidator findByIdRequestValidator = new FindByIdRequestValidator();
    FindProductByIdService findProductByIdService = new FindProductByIdService(productDatabase, findByIdRequestValidator);
    SearchProductRequestValidator searchProductRequestValidator = new SearchProductRequestValidator();
    SearchProductService searchProductService = new SearchProductService(productDatabase, searchProductRequestValidator);

    public InternetStoreBusinessSide() {

        menuNumberToAction = new HashMap();

        menuNumberToAction.put(1, new AddProductUIAction(addProductService));
        menuNumberToAction.put(2, new DeleteByIdUIAction(deleteByIdService));
        menuNumberToAction.put(3, new GetAllProductsUIAction(getAllProductsService));
        menuNumberToAction.put(4, new ChangeTitleUIAction(changeTitleService));
        menuNumberToAction.put(5, new ChangeDescriptionUIAction(changeDescriptionService));
        menuNumberToAction.put(6, new FindByIdUIAction(findProductByIdService));
        menuNumberToAction.put(7, new SearchProductUIAction(searchProductService));
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
                "1   Add item\n" +
                "2   Delete by id\n" +
                "3   Print products\n" +
                "4   Change title\n" +
                "5   Change description\n" +
                "6   Find product by id\n"+
                "7   Search product by title / description\n"+
                "8   Print order list\n"+
                "9   Check clients\n"+
                "10  Remove client\n"+
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

