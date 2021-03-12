package internet_store.core.service.ordering;

import internet_store.core.domain.Order;
import internet_store.core.domain.TelegramChatId;
import internet_store.core.persistence.OrderRepository;
import internet_store.integration.PrintService;
import internet_store.integration.mail.EmailServiceImpl;
import internet_store.integration.telegram.ChatBot;
import internet_store.integration.telegram.service.FindTelegramChatIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderStatusService {
    @Autowired
    private FindTelegramChatIdService findTelegramChatIdService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private FindTelegramChatIdService telegramChatIdService;
    @Autowired
    private ChatBot chatBot;
    @Autowired
    private EmailServiceImpl emailService;
    @Autowired
    private PrintService printService;
    @Autowired
    private CreatePdfOrder pdfOrder;
    private Order order;

    public void changeOrderStatus(String orderNumber, String orderStatus) {
        List<Order> orders = orderRepository.findAllByNumber(orderNumber);
        Optional<List<Order>> optionalOrderList = Optional.ofNullable(orders);

        optionalOrderList.ifPresent(orderList -> {
            this.order = orderList.get(0);
            changeOrderStatusAndSave(orderStatus, orderList);
            checkIsFirstOrderStatus(orderStatus);
            telegramNotification();
        });
    }

    private void checkIsFirstOrderStatus(String orderStatus) {
        if (orderStatus.equals("ORDER RECEIVED")) {
            sendEmailAboutConfirmationOrder();
        } else {
            sendEmailAboutChangedStatus();
        }
    }

    private void changeOrderStatusAndSave(String orderStatus, List<Order> orders) {
        for (Order order : orders) {
            order.setStatus(orderStatus);
            orderRepository.save(order);
        }
    }

    private void sendEmailAboutConfirmationOrder() {
        Thread sendEmailConfirmationThread = new Thread(() ->
                emailService.sendSimpleMessage(order.getClient().getEmail(), "Order confirmed",
                        printService.createPrintReport(order) + "\n\n<< Please find our Telegram bot " +
                                "EStoreBot for more information >>"));
        sendEmailConfirmationThread.start();
    }

    public void telegramNotification() {
        List<TelegramChatId> clientsChatId = findTelegramChatIdService.execute(order.getNumber());
        Optional<List<TelegramChatId>> optionalClientChatId = Optional.ofNullable(clientsChatId);

        optionalClientChatId.ifPresent(listChatid -> {
            Thread sendTelegramNotificationThread = new Thread(() ->
                    listChatid.forEach(id -> printService.printTelegramNotification(id.getChatId(),
                            printService.createPrintReport(order))));
            sendTelegramNotificationThread.start();
        });
    }

    private void sendEmailAboutChangedStatus() {
        Thread sendMailThread = new Thread(() -> emailService.sendSimpleMessage(order.getClient()
                .getEmail(), "Order status changed", printService.createPrintReport(order)));
        sendMailThread.start();
    }
}