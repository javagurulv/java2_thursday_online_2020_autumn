package book_library.core.database.Book;

import book_library.core.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    void save(Book book);

    boolean deleteById(Long id);

    boolean hasTheSameBookInDatabase(Book bookToCompare);

    boolean isSuchIdPresentsInDatabase(Long idToCheck);

    List<Book> getAllBooks();

    List<Book> findByTitle(String title);

    List<Book> findByAuthor(String author);

    List<Book> findByTitleAndAuthor(String title, String author);

    Book getBookById(Long id);

    Optional<Book> getById(Long id);


}
