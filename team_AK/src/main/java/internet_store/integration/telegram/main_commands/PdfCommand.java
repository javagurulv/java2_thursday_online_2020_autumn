package internet_store.integration.telegram.main_commands;

import internet_store.integration.PrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PdfCommand implements UpdateMainCommand {
    @Autowired
    private PrintService printService;

    @Override
    public void mainCommandUpdateOutput(long chatId) {
        String message = "For information please enter order number and phone number";
        printService.printTelegramNotification(chatId, message);
        String orderNumberMessage = "Please enter order number";
        printService.printTelegramNotification(chatId, orderNumberMessage);
    }
}