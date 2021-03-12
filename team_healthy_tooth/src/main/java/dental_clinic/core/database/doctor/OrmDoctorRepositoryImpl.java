package dental_clinic.core.database.doctor;

import dental_clinic.core.domain.Doctor;
import dental_clinic.core.domain.DoctorsWorkGraphic;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class OrmDoctorRepositoryImpl implements DoctorRepository{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Doctor> getDoctorList() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT d FROM Doctor d", Doctor.class)
                .getResultList();
    }

    @Override
    public void addDoctor(Doctor doctor) {
        sessionFactory.getCurrentSession().save(doctor);
        Long id = getDoctorList().get(getDoctorList().size()-1).getId();
        DoctorsWorkGraphic doctorsWorkGraphic = new DoctorsWorkGraphic(getDoctorById(id).get());
        sessionFactory.getCurrentSession().save(doctorsWorkGraphic);
    }

    @Override
    public void deleteDoctorById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("UPDATE Doctor SET isEmployed = false " +
                "WHERE id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public boolean containsDoctor(Doctor doctor) {
        return getDoctorList().stream()
                .filter(doctor1 -> doctor1.equals(doctor))
                .findAny().isPresent();
    }

    @Override
    public boolean containsId(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT d FROM Doctor d WHERE id = :id");
        query.setParameter("id", id);
        return !query.getResultList().isEmpty();
    }

    @Override
    public boolean specificDoctorIsEmployed(Doctor doctor) {
        String sql = "SELECT d FROM Doctor WHERE name = " + doctor.getName() +" AND surname = " + doctor.getSurname();
        return sessionFactory.getCurrentSession()
                .createQuery(sql, Doctor.class)
                .getResultList().get(0).isEmployed();
    }

    @Override
    public Optional<Doctor> getDoctorById(Long id) {
        Doctor doctor = sessionFactory.getCurrentSession().get(Doctor.class, id);
        return Optional.ofNullable(doctor);
    }

    @Override
    public void updateWorkGraphicForSpecificDate(Long id, Integer day, String timeFrom, String timeTo) {
        String sql = "UPDATE DoctorsWorkGraphic SET " + getDayNameStart(day) + " = : timeFrom WHERE doctor_id = :id";
        Query query = sessionFactory.getCurrentSession().createQuery(sql);
        query.setParameter("timeFrom", timeFrom);
        query.setParameter("id", id);
        query.executeUpdate();
        String sqlTo = "UPDATE DoctorsWorkGraphic SET " + getDayNameEnd(day) + " = : timeTo WHERE doctor_id = :id";
        Query queryTo = sessionFactory.getCurrentSession().createQuery(sqlTo);
        queryTo.setParameter("timeTo", timeTo);
        queryTo.setParameter("id", id);
        queryTo.executeUpdate();
    }

    @Override
    public DoctorsWorkGraphic getWorkGraphic(Doctor doctor) {
        String sql = "SELECT w FROM DoctorsWorkGraphic w WHERE doctor_id = " + doctor.getId();
        return sessionFactory.getCurrentSession()
                .createQuery(sql, DoctorsWorkGraphic.class)
                .getSingleResult();
    }

    private String getDayNameStart(int day) {
        switch (day) {
            case 1: return "monday_start";
            case 2: return "tuesday_start";
            case 3: return "wednesday_start";
            case 4: return "thursday_start";
            case 5: return "friday_start";
            case 6: return "saturday_start";
            default: return "sunday_start";
        }
    }

    private String getDayNameEnd(int day) {
        switch (day) {
            case 1: return "monday_end";
            case 2: return "tuesday_end";
            case 3: return "wednesday_end";
            case 4: return "thursday_end";
            case 5: return "friday_end";
            case 6: return "saturday_end";
            default: return "sunday_end";
        }
    }
}
