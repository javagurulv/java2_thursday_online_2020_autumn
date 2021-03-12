package electronic_library.core.responses.reader_books;

import electronic_library.core.domain.ReaderBooks;
import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.CoreResponse;

import java.util.List;

public class AddReaderBooksResponse extends CoreResponse {

    private ReaderBooks newReaderBooks;
    public AddReaderBooksResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddReaderBooksResponse(ReaderBooks newReaderBooks) {
        this.newReaderBooks = newReaderBooks;
    }

    public ReaderBooks getNewReaderBooks() {
        return newReaderBooks;
    }
}
