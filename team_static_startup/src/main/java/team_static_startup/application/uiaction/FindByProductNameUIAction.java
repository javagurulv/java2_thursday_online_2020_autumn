package team_static_startup.application.uiaction;

import team_static_startup.application.Product;
import team_static_startup.application.ProductDatabase;

import java.util.List;
import java.util.Scanner;

public class FindByProductNameUIAction implements UIAction {

    private final ProductDatabase productDatabase;

    public FindByProductNameUIAction(ProductDatabase productDatabase) {
        this.productDatabase = productDatabase;
    }

    public void execute() {
        Scanner myInput = new Scanner(System.in);
        System.out.print("Enter product name to search for: ");
        String productName = myInput.nextLine();

        List<Product> productFound = productDatabase.findByProductName(productName);

        if (productFound.isEmpty()) {
            System.out.println("No product with name = " + productName + " in the database");
        } else {
            System.out.println("\nFound products in the database :");
            for (Product product : productFound) {
                System.out.print(product.toString() + "\n");
            }
        }
    }

}