package book_library.core.services.Book;

import book_library.core.database.Book.BookRepository;
import book_library.core.requests.Book.UpdateBookRequest;
import book_library.core.responses.Book.UpdateBookResponse;
import book_library.core.responses.CoreError;
import book_library.core.validators.Book.UpdateBookRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class UpdateBookService {

    @Autowired private BookRepository bookRepository;
    @Autowired private UpdateBookRequestValidator validator;

    public UpdateBookResponse execute (UpdateBookRequest request){
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new UpdateBookResponse(errors);
        }

        return bookRepository.getById(request.getId())
                .map(book -> {
                    book.setTitle(request.getNewTitle());
                    book.setAuthor(request.getNewAuthor());
                    return new UpdateBookResponse(book);
                })
                .orElseGet(()->{
                    errors.add(new CoreError("id", "Not Found!"));
                    return new UpdateBookResponse(errors);
                });
    }
}
