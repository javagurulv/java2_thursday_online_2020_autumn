package electronic_library.core.services.reader_books;

import electronic_library.core.database.reader_books.ReaderBooksRepository;
import electronic_library.core.domain.ReaderBooks;
import electronic_library.core.requests.reader_books.GetAllReaderBooksRequest;
import electronic_library.core.responses.reader_books.GetAllReaderBooksResponse;
import electronic_library.core.services.reader_books.validarors.GetAllReaderBooksValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class GetAllReaderBooksService {
    @Autowired
    private ReaderBooksRepository readerBooksRepository;

    @Autowired
    private GetAllReaderBooksValidator getAllReaderBooksValidator;

    public GetAllReaderBooksResponse execute(GetAllReaderBooksRequest request) {
        List<ReaderBooks> readerBooks = readerBooksRepository.listAllRecordsInReaderBooks();
        return new GetAllReaderBooksResponse(readerBooks);
    }
}
