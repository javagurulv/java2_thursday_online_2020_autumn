package book_library.core.requests.Book;

public class AddBookRequest {

    private String title;
    private String author;

    public AddBookRequest(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public AddBookRequest() {

    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
