package electronic_library.core.requests.reader_books;

public class FindReaderBooksByBookIdRequest {

    private String BookId;

    public FindReaderBooksByBookIdRequest(String BookId) {
        this.BookId = BookId;
    }

    public String getBookId() {
        return BookId;
    }
}
