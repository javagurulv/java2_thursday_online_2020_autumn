package lv.estore.app.userInterface;

import lv.estore.app.core.request.IdRequest;
import lv.estore.app.core.services.iService;

import java.util.Scanner;

public class FindByIdUIAction implements UIAction{

    private iService findByIdService;

    public FindByIdUIAction(final iService findByIdService) {
        this.findByIdService = findByIdService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Find product by 'Id': ");
        long id = Long.parseLong(scanner.nextLine());

        IdRequest findByIdRequest = new IdRequest(id);
        findByIdService.execute(findByIdRequest);
        System.out.println("Type 'Enter' to continue.");
    }
}
