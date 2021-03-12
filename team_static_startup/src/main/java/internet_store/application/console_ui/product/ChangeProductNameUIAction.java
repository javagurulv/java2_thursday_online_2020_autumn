package internet_store.application.console_ui.product;

import internet_store.application.console_ui.UIAction;
import internet_store.application.core.domain.Product;
import internet_store.application.core.requests.product.ChangeProductNameRequest;
import internet_store.application.core.requests.product.FindByIdRequest;
import internet_store.application.core.responses.product.ChangeProductNameResponse;
import internet_store.application.core.responses.product.FindByProductIdResponse;
import internet_store.application.core.services.product.ChangeProductNameService;
import internet_store.application.core.services.product.FindByProductIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

//@Component
public class ChangeProductNameUIAction implements UIAction {

    @Autowired
    private ChangeProductNameService changeProductNameService;
    @Autowired
    private FindByProductIdService findByIdService;


    public void execute() {
        Scanner myInput = new Scanner(System.in);
        System.out.print("Enter product ID for searching : ");
        String id = myInput.nextLine();

        FindByIdRequest idRequest = new FindByIdRequest(id);
        FindByProductIdResponse idResponse = findByIdService.execute(idRequest);

        Optional<Product> foundProduct = idResponse.getProductFoundById();

        if (idResponse.hasErrors()) {
            idResponse.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            if (idResponse.getProductFoundById().isEmpty()) {
                System.out.println("\nNo product with ID = " + id + " in the DataBase");
            } else {
                Product product = foundProduct.get();
                System.out.println("Found product in the database :");
                System.out.print(product.toString() + "\n");
            }
        }

        System.out.print("Enter new name for product: ");
        String newName = myInput.nextLine();
        ChangeProductNameRequest changeProductNameRequest = new ChangeProductNameRequest(foundProduct.get().getId(),
                newName);
        ChangeProductNameResponse changeProductNameResponse = changeProductNameService.execute(changeProductNameRequest);

        //boolean productNameChange = changeProductNameService.changeProductName(foundProduct.get().getId(), newName);

        if (changeProductNameResponse.isNameChanged()) {
            System.out.println("Product name changed successfully!");
        } else {
            System.out.println("Could not change name");
        }
    }

}
