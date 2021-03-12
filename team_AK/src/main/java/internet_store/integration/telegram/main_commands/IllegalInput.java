package internet_store.integration.telegram.main_commands;

import internet_store.integration.PrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IllegalInput implements UpdateMainCommand {
    @Autowired
    private PrintService printService;

    @Override
    public void mainCommandUpdateOutput(long chatId) {
        String message = "Illegal input. Nothing to do " + "\uD83D\uDCA4" + " For information /start";
        printService.printTelegramNotification(chatId, message);
    }
}