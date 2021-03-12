package electronic_library.core.responses.reader_books;

import electronic_library.core.domain.ReaderBooks;
import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.CoreResponse;

import java.util.List;

public class FindReaderBooksResponse extends CoreResponse {

    private List<ReaderBooks> readerBooks;

    public FindReaderBooksResponse(List<ReaderBooks> readerBooks, List<CoreError> errors) {
        super(errors);
        this.readerBooks = readerBooks;
    }

    public List<ReaderBooks> getReaderBooks() {
        return readerBooks;
    }

}
