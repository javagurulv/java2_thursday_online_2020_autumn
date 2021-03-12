package electronic_library.core.services.reader_books.validarors;

import electronic_library.core.requests.reader_books.FindReaderBooksByBookIdRequest;
import electronic_library.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FindReaderBooksByBookIdValidator {

    public List<CoreError> validate(FindReaderBooksByBookIdRequest request) {

        List<CoreError> errors = new ArrayList<>();

        if (request.getBookId() == null || (request.getBookId().isBlank()))  {
            errors.add(new CoreError("Book ID", "not be empty."));
        } else try {
            Long.parseLong(request.getBookId());
        } catch (NumberFormatException e) {
            errors.add(new CoreError("Book ID", "Should be valid."));
        }
        return errors;
    }
}
