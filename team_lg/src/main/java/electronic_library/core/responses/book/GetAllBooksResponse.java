package electronic_library.core.responses.book;

import electronic_library.core.domain.Book;
import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.CoreResponse;

import java.util.List;

public class GetAllBooksResponse extends CoreResponse {

    private List<Book> books;

    public GetAllBooksResponse(List<Book> books) {
        this.books = books;
    }

    public GetAllBooksResponse(List<CoreError> errors, List<Book> books) {
        super(errors);
    }

    public List<Book> getBooks() {
        return books;
    }

}
