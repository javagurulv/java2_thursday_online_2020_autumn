package dental_clinic.core.database.visit;

import dental_clinic.core.domain.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class JdbcVisitRepositoryImpl implements VisitRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void addVisit(Visit visit) {

    }

    @Override
    public List<Visit> searchVisitByPatientId(Long patientsId) {
        return null;
    }

    @Override
    public List<Visit> searchVisitByDate(int dayFrom, int dayTo, int monthFrom, int monthTo) {
        return null;
    }
}
