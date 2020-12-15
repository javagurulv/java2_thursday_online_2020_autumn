package lv.estore.app.userInterface;

import lv.estore.app.core.request.IdRequest;
import lv.estore.app.core.services.iService;

import java.util.Scanner;

public class RemoveByIdUIAction implements UIAction {

    private iService removeByIdService;

    public RemoveByIdUIAction(final iService removeByIdService) {
        this.removeByIdService = removeByIdService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type 'Id' of the product you want to remove: ");
        long id = Long.parseLong(scanner.nextLine());

        IdRequest removeByIdRequest = new IdRequest(id);
        removeByIdService.execute(removeByIdRequest);
        System.out.println("Type 'Enter' to continue.");
    }
}
