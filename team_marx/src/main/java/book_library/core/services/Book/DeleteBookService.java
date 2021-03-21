package book_library.core.services.Book;

import book_library.core.database.Book.BookRepository;
import book_library.core.requests.Book.DeleteBookRequest;
import book_library.core.requests.Book.RemoveBookRequest;
import book_library.core.responses.Book.DeleteBookResponse;
import book_library.core.responses.Book.RemoveBookResponse;
import book_library.core.responses.CoreError;
import book_library.core.validators.Book.DeleteBookRequestValidator;
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
    private DeleteBookRequestValidator validator;

    @Transactional
    public DeleteBookResponse execute(DeleteBookRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteBookResponse(errors);
        }
        return bookRepository.getById(request.getId())
                .map(book -> {
                    bookRepository.deleteById(request.getId());
                    return new DeleteBookResponse(book);
                })
                .orElseGet(()->{
                    errors.add(new CoreError("id", "Not found!"));
                    return new DeleteBookResponse(errors);
                });
    }
}
