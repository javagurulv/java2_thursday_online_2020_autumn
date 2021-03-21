package book_library.core.responses.Book;

import book_library.core.domain.Book;
import book_library.core.responses.CoreError;
import book_library.core.responses.CoreResponse;

import java.util.List;

public class UpdateBookResponse extends CoreResponse {

    private Book updatedBook;

    public UpdateBookResponse(List<CoreError> errors) {
        super(errors);
    }

    public UpdateBookResponse(Book updatedBook) {
        this.updatedBook = updatedBook;
    }

    public Book getUpdatedBook() {
        return updatedBook;
    }
}
