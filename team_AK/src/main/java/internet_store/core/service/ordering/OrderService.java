package internet_store.core.service.ordering;

import com.itextpdf.text.DocumentException;
import internet_store.core.domain.Order;
import internet_store.core.domain.ProductInCart;
import internet_store.core.operation.OrderSumProperty;
import internet_store.core.persistence.CartRepository;
import internet_store.core.persistence.OrderRepository;
import internet_store.core.service.cart.CartProductsCountService;
import internet_store.core.service.cart.TotalSumCartService;
import internet_store.core.service.session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderService {
    @Autowired
    private CartProductsCountService countService;
    @Autowired
    private TotalSumCartService totalSumCartService;
    @Autowired
    private CreateOrderNumberService numberService;
    @Autowired
    private OrderSumProperty orderSumProperty;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderStatusService orderStatusService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private CreatePdfOrder pdfOrder;
    @Autowired
    private OrderSumProperty sumProperty;
    private String orderNumber;
    private BigDecimal totalSumInCart;
    private BigDecimal taxAmount;
    private BigDecimal total;

    public Order createOrder() {
        Order order = new Order();
        totalSumInCart = totalSumCartService.calculateTotalSum();
        if (numberService.getOrderHaveNumber()) {
            orderNumber = numberService.getFullOrderNumber();
        } else {
            orderNumber = numberService.createOrderNumber();
        }

        taxAmount = orderSumProperty.getTaxAmount(totalSumInCart);
        total = orderSumProperty.getAmountWithTax(totalSumInCart);
        order.setNumber(orderNumber);
        order.setDate(new Date());
        order.setClient(sessionService.getSessionClient());
        order.setSum(totalSumInCart);
        order.setTax(taxAmount);
        order.setTotal(total);
        return order;
    }

    public void saveOrder() {
        List<ProductInCart> itemsForOrder = cartRepository.itemsForOrder(sessionService.getSessionId());
        itemsForOrder.forEach(productInCart -> {
            Order orderForSave = new Order();
            orderForSave.setNumber(orderNumber);
            orderForSave.setDate(new Date());
            orderForSave.setClient(sessionService.getSessionClient());
            orderForSave.setCart(productInCart);
            productInCart.setOrdered(true);
            orderForSave.setSum(totalSumInCart);
            orderForSave.setTax(taxAmount);
            orderForSave.setTotal(total);
            productInCart.getProduct().setQuantity(newProductQuantity(productInCart));
            orderRepository.saveAndFlush(orderForSave);
        });
        createOrderReport();
        orderStatusService.changeOrderStatus(orderNumber, "ORDER RECEIVED");
    }

    private void createOrderReport() {
        List<Order> orders = orderRepository.findAllByNumber(orderNumber);
        Thread createPdf = new Thread(() -> {
            try {
                pdfOrder.createPdfOrder(orders, orderSumProperty.getCurrencySymbol());
            } catch (IOException | DocumentException e) {
                e.printStackTrace();
            }
        });
        createPdf.setDaemon(true);
        createPdf.start();
    }

    private long newProductQuantity(ProductInCart productInCart) {
        return productInCart.getProduct().getQuantity() - productInCart.getQuantity();
    }

    public List<ProductInCart> getAllItemsFromCart() {
        return cartRepository.itemsForOrder(sessionService.getSessionId());
    }

    public boolean isCanMakeOrder() {
        return sessionService.getSessionClient() != null && countService.getCartCount() != 0;
    }
}