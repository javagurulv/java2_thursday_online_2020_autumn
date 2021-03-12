package internet_store.integration.telegram.tasks.tasks;

import internet_store.core.domain.Product;
import internet_store.core.operation.OrderSumProperty;
import internet_store.core.request.product.SearchProductByTitleRequest;
import internet_store.core.response.product.SearchProductByTitleResponse;
import internet_store.core.service.product.SearchProductByTitleService;
import internet_store.integration.PrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskFindProduct extends MasterTask {
    @Autowired
    private SearchProductByTitleService searchProductService;
    @Autowired
    private OrderSumProperty sumProperty;
    @Autowired
    private PrintService printService;
    private boolean isTaskRun = false;

    @Override
    public void runTask(String chatBotMessage, long chatId) {
        SearchProductByTitleResponse response = searchProductService.execute(new SearchProductByTitleRequest(chatBotMessage));
        Product product = response.getProduct();

        if (product != null) {
            isTaskRun = false;

            String message = "Yes, I find it \uD83C\uDD97";
            printService.printTelegramNotification(chatId, message);
            String messageAboutProduct = product.getTitle() + "\n" +
                    "Description: " + product.getDescription() + "\n" +
                    "Category: " + product.getCategory() + "\n" +
                    "Quantity: " + product.getQuantity() + "\n" +
                    "Price: " + product.getPrice() + " " + sumProperty.getCurrencySymbol();
            printService.printTelegramNotification(chatId, messageAboutProduct);
            String messageCompleted = "Command completed " + "✅" + " /start";
            printService.printTelegramNotification(chatId, messageCompleted);
        } else {
            String messageNoFonded = "❌" + " Sorry no founded try again or /exit";
            printService.printTelegramNotification(chatId, messageNoFonded);
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
}