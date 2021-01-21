package internet_store.application.console_ui;

import internet_store.application.core.services.FindOrderByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class FindOrderByIdUIAction implements UIAction {

    @Autowired FindOrderByIdService findOrderByIdService;

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter order ID to search: ");
        Long id = scanner.nextLong();

        System.out.println(findOrderByIdService.execute(id).toString());
    }

}
