package electronic_library.core.responses.reader_books;

import electronic_library.core.domain.Book;
import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.CoreResponse;

import java.util.List;
import java.util.Optional;

public class FindReaderBooksByBookIdResponse extends CoreResponse {

    private Optional<Book> findReaderBooksByBookId;

    public FindReaderBooksByBookIdResponse(Optional<Book> findReaderBooksByBookId) {
        this.findReaderBooksByBookId = findReaderBooksByBookId;
    }

    public FindReaderBooksByBookIdResponse(List<CoreError> errors) {
        super(errors);
    }

    public Optional<Book> getFindReaderBooksByBookId() {
        return findReaderBooksByBookId;
    }

}
