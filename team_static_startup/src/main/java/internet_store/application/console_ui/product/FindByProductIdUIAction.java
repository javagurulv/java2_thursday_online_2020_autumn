package internet_store.application.console_ui.product;

import internet_store.application.console_ui.UIAction;
import internet_store.application.core.domain.Product;
import internet_store.application.core.requests.product.FindByIdRequest;
import internet_store.application.core.responses.product.FindByProductIdResponse;
import internet_store.application.core.services.product.FindByProductIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

//@Component
public class FindByProductIdUIAction implements UIAction {

    @Autowired
    private FindByProductIdService findByIdService;

    public void execute() {
        Scanner myInput = new Scanner(System.in);
        System.out.print("Enter product ID for searching : ");
        String id = myInput.nextLine();

        FindByIdRequest request = new FindByIdRequest(id);
        FindByProductIdResponse response = findByIdService.execute(request);

        Optional<Product> foundProduct = response.getProductFoundById();

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else { if (response.getProductFoundById().isEmpty()) {
            System.out.println("\nNo product with ID = " + id + " in the DataBase");
        } else {
            Product product = foundProduct.get();
            System.out.println("Found product in the database :");
            System.out.print(product.toString() + "\n");
        }
        }
    }


}
