package internet_store.lesson_5.console_ui;

import internet_store.lesson_5.core.domain.Product;
import internet_store.lesson_5.core.requests.DeleteByProductRequest;
import internet_store.lesson_5.core.responses.DeleteByProductResponse;
import internet_store.lesson_5.core.services.DeleteProductByProductService;

import java.math.BigDecimal;
import java.util.Scanner;

public class DeleteByProductUIAction implements UIAction {

    private final DeleteProductByProductService deleteProductByProductService;

    public DeleteByProductUIAction(DeleteProductByProductService deleteProductByProductService) {
        this.deleteProductByProductService = deleteProductByProductService;
    }

    public void execute() {
        Scanner myInput = new Scanner(System.in);
        System.out.println("Deleting product by name and description : ");
        System.out.print("Enter product name : ");
        String productName = myInput.nextLine();
        System.out.print("Enter product description : ");
        String productDescription = myInput.nextLine();
        System.out.print("Enter product price : ");
        BigDecimal productPrice = myInput.nextBigDecimal();
        DeleteByProductRequest request = new DeleteByProductRequest(productName, productDescription, productPrice);
        DeleteByProductResponse response = deleteProductByProductService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
        } else {
            System.out.println("\nProduct deleted\n"
                    + response.getDeletedProduct().getName() + "\n"
                    + response.getDeletedProduct().getDescription() + "\n"
                    + response.getDeletedProduct().getPrice() + " EUR");
        }

        boolean productDeleted = deleteProductByProductService.delete(new Product(productName, productDescription, productPrice));
        if (productDeleted) {
            System.out.println("\nProduct deleted");
        } else {
            System.out.println("\nThere is no such product in the database");
        }
    }

}
