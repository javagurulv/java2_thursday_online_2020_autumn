package electronic_library.core.services.reader_books;

import electronic_library.core.database.book.ElectronicLibraryRepository;
import electronic_library.core.database.reader.ReaderRepository;
import electronic_library.core.database.reader_books.ReaderBooksRepository;
import electronic_library.core.requests.reader_books.FindReaderBooksByBookIdRequest;
import electronic_library.core.requests.reader_books.FindReaderBooksByReaderIdRequest;
import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.reader_books.FindReaderBooksByBookIdResponse;
import electronic_library.core.responses.reader_books.FindReaderBooksByReaderIdResponse;
import electronic_library.core.services.reader_books.validarors.FindReaderBooksByBookIdValidator;
import electronic_library.core.services.reader_books.validarors.FindReaderBooksByReaderIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class FindReaderBooksByReaderIdService {

    @Autowired
    private ReaderBooksRepository readerBooksRepository;

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private FindReaderBooksByReaderIdValidator validator;

    public FindReaderBooksByReaderIdResponse findReaderBooksByReaderIdResponse (FindReaderBooksByReaderIdRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new FindReaderBooksByReaderIdResponse(errors);
        }
        Long id = Long.parseLong(request.getReaderId());
        return new FindReaderBooksByReaderIdResponse(readerRepository.findReaderById(id));
    }
}
