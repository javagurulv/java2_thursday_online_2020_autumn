package book_library.core.requests.Book;

public class GetBookRequest {

    private Long id;

    public GetBookRequest() {
    }

    public GetBookRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
