package internet_store.web_ui.controller.order;

import internet_store.core.operation.OrderSumProperty;
import internet_store.core.operation.OpenOrderPdfFormat;
import internet_store.core.persistence.CartRepository;
import internet_store.core.service.ordering.OrderStatusService;
import internet_store.core.service.ordering.paging.OrderControlPagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class OrderControlController {
    @Autowired
    private CartRepository CartRepository;
    @Autowired
    private OrderControlPagingService paging;
    @Autowired
    private OrderSumProperty orderSumProperty;
    @Autowired
    private OrderStatusService orderStatusService;
    @Autowired
    private OpenOrderPdfFormat openOrderPdfFormat;

    @GetMapping(value = "/service/order_control")
    public String getOrders(ModelMap modelMap) {
        paging.startPaging();

        modelMap.addAttribute("info", "");
        refreshData(modelMap);
        return "order/order_control";
    }

    @PostMapping(value = "/service/order_control_next")
    public String nextPageOrderControl(ModelMap modelMap) {
        if (paging.isLastPage()) {
            modelMap.addAttribute("info", "View control : Last page");
        } else {
            modelMap.addAttribute("info", "");
            paging.nextPage(true);
        }

        refreshData(modelMap);
        return "order/order_control";
    }

    @PostMapping(value = "/service/order_control_prev")
    public String prevPageOrderControl(ModelMap modelMap) {
        if (paging.isFirstPage()) {
            modelMap.addAttribute("info", "View control : First page");
        } else {
            modelMap.addAttribute("info", "");
            paging.nextPage(false);
        }

        refreshData(modelMap);
        return "order/order_control";
    }

    @PostMapping(value = "/service/status_change")
    public String statusChange(@RequestParam(value = "orderNumber") String orderNumber,
                               @RequestParam(value = "order_status") String status,
                               ModelMap modelMap) {

        orderStatusService.changeOrderStatus(orderNumber, status);

        modelMap.addAttribute("info", "");
        refreshData(modelMap);
        return "order/order_control";
    }

    @PostMapping(value = "/service/view_pdf")
    public String showPdf(@RequestParam(value = "orderNumber") String orderNumber, ModelMap modelMap) throws IOException {

        openOrderPdfFormat.showPdfOrder(orderNumber);
        modelMap.addAttribute("info", "");
        refreshData(modelMap);
        return "order/order_control";
    }

    private void refreshData(ModelMap modelMap) {
        modelMap.addAttribute("orders", paging.refreshTableContent());
        modelMap.addAttribute("currency_symbol", orderSumProperty.getCurrencySymbol());
        modelMap.addAttribute("pages", "Page " + paging.getCurrentPage() + " of "
                + paging.getPagesQuantity());
    }
}