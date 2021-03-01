package book_library.core.database.ReaderBook;

import book_library.core.domain.ReaderBook;
import book_library.core.requests.ReaderBook.ReturnBookRequest;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class ReaderBookRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(ReaderBook readerBook) {
        sessionFactory.getCurrentSession().save(readerBook);
    }

    public ReaderBook getById(Long id) {
        return sessionFactory.getCurrentSession().get(ReaderBook.class, id);
    }

    public boolean isBookInLibrary(Long id) {
        boolean answer = false;
        Query query1 = sessionFactory.getCurrentSession().createQuery(
                "SELECT b FROM ReaderBook b WHERE book_id = :book_id");
        query1.setParameter("book_id", id);
        Query query2 = sessionFactory.getCurrentSession().createQuery(
                "SELECT b FROM ReaderBook b WHERE book_id = :book_id AND book_return_date IS NULL");
        query2.setParameter("book_id", id);
        if (query1.getResultList().isEmpty()) {
            answer = true;
        } else if (query2.getResultList().isEmpty()) {
            answer = true;
        }
        return answer;
    }

    public Long returnBook(ReturnBookRequest request) {
        Long updatedReadeBookId = 0L;
        Query query1 = sessionFactory.getCurrentSession().createQuery(
                "SELECT b FROM ReaderBook b WHERE book_id = :book_id AND reader_id = :reader_id AND book_return_date IS NULL");
        query1.setParameter("book_id", request.getBookId());
        query1.setParameter("reader_id", request.getReaderId());
        if (!query1.getResultList().isEmpty()) {
            Query query2 = sessionFactory.getCurrentSession().createQuery(
                    "UPDATE ReaderBook SET book_return_date = :book_return_date WHERE book_id = :book_id AND reader_id = :reader_id AND book_return_date IS NULL");
            query2.setParameter("book_id", request.getBookId());
            query2.setParameter("reader_id", request.getReaderId());
            query2.setParameter("book_return_date", request.getBookReturnDate());

            Query query3 = sessionFactory.getCurrentSession().createQuery(
                    "SELECT b.id FROM ReaderBook b WHERE book_id = :book_id AND reader_id = :reader_id AND book_return_date IS NULL");
            query3.setParameter("book_id", request.getBookId());
            query3.setParameter("reader_id", request.getReaderId());

            Long idToUpdate = (Long) query3.getSingleResult();
            updatedReadeBookId = (query2.executeUpdate() == 1)
                    ? idToUpdate
                    : 0L;
        }
        return updatedReadeBookId;
    }

    public boolean isRecordWithSuchBookIdInReaderBook(Long id) {
        boolean answer = false;
        Query query1 = sessionFactory.getCurrentSession().createQuery(
                "SELECT b FROM ReaderBook b WHERE book_id = :book_id");
        query1.setParameter("book_id", id);
        if (!query1.getResultList().isEmpty()) {
            answer = true;
        }
        return answer;
    }
}
