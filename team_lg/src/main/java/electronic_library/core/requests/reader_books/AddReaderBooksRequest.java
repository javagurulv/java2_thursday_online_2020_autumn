package electronic_library.core.requests.reader_books;

import electronic_library.core.domain.Book;
import electronic_library.core.domain.Reader;

import java.util.Date;

public class AddReaderBooksRequest {

 //   private Reader reader;
//    private Book book;
    private Long readerId;
    private Long bookId;
    private String bookOutDate;
    private Date bookReturnDate;

    public AddReaderBooksRequest(Long readerId, Long bookId, String bookOutDate) {
        this.readerId = readerId;
        this.bookId = bookId;
        this.bookOutDate = bookOutDate;
    }
    public AddReaderBooksRequest(Long readerId, Long bookId, String bookOutDate, Date bookReturnDate) {
        this.readerId = readerId;
        this.bookId = bookId;
        this.bookOutDate = bookOutDate;
        this.bookReturnDate = bookReturnDate;
    }

    public Long getReaderId() {
        return readerId;
    }

    public Long getBookId() {
        return bookId;
    }

    public String getBookOutDate() {
        return bookOutDate;
    }

    public Date getBookReturnDate() {
        return bookReturnDate;
    }
}
