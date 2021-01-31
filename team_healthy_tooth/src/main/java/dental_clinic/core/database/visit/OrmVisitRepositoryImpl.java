package dental_clinic.core.database.visit;

import dental_clinic.core.domain.Manipulation;
import org.hibernate.SessionFactory;
import dental_clinic.core.domain.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrmVisitRepositoryImpl implements VisitRepository{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addVisit(Visit visit) {
        sessionFactory.getCurrentSession().save(visit);
    }

    @Override
    public List<Visit> searchVisitByPatientId(Long patientsId) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT v FROM Visit v WHERE patient_id = :patient_id", Visit.class
        );
        query.setParameter("patient_id", patientsId);
        return query.getResultList();
    }

    @Override
    public List<Visit> searchVisitByDate(int dayFrom, int dayTo, int monthFrom, int monthTo) {
        List<Visit> visits = sessionFactory.getCurrentSession()
                .createQuery("SELECT v FROM Visit v", Visit.class)
                .getResultList();
        Date dateFrom = new Date(2021, monthFrom-1, dayFrom, 00, 00);
        Date dateTo = new Date(2021, monthTo-1, dayTo, 23, 59);
        return visits.stream()
                .filter(visit -> (visit.getDate().after(dateFrom)
                        && visit.getDate().before(dateTo)) ||
                        (visit.getDate().equals(dateFrom) ||
                                visit.getDate().equals(dateTo)))
                .collect(Collectors.toList());
    }
}
