package book_library.core.database;

import book_library.core.domain.Book;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Component
@Transactional
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
        return false;
    }

    @Override
    public boolean isSuchIdPresentsInDatabase(Long idToCheck) {
        return true;
    }

    @Override
    public List<Book> getAllBooks() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT b FROM Book b", Book.class)
                .getResultList();
    }

    @Override
    public List<Book> findByTitle(String title) {
        return null;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return null;
    }

    @Override
    public List<Book> findByTitleAndAuthor(String title, String author) {
        return null;
    }
}