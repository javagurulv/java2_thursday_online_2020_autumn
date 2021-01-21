package internet_store.application.console_ui;

import internet_store.application.core.services.FindCustomerByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class FindCustomerByIdUIAction implements UIAction {

    @Autowired FindCustomerByIdService findCustomerByIdService;

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter customer ID to search: ");
        Long id = scanner.nextLong();

        System.out.println(findCustomerByIdService.execute(id).toString());
    }

}
