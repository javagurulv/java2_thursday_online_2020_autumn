package book_library.core.database.Book;

import book_library.core.database.Mapper.Book.BookRowMapper;
import book_library.core.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

//@Component
public class JdbcBookRepositoryImpl implements BookRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Book book) {
        jdbcTemplate.update(
                "INSERT INTO books (title, author)"
                        + "VALUES (?, ?)",
                book.getTitle(), book.getAuthor()
        );
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM books WHERE id = ?";
        Object[] args = new Object[]{id};
        return jdbcTemplate.update(sql, args) == 1;
    }

    @Override
    public boolean hasTheSameBookInDatabase(Book bookToCompare) {
        String sql = "SELECT * FROM books WHERE title = ? AND author = ?";
        Object[] args = new Object[]{bookToCompare.getTitle(), bookToCompare.getAuthor()};
        return !jdbcTemplate.query(sql, args, new BookRowMapper()).isEmpty();
    }

    @Override
    public boolean isSuchIdPresentsInDatabase(Long idToCheck) {
        String sql = "SELECT * FROM books WHERE id = ?";
        Object[] args = new Object[]{idToCheck};
        return !jdbcTemplate.query(sql, args, new BookRowMapper()).isEmpty();
    }

    @Override
    public List<Book> getAllBooks() {
        String sql = "SELECT * FROM books";
        return jdbcTemplate.query(sql, new BookRowMapper());
    }

    @Override
    public List<Book> findByTitle(String title) {
        String sql = "SELECT * FROM books WHERE title = ?";
        Object[] args = new Object[]{title};
        return jdbcTemplate.query(sql, args, new BookRowMapper());
    }

    @Override
    public List<Book> findByAuthor(String author) {
        String sql = "SELECT * FROM books WHERE author = ? ";
        Object[] args = new Object[]{author};
        return jdbcTemplate.query(sql, args, new BookRowMapper());
    }

    @Override
    public List<Book> findByTitleAndAuthor(String title, String author) {
        String sql = "SELECT * FROM books WHERE title = ? AND author = ?";
        Object[] args = new Object[]{title, author};
        return jdbcTemplate.query(sql, args, new BookRowMapper());
    }

    @Override
    public Book getBookById(Long id) {
        String sql = "SELECT * FROM books WHERE id = ?";
        Object[] args = new Object[]{id};
        return (Book) jdbcTemplate.query(sql, args, new BookRowMapper());
    }

    @Override
    public Optional<Book> getById(Long id) {
        return Optional.empty();
    }
}
