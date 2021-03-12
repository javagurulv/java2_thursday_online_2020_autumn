package electronic_library.core.services.reader_books;

import electronic_library.core.database.reader_books.ReaderBooksRepository;
import electronic_library.core.domain.ReaderBooks;
import electronic_library.core.requests.reader_books.AddReaderBooksRequest;
import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.reader_books.AddReaderBooksResponse;
import electronic_library.core.services.reader_books.validarors.AddReaderBooksValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class AddReaderBooksService {

    @Autowired
    private ReaderBooksRepository readerBooksRepository;

    @Autowired
    private AddReaderBooksValidator validator;

    public AddReaderBooksResponse execute(AddReaderBooksRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddReaderBooksResponse(errors);
        }
        ReaderBooks readerBooks = new ReaderBooks(request.getReaderId(), request.getBookId(),
                        request.getBookOutDate(), request.getBookReturnDate());

        if (readerBooksRepository.containsReaderBooks(readerBooks.getReader(), readerBooks.getBook(), readerBooks.getBookOutDate())) {
            errors.add(new CoreError("database", "Database contains the this reader"));
            return new AddReaderBooksResponse(errors);
        } else {
            readerBooksRepository.addRecordToReaderBooks(getReaderBooks(readerBooks));
            return new AddReaderBooksResponse(readerBooks);
        }
    }
    private ReaderBooks getReaderBooks(ReaderBooks readerBooks) {
        return readerBooks;
    }
}
