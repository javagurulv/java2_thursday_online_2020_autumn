package internet_store.integration.telegram.tasks.tasks;

import internet_store.integration.PrintService;
import internet_store.integration.telegram.service.CheckOrderByClientPhoneService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskCheckClientPhoneNumber {
    @Autowired
    private CheckOrderByClientPhoneService checkOrderByClientPhoneService;
    @Autowired
    private PrintService printService;
    @Getter
    @Setter
    private boolean isSearchPhoneNumberCompleted = false;

    public void checkPhoneNumber(String chatBotMessage, long chatId) {
        boolean isPhoneNumberExists = checkOrderByClientPhoneService.isClientPhoneExists(chatBotMessage);
        if (isPhoneNumberExists) {
            isSearchPhoneNumberCompleted = true;
        } else {
            String message = "❌" + " Please check phone number and try again or /exit";
            printService.printTelegramNotification(chatId, message);
        }
    }
}