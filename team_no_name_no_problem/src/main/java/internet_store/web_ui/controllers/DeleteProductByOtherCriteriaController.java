package internet_store.web_ui.controllers;


import internet_store.core.requests.product.DeleteProductByOtherRequest;
import internet_store.core.response.product.DeleteByOtherResponse;
import internet_store.core.services.product.DeleteByOtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeleteProductByOtherCriteriaController {

    @Autowired
    private DeleteByOtherService deleteByOtherService;

    @GetMapping(value = "/deleteProductByOtherCriteria")
    public String showDeleteProductByOtherCriteriaPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new DeleteProductByOtherRequest());
        return "deleteProductByOtherCriteria";
    }

    @PostMapping("/deleteProductByOtherCriteria")
    public String processDeleteProductByOtherCriteriaRequest(
            @ModelAttribute(value = "request") DeleteProductByOtherRequest deleteProductByOtherRequest, ModelMap modelMap) {
        DeleteByOtherResponse deleteByOtherResponse = deleteByOtherService.execute(deleteProductByOtherRequest);{
            if (deleteByOtherResponse.hasErrors()) {
                modelMap.addAttribute("errors", deleteByOtherResponse.hasErrors());
                return "/deleteProductByOtherCriteria";
            } else {
                return "index";
            }
        }
    }
}
