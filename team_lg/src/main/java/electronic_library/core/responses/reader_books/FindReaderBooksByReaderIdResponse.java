package electronic_library.core.responses.reader_books;

import electronic_library.core.domain.Reader;
import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.CoreResponse;

import java.util.List;
import java.util.Optional;

public class FindReaderBooksByReaderIdResponse extends CoreResponse {

    private Optional<Reader> findReaderById;

    public FindReaderBooksByReaderIdResponse(Optional<Reader> findReaderById) {
        this.findReaderById = findReaderById;
    }

    public FindReaderBooksByReaderIdResponse(List<CoreError> errors) {
        super(errors);
    }

    public Optional<Reader> getFindReaderById() {
        return findReaderById;
    }

}
