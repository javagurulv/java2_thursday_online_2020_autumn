package internet_store.web_ui.controller.order;

import internet_store.core.domain.Client;
import internet_store.core.domain.Order;
import internet_store.core.operation.OrderSumProperty;
import internet_store.core.persistence.ClientRepository;
import internet_store.core.request.session.SaveClientSesionRequest;
import internet_store.core.service.cart.CartProductsCountService;
import internet_store.core.service.client.AddClientService;
import internet_store.core.service.ordering.CreateOrderNumberService;
import internet_store.core.service.ordering.OrderService;
import internet_store.core.service.ordering.OrderStatusService;
import internet_store.core.service.session.FindSessionService;
import internet_store.core.service.session.SaveSessionService;
import internet_store.core.service.session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Objects;

@Controller
public class CompleteOrderController {
    @Autowired
    private OrderStatusService orderStatusService;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AddClientService addClientService;
    @Autowired
    private CartProductsCountService cartCountService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private CreateOrderNumberService numberService;
    @Autowired
    private OrderSumProperty orderSumProperty;
    @Autowired
    private SaveSessionService saveSessionService;
    @Autowired
    private FindSessionService findSessionService;

    @GetMapping(value = "/estore/cart_make_order")
    public String getCartOrder(ModelMap modelMap) {
        Client client = sessionService.getSessionClient();

        Order order = orderService.createOrder();

        if (orderService.getAllItemsFromCart().size() == 0) {
            modelMap.addAttribute("order", new Order());
        } else {
            modelMap.addAttribute("order", order);
            modelMap.addAttribute("items", orderService.getAllItemsFromCart());
        }

        modelMap.addAttribute("info", "");
        modelMap.addAttribute("client", Objects.requireNonNullElseGet(client, Client::new));
        refreshData(modelMap);

        if (orderService.isCanMakeOrder()) {
            modelMap.addAttribute("disabled", "false");
        } else {
            modelMap.addAttribute("disabled", "true");
        }
        return "cart/cart_make_order";
    }

    @PostMapping(value = "/estore/make_order")
    public String makeOrder(@ModelAttribute(value = "client") Client client, ModelMap modelMap) {

        orderService.saveOrder();
        orderStatusService.changeOrderStatus(numberService.getFullOrderNumber(), "ORDER RECEIVED");

        numberService.setOrderHaveNumber(false);

        saveSessionService.saveClientSession(new SaveClientSesionRequest(sessionService.getSessionId()));
        sessionService.setSessionClient(new Client());

        modelMap.addAttribute("order", new Order());
        modelMap.addAttribute("items", new ArrayList<>());
        modelMap.addAttribute("client", new Client());
        modelMap.addAttribute("info", "");
        modelMap.addAttribute("disabled", "true");
        refreshData(modelMap);
        return "cart/cart_make_order";
    }

    private void refreshData(ModelMap modelMap) {
        modelMap.addAttribute("cartCount", cartCountService.getCartCount());
        modelMap.addAttribute("orderNumber", numberService.getFullOrderNumber());
        modelMap.addAttribute("currency_symbol", orderSumProperty.getCurrencySymbol());
    }
}