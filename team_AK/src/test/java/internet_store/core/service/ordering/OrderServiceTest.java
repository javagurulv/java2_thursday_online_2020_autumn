package internet_store.core.service.ordering;

import internet_store.core.domain.Client;
import internet_store.core.domain.Order;
import internet_store.core.operation.OrderSumProperty;
import internet_store.core.service.cart.TotalSumCartService;
import internet_store.core.service.session.SessionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {
    @Mock
    private TotalSumCartService totalSumCartService;
    @Mock
    private CreateOrderNumberService numberService;
    @Mock
    private SessionService sessionService;
    @Mock
    private OrderSumProperty orderSumProperty;
    @InjectMocks
    private OrderService orderService;

    @Test
    public void createNewOrder() {
        Mockito.when(totalSumCartService.calculateTotalSum()).thenReturn(new BigDecimal("10.00"));
        Mockito.when(numberService.getOrderHaveNumber()).thenReturn(false);
        Mockito.when(numberService.createOrderNumber()).thenReturn("5");
        Mockito.when(orderSumProperty.getTaxAmount(new BigDecimal("10.00"))).thenReturn(new BigDecimal("2.10"));
        Mockito.when(orderSumProperty.getAmountWithTax(new BigDecimal("10.00"))).thenReturn(new BigDecimal("12.10"));
        Mockito.when(sessionService.getSessionClient()).thenReturn(new Client());

        Order newOrder = orderService.createOrder();
        assertEquals("5", newOrder.getNumber());
        assertEquals(new BigDecimal("10.00"), newOrder.getSum());
        assertEquals(new BigDecimal("2.10"), newOrder.getTax());
        assertEquals(new BigDecimal("12.10"), newOrder.getTotal());
    }
}