package internet_store.console_ui.product;

import internet_store.console_ui.UIAction;
import internet_store.core.requests.product.DeleteProductByIdRequest;
import internet_store.core.response.product.DeleteProductByIdResponse;
import internet_store.core.services.product.DeleteProductByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

//@Component
public class DeleteProductByIdUIAction implements UIAction {

    @Autowired private DeleteProductByIdService deleteByIdService;

    @Override
    public void execute(){

        Scanner in = new Scanner(System.in);

        System.out.println("Please enter product's id to delete");
        Long id = in.nextLong();

        DeleteProductByIdRequest deleteProductRequest = new DeleteProductByIdRequest(id);
        DeleteProductByIdResponse deleteProductResponse = deleteByIdService.execute(deleteProductRequest);

        if (deleteProductResponse.hasErrors()){
            deleteProductResponse.getErrors().forEach(System.out::println);
        }else{
            System.out.println("Product is deleted");
        }
    }

}

