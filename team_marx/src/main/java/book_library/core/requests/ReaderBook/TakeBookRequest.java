package book_library.core.requests.ReaderBook;

import java.util.Date;

public class TakeBookRequest {
    private Long readerId;
    private Long bookId;
    private Date bookOutDate;

    public TakeBookRequest() {
    }

    public TakeBookRequest(Long readerId, Long bookId, Date bookOutDate) {
        this.readerId = readerId;
        this.bookId = bookId;
        this.bookOutDate = bookOutDate;
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

    public Date getBookOutDate() {
        return bookOutDate;
    }

    public void setBookOutDate(Date bookOutDate) {
        this.bookOutDate = bookOutDate;
    }
}
