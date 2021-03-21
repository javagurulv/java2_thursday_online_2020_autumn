package book_library.core.services.Book;

import book_library.core.database.Book.BookRepository;
import book_library.core.requests.Book.RemoveBookRequest;
import book_library.core.responses.Book.RemoveBookResponse;
import book_library.core.responses.CoreError;
import book_library.core.validators.Book.RemoveBookRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class DeleteBookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private RemoveBookRequestValidator validator;

    @Transactional
    public RemoveBookResponse execute(RemoveBookRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new RemoveBookResponse(errors);
        }
        boolean isBookRemoved = bookRepository.deleteById(request.getBookIdToRemove());
        return new RemoveBookResponse(isBookRemoved);
    }
}
