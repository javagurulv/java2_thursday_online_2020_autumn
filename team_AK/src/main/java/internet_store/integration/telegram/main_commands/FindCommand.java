package internet_store.integration.telegram.main_commands;

import internet_store.integration.PrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindCommand implements UpdateMainCommand {
    @Autowired
    private PrintService printService;

    @Override
    public void mainCommandUpdateOutput(long chatId) {
        String message = "Please enter product title";
        printService.printTelegramNotification(chatId, message);
    }
}