package internet_store.console_ui.product;

import internet_store.console_ui.UIAction;
import internet_store.core.requests.product.ChangeProductRequest;
import internet_store.core.response.product.ChangeProductResponse;
import internet_store.core.services.product.ChangeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

//@Component
public class ChangeProductUIAction implements UIAction {

    @Autowired private ChangeProductService changeProductService;

    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);

        System.out.println("Please enter product ID");
        Long id = in.nextLong();

        System.out.println("Please enter new title for product");
        String title = in.nextLine();
        title = in.nextLine();

        System.out.println("Please enter new description for product");
        String description = in.nextLine();

        System.out.println("Please enter new price for product");
        Integer price = in.nextInt();

        ChangeProductRequest changeProductRequest = new ChangeProductRequest(id, title, description, price);
        ChangeProductResponse changeProductResponse = changeProductService.execute(changeProductRequest);

        if (changeProductResponse.hasErrors()){
            changeProductResponse.getErrors().forEach(System.out::println);
        }else{
            System.out.println("Product is updated");
        }

    }
}
