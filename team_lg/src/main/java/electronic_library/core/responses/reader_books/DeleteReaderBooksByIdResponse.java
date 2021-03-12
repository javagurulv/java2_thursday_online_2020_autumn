package electronic_library.core.responses.reader_books;

import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.CoreResponse;

import java.util.List;

public class DeleteReaderBooksByIdResponse extends CoreResponse {

    private boolean readerBooksRemoved;

    public DeleteReaderBooksByIdResponse(boolean readerBooksRemoved) {
        this.readerBooksRemoved = readerBooksRemoved;
    }

    public DeleteReaderBooksByIdResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isReaderBooksRemoved() {
        return readerBooksRemoved;
    }

}
