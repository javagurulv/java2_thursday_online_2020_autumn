package electronic_library.core.database.reader_books;

import electronic_library.core.domain.ReaderBooks;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReaderBooksRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        ReaderBooks readerBooks = new ReaderBooks();
        readerBooks.setId(rs.getLong("id"));

        readerBooks.setBookOutDate(rs.getDate("bookOutDate"));
        readerBooks.setBookReturnDate(rs.getDate("bookReturnDate"));
        return readerBooks;
    }
}
