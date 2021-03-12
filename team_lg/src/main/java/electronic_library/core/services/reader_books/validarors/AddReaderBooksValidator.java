package electronic_library.core.services.reader_books.validarors;

import electronic_library.core.requests.reader_books.AddReaderBooksRequest;
import electronic_library.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddReaderBooksValidator {

    public List<CoreError> validate(AddReaderBooksRequest request) {

        List<CoreError> errors = new ArrayList<>();
        validateReaderBooksReaderId(request).ifPresent(errors::add);
        validateReaderBooksBookId(request).ifPresent(errors::add);
        validateReaderBooksBookOutDate(request).ifPresent(errors::add);
        validateReaderBooksBookReturnDate(request).ifPresent(errors::add);
        return errors;
    }
    private Optional<CoreError> validateReaderBooksReaderId(AddReaderBooksRequest request) {
        if (request.getReaderId() == null) {
            Optional.of(new CoreError("Reader ID", "not be empty."));
        } else try {
            request.getReaderId();
        } catch (NumberFormatException e) {
            Optional.of(new CoreError("Reader ID", "Should be valid."));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateReaderBooksBookId(AddReaderBooksRequest request) {
         if (request.getBookId() == null) {
            Optional.of(new CoreError("Book ID", "not be empty."));
        } else try {
            request.getBookId();
        } catch (NumberFormatException e) {
            Optional.of(new CoreError("Book ID", "Should be valid."));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateReaderBooksBookOutDate(AddReaderBooksRequest request) {
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        try {
            dateTimeFormatter.parse(request.getBookOutDate());
        }
        catch (ParseException e) {
            Optional.of(new CoreError("bookOutDate", "Not valid input for book out date"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateReaderBooksBookReturnDate(AddReaderBooksRequest request) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm").withResolverStyle(ResolverStyle.STRICT);
        try {
            dateTimeFormatter.parse((CharSequence) request.getBookReturnDate());
        }
        catch (DateTimeParseException e) {
            Optional.of(new CoreError("bookReturnDate", "Not valid input for book return date"));
        }
        return Optional.empty();
    }
}


