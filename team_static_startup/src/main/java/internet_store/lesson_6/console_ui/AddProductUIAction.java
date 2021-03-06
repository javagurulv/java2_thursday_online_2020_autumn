package internet_store.lesson_6.console_ui;

import internet_store.lesson_6.core.requests.AddProductRequest;
import internet_store.lesson_6.core.responses.AddProductResponse;
import internet_store.lesson_6.core.services.AddProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class AddProductUIAction implements UIAction {

    @Autowired
    private AddProductService addProductService;

    public void execute() {
        Scanner myInput = new Scanner(System.in);
        System.out.println("Adding product to database : ");
        System.out.print("Enter product name : ");
        String productName = myInput.nextLine();
        System.out.print("Enter product description : ");
        String productDescription = myInput.nextLine();
        System.out.print("Enter product price : ");
        BigDecimal productPrice = myInput.nextBigDecimal();
        AddProductRequest request = new AddProductRequest(productName, productDescription, productPrice);
        AddProductResponse response = addProductService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
        } else {
            System.out.println("\nProduct added\n" + productName + "\n" + productDescription + "\n" + productPrice + " EUR");
        }
    }

}
