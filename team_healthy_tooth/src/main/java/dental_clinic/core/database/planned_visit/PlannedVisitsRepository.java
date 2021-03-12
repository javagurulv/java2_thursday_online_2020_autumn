package dental_clinic.core.database.planned_visit;

import dental_clinic.core.domain.PlannedVisit;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PlannedVisitsRepository {

    List <PlannedVisit> getPlannedVisits();

    Optional<PlannedVisit>getPlannedVisitById(Long id);

    void addPlannedVisit(PlannedVisit plannedVisit);

    void cancelPlannedVisit(Long id);

    void changePlannedVisitTime(Long id, Date visitTime);

    boolean containsId(Long id);

    boolean containsPlannedVisitInTheSameTimeTheSameDoctor(PlannedVisit plannedVisit);

    List <PlannedVisit> searchPlannedVisitsByPersonalCode(String personalCode);

    List<PlannedVisit> searchPlannedVisitsByDate(int dayFrom, int dayTo, int monthFrom, int monthTo);
}
