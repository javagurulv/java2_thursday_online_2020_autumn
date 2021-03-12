package electronic_library.core.responses.reader_books;

import electronic_library.core.domain.ReaderBooks;
import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.CoreResponse;

import java.util.List;

public class GetAllReaderBooksResponse extends CoreResponse {

    private List<ReaderBooks> readerBooks;

    public GetAllReaderBooksResponse(List<ReaderBooks> readerBooks) {
        this.readerBooks = readerBooks;
    }

    public GetAllReaderBooksResponse(List<CoreError> errors, List<ReaderBooks> readerBooks) {
        super(errors);
    }

    public List<ReaderBooks> getReaderBooks() {
        return readerBooks;
    }

}
