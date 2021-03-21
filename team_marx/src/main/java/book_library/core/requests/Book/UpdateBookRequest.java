package book_library.core.requests.Book;

public class UpdateBookRequest {

    private Long id;
    private String newTitle;
    private String newAuthor;

    public UpdateBookRequest() {
    }

    public Long getId() {
        return id;
    }

    public String getNewTitle() {
        return newTitle;
    }

    public String getNewAuthor() {
        return newAuthor;
    }
}
