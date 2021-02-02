package internet_store.controller.product;

import internet_store.core.service.product.paging.ProductsPagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ViewProductController {

    @Autowired
    ProductsPagingService paging;

    @GetMapping(value = "view_product")
    public String showProduct(ModelMap modelMap) {
        paging.startPaging();

        modelMap.addAttribute("info", "");
        modelMap.addAttribute("pages", "Page " + paging.getCurrentPage() + " of "
                + paging.getPagesQuantity());
        modelMap.addAttribute("productList", paging.getListOnePage());

        return "product/view_product";
    }

    @PostMapping(value = "/view_product_next")
    public String nextPageProductControl(ModelMap modelMap) {
        if (paging.isLastPage()) {
            modelMap.addAttribute("info", "View control : Last page");
        } else {
            modelMap.addAttribute("info", "");
            paging.nextPage(true);
        }
        modelMap.addAttribute("pages", "          Page " + paging.getCurrentPage() + " of "
                + paging.getPagesQuantity() + "          ");
        modelMap.addAttribute("productList", paging.getListOnePage());
        return "product/view_product";
    }

    @PostMapping(value = "/view_product_prev")
    public String prevPageProductControl(ModelMap modelMap) {
        if (paging.isFirstPage()) {
            modelMap.addAttribute("info", "View control : First page");
        } else {
            modelMap.addAttribute("info", "");
            paging.nextPage(false);
        }
        modelMap.addAttribute("pages", "          Page " + paging.getCurrentPage() + " of "
                + paging.getPagesQuantity() + "          ");
        modelMap.addAttribute("productList", paging.getListOnePage());
        return "product/view_product";
    }
}