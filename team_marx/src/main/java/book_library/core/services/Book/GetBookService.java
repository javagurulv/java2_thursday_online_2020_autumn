package book_library.core.services.Book;

import book_library.core.database.Book.BookRepository;
import book_library.core.requests.Book.GetBookRequest;
import book_library.core.responses.Book.GetBookResponse;
import book_library.core.responses.CoreError;
import book_library.core.validators.Book.GetBookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class GetBookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private GetBookValidator validator;

    public GetBookResponse execute(GetBookRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetBookResponse(errors);
        }
        return bookRepository.getById(request.getId())
                .map(GetBookResponse::new)
                .orElseGet(() -> {
                    errors.add(new CoreError("id", "Not Found!"));
                    return new GetBookResponse(errors);
                });
    }
}
