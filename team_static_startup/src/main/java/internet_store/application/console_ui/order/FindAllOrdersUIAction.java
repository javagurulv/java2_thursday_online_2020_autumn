package internet_store.application.console_ui.order;

import internet_store.application.console_ui.UIAction;
import internet_store.application.core.responses.order.FindAllOrdersResponse;
import internet_store.application.core.services.order.FindAllOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class FindAllOrdersUIAction implements UIAction {

    @Autowired
    FindAllOrdersService findAllOrdersService;

    @Override
    public void execute() {
        FindAllOrdersResponse response = findAllOrdersService.execute();
        response.getOrders().forEach(System.out::println);
    }

}
