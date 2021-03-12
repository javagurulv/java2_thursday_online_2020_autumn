package electronic_library.core.services.reader_books;

import electronic_library.core.database.reader_books.ReaderBooksRepository;
import electronic_library.core.domain.Reader;
import electronic_library.core.domain.ReaderBooks;
import electronic_library.core.requests.Ordering;
import electronic_library.core.requests.Paging;
import electronic_library.core.requests.reader_books.FindReaderBooksRequest;
import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.reader_books.FindReaderBooksResponse;
import electronic_library.core.services.reader_books.validarors.FindReaderBooksRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class FindReaderBooksService {

    @Autowired
    private ReaderBooksRepository readerBooksRepository;

    @Autowired
    private FindReaderBooksRequestValidator validator;

    public FindReaderBooksResponse execute(FindReaderBooksRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new FindReaderBooksResponse(null,errors);
        }

        List<ReaderBooks> readerBooks = find(request);
        readerBooks = order(readerBooks, request.getOrdering());
        readerBooks = paging(readerBooks, request.getPaging());

        return new FindReaderBooksResponse(readerBooks, null);
    }

    private List<ReaderBooks> order(List<ReaderBooks> readersBooks, Ordering ordering) {
        if (ordering != null) {
            Comparator<ReaderBooks> comparator = ordering.getOrderBy().equals("reader")
                    ? Comparator.comparing(ReaderBooks::getBookOutDate)
                    : Comparator.comparing(ReaderBooks::getBookReturnDate);
            if (ordering.getOrderDirection().equals("DESC")) {
                comparator = comparator.reversed();
            }
            return readersBooks.stream().sorted(comparator).collect(Collectors.toList());
        } else {
            return readersBooks;
        }
    }

    private List<ReaderBooks> find(FindReaderBooksRequest request) {
        List<ReaderBooks> readerBooks = new ArrayList<>();
        if (request.isReaderIdProvided() && !request.isBookIdProvided()) {
            readerBooks = readerBooksRepository.findReaderBooksByReaderId(request.getReaderId());
        }
        if (!request.isReaderIdProvided() && request.isBookIdProvided()) {
            readerBooks = readerBooksRepository.findReaderBooksByBookId(request.getBookId());
        }
            return readerBooks;
    }

    private List<ReaderBooks> paging(List<ReaderBooks> readerBooks, Paging paging) {
        if (paging != null) {
            int skip = (paging.getPageNumber() - 1) * paging.getPageSize();
            return readerBooks.stream()
                    .skip(skip)
                    .limit(paging.getPageSize())
                    .collect(Collectors.toList());
        } else {
            return readerBooks;
        }
    }
}
