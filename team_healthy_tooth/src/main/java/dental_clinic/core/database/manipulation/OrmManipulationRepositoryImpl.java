package dental_clinic.core.database.manipulation;

import dental_clinic.core.domain.Manipulation;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class OrmManipulationRepositoryImpl implements ManipulationRepository{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addManipulation(Manipulation manipulation) {
        sessionFactory.getCurrentSession().save(manipulation);
    }

    @Override
    public List<Manipulation> getManipulationsList() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT m FROM Manipulation m", Manipulation.class)
                .getResultList();
    }

    @Override
    public Optional<Manipulation> getManipulationById(Long id) {
        Manipulation manipulation = sessionFactory.getCurrentSession().get(Manipulation.class, id);
        if (manipulation == null) {
            return Optional.empty();
        } else {
            return Optional.of(manipulation);
        }
    }

    @Override
    public void deactivateManipulation(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("UPDATE Manipulation SET isActive = false " +
                "WHERE id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public boolean containsTheSameManipulation(String manipulationType, Integer price) {
        List<Manipulation>manipulations = getManipulationsList();
        return manipulations.stream()
                .anyMatch(manipulation -> manipulation.getManipulationType().equals(manipulationType) &&
                        manipulation.getPrice().equals(price));
    }

    @Override
    public boolean manipulationIsActive(Long id) {
        String sql = "SELECT m FROM Manipulation m WHERE id = " + id;
        return sessionFactory.getCurrentSession()
                .createQuery(sql, Manipulation.class)
                .getResultList().get(0).isActive();
    }

    @Override
    public boolean containsId(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT m FROM Manipulation m WHERE id = :id");
        query.setParameter("id", id);
        return !query.getResultList().isEmpty();
    }
}
