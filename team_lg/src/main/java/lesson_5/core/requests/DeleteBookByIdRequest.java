package lesson_5.core.requests;

public class DeleteBookByIdRequest {

    private Long bookIdToDelete;

    public DeleteBookByIdRequest(Long bookIdToDelete) {
        this.bookIdToDelete = bookIdToDelete;
    }

    public Long getBookIdToDelete() {
        return bookIdToDelete;
    }
}
