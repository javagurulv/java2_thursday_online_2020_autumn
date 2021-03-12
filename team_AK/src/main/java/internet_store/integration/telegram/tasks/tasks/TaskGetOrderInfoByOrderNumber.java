package internet_store.integration.telegram.tasks.tasks;

import internet_store.core.domain.Order;
import internet_store.integration.PrintService;
import internet_store.integration.telegram.service.AddTelegramChatIdService;
import internet_store.integration.telegram.service.CheckTelegramChatIdService;
import internet_store.integration.telegram.service.FindOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class TaskGetOrderInfoByOrderNumber extends MasterTask {
    @Autowired
    private FindOrderService findOrderService;
    @Autowired
    private CheckTelegramChatIdService telegramChatIdService;
    @Autowired
    private AddTelegramChatIdService addTelegramChatIdService;
    @Autowired
    private PrintService printService;
    private boolean isTaskRun = false;

    @Override
    public void runTask(String chatBotMessage, long chatId) {
        AtomicBoolean recordChatIdAndOrderNumberExists = new AtomicBoolean(false);

        Optional<Order> clientOrder = findOrderService.tryFindOrder(chatBotMessage);

        clientOrder.ifPresentOrElse(order -> {
            printService.printTelegramNotification(chatId, printService.createPrintReport(order));
            recordChatIdAndOrderNumberExists
                    .set(isRecordChatIdAndOrderNumber(chatId, order.getNumber()));
            String message = "Command completed " + "✅" + " /start";
            printService.printTelegramNotification(chatId, message);
            isTaskRun = false;
        }, () -> {
            String message = "❌" + "Please check order number and try again or /exit";
            printService.printTelegramNotification(chatId, message);
        });

        if (!(recordChatIdAndOrderNumberExists).get()) {
            clientOrder.ifPresent(order -> addTelegramChatIdService
                    .AddNewClientChatId(chatId, order.getNumber()));
        }
    }

    @Override
    public boolean isTaskRun() {
        return isTaskRun;
    }

    @Override
    public void setTaskRun(boolean taskRunStatus) {
        isTaskRun = taskRunStatus;
    }

    private boolean isRecordChatIdAndOrderNumber(long chatId, String orderNumber) {
        return telegramChatIdService.isRecordExists(chatId, orderNumber);
    }
}