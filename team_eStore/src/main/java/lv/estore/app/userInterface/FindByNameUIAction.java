package lv.estore.app.userInterface;

import lv.estore.app.core.request.NameRequest;
import lv.estore.app.core.services.iService;

import java.util.Scanner;

public class FindByNameUIAction implements UIAction{

    private iService findByNameService;

    public FindByNameUIAction(final iService findByNameService) {
        this.findByNameService = findByNameService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Find product by 'Name': ");
        String name = scanner.nextLine();

        NameRequest findByNameRequest = new NameRequest(name);
        findByNameService.execute(findByNameRequest);
        System.out.println("Type 'Enter' to continue.");
    }
}
