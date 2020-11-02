package team_static_startup.application.uiaction;

import team_static_startup.application.ProductDatabase;

import java.util.Scanner;

public class DeleteByProductNameUIAction implements UIAction {

    private final ProductDatabase productDatabase;

    public DeleteByProductNameUIAction(ProductDatabase productDatabase) {
        this.productDatabase = productDatabase;
    }

    public void execute() {
        Scanner myInput = new Scanner(System.in);
        System.out.println("Deleting product by name : ");
        System.out.print("Enter product name : ");
        String productName = myInput.nextLine();

        productDatabase.deleteByProductName(productName);
    }

}
