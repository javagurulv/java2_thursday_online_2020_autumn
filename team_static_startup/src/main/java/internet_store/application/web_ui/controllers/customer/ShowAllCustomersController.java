package internet_store.application.web_ui.controllers.customer;

import internet_store.application.core.responses.customer.GetAllCustomersResponse;
import internet_store.application.core.services.customer.GetAllCustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowAllCustomersController {

    @Autowired private GetAllCustomersService getAllCustomersService;

    @GetMapping(value = "customer/showAllCustomers")
    public String showAllCustomersPage(ModelMap modelMap){
        GetAllCustomersResponse response = getAllCustomersService.execute();
        modelMap.addAttribute("customers", response.getCustomerList());
        return "customer/showAllCustomers";
    }

}
