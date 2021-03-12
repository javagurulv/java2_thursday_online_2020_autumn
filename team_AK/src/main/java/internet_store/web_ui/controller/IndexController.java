package internet_store.web_ui.controller;

import internet_store.core.domain.Product;
import internet_store.core.persistence.CartRepository;
import internet_store.core.request.cart.AddProductToCartRequest;
import internet_store.core.request.product.CheckStockQuantityRequest;
import internet_store.core.response.product.CheckStockQuantityResponse;
import internet_store.core.service.cart.AddToCartService;
import internet_store.core.service.cart.CartProductsCountService;
import internet_store.core.service.product.CheckStockQuantityService;
import internet_store.core.service.product.ProductRangeService;
import internet_store.core.service.product.RandomProductListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private ProductRangeService rangeService;
    @Autowired
    private CheckStockQuantityService quantityService;
    @Autowired
    private CartProductsCountService cartCountService;
    @Autowired
    private AddToCartService addToCartService;
    @Autowired
    private RandomProductListService randomService;
    @Autowired
    private CartRepository CartRepository;
    private List<Product> startPageProducts;

    @GetMapping(value = "/index")
    public String index(ModelMap modelMap) {
        newRandomProductsOnScreen();

        modelMap.addAttribute("error", "");
        refreshData(modelMap);
        return "/index";
    }

    @PostMapping(value = "/buy_product")
    public String productBuy(@RequestParam(value = "quantity") Long quantity,
                             @RequestParam(value = "buy_product") String productTitle, ModelMap modelMap) {

        CheckStockQuantityRequest request = new CheckStockQuantityRequest(quantity, productTitle);
        CheckStockQuantityResponse response = quantityService.execute(request);

        if (response.hasErrors()) {
            String errorMessage = response.getErrors().get(0).getMessage();
            modelMap.addAttribute("error", errorMessage);
        } else {
            AddProductToCartRequest addRequest = new AddProductToCartRequest(quantity, productTitle);
            addToCartService.execute(addRequest);
            modelMap.addAttribute("error", "");
        }
        newRandomProductsOnScreen();
        refreshData(modelMap);
        return "index";
    }

    @GetMapping(value = "/service")
    public String logIn() {
        return "service/service";
    }

    private void newRandomProductsOnScreen() {
        List<Product> randomList = randomService.createRandomProductsList();
        if (randomList.size() < 6) {
            startPageProducts = rangeService.getProductsRange(6, 0);
        } else {
            startPageProducts = randomList;
        }
    }

    private void refreshData(ModelMap modelMap) {
        modelMap.addAttribute("cartCount", cartCountService.getCartCount());
        modelMap.addAttribute("products", startPageProducts);
    }
}