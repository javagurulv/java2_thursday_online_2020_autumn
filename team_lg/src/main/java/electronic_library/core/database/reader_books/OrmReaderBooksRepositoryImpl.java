package electronic_library.core.database.reader_books;

import electronic_library.core.domain.Book;
import electronic_library.core.domain.Reader;
import electronic_library.core.domain.ReaderBooks;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Component
@Profile("orm")
@Transactional
public class OrmReaderBooksRepositoryImpl implements ReaderBooksRepository{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addRecordToReaderBooks(ReaderBooks readerBooks) {
        sessionFactory.getCurrentSession().save(readerBooks);
    }

    @Override
    public boolean deleteRecordFromReaderBooksById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "DELETE ReaderBooks WHERE id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public List<ReaderBooks> listAllRecordsInReaderBooks() {
        return sessionFactory.getCurrentSession().getSession().createQuery("FROM ReaderBooks").list();
    }

    @Override
    public boolean isNotReturnBookById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT r FROM ReaderBooks r where id = : id AND bookReturnDate = : bookReturnDate");
        query.setParameter("id", id);
        query.setParameter("bookReturnDate", null);
        return query.getResultList().size() > 0;
    }

    @Override
    public List<ReaderBooks> findReaderBooksByBookId(Long bookId) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT r FROM ReaderBooks r where book_id = : book_id");
        query.setParameter("book_id", bookId);
        return query.getResultList();
    }

    @Override
    public List<ReaderBooks> findReaderBooksByReaderId(Long readerId) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT r FROM ReaderBooks r where reader_id = : reader_id");
        query.setParameter("reader_id", readerId);
        return query.getResultList();
    }

    @Override
    public boolean returnBookByReaderBooksId(Long id, Date returnDate) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("UPDATE Product SET bookReturnDate = :bookReturnDate WHERE id = :id");
        query.setParameter("bookReturnDate", returnDate);
        query.setParameter("id", id);
        return query.executeUpdate() > 0;
    }

    @Override
    public boolean containsReaderBooks(Reader reader, Book book, Date bookOutDate) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT r FROM ReaderBooks r where reader_id =: reader_id AND book_id =: book_id AND bookOutDate =:bookOutDate ");
        query.setParameter("reader_id", reader.getId());
        query.setParameter("book_id", book.getId());
        query.setParameter("book_out_date",bookOutDate);
        return query.getResultList().size() > 0;
    }
}
