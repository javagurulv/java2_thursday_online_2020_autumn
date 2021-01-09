package lesson_5.core.database;

import lesson_5.core.domain.Book;

import java.util.List;
import java.util.Optional;

public interface ElectronicLibrary {

    void saveBook(Book book);

    boolean deleteBook(Book book);

    boolean deleteBookById(Long BookId);

    boolean deleteBookByTitle(String bookTitle);

    boolean deleteBookByAuthor(String bookAuthor);

    Optional<Book> findBookById(Long id);

    List<Book> findBookByTitle(String bookTitle);

    List<Book> findBookByAuthor(String bookAuthor);

    List<Book> findByTitleAndAuthor(String bookTitle, String bookAuthor);

    List<Book> getElectronicLibrary();

}
