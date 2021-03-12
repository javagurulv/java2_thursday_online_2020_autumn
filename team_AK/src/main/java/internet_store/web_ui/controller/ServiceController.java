package internet_store.web_ui.controller;

import internet_store.core.operation.OrderSumProperty;
import internet_store.core.service.client.paging.ClientPagingService;
import internet_store.core.service.ordering.CreateOrderNumberService;
import internet_store.core.service.ordering.paging.OrderControlPagingService;
import internet_store.core.service.product.paging.ProductsPagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller()
public class ServiceController {
    @Autowired
    private ProductsPagingService productsPagingService;
    @Autowired
    private ClientPagingService clientPagingService;
    @Autowired
    private OrderControlPagingService orderPagingService;
    @Autowired
    private CreateOrderNumberService numberService;
    @Autowired
    private OrderSumProperty orderSumProperty;

    @GetMapping(value = "service/general_settings")
    public String entry(ModelMap modelMap) {
        refreshData(modelMap);
        return "service/general_service";
    }

    @PostMapping(value = "/service/set_product_on_page")
    public String setProductOnPage(@RequestParam("product_on_page") Integer productOnPage,
                                   ModelMap modelMap) {
        productsPagingService.setRecordsCountOnPage(productOnPage);
        refreshData(modelMap);
        return "service/general_service";
    }

    @PostMapping(value = "/service/set_client_on_page")
    public String setClientOnPage(@RequestParam("client_on_page") Integer clientOnPage,
                                  ModelMap modelMap) {
        clientPagingService.setRecordsCountOnPage(clientOnPage);
        refreshData(modelMap);
        return "service/general_service";
    }

    @PostMapping(value = "/service/set_order_on_page")
    public String setOrderOnPage(@RequestParam("order_on_page") Integer orderOnPage,
                                 ModelMap modelMap) {
        orderPagingService.setRecordsCountOnPage(orderOnPage);
        refreshData(modelMap);
        return "service/general_service";
    }

    @PostMapping(value = "/service/tax")
    public String setTax(@RequestParam("tax_rate") BigDecimal taxRate,
                         ModelMap modelMap) {
        orderSumProperty.setTaxRate(taxRate);
        refreshData(modelMap);
        return "service/general_service";
    }

    @PostMapping(value = "/service/currency")
    public String setCurrency(@RequestParam("currency") String currency,
                              ModelMap modelMap) {

        orderSumProperty.setCurrencySymbol(currency);
        refreshData(modelMap);
        return "service/general_service";
    }

    @PostMapping(value = "/service/order_number")
    public String setOrderNumber(@RequestParam("orderNumber") Integer orderNumber,
                                 ModelMap modelMap) {
        numberService.setOrderNumber(orderNumber);
        refreshData(modelMap);
        return "service/general_service";
    }

    private void refreshData(ModelMap modelMap) {
        modelMap.addAttribute("product_page_quantity", productsPagingService.getRecordsCountOnPage());
        modelMap.addAttribute("client_page_quantity", clientPagingService.getRecordsCountOnPage());
        modelMap.addAttribute("order_page_quantity", orderPagingService.getRecordsCountOnPage());
        modelMap.addAttribute("tax", orderSumProperty.getTaxRate());
        modelMap.addAttribute("currency", orderSumProperty.getCurrencySymbol());
        modelMap.addAttribute("orderNumber", numberService.getOrderNumber());
    }
}