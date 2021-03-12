package internet_store.integration.telegram.tasks.tasks;

import org.telegram.telegrambots.meta.exceptions.TelegramApiValidationException;

import java.io.IOException;

public abstract class MasterTask {
    public abstract void runTask(String chatBotMessage, long chatId) throws TelegramApiValidationException, IOException;
    public abstract boolean isTaskRun();
    public abstract void setTaskRun(boolean taskRunStatus);
}