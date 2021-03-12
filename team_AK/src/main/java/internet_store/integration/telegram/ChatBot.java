package internet_store.integration.telegram;

import internet_store.integration.PrintService;
import internet_store.integration.telegram.service.AddTelegramChatIdService;
import internet_store.integration.telegram.service.CheckTelegramChatIdService;
import internet_store.integration.telegram.service.FindOrderService;
import internet_store.integration.telegram.tasks.TelegramTasksRouter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class ChatBot extends TelegramLongPollingBot {
    @Autowired
    private FindOrderService findOrderService;
    @Autowired
    private CheckTelegramChatIdService telegramChatIdService;
    @Autowired
    private AddTelegramChatIdService addTelegramChatIdService;
    @Autowired
    private TelegramTasksRouter telegramTasksRouter;
    @Autowired
    private PrintService printService;
    @Value("${botName}")
    private String botName;
    @Value("${botToken}")
    private String botToken;

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            String chatMessage = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            telegramTasksRouter.getChatBotCommand(chatMessage, chatId);
        }
    }
}