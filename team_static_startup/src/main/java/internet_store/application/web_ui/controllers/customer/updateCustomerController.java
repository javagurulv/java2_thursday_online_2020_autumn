package internet_store.application.web_ui.controllers.customer;

import internet_store.application.core.requests.customer.UpdateCustomerRequest;
import internet_store.application.core.responses.customer.UpdateCustomerResponse;
import internet_store.application.core.services.customer.UpdateCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class updateCustomerController {

    @Autowired
    private UpdateCustomerService updateCustomerService;

    @GetMapping(value = "/customer/updateCustomer")
    public String showUpdateCustomerPage(ModelMap modelMap){
        modelMap.addAttribute("request", new UpdateCustomerRequest());
        return "customer/updateCustomer";
    }

    @PostMapping("/customer/updateCustomer")
    public String showUpdateCustomer(
            @ModelAttribute(value = "request") UpdateCustomerRequest request, ModelMap modelMap){
        UpdateCustomerResponse response = updateCustomerService.execute(request);
        if (response.hasErrors()){
            modelMap.addAttribute("errors", response.getErrors());
        } else {
            modelMap.addAttribute("customers", response.getUpdatedCustomer());
        }
        return "customer/updateCustomer";
    }

}
