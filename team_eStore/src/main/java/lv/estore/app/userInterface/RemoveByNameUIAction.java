package lv.estore.app.userInterface;

import lv.estore.app.core.request.NameRequest;
import lv.estore.app.core.services.iService;

import java.util.Scanner;

public class RemoveByNameUIAction implements UIAction {

    private iService removeByNameService;

    public RemoveByNameUIAction(final iService removeByNameService) {
        this.removeByNameService = removeByNameService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type ID of the product you want to remove: ");
        String name = scanner.nextLine();

        NameRequest removeByNameRequest = new NameRequest(name);
        removeByNameService.execute(removeByNameRequest);
        System.out.println("Type 'Enter' to continue.");
    }
}
