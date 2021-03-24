package book_library.core.database.Book;

import book_library.core.domain.Book;
import book_library.core.domain.Reader;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

//@Component
//@Transactional
public class OrmBookRepositoryImp implements BookRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Book book) {
        sessionFactory.getCurrentSession().save(book);
    }

    @Override
    public boolean deleteById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "DELETE Book WHERE id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean hasTheSameBookInDatabase(Book bookToCompare) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT b FROM Book b WHERE title = :title AND author = :author");
        query.setParameter("title", bookToCompare.getTitle());
        query.setParameter("author", bookToCompare.getAuthor());
        return !query.getResultList().isEmpty();
    }

    @Override
    public boolean isSuchIdPresentsInDatabase(Long idToCheck) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT b FROM Book b WHERE id = :id");
        query.setParameter("id", idToCheck);
        return !query.getResultList().isEmpty();
    }

    @Override
    public List<Book> getAllBooks() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT b FROM Book b", Book.class)
                .getResultList();
    }

    @Override
    public List<Book> findByTitle(String title) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT b FROM Book b WHERE title = :title");
        query.setParameter("title", title);
        return query.getResultList();
    }

    @Override
    public List<Book> findByAuthor(String author) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT b FROM Book b WHERE author = :author");
        query.setParameter("author", author);
        return query.getResultList();
    }

    @Override
    public List<Book> findByTitleAndAuthor(String title, String author) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT b FROM Book b WHERE title = :title AND author = :author");
        query.setParameter("title", title);
        query.setParameter("author", author);
        return query.getResultList();
    }

    @Override
    public Book getBookById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT b FROM Book b WHERE id = :id");
        query.setParameter("id", id);
        return (Book) query.getSingleResult();
    }

    @Override
    public Optional<Book> getById(Long id) {
        Book book = sessionFactory.getCurrentSession().get(Book.class, id);
        if (book == null) {
            return Optional.empty();
        } else {
            return Optional.of(book);
        }
    }
}
