package internet_store.core.operation;

import internet_store.core.service.ordering.CreatePdfOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.File;
import java.io.IOException;

@Component
public class OpenOrderPdfFormat {
    @Autowired
    private CreatePdfOrder pdfOrder;

    public void showPdfOrder(String orderNumber) throws IOException {
        File file = new File(pdfOrder.getPdfFileAndPath(orderNumber));
        if (file.toString().endsWith(".pdf"))
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file);
        else {
            Desktop desktop = Desktop.getDesktop();
            desktop.open(file);
        }
    }
}