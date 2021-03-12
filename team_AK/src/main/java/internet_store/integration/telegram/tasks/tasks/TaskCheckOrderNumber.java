package internet_store.integration.telegram.tasks.tasks;

import internet_store.integration.PrintService;
import internet_store.integration.telegram.service.CheckOrderByOrderNumberService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskCheckOrderNumber {
    @Autowired
    private CheckOrderByOrderNumberService checkOrderByOrderNumberService;
    @Autowired
    private PrintService printService;
    @Getter
    @Setter
    private boolean isSearchOrderNumberCompleted = false;
    @Getter
    private String orderNumber;

    public void checkOrderNumber(String chatBotMessage, long chatId) {
        boolean isOrderNumberExists = checkOrderByOrderNumberService.isOrderExists(chatBotMessage);
        if (isOrderNumberExists) {
            orderNumber = chatBotMessage;
            isSearchOrderNumberCompleted = true;
        } else {
            String message = "❌" + " Please check order number and try again or /exit";
            printService.printTelegramNotification(chatId, message);
        }
    }
}