package book_library.core.validators.Book;

import book_library.core.requests.Book.GetBookRequest;
import book_library.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GetBookValidator {

    public List<CoreError> validate (GetBookRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        return errors;
    }

    private Optional <CoreError> validateId (GetBookRequest request){
        return (request.getId() == null)
                ? Optional.of(new CoreError("id", "Must not be empty"))
                : Optional.empty();
    }
}
