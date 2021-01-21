package internet_store.application.console_ui;

import internet_store.application.core.services.GetAllOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindAllOrdersUIAction implements UIAction {

    @Autowired GetAllOrdersService getAllOrdersService;

    @Override
    public void execute() {
        getAllOrdersService.execute().forEach(System.out::println);
    }

}
