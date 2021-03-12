package electronic_library.ui.reader_books;

import electronic_library.core.requests.reader_books.AddReaderBooksRequest;
import electronic_library.core.responses.reader_books.AddReaderBooksResponse;
import electronic_library.core.services.reader_books.AddReaderBooksService;
import electronic_library.ui.UICommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

@Component
public class AddReaderBooksUICommand implements UICommand {

    @Autowired
    private AddReaderBooksService addReaderBooksService;

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter reader ID: ");
        Long readerId = scanner.nextLong();

        System.out.print("Please enter book ID: ");
        Long bookId = scanner.nextLong();

        Date bookOutDate = new Date();

        System.out.print("Please enter date and time (yyyy/MM/dd HH:mm): ");
        String inputBookOutDate = scanner.nextLine();
/*
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        if (inputBookOutDate.isEmpty()) {
            bookOutDate = null;
        } else {
            try {
                bookOutDate = formatDate.parse(inputBookOutDate);
            } catch (ParseException e) {
                System.out.println("Incorrect input, please enter date format (yyyy/MM/dd HH:mm)!");
            }
        }
*/
        AddReaderBooksRequest request = new AddReaderBooksRequest(readerId, bookId, inputBookOutDate);
        AddReaderBooksResponse response = addReaderBooksService.execute(request);

        if (response.hasErrors()) {
            System.out.println("\n================== INCORRECT DATA ENTRY ======================");
            response.getErrors().forEach(System.out::println);
            System.out.println("==============================================================");
        } else {
            System.out.println("\n==============================================================");
            System.out.println("New ID: " + response.getNewReaderBooks().getId());
            System.out.println("Reader Books: reader: " + readerId + ", book: " + bookId + ", book out date: " + bookOutDate);
            System.out.println("\nThe record has been successfully added to Reader's books in electronic library!");
            System.out.println("==============================================================");
        }
    }
}
