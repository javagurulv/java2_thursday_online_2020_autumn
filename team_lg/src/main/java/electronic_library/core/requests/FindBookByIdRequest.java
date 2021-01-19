package electronic_library.core.requests;

public class FindBookByIdRequest {

    private String bookId;

    public FindBookByIdRequest(String bookId) {
        this.bookId = bookId;
    }

    public String getBookId() {
        return bookId;
    }
}
