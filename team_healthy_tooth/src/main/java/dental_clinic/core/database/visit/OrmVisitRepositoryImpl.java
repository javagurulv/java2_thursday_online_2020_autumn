package dental_clinic.core.database.visit;

import dental_clinic.core.domain.ToothStatus;
import dental_clinic.core.domain.Visit;
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
public class OrmVisitRepositoryImpl implements VisitRepository{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addVisit(Visit visit) {
        sessionFactory.getCurrentSession().save(visit);

        String sql = "UPDATE JowlEntity SET d"+ visit.getToothNumber()
                + " = " + getToothStatusInt(visit.getToothStatus())
                +" WHERE patient_id = " + visit.getPersonalData().getId();
        Query query = sessionFactory.getCurrentSession().createQuery(sql);
        query.executeUpdate();

    }

    @Override
    public List<Visit> searchVisitByPatientId(Long patientsId) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT v FROM Visit v WHERE patient_id = :patient_id");
        query.setParameter("patient_id", patientsId);
        return query.getResultList();
    }

    @Override
    public List<Visit> searchVisitByDate(int dayFrom, int dayTo, int monthFrom, int monthTo) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dateFromText= getDayFromInStringFormat(dayFrom) + "-" + getMonthFromInStringFormat(monthFrom) + "-2021";
        String dateToText = getDayToInStringFormat(dayTo) + "-" + getMonthToInStringFormat(monthTo) + "-2021";

        try {
            Date dateFrom = simpleDateFormat.parse(dateFromText);
            Date dateTo = simpleDateFormat.parse(dateToText);
            List <Visit> visitList = new ArrayList<>();
            for (Visit visit : searchAllVisits()) {
                if (visit.getDate().after(dateFrom) && visit.getDate().before(dateTo)) {
                    visitList.add(visit);
                }
            }
            return visitList;
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Optional<Visit> getVisitById(Long id) {
        Visit visit = sessionFactory.getCurrentSession().get(Visit.class, id);
        if (visit == null) {
            return Optional.empty();
        } else {
            return Optional.of(visit);
        }

    }

    private List<Visit> searchAllVisits() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT v FROM Visit v", Visit.class)
                .getResultList();
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

    private int getToothStatusInt(ToothStatus toothStatus) {
        switch (toothStatus) {
            case KARIES: return 0;
            case PLOMBA: return 1;
            case SAKNE: return 2;
            case KRONITIS: return 3;
            case KLAMERS: return 4;
            case NAV_ZOBA: return 5;
            case FASETE: return 6;
            case NONEMAMA_PROTEZE: return 7;
            case KRONITIS_AR_FAS: return 8;
            case PLAST_KRONITIS: return 9;
            case TILTINI: return 10;
            default: HEALTHY: return 11;
        }
    }
}
