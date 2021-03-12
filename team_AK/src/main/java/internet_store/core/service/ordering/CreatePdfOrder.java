package internet_store.core.service.ordering;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import internet_store.core.domain.Order;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class CreatePdfOrder {
    @Getter
    @Setter
    @Value("${order-save-directory}")
    private String orderDirectoryPath;
    private List<Order> productsForReport;
    private String clientName;
    private String clientSurname;
    private String clientPhone;
    private String clientEmail;
    private String currencySymbol;
    private BigDecimal orderSum;
    private BigDecimal orderTax;
    private BigDecimal orderTotal;

    public void createPdfOrder(List<Order> productsForReport, String currencySymbol) throws IOException, DocumentException {
        this.productsForReport = productsForReport;
        String orderNumber = productsForReport.get(0).getNumber();
        clientName = productsForReport.get(0).getClient().getName();
        clientSurname = productsForReport.get(0).getClient().getSurname();
        clientPhone = productsForReport.get(0).getClient().getPhoneNumber();
        clientEmail = productsForReport.get(0).getClient().getEmail();
        this.currencySymbol = currencySymbol;
        orderSum = productsForReport.get(0).getSum();
        orderTax = productsForReport.get(0).getTax();
        orderTotal = productsForReport.get(0).getTotal();

        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(getPdfFileAndPath(orderNumber)));
        } catch (FileNotFoundException e) {
            boolean directoryCreated = new File(orderDirectoryPath).mkdir();
            if (directoryCreated) {
                PdfWriter.getInstance(document, new FileOutputStream(getPdfFileAndPath(orderNumber)));
            }
        }

        document.open();
        document.add(loadIcon());

        Paragraph orderDocument = new Paragraph("Order Nr." + orderNumber);
        orderDocument.setAlignment(Element.ALIGN_CENTER);
        document.add(orderDocument);

        document.add(Chunk.NEWLINE);

        createClientReport(document);

        document.add(Chunk.NEWLINE);

        PdfPTable table = createTableHead();
        createTableBody(table);

        document.add(table);
        document.add(Chunk.NEWLINE);

        createSumReport(document);
        document.add(Chunk.NEWLINE);

        Paragraph createDate = new Paragraph("Report create date: " + new Date(), reportDateFont());
        document.add(createDate);
        document.close();
    }

    private void createSumReport(Document document) throws DocumentException {
        Paragraph sum = new Paragraph("     Sum: " + orderSum + " "
                + currencySymbol, reportHeadFont());
        sum.setAlignment(Element.ALIGN_RIGHT);
        document.add(sum);

        Paragraph tax = new Paragraph("     Tax: " + orderTax + " "
                + currencySymbol, reportHeadFont());
        tax.setAlignment(Element.ALIGN_RIGHT);
        document.add(tax);

        Paragraph total = new Paragraph("Total: " + orderTotal + " "
                + currencySymbol, reportHeadFont());
        total.setAlignment(Element.ALIGN_RIGHT);
        document.add(total);
    }

    private void createTableBody(PdfPTable table) {
        AtomicReference<PdfPCell> row = new AtomicReference<>();
        AtomicInteger rowCount = new AtomicInteger(1);

        productsForReport.forEach(i -> {
            row.set(new PdfPCell(new Phrase(Integer.toString(rowCount.getAndIncrement()), reportTableFont())));
            row.get().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(row.get());
            row.set(new PdfPCell(new Phrase(i.getCart().getProduct().getTitle(), reportTableFont())));
            row.get().setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(row.get());
            row.set(new PdfPCell(new Phrase(i.getCart().getQuantity().toString(), reportTableFont())));
            row.get().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(row.get());
            row.set(new PdfPCell(new Phrase(i.getCart().getProduct().getPrice().toString(), reportTableFont())));
            row.get().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(row.get());
            row.set(new PdfPCell(new Phrase(i.getCart().getSum().toString(), reportTableFont())));
            row.get().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(row.get());
        });

    }

    private PdfPTable createTableHead() {
        float[] columnWidth = {15, 100, 25, 25, 25};
        PdfPTable table = new PdfPTable(columnWidth);

        PdfPCell row = new PdfPCell(new Phrase("#", reportHeadFont()));
        row.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(row);

        row = new PdfPCell(new Phrase("Product Title", reportHeadFont()));
        row.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(row);

        row = new PdfPCell(new Phrase("Quantity", reportHeadFont()));
        row.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(row);

        row = new PdfPCell(new Phrase("Price", reportHeadFont()));
        row.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(row);

        row = new PdfPCell(new Phrase("Sum", reportHeadFont()));
        row.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(row);

        table.setHeaderRows(1);
        table.setWidthPercentage(100f);
        return table;
    }

    private void createClientReport(Document document) throws DocumentException {
        document.add(new Paragraph("Client name:        " + clientName, reportHeadFont()));
        document.add(new Paragraph("Client surname:  " + clientSurname, reportHeadFont()));
        document.add(new Paragraph("Client phone:       " + clientPhone, reportHeadFont()));
        document.add(new Paragraph("Client email:        " + clientEmail, reportHeadFont()));
    }

    private Image loadIcon() throws IOException, BadElementException {
        URL imageUrl = this.getClass().getResource("/templates/internet_store/images/estore.png");

        Image image = Image.getInstance(imageUrl);
        image.setAlignment(Element.ALIGN_LEFT);
        image.scaleAbsolute(50, 20);
        return image;
    }

    public String getPdfFileAndPath(String orderNumber) {
        return orderDirectoryPath + orderNumber.replace("/", "_") + ".pdf";
    }

    private Font reportHeadFont() {
        return new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
    }

    private Font reportTableFont() {
        return new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL);
    }

    private Font reportDateFont() {
        return new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.NORMAL);
    }
}