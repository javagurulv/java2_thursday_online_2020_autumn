package internet_store.integration.telegram.main_commands;

import internet_store.integration.PrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StartCommand implements UpdateMainCommand {
    @Autowired
    private PrintService printService;

    @Override
    public void mainCommandUpdateOutput(long chatId) {
        String message = "Welcome to EStore " + "\uD83C\uDFEC" + "\n" +
                "For information about order /order" + "\n" +
                "Get order pdf file /pdf" + "\n" +
                "Find product by title /find" + "\n" +
                "Return Main menu /exit";
        printService.printTelegramNotification(chatId, message);
    }
}