package dental_clinic.core.database.doctor;

import org.hibernate.SessionFactory;
import dental_clinic.core.domain.Doctor;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Component
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
    }

    @Override
    public boolean deleteDoctorById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "delete Doctor where id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean containsDoctor(Doctor doctor) {
        return sessionFactory.getCurrentSession().contains(doctor);
    }

    @Override
    public boolean containsId(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT d FROM Doctor d WHERE id = :id", Doctor.class
        );
        query.setParameter("id", id);
        return !query.getResultList().isEmpty();
    }

    @Override
    public boolean specificDoctorIsEmployed(Doctor doctor) {
        String sql = "SELECT d FROM Doctor d WHERE name = " + doctor.getName() +
                " AND surname = " + doctor.getSurname();
        return sessionFactory.getCurrentSession()
                .createQuery(sql, Doctor.class)
                .getResultList().get(0).isEmployed();
    }

    @Override
    public Optional<Doctor> getDoctorById(Long id) {
        Doctor doctor = (Doctor) sessionFactory.getCurrentSession().createCriteria(Doctor.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
        return Optional.ofNullable(doctor);
    }

    @Override //TODO
    public void updateWorkGraphicForSpecificDate(Long id, Integer day, String timeFrom, String timeTo) {

    }
}
