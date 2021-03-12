package electronic_library.core.database.reader_books;

import electronic_library.core.domain.Book;
import electronic_library.core.domain.Reader;
import electronic_library.core.domain.ReaderBooks;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Profile("noJdbc")
public class ReaderBooksRepositoryImpl implements ReaderBooksRepository {

    private final List<ReaderBooks> readerBooksList = new ArrayList<>();
    private Long readerBooksId = 1L;

    @Override
    public void addRecordToReaderBooks(ReaderBooks readerBooks) {
        readerBooks.setId(readerBooksId);
        readerBooksId++;
        readerBooksList.add(readerBooks);
    }

    @Override
    public boolean deleteRecordFromReaderBooksById(Long id) {
        for (ReaderBooks readerBooks:readerBooksList) {
            if (readerBooks.getId().equals(id)){
                readerBooksList.remove(readerBooks);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<ReaderBooks> listAllRecordsInReaderBooks() {
        return readerBooksList;
    }

    @Override
    public boolean isNotReturnBookById(Long id) {
        for (ReaderBooks readerBooks:readerBooksList)
            if ((readerBooks.getId().equals(id)) && (readerBooks.getBookReturnDate().equals(null))) {
                return true;
            }
        return false;
    }

    @Override
    public List<ReaderBooks> findReaderBooksByBookId(Long bookId) {
        return readerBooksList.stream()
                .filter(readerBooks -> readerBooks.getBook().equals(bookId))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReaderBooks> findReaderBooksByReaderId(Long readerId) {
        return readerBooksList.stream()
                .filter(readerBooks -> readerBooks.getReader().equals(readerId))
                .collect(Collectors.toList());
    }

    @Override
    public boolean returnBookByReaderBooksId(Long id, Date returnDate) {
        for (ReaderBooks readerBooks : readerBooksList) {
            if (readerBooks.getId().equals(id)) {
                readerBooks.setBookReturnDate(returnDate);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsReaderBooks(Reader reader, Book book, Date bookOutDate) {
        for (ReaderBooks readerBooks : readerBooksList) {
            if ((readerBooks.getReader().equals(reader.getId())) &&
                    (readerBooks.getBook().equals(book.getId())) &&
                    (readerBooks.getBookOutDate().equals(bookOutDate))) {
                return true;
            }
        }
        return false;
    }
}
