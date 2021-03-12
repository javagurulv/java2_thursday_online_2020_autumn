package electronic_library.core.services.reader_books;

import electronic_library.core.database.book.ElectronicLibraryRepository;
import electronic_library.core.database.reader_books.ReaderBooksRepository;
import electronic_library.core.requests.reader_books.FindReaderBooksByBookIdRequest;
import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.reader_books.FindReaderBooksByBookIdResponse;
import electronic_library.core.services.reader_books.validarors.FindReaderBooksByBookIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class FindReaderBooksByBookIdService {

    @Autowired
    private ReaderBooksRepository readerBooksRepository;

    @Autowired
    private ElectronicLibraryRepository electronicLibrary;

    @Autowired
    private FindReaderBooksByBookIdValidator validator;

    public FindReaderBooksByBookIdResponse findReaderBooksByBookIdResponse (FindReaderBooksByBookIdRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new FindReaderBooksByBookIdResponse(errors);
        }
        Long id = Long.parseLong(request.getBookId());
        return new FindReaderBooksByBookIdResponse(electronicLibrary.findBookById(id));
    }
}
