package electronic_library.core.services.book.validators;

import electronic_library.core.requests.book.DeleteBookByIdRequest;
import electronic_library.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteBookByIdValidator {

    public List<CoreError> validate(DeleteBookByIdRequest request) {

        List<CoreError> errors = new ArrayList<>();

        if (isBookIdEmpty(request)) {
            errors.add(new CoreError("Book ID", "not be empty!"));
        } else {
            if (isBookIdNegative(request)) {
                errors.add(new CoreError("Book ID", "not be negative!"));
            }
        }
        return errors;
    }

    private boolean isBookIdNegative(DeleteBookByIdRequest request) {
        return request.getBookIdToDelete() < 0;
    }

    private boolean isBookIdEmpty(DeleteBookByIdRequest request) {
        return request.getBookIdToDelete() == null;
    }
}
