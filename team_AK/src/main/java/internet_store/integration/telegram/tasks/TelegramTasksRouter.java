package internet_store.integration.telegram.tasks;

import internet_store.integration.PrintService;
import internet_store.integration.telegram.main_commands.*;
import internet_store.integration.telegram.tasks.tasks.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.exceptions.TelegramApiValidationException;

import java.io.IOException;

@Component
public class TelegramTasksRouter {
    @Autowired
    private TaskGetOrderInfoByOrderNumber taskGetOrderInfoByOrderNumber;
    @Autowired
    private TaskGetPdfOrder taskGetPdfOrder;
    @Autowired
    private PrintService printService;
    @Autowired
    private TasksControl tasksControl;
    @Autowired
    private TaskFindProduct taskFindProduct;
    @Autowired
    private StartCommand startCommand;
    @Autowired
    private OrderCommand orderCommand;
    @Autowired
    private PdfCommand pdfCommand;
    @Autowired
    private ExitCommand exitCommand;
    @Autowired
    private FindCommand findCommand;
    @Autowired
    private IllegalInput illegalInput;

    public void getChatBotCommand(String messageText, long chatId) throws IOException, TelegramApiValidationException {

        switch (messageText) {
            case "/start" -> {
                startCommand.mainCommandUpdateOutput(chatId);
                return;
            }
            case "/order" -> {
                orderCommand.mainCommandUpdateOutput(chatId);
                tasksControl.allTasksStop();
                taskGetOrderInfoByOrderNumber.setTaskRun(true);
                return;
            }
            case "/pdf" -> {
                pdfCommand.mainCommandUpdateOutput(chatId);
                tasksControl.allTasksStop();
                taskGetPdfOrder.setTaskRun(true);
                return;
            }
            case "/find" -> {
                findCommand.mainCommandUpdateOutput(chatId);
                tasksControl.allTasksStop();
                taskFindProduct.setTaskRun(true);
                return;
            }
            case "/exit" -> {
                exitCommand.mainCommandUpdateOutput(chatId);
                tasksControl.allTasksStop();
                return;
            }
        }

        MasterTask runningTask = tasksControl.runTask();

        if (runningTask != null) {
            runningTask.runTask(messageText, chatId);
        } else {
            illegalInput.mainCommandUpdateOutput(chatId);
        }
    }
}