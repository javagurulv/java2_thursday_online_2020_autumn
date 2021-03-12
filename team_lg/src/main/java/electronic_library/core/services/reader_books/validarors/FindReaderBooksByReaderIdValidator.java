package electronic_library.core.services.reader_books.validarors;

import electronic_library.core.requests.reader_books.FindReaderBooksByReaderIdRequest;
import electronic_library.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FindReaderBooksByReaderIdValidator {

    public List<CoreError> validate(FindReaderBooksByReaderIdRequest request) {

        List<CoreError> errors = new ArrayList<>();

        if (request.getReaderId() == null || (request.getReaderId().isBlank()))  {
            errors.add(new CoreError("Reader ID", "not be empty."));
        } else try {
            Long.parseLong(request.getReaderId());
        } catch (NumberFormatException e) {
            errors.add(new CoreError("Reader ID", "Should be valid."));
        }
        return errors;
    }
}
