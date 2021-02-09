package internet_store.application.web_ui.controllers;

import internet_store.application.core.requests.customer.AddCustomerRequest;
import internet_store.application.core.requests.product.AddProductRequest;
import internet_store.application.core.responses.customer.AddCustomerResponse;
import internet_store.application.core.responses.product.AddProductResponse;
import internet_store.application.core.services.customer.AddCustomerService;
import internet_store.application.core.services.product.AddProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddProductController {

    @Autowired
    private AddProductService addProductService;


    @GetMapping(value = "/addProduct")
    public String showAddProductPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddProductRequest());
        return "addProduct";
    }

    @PostMapping("/addProduct")
    public String processAddProductRequest(@ModelAttribute(value = "request") AddProductRequest request, ModelMap modelMap) {
        AddProductResponse response = addProductService.execute(request);
        return "redirect:/";


/*        @PostMapping("/addCustomer")
        public String processAddCustomerRequest(@ModelAttribute(value = "request") AddCustomerRequest request, ModelMap modelMap) {
            AddCustomerResponse response = addCustomerService.execute(request);
            if (response.hasErrors()) {
                modelMap.addAttribute("errors", response.getErrors());
                return "addCustomer";
            } else {
                return "redirect:/";
            }
        }*/

    }

}
