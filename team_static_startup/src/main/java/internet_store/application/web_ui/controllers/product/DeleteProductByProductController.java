package internet_store.application.web_ui.controllers.product;

import internet_store.application.core.requests.product.DeleteByProductRequest;
import internet_store.application.core.responses.product.DeleteByProductResponse;
import internet_store.application.core.services.product.DeleteProductByProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeleteProductByProductController {

    @Autowired
    private DeleteProductByProductService deleteProductByProductService;

    @GetMapping(value = "/product/deleteProductByProduct")
    public String showDeleteProductByProductPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new DeleteByProductRequest());
        return "/product/deleteProductByProduct";
    }

    @PostMapping("/product/deleteProductByProduct")
    public String processDeleteProductByProductRequest(
            @ModelAttribute(value = "request") DeleteByProductRequest deleteByProductRequest, ModelMap modelMap) {
        DeleteByProductResponse response = deleteProductByProductService.execute(deleteByProductRequest);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        } else {
            modelMap.addAttribute("products", response.getDeletedProduct());
        }
        return "/product/deleteProductByProduct";
    }


}
