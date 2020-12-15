package lv.estore.app.userInterface;

import lv.estore.app.core.request.AddRequest;
import lv.estore.app.core.responses.AddResponse;
import lv.estore.app.core.services.iService;

import java.math.BigDecimal;
import java.util.Scanner;

public class AddUIAction implements UIAction{

    private iService addService;

    public AddUIAction(final iService addService) {
        this.addService = addService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type product name: ");
        String name = scanner.nextLine();
        System.out.println("Type product description: ");
        String description = scanner.nextLine();
        System.out.println("Type price: ");
        BigDecimal price;
        try {
            price = new BigDecimal(scanner.nextLine());
        } catch (NumberFormatException ex) {
            throw new RuntimeException("Type price in valid decimal format.");
        }
        AddRequest addRequest = new AddRequest(name, description, price);
        AddResponse response = (AddResponse) addService.execute(addRequest);
        System.out.println(response);
        System.out.println("Type \"Enter\" to continue.");
    }
}
