package electronic_library.core.requests.reader_books;

import electronic_library.core.requests.Ordering;
import electronic_library.core.requests.Paging;

import java.util.Date;

public class FindReaderBooksRequest {

    private Long bookId;
    private Long readerId;
    private Date bookOutDate;
    private Date bookReturnDate;
    private Ordering ordering;
    private Paging paging;

    public FindReaderBooksRequest(Long bookId, Long readerId, Date bookOutDate, Date bookReturnDate) {
        this.bookId = bookId;
        this.readerId = readerId;
        this.bookOutDate = bookOutDate;
        this.bookReturnDate = bookReturnDate;
    }

    public FindReaderBooksRequest(Long bookId, Long readerId, Date bookOutDate, Date bookReturnDate, Ordering ordering, Paging paging) {
        this.bookId = bookId;
        this.readerId = readerId;
        this.bookOutDate = bookOutDate;
        this.bookReturnDate = bookReturnDate;
        this.ordering = ordering;
        this.paging = paging;
    }

    public FindReaderBooksRequest(Long bookId, Long readerId, Date bookOutDate, Date bookReturnDate, Ordering ordering) {
        this.bookId = bookId;
        this.readerId = readerId;
        this.bookOutDate = bookOutDate;
        this.bookReturnDate = bookReturnDate;
        this.ordering = ordering;
    }

    public FindReaderBooksRequest(Long bookId, Long readerId, Date bookOutDate, Date bookReturnDate, Paging paging) {
        this.bookId = bookId;
        this.readerId = readerId;
        this.bookOutDate = bookOutDate;
        this.bookReturnDate = bookReturnDate;
        this.paging = paging;
    }

    public Long getBookId() {
        return bookId;
    }

    public Long getReaderId() {
        return readerId;
    }

    public Date getBookOutDate() {
        return bookOutDate;
    }

    public Date getBookReturnDate() {
        return bookReturnDate;
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public Paging getPaging() {
        return paging;
    }
    public boolean isBookIdProvided() {
        return this.bookId != null && !this.bookId.equals(0);
    }

    public boolean isReaderIdProvided() {
        return this.readerId != null && !this.readerId.equals(0);
    }

}
