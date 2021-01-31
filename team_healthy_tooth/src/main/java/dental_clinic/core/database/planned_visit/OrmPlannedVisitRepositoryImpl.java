package dental_clinic.core.database.planned_visit;

import org.hibernate.SessionFactory;
import dental_clinic.core.domain.PlannedVisit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrmPlannedVisitRepositoryImpl implements PlannedVisitsRepository{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<PlannedVisit> getPlannedVisits() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT p FROM PlannedVisit p", PlannedVisit.class)
                .getResultList();
    }

    @Override
    public void addPlannedVisit(PlannedVisit plannedVisit) {
        sessionFactory.getCurrentSession().save(plannedVisit);
    }

    @Override
    public void cancelPlannedVisit(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "delete PlannedVisit where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override //TODO
    public void changePlannedVisitTime(Long id, GregorianCalendar visitTime) {

    }

    @Override
    public boolean containsId(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT p FROM PlannedVisit p WHERE id = :id", PlannedVisit.class
        );
        query.setParameter("id", id);
        return !query.getResultList().isEmpty();
    }

    @Override
    public boolean containsPlannedVisitInTheSameTimeTheSameDoctor(PlannedVisit plannedVisit) {
        return sessionFactory.getCurrentSession().contains(plannedVisit);
    }

    @Override
    public List<PlannedVisit> searchPlannedVisitsByPersonalCode(String personalCode) {
        String sql = "SELECT p FROM PlannedVisit p " +
                "WHERE personalCode = " + personalCode;
        return sessionFactory.getCurrentSession()
                .createQuery(sql, PlannedVisit.class)
                .getResultList();
    }

    @Override
    public List<PlannedVisit> searchPlannedVisitsByDate(int dayFrom, int dayTo, int monthFrom, int monthTo) {
        List<PlannedVisit> allVisits = sessionFactory.getCurrentSession()
                .createQuery("SELECT p FROM PlannedVisit p", PlannedVisit.class)
                .getResultList();
        GregorianCalendar dateFrom = new GregorianCalendar(2021, monthFrom-1, dayFrom, 00, 00);
        GregorianCalendar dateTo = new GregorianCalendar(2021, monthTo-1, dayTo, 23, 59);
        return allVisits.stream()
                .filter(plannedVisit -> (plannedVisit.getVisitTime().after(dateFrom)
                        && plannedVisit.getVisitTime().before(dateTo)) ||
                        (plannedVisit.getVisitTime().equals(dateFrom) ||
                                plannedVisit.getVisitTime().equals(dateTo)))
                .collect(Collectors.toList());
    }
}
