package electronic_library.core.database.reader_books;

import electronic_library.core.domain.Book;
import electronic_library.core.domain.Reader;
import electronic_library.core.domain.ReaderBooks;

import java.util.Date;
import java.util.List;

public interface ReaderBooksRepository {

    void addRecordToReaderBooks(ReaderBooks readerBooks);

    boolean deleteRecordFromReaderBooksById(Long id);

    List<ReaderBooks> listAllRecordsInReaderBooks();

    boolean isNotReturnBookById(Long id);

    List<ReaderBooks> findReaderBooksByBookId(Long bookId);

    List<ReaderBooks> findReaderBooksByReaderId(Long readerId);

    boolean returnBookByReaderBooksId(Long id, Date returnDate);

    boolean containsReaderBooks (Reader reader, Book book, Date bookOutDate);

}
