package lesson_12.core.requests.book;

public class FindBookByIdRequest {

    private String bookId;

    public FindBookByIdRequest(String bookId) {
        this.bookId = bookId;
    }

    public String getBookId() {
        return bookId;
    }
}
