package internet_store.integration.telegram.tasks.tasks;

import internet_store.integration.PrintService;
import internet_store.integration.telegram.ChatBot;
import internet_store.integration.telegram.service.CheckOrderByClientPhoneService;
import internet_store.integration.telegram.service.CheckOrderByOrderNumberService;
import internet_store.integration.telegram.service.FindTelegramChatIdService;
import internet_store.integration.telegram.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TaskGetPdfOrder extends MasterTask {
    @Autowired
    private CheckOrderByOrderNumberService checkOrderByOrderNumberService;
    @Autowired
    private CheckOrderByClientPhoneService checkOrderByClientPhoneService;
    @Autowired
    private PrintService printService;
    @Autowired
    private ChatBot chatBot;
    @Autowired
    private FindTelegramChatIdService findTelegramChatIdService;
    @Autowired
    private UploadService uploadService;
    @Autowired
    private TaskCheckClientPhoneNumber taskCheckClientPhoneNumber;
    @Autowired
    private TaskCheckOrderNumber taskCheckOrderNumber;
    private boolean isTaskRun = false;
    @Value("${order-save-directory}")
    private String orderDirectoryPath;

    @Override
    public void runTask(String chatBotMessage, long chatId) {

        if (!taskCheckOrderNumber.isSearchOrderNumberCompleted()) {
            taskCheckOrderNumber.checkOrderNumber(chatBotMessage, chatId);
            if (taskCheckOrderNumber.isSearchOrderNumberCompleted()) {
                String message = "Please enter phone number";
                printService.printTelegramNotification(chatId, message);
                return;
            }
        }

        if (taskCheckOrderNumber.isSearchOrderNumberCompleted() && !taskCheckClientPhoneNumber.isSearchPhoneNumberCompleted()) {
            taskCheckClientPhoneNumber.checkPhoneNumber(chatBotMessage, chatId);
        }

        if (taskCheckClientPhoneNumber.isSearchPhoneNumberCompleted()) {
            sendClientPdfOrder(chatId);
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

    private void sendClientPdfOrder(long chatId) {
        String waitMessage = "Waiting.....";
        printService.printTelegramNotification(chatId, waitMessage);

        Thread pdfUpload = new Thread(() -> {
            try {
                uploadService.uploadFile(orderDirectoryPath + getPdfFile(), chatId);
                if (uploadService.isSuccessful()) {
                    String message = "Command completed " + "✅" + " /start";
                    printService.printTelegramNotification(chatId, message);
                }
            } catch (IOException e) {
                String errorMessage = "❌" + "Something going wrong. I do one more attempt. Please wait.";
                printService.printTelegramNotification(chatId, errorMessage);
            }
        });
        pdfUpload.start();

        taskCompleted();
    }

    private void taskCompleted() {
        taskCheckOrderNumber.setSearchOrderNumberCompleted(false);
        taskCheckClientPhoneNumber.setSearchPhoneNumberCompleted(false);
        isTaskRun = false;
    }

    private String getPdfFile() {
        return taskCheckOrderNumber.getOrderNumber()
                .replace("/", "_") + ".pdf";
    }
}