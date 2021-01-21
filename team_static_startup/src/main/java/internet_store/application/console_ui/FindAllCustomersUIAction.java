package internet_store.application.console_ui;

import internet_store.application.core.services.GetAllCustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindAllCustomersUIAction implements UIAction {

    @Autowired GetAllCustomersService getAllCustomersService;

    @Override
    public void execute() {
        getAllCustomersService.execute().forEach(System.out::println);
    }

}
