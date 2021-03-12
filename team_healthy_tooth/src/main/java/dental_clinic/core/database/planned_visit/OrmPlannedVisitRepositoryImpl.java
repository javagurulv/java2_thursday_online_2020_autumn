package dental_clinic.core.database.planned_visit;

import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.domain.PlannedVisit;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
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
    public Optional<PlannedVisit> getPlannedVisitById(Long id) {
        PlannedVisit plannedVisit = sessionFactory.getCurrentSession().get(PlannedVisit.class, id);
        if (plannedVisit == null) {
            return Optional.empty();
        } else {
            return Optional.of(plannedVisit);
        }
    }

    @Override
    public void addPlannedVisit(PlannedVisit plannedVisit) {
        sessionFactory.getCurrentSession().save(plannedVisit);
    }

    @Override
    public void cancelPlannedVisit(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "DELETE PlannedVisit WHERE id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void changePlannedVisitTime(Long id, Date visitTime) {
        Query query = sessionFactory.getCurrentSession().createQuery("UPDATE PlannedVisit SET dateAndTime = :visitTime " +
                "WHERE patient_id = :id");
        query.setParameter("visitTime", visitTime);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public boolean containsId(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT p FROM PlannedVisit p WHERE id = :id");
        query.setParameter("id", id);
        return !query.getResultList().isEmpty();
    }

    @Override
    public boolean containsPlannedVisitInTheSameTimeTheSameDoctor(PlannedVisit plannedVisit) {
        return getPlannedVisits().stream()
                .anyMatch(plannedVisit1 -> plannedVisit1.equals(plannedVisit));
    }

    @Override
    public List<PlannedVisit> searchPlannedVisitsByPersonalCode(String personalCode) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT p FROM PersonalData p WHERE personalCode = :personalCode");
        query.setParameter("personalCode", personalCode);
        List<PersonalData> list=  query.getResultList();
        Query query1 = sessionFactory.getCurrentSession().createQuery(
                "SELECT p FROM PlannedVisit p WHERE patient_id = :id");
        query1.setParameter("id", list.get(0).getId());
        return query1.getResultList();
    }

    @Override
    public List<PlannedVisit> searchPlannedVisitsByDate(int dayFrom, int dayTo, int monthFrom, int monthTo) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dateFromText= getDayFromInStringFormat(dayFrom) + "-" + getMonthFromInStringFormat(monthFrom) + "-2021";
        String dateToText = getDayToInStringFormat(dayTo) + "-" + getMonthToInStringFormat(monthTo) + "-2021";

        try {
            Date dateFrom = simpleDateFormat.parse(dateFromText);
            Date dateTo = simpleDateFormat.parse(dateToText);
            List <PlannedVisit> visitList = new ArrayList<>();
            for (PlannedVisit plannedVisit : getPlannedVisits()) {
                if (plannedVisit.getVisitTime().after(dateFrom) && plannedVisit.getVisitTime().before(dateTo)) {
                    visitList.add(plannedVisit);
                }
            }
            return visitList;
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }
    }

    private String getDayFromInStringFormat(int dayFrom) {
        String dayFromText;
        if (dayFrom <10) {
            dayFromText = "0" + dayFrom;
        } else {
            dayFromText = "" + dayFrom;
        }
        return dayFromText;
    }

    private String getMonthFromInStringFormat(int monthFrom) {
        String monthFromText;
        if (monthFrom < 10) {
            monthFromText = "0" + monthFrom;
        } else {
            monthFromText = "" + monthFrom;
        }
        return monthFromText;
    }

    private String getDayToInStringFormat(int dayTo) {
        String dayToText;
        if (dayTo < 10) {
            dayToText = "0" + dayTo;
        } else {
            dayToText = "" + dayTo;
        }
        return dayToText;
    }

    private String getMonthToInStringFormat(int monthTo) {
        String monthToText;
        if (monthTo < 10) {
            monthToText = "0" + monthTo;
        } else {
            monthToText = "" + monthTo;
        }
        return monthToText;
    }
}
