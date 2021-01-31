package dental_clinic.core.database.patient;

import org.hibernate.SessionFactory;
import dental_clinic.core.domain.Patient;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.domain.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Component
public class OrmPatientRepositoryImpl implements PatientRepository{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<PersonalData> getPatients() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT p FROM PersonalData p", PersonalData.class)
                .getResultList();
    }

    @Override
    public void addPatient(PersonalData personalData) {
        sessionFactory.getCurrentSession().save(personalData);
    }

    @Override
    public void deletePatient(Long id) {
        cleanVisits(id);
        cleanPlannedVisit(id);
        cleanJowl(id);
        Query query = sessionFactory.getCurrentSession().createQuery(
                "delete PersonalData where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Optional<Patient> getSpecificPatientHistory(Long id) {
        return Optional.empty();
    }

    @Override
    public List<PersonalData> findPatientsBySurname(String surname) {
        return null;
    }

    @Override
    public List<PersonalData> findPatientsByPersonalCode(String personalCode) {
        String sql = "SELECT p FROM PersonalData p WHERE personalCode = " + personalCode;
        return sessionFactory.getCurrentSession()
                .createQuery(sql, PersonalData.class)
                .getResultList();
    }

    @Override
    public void addVisit(Long id, Visit newVisit) {

    }

    @Override
    public boolean containsPatientWithSpecificId(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT p FROM PersonalData p WHERE id = :id", PersonalData.class
        );
        query.setParameter("id", id);
        return !query.getResultList().isEmpty();
    }

    @Override
    public Optional<Patient> getPatientCard(Long id) {
        return Optional.empty();
    }

    @Override
    public void changeSurname(Long idToSearch, String updatedSurname) {

    }

    @Override
    public void changePhone(Long idToSearch, String updatedSurname) {

    }

    @Override
    public Optional<Patient> findPatientByIdNumber(Long idToSearch) {
        return Optional.empty();
    }

    @Override
    public boolean containsSpecificPersonalData(PersonalData personalData) {
        return sessionFactory.getCurrentSession().contains(personalData);
    }

    private void cleanVisits(Long patientId) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "delete Visit where patient_id = :patient_id");
        query.setParameter("patient_id", patientId);
        query.executeUpdate();
    }

    private void cleanPlannedVisit(Long patientId){
        Query query = sessionFactory.getCurrentSession().createQuery(
                "delete PersonalData where patient_id = :id");
        query.setParameter("id", patientId);
        query.executeUpdate();
    }

    private void cleanJowl(Long patientId){
        Query query = sessionFactory.getCurrentSession().createQuery(
                "delete Jowl where patient_id = :id");
        query.setParameter("id", patientId);
        query.executeUpdate();
    }

}
