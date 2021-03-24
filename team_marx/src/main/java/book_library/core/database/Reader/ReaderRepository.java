package book_library.core.database.Reader;

import book_library.core.domain.Reader;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

//@Component
//@Transactional
public class ReaderRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(Reader reader) {
        sessionFactory.getCurrentSession().save(reader);
    }

    public boolean deleteById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "DELETE Reader WHERE id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result == 1;
    }

    public Reader findById(Long id) {
        return sessionFactory.getCurrentSession().get(Reader.class, id);
    }

    public boolean hasTheSameReaderInDatabase(Reader reader) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT b FROM Reader b WHERE first_name = :first_name AND last_name = :last_name AND personal_code = :personal_code");
        query.setParameter("first_name", reader.getFirstName());
        query.setParameter("last_name", reader.getLastName());
        query.setParameter("personal_code", reader.getPersonalCode());
        return !query.getResultList().isEmpty();
    }

    public boolean isSuchIdPresentsInDatabase(Long idToCheck) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT r FROM Reader r WHERE id = :id");
        query.setParameter("id", idToCheck);
        return !query.getResultList().isEmpty();
    }

    public List<Reader> getAllReaders() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT r FROM Reader r", Reader.class)
                .getResultList();
    }

    public List<Reader> findByFirstName(String firstName) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT r FROM Reader r WHERE first_name = :first_name");
        query.setParameter("first_name", firstName);
        return query.getResultList();
    }

    public List<Reader> findByLastName(String lastName) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT r FROM Reader r WHERE last_name = :last_name");
        query.setParameter("last_name", lastName);
        return query.getResultList();
    }

    public List<Reader> findByPersonalCode(Long personalCode) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT r FROM Reader r WHERE personal_code = :personal_code");
        query.setParameter("personal_code", personalCode);
        return query.getResultList();
    }

    public List<Reader> findByFirstNameAndLastName(String firstName, String lastName) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT r FROM Reader r WHERE first_name = :first_name AND last_name = :last_name");
        query.setParameter("first_name", firstName);
        query.setParameter("last_name", lastName);
        return query.getResultList();
    }

    public List<Reader> findByFirstNameAndPersonalCode(String firstName, Long personalCode) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT r FROM Reader r WHERE first_name = :first_name AND personal_code = :personal_code");
        query.setParameter("first_name", firstName);
        query.setParameter("personal_code", personalCode);
        return query.getResultList();
    }

    public List<Reader> findByLastNameAndPersonalCode(String lastName, Long personalCode) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT r FROM Reader r WHERE last_name = :last_name AND personal_code = :personal_code");
        query.setParameter("last_name", lastName);
        query.setParameter("personal_code", personalCode);
        return query.getResultList();
    }

    public List<Reader> findByFirstNameAndLastNameAndPersonalCode(String firstName, String lastName, Long personalCode) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT r FROM Reader r WHERE first_name = :first_name AND last_name = :last_name AND personal_code = :personal_code");
        query.setParameter("first_name", firstName);
        query.setParameter("last_name", lastName);
        query.setParameter("personal_code", personalCode);
        return query.getResultList();
    }

    public Reader getReaderById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT r FROM Reader r WHERE id = :id");
        query.setParameter("id", id);
        return (Reader) query.getSingleResult();
    }
}
