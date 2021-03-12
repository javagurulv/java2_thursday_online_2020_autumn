package internet_store.integration;

import internet_store.core.domain.Order;
import internet_store.core.operation.OrderSumProperty;
import internet_store.integration.telegram.ChatBot;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class PrintService {
    @Autowired
    private ChatBot chatBot;
    @Autowired
    private OrderSumProperty orderSumProperty;

    public String createPrintReport(Order order) {
        return "New information about order number: " + order.getNumber() + "\n"
                + "Order date: " + order.getDate() + "\n"
                + "Total sum: " + order.getTotal() + " " + orderSumProperty.getCurrencySymbol() + "\n"
                + "Order status: " + order.getStatus();
    }

    @SneakyThrows(TelegramApiException.class)
    public void printTelegramNotification(long chatId, String message) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(String.valueOf(chatId))
                .text(message)
                .build();
        chatBot.execute(sendMessage);
    }
}