package book_library.core.validators.Book;

import book_library.core.requests.Book.UpdateBookRequest;
import book_library.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UpdateBookRequestValidator {

    public List<CoreError> validate(UpdateBookRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateTitle(request).ifPresent(errors::add);
        validateAuthor(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateTitle(UpdateBookRequest request) {
        return (request.getNewTitle() == null || request.getNewTitle().isEmpty())
                ? Optional.of(new CoreError("newTitle", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateAuthor(UpdateBookRequest request) {
        return (request.getNewAuthor() == null || request.getNewAuthor().isEmpty())
                ? Optional.of(new CoreError("newAuthor", "Must not be empty!"))
                : Optional.empty();
    }
}
