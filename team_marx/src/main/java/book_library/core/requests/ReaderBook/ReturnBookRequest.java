package book_library.core.requests.ReaderBook;

import java.util.Date;

public class ReturnBookRequest {
    private Long readerId;
    private Long bookId;
    private Date bookReturnDate;

    public ReturnBookRequest() {
    }

    public ReturnBookRequest(Long readerId, Long bookId, Date bookReturnDate) {
        this.readerId = readerId;
        this.bookId = bookId;
        this.bookReturnDate = bookReturnDate;
    }

    public Long getReaderId() {
        return readerId;
    }

    public void setReaderId(Long readerId) {
        this.readerId = readerId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Date getBookReturnDate() {
        return bookReturnDate;
    }

    public void setBookReturnDate(Date bookReturnDate) {
        this.bookReturnDate = bookReturnDate;
    }
}
