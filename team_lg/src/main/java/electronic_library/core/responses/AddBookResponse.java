package electronic_library.core.responses;

import electronic_library.core.domain.Book;

import java.util.List;

public class AddBookResponse extends CoreResponse {

    private Book newBook;

    public AddBookResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddBookResponse(Book newBook) {
        this.newBook = newBook;
    }

    public Book getNewBook() {
        return newBook;
    }

}