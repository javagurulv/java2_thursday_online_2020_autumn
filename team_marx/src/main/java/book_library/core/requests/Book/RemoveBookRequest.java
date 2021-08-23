package book_library.core.requests.Book;

public class RemoveBookRequest {

    private Long bookIdToRemove;

    public RemoveBookRequest(Long bookIdToRemove) {
        this.bookIdToRemove = bookIdToRemove;
    }

    public Long getBookIdToRemove() {
        return bookIdToRemove;
    }
}
