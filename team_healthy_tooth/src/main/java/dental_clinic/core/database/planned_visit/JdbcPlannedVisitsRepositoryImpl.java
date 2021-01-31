package dental_clinic.core.database.planned_visit;

import dental_clinic.core.domain.PlannedVisit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.GregorianCalendar;
import java.util.List;

//@Component
public class JdbcPlannedVisitsRepositoryImpl implements PlannedVisitsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<PlannedVisit> getPlannedVisits() {
        return null;
    }

    @Override
    public void addPlannedVisit(PlannedVisit plannedVisit) {

    }

    @Override
    public void cancelPlannedVisit(Long id) {

    }

    @Override
    public void changePlannedVisitTime(Long id, GregorianCalendar visitTime) {

    }

    @Override
    public boolean containsId(Long id) {
        return false;
    }

    @Override
    public boolean containsPlannedVisitInTheSameTimeTheSameDoctor(PlannedVisit plannedVisit) {
        return false;
    }

    @Override
    public List<PlannedVisit> searchPlannedVisitsByPersonalCode(String personalCode) {
        return null;
    }

    @Override
    public List<PlannedVisit> searchPlannedVisitsByDate(int dayFrom, int dayTo, int monthFrom, int monthTo) {
        return null;
    }
}
