package book_library.core.requests.Book;

public class DeleteBookRequest {

    private Long id;

    public DeleteBookRequest() {
    }

    public DeleteBookRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
