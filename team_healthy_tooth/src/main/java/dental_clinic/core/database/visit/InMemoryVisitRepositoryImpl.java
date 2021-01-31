package dental_clinic.core.database.visit;

import dental_clinic.core.domain.Visit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
/*
//@Component
public class InMemoryVisitRepositoryImpl implements VisitRepository {

    private List<Visit> visitList = new ArrayList<>();
    private Long id = 1L;

    @Override
    public void addVisit(Visit visit) {
        visit.setId(id);
        id++;
        visitList.add(visit);
    }

    @Override
    public List<Visit> searchVisitByPatientId(Long patientsId) {
        return visitList.stream()
                .filter(visit -> visit.getPatientsId().equals(patientsId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Visit> searchVisitByDate(int dayFrom, int dayTo, int monthFrom, int monthTo) {
        Date dateFrom = new Date(2021, monthFrom-1, dayFrom, 00, 00);
        Date dateTo = new Date(2021, monthTo-1, dayTo, 23, 59);
        return visitList.stream()
            .filter(visit -> (visit.getDate().after(dateFrom)
                && visit.getDate().before(dateTo)) ||
                (visit.getDate().equals(dateFrom) ||
                visit.getDate().equals(dateTo)))
               .collect(Collectors.toList());
    }
}*/
