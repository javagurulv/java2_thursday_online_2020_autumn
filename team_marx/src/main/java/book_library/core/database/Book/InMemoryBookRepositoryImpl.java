package book_library.core.database.Book;

import book_library.core.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@Component
public class InMemoryBookRepositoryImpl implements BookRepository {

    private Long nexId = 1L;
    private List<Book> books = new ArrayList<>();

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void save(Book book) {
        book.setId(nexId);
        nexId++;
        books.add(book);
    }

    @Override
    public boolean deleteById(Long id) {
        boolean isBookDeleted = false;
        Optional<Book> bookToDeleteOPT = books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
        if (bookToDeleteOPT.isPresent()) {
            Book bookToRemove = bookToDeleteOPT.get();
            isBookDeleted = books.remove(bookToRemove);
        }
        return isBookDeleted;

    }

    @Override
    public List<Book> getAllBooks() {
        return books;
    }


    @Override
    public boolean hasTheSameBookInDatabase(Book bookToCompare) {

        return books.contains(bookToCompare);
    }

    @Override
    public boolean isSuchIdPresentsInDatabase(Long idToCheck) {
        return !bookRepository.getAllBooks().stream()
                .filter(book -> book.getId().equals(idToCheck)).findFirst().isEmpty();
    }

    @Override
    public List<Book> findByTitle(String title) {
        return books.stream()
                .filter(book -> book.getTitle().equals(title))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return books.stream()
                .filter(book -> book.getAuthor().equals(author))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findByTitleAndAuthor(String title, String author) {
        return books.stream()
                .filter(book -> book.getAuthor().equals(author))
                .filter(book -> book.getTitle().equals(title))
                .collect(Collectors.toList());
    }

    @Override
    public Book getBookById(Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .collect(Collectors.toList()).get(0);
    }

    @Override
    public Optional<Book> getById(Long id) {
        return Optional.empty();
    }
}
