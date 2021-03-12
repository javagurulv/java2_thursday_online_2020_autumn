package electronic_library.core.services.reader_books.validarors;

import electronic_library.core.requests.reader_books.DeleteReaderBooksByIdRequest;
import electronic_library.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteReaderBooksByIdValidator {
    public List<CoreError> validate(DeleteReaderBooksByIdRequest request) {

        List<CoreError> errors = new ArrayList<>();

        if (isReaderBooksIdEmpty(request)) {
            errors.add(new CoreError("ReaderBooks ID", "not be empty!"));
        } else {
            if (isReaderIdNegative(request)) {
                errors.add(new CoreError("ReaderBooks ID", "not be negative!"));
            }
        }
        return errors;
    }

    private boolean isReaderIdNegative(DeleteReaderBooksByIdRequest request) {
        return request.getReaderBooksIdToDelete() < 0;
    }
    private boolean isReaderBooksIdEmpty(DeleteReaderBooksByIdRequest request) {
        return request.getReaderBooksIdToDelete() == null;
    }

}
