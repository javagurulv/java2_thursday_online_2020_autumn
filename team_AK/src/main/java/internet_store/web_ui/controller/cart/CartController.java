package internet_store.web_ui.controller.cart;

import internet_store.core.domain.Client;
import internet_store.core.operation.OrderSumProperty;
import internet_store.core.persistence.CartRepository;
import internet_store.core.request.cart.DeleteProductFromCartRequest;
import internet_store.core.service.cart.CartProductsCountService;
import internet_store.core.service.cart.DeleteProductFromCartService;
import internet_store.core.service.cart.TotalSumCartService;
import internet_store.core.service.cart.paging.CartPagingService;
import internet_store.core.service.session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
public class CartController {
    @Autowired
    private CartRepository CartRepository;
    @Autowired
    private CartProductsCountService cartCountService;
    @Autowired
    private CartPagingService paging;
    @Autowired
    private TotalSumCartService totalSumCartService;
    @Autowired
    private OrderSumProperty orderSumProperty;
    @Autowired
    private DeleteProductFromCartService service;
    @Autowired
    private SessionService sessionService;

    @GetMapping(value = "/estore/cart")
    public String getCart(ModelMap modelMap) {
        long countProductInCart = cartCountService.getCartCount();

        paging.startPaging();

        if (countProductInCart == 0) {
            orderSumProperty.setTaxAmount(new BigDecimal("0.00"));
            orderSumProperty.setAmountWithTax(new BigDecimal("0.00"));
        }
        modelMap.addAttribute("info", "");
        refreshData(modelMap);
        return "cart/cart";
    }

    @PostMapping(value = "/estore/view_cart_next")
    public String nextPageCartControl(ModelMap modelMap) {
        if (paging.isLastPage()) {
            modelMap.addAttribute("info", "View control : Last page");
        } else {
            modelMap.addAttribute("info", "");
            paging.nextPage(true);
        }

        refreshData(modelMap);
        return "cart/cart";
    }

    @PostMapping(value = "/estore/view_cart_prev")
    public String prevPageCartControl(ModelMap modelMap) {
        if (paging.isFirstPage()) {
            modelMap.addAttribute("info", "View control : First page");
        } else {
            modelMap.addAttribute("info", "");
            paging.nextPage(false);
        }

        refreshData(modelMap);
        return "cart/cart";
    }

    @PostMapping(value = "/estore/deleted_from_cart")
    public String deleteProductFromCart(@RequestParam(value = "deletedProduct") Long deletedCartId,
                                        ModelMap modelMap) {

        DeleteProductFromCartRequest request = new DeleteProductFromCartRequest(deletedCartId);
        service.execute(request);
        paging.startPaging();

        modelMap.addAttribute("info", "");
        refreshData(modelMap);
        sessionService.setSessionClient(new Client());
        return "cart/cart";
    }

    private void refreshData(ModelMap modelMap) {
        modelMap.addAttribute("cartList", paging.getListOnePage());
        modelMap.addAttribute("pages", "Page " + paging.getCurrentPage() + " of "
                + paging.getPagesQuantity());
        modelMap.addAttribute("cartCount", cartCountService.getCartCount());
        modelMap.addAttribute("currencySymbol", orderSumProperty.getCurrencySymbol());
        modelMap.addAttribute("taxRate", orderSumProperty.getTaxRate());
        modelMap.addAttribute("totalSum", totalSumCartService.calculateTotalSum());
        modelMap.addAttribute("taxAmount", orderSumProperty.getTaxAmount(totalSumCartService.calculateTotalSum()));
        modelMap.addAttribute("total", orderSumProperty.getAmountWithTax(totalSumCartService.calculateTotalSum()));
        modelMap.addAttribute("currency_symbol", orderSumProperty.getCurrencySymbol());
    }
}