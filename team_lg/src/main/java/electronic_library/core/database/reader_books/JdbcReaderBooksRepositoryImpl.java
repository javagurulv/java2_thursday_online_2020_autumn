package electronic_library.core.database.reader_books;

import electronic_library.core.database.reader.ReaderRowMapper;
import electronic_library.core.domain.Book;
import electronic_library.core.domain.Reader;
import electronic_library.core.domain.ReaderBooks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.Date;
import java.util.List;

@Component
@Profile("jdbc")
public class JdbcReaderBooksRepositoryImpl implements ReaderBooksRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addRecordToReaderBooks(ReaderBooks readerBooks) {
        jdbcTemplate.update("INSERT INTO reader_books (reader_id, book_id, book_out_date) VALUES (?, ?, ?)",
                readerBooks.getReader(), readerBooks.getBook(), readerBooks.getBookOutDate());
    }

    @Override
    public boolean deleteRecordFromReaderBooksById(Long id) {
        return jdbcTemplate.update("DELETE FROM reader_books WHERE id = ?", new Object[]{id}) == 1;
    }

    @Override
    public List<ReaderBooks> listAllRecordsInReaderBooks() {
        return jdbcTemplate.query("SELECT * FROM reader_books " +
                "JOIN books ON books.id = reader_books.book_id " +
                "JOIN readers ON readers.id = reader_books.reader_id", new ReaderBooksRowMapper());
    }

    @Override
    public boolean isNotReturnBookById(Long id) {
        return jdbcTemplate.query("SELECT * FROM reader_books WHERE id = ? AND book_return_date = ?",
                new Object[]{id, null}, new ReaderBooksRowMapper()).size() == 1;
    }

    @Override
    public List<ReaderBooks> findReaderBooksByBookId(Long bookId) {
        return jdbcTemplate.query("SELECT * FROM reader_books WHERE book_id LIKE ?",
                new Object[]{bookId}, new ReaderRowMapper());
    }

    @Override
    public List<ReaderBooks> findReaderBooksByReaderId(Long readerId) {
        return jdbcTemplate.query("SELECT * FROM reader_books WHERE reader_id LIKE ?",
                new Object[]{readerId}, new ReaderRowMapper());
    }

    @Override
    public boolean returnBookByReaderBooksId(Long id, Date returnDate) {
        String query = "UPDATE reader_books SET book_return_date = ? WHERE id = ?";
        int answer = jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, (java.sql.Date) returnDate);
            preparedStatement.setLong(2, id);
            return preparedStatement;
        });
        return answer > 0;
    }

    @Override
    public boolean containsReaderBooks(Reader reader, Book book, Date bookOutDate) {
        return jdbcTemplate.query("SELECT * FROM reader_books WHERE reader_id LIKE ? AND book_id LIKE ? AND book_return_date LIKE ?",
                new Object[]{reader.getId(), book.getId(), bookOutDate}, new ReaderRowMapper()).size() > 0;
    }
}
