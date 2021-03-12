package electronic_library.core.responses.reader_books;

import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.CoreResponse;

import java.util.List;

public class ReturnReaderBooksByIdResponse extends CoreResponse {
    private boolean readerBooksReturned;

    public ReturnReaderBooksByIdResponse(boolean readerBooksReturned) {
        this.readerBooksReturned = readerBooksReturned;
    }

    public ReturnReaderBooksByIdResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isReaderBooksReturned() {
        return readerBooksReturned;
    }

}
