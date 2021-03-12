package book_library.core.responses.Book;

import book_library.core.domain.Book;
import book_library.core.responses.CoreError;
import book_library.core.responses.CoreResponse;

import java.util.List;

public class GetBookResponse extends CoreResponse {

    private Book book;

    public GetBookResponse(List<CoreError> errors) {
        super(errors);
    }

    public GetBookResponse(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }
}
