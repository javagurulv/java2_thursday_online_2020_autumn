package book_library.core.responses.Book;

import book_library.core.domain.Book;
import book_library.core.responses.CoreError;
import book_library.core.responses.CoreResponse;

import java.util.List;

public class DeleteBookResponse  extends CoreResponse {

    private Book deletedBook;

    public DeleteBookResponse(List<CoreError> errors) {
        super(errors);
    }

    public DeleteBookResponse(Book deletedBook) {
        this.deletedBook = deletedBook;
    }

    public Book getDeletedBook() {
        return deletedBook;
    }
}
