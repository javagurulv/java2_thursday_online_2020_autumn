package electronic_library.core.services.reader_books;

import electronic_library.core.database.reader_books.ReaderBooksRepository;
import electronic_library.core.requests.reader_books.DeleteReaderBooksByIdRequest;
import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.reader_books.DeleteReaderBooksByIdResponse;
import electronic_library.core.services.reader_books.validarors.DeleteReaderBooksByIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class DeleteReaderBooksByIdService {

    @Autowired
    private ReaderBooksRepository readerBooksRepository;

    @Autowired
    private DeleteReaderBooksByIdValidator validator;

    public DeleteReaderBooksByIdResponse deleteReaderBooksByIdResponse(DeleteReaderBooksByIdRequest request) {
        List<CoreError> errors = validator.validate(request);
        Long id = request.getReaderBooksIdToDelete();
        if (!errors.isEmpty()) {
            return new DeleteReaderBooksByIdResponse(errors);
        } else return new DeleteReaderBooksByIdResponse(readerBooksRepository.deleteRecordFromReaderBooksById(id));
    }
}
