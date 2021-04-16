package store.service.utility;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import store.entity.orders.Order;
import store.service.orders.OrderService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class PdfServiceImpl implements PdfService {

    @Autowired
    OrderService orderService;

    public static void prepareDocument(Long orderId) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("pdf_orders/order.pdf"));
            document.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Chunk chunk = new Chunk(orderId.toString(), font);
            document.add(chunk);

            document.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Resource getPdf() {
        Order order;
        prepareDocument(1L);
        return throwOutDocument();
    }

    public Resource throwOutDocument() {
        try {
            Path file = Paths.get("pdf_orders").resolve("order.pdf");
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
        } catch (MalformedURLException e) {
            System.out.println("error " + e);
        }
        return null;
    }

}
