package internet_store.application.web_ui.controllers;

import internet_store.application.core.requests.product.AddProductRequest;
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
    public String processAddProductRequest(@ModelAttribute(value = "request") AddProductRequest request) {
        addProductService.execute(request);
        return "index";
    }

}
